package com.yuan.learnproject.ui.fragment;

import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.FragmentInfoBean;
import com.yuan.learnproject.bean.project.ProjectTreeBean;
import com.yuan.learnproject.contract.ProjectTreeContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerProjectTreeComponent;
import com.yuan.learnproject.di.module.ProjectTreeModule;
import com.yuan.learnproject.presenter.ProjectTreePresenter;
import com.yuan.learnproject.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/22
 **/
public class ProjectTreeFragment extends BaseFragment<ProjectTreePresenter> implements ProjectTreeContract.ProjectTreeView {
    private final static String TAG = ProjectTreeFragment.class.getCanonicalName();

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerProjectTreeComponent.builder()
            .appComponent(appComponent)
            .projectTreeModule(new ProjectTreeModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.project_tree_fragment;
    }

    @Override
    protected void init() {
        mPresenter.getProjectTree();
    }

    private void initTabLayout(List<ProjectTreeBean> projectTreeBeanList) {
        PagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), initFragments(projectTreeBeanList));
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private List<FragmentInfoBean> initFragments(List<ProjectTreeBean> data) {
        if (data != null) {
            List<FragmentInfoBean> fragmentInfoBeans = new ArrayList<>(data.size());
            for (ProjectTreeBean childrenBean : data) {
                FragmentInfoBean info = new FragmentInfoBean(childrenBean.getName(), ProjectFragment.class);
                info.setId(childrenBean.getId());
                fragmentInfoBeans.add(info);
            }
            return fragmentInfoBeans;
        }
        return null;
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProjectTree(List<ProjectTreeBean> projectTreeBeanList) {
        initTabLayout(projectTreeBeanList);
    }
}
