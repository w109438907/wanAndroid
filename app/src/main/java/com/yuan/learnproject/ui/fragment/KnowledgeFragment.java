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
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.contract.KnowledgeContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerKnowledgeComponent;
import com.yuan.learnproject.di.module.KnowledgeModule;
import com.yuan.learnproject.presenter.KnowledgePresenter;
import com.yuan.learnproject.ui.activity.DetailActivity;
import com.yuan.learnproject.ui.activity.MainActivity;
import com.yuan.learnproject.ui.adapter.KnowledgeQuickAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/16
 **/
public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.KnowledgeView {
    private final static String TAG = MainArticleFragment.class.getCanonicalName();

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;

    private int cid = 0;
    private int currentPageIndex = 0;
    private KnowledgeQuickAdapter mAdapter;
    private MainArticleDataBean cacheData;
    private boolean isRefresh = false;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerKnowledgeComponent.builder()
                .appComponent(appComponent)
                .knowledgeModule(new KnowledgeModule(this))
                .build()
                .inject(this);
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    protected int getLayout() {
        return R.layout.main_article;
    }

    @Override
    protected void init() {
        initRefreshLayout();
        initRecycleView();
        mPresenter.getKnowledgeList(currentPageIndex, cid);
    }

    private void initRecycleView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new KnowledgeQuickAdapter(getActivity());
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setPreLoadNumber(5);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainArticleDataBean data = (MainArticleDataBean) adapter.getData().get(position);
                startDetailActivity(data.getLink(), data.getId(), data.getTitle());
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.collection) {
                    if (MainActivity.hasLogin) {
                        MainArticleDataBean data = (MainArticleDataBean) adapter.getData().get(position);
                        if (data.isCollect()){
                            mPresenter.cancelCollect(data.getId());
                        }else {
                            mPresenter.addCollect(data.getId());
                        }
                        data.setCollect(!data.isCollect());
                        mAdapter.setData(position, data);
                    }else {
                        Toast.makeText(getActivity(), "please login first", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e(TAG, "onLoadMoreRequested: "  + currentPageIndex);
                currentPageIndex++;
                isRefresh = false;
                mPresenter.getKnowledgeList(currentPageIndex, cid);
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
                mPresenter.getKnowledgeList(0, cid);
            }
        });
        mSmartRefresh.setEnableLoadMore(false);
    }

    @Override
    public void onError(String msg) {
        if (mSmartRefresh.getState() == RefreshState.Refreshing) {
            mSmartRefresh.finishRefresh(false);
        }
        mAdapter.loadMoreFail();
    }

    @Override
    public void showResult(MainArticleBean mainArticleBean) {
        List<MainArticleDataBean> data = mainArticleBean.getDatas();
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
        Log.e(TAG, isRefresh + ", showResult: " + mainArticleBean.toString());
        if (isRefresh) {
            isRefresh = false;
            mAdapter.replaceData(data);
        } else {
            mAdapter.addData(data);
        }
        int size = data.size();
        if (size < mainArticleBean.getSize()) {
            mAdapter.loadMoreEnd();
        }else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void collectArticleSuccess() {
        Toast.makeText(getActivity(), "collect success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelCollectArticleSuccess() {
        Toast.makeText(getActivity(), "cancel collect success!", Toast.LENGTH_SHORT).show();
    }
}
