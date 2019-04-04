package com.yuan.learnproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.articles.CollectionArticleBean;
import com.yuan.learnproject.bean.articles.CollectionArticleDataBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.contract.CollectContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerCollectComponent;
import com.yuan.learnproject.di.module.CollectModule;
import com.yuan.learnproject.presenter.CollectPresenter;
import com.yuan.learnproject.ui.activity.DetailActivity;
import com.yuan.learnproject.ui.activity.MainActivity;
import com.yuan.learnproject.ui.adapter.ArticleQuickAdapter;
import com.yuan.learnproject.ui.adapter.CollectionQuickAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/4/3
 **/
public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectContract.CollectView {
    private final static String TAG = CollectFragment.class.getCanonicalName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;
    
    private CollectionQuickAdapter mAdapter;
    private boolean isRefresh = false;
    private int currentPageIndex = 0;

    @Override
    protected int getLayout() {
        return R.layout.main_article;
    }

    @Override
    protected void init() {
        initRefreshLayout();
        initRecycleView();
        mPresenter.getCollectArticles(0);
    }

    private void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new CollectionQuickAdapter(getActivity());
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setPreLoadNumber(5);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CollectionArticleDataBean data = (CollectionArticleDataBean) adapter.getData().get(position);
                startDetailActivity(data.getLink(), data.getId(), data.getTitle());
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.collection) {
                    if (MainActivity.hasLogin) {
                        CollectionArticleDataBean data = (CollectionArticleDataBean) adapter.getData().get(position);
                            mPresenter.deleteCollectArticle(position, data.getId(), data.getOriginId());
                    } else {
                        Toast.makeText(getActivity(), "please login first", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e(TAG, "onLoadMoreRequested: " + currentPageIndex);
                currentPageIndex++;
                isRefresh = false;
                mPresenter.getCollectArticles(currentPageIndex);
            }
        }, mRecyclerView);
    }

    private void startDetailActivity(String url, int id, String title) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(GlobalConstant.CONTENT_URL_KEY, url);
        bundle.putInt(GlobalConstant.CONTENT_ID_KEY, id);
        bundle.putString(GlobalConstant.CONTENT_TITLE_KEY, title);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initRefreshLayout() {
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                isRefresh = true;
                mPresenter.getCollectArticles(0);
            }
        });
        mSmartRefresh.setEnableLoadMore(false);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCollectComponent.builder()
                .appComponent(appComponent)
                .collectModule(new CollectModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showCollect(CollectionArticleBean collectionArticleBean) {
        List<CollectionArticleDataBean> data = collectionArticleBean.getDatas();
        if (data == null) {
            if (mSmartRefresh.getState() == RefreshState.Loading) {
                mSmartRefresh.finishLoadMore(false);
            }
            if (mSmartRefresh.getState() == RefreshState.Refreshing) {
                mSmartRefresh.finishRefresh(false);
            }
            return;
        }
        if (mSmartRefresh.getState() == RefreshState.Refreshing) {
            mSmartRefresh.finishRefresh(true);
        }
        if (isRefresh) {
            isRefresh = false;
            mAdapter.replaceData(data);
        } else {
            mAdapter.addData(data);
        }
        int size = data.size();
        if (size < collectionArticleBean.getSize()) {
            mAdapter.loadMoreEnd();
        }else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onError(String msg) {
        if (mSmartRefresh.getState() == RefreshState.Refreshing) {
            mSmartRefresh.finishRefresh(false);
        }
        mAdapter.loadMoreFail();
    }

    @Override
    public void onDeleteCollectionSuccess(int position) {
        Toast.makeText(getActivity(), "cancel collect success!", Toast.LENGTH_SHORT).show();
        mAdapter.remove(position);
    }
}
