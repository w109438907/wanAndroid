package com.yuan.learnproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.bean.articles.MainBannerBean;
import com.yuan.learnproject.contract.MainArticleContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerMainArticleComponent;
import com.yuan.learnproject.di.module.MainArticleModule;
import com.yuan.learnproject.presenter.MainArticlePresenter;
import com.yuan.learnproject.ui.activity.DetailActivity;
import com.yuan.learnproject.ui.activity.MainActivity;
import com.yuan.learnproject.ui.adapter.ArticleQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class MainArticleFragment extends BaseFragment<MainArticlePresenter> implements MainArticleContract.MainArticleView {
    private final static String TAG = MainArticleFragment.class.getCanonicalName();
    private int currentPageIndex = 0;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;

    private Banner mBanner;
    private ArticleQuickAdapter mAdapter;
    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainArticleComponent
                .builder()
                .appComponent(appComponent)
                .mainArticleModule(new MainArticleModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.main_article;
    }

    @Override
    protected void init() {
        initRefreshLayout();
        initRecycleView();
        mPresenter.requestBanner();
        mPresenter.requestHomeData();
    }

    private void initRecycleView() {
        View mBannerView = getLayoutInflater().inflate(R.layout.main_article_banner, null);
        mBanner = mBannerView.findViewById(R.id.banner);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new ArticleQuickAdapter(getActivity());
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setPreLoadNumber(5);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MainArticleDataBean data = (MainArticleDataBean) adapter.getData().get(position);
                startDetailActivity(data.getLink());
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
                mPresenter.requestData(currentPageIndex);
            }
        }, mRecyclerView);
        mAdapter.addHeaderView(mBannerView);
    }

    private void initRefreshLayout() {
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.requestBanner();
                mPresenter.requestHomeData();
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

    private void startDetailActivity(String url) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private MainArticleDataBean cacheData;
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
        boolean dataUpdated = false;
        if (mainArticleBean.getCurPage() - 1 == 0) {
            if (cacheData != null) {
                dataUpdated = !cacheData.equals(data.get(0));
            }
            cacheData = data.get(0);
        }
        if (mSmartRefresh.getState() == RefreshState.Refreshing) {
            mSmartRefresh.finishRefresh(true);
        }
        Log.e(TAG, dataUpdated + ", showResult: " + mainArticleBean.toString());
        if (dataUpdated) {
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
    public void showBanner(List<MainBannerBean> mainBannerBean) {
        if (mBanner == null) {
            return;
        }
        int size = mainBannerBean.size();
        List<String> image = new ArrayList<>(size);
        List<String> title = new ArrayList<>(size);
        for (MainBannerBean bean : mainBannerBean) {
            image.add(bean.getImagePath());
            title.add(bean.getTitle());
        }
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBanner.setOnBannerListener(position -> startDetailActivity(mainBannerBean.get(position).getUrl()))
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setDelayTime(5000)
                .setImages(image)
                .setBannerTitles(title)
                .start();
    }

    @Override
    public void getBannerError() {
        if (mSmartRefresh.getState() == RefreshState.Refreshing) {
            mSmartRefresh.finishRefresh(false);
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
