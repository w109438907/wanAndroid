package com.yuan.learnproject.ui.fragment;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.contract.ProjectContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerProjectComponent;
import com.yuan.learnproject.di.module.ProjectModule;
import com.yuan.learnproject.presenter.ProjectPresenter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.ProjectView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;

    private int cid = 0;
    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectComponent.builder()
                .appComponent(appComponent)
                .projectModule(new ProjectModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.main_article;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    protected void init() {

    }

    @Override
    public void collectArticleSuccess() {

    }

    @Override
    public void cancelCollectArticleSuccess() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showProject(MainArticleBean mainArticleBean) {

    }
}
