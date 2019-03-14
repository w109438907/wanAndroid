package com.yuan.learnproject.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.knowledge.TreesBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.contract.KnowledgeTreeContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerKnowledgeTreeComponent;
import com.yuan.learnproject.di.module.KnowledgeTreeModule;
import com.yuan.learnproject.presenter.KnowledgeTreePresenter;
import com.yuan.learnproject.ui.activity.KnowledgeActivity;
import com.yuan.learnproject.ui.adapter.KnowledgeTreeQuickAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/8
 **/
public class KnowledgeTreeFragment extends BaseFragment<KnowledgeTreePresenter> implements KnowledgeTreeContract.KnowledgeView {
    private final static String TAG = KnowledgeTreeFragment.class.getCanonicalName();
    private KnowledgeTreeQuickAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout mSmartRefresh;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerKnowledgeTreeComponent.builder()
                .appComponent(appComponent)
                .knowledgeTreeModule(new KnowledgeTreeModule(this))
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
        mPresenter.getKnowledgeData();
    }

    @Override
    public void onError(String msg) {

    }

    private void initRefreshLayout() {
        mSmartRefresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        mSmartRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));
        mSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getKnowledgeData();
            }
        });
        mSmartRefresh.setEnableLoadMore(false);
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
        mAdapter = new KnowledgeTreeQuickAdapter(getActivity());
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TreesBean treesBean = mAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), KnowledgeActivity.class);
                intent.putExtra(GlobalConstant.CONTENT_DATA_KEY, mGson.toJson(treesBean, TreesBean.class));
                startActivity(intent);
            }
        });
    }

    @Override
    public void showTreeData(List<TreesBean> treesBeans) {
        mSmartRefresh.finishRefresh();
        mAdapter.replaceData(treesBeans);
    }
}
