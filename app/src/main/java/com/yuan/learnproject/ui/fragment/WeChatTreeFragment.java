package com.yuan.learnproject.ui.fragment;

import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.FragmentInfoBean;
import com.yuan.learnproject.bean.wechat.WeChatChapterBean;
import com.yuan.learnproject.contract.WeChatTreeContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerWeChatTreeComponent;
import com.yuan.learnproject.di.module.WeChatTreeModule;
import com.yuan.learnproject.presenter.WeChatTreePresenter;
import com.yuan.learnproject.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public class WeChatTreeFragment extends BaseFragment<WeChatTreePresenter> implements WeChatTreeContract.WeChatTreeView {
    private final static String TAG = WeChatTreeFragment.class.getCanonicalName();

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerWeChatTreeComponent.builder()
                .appComponent(appComponent)
                .weChatTreeModule(new WeChatTreeModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.project_tree_fragment;
    }

    @Override
    protected void init() {
        mPresenter.getWeChatChapters();
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(List<WeChatChapterBean> weChatChapterBeans) {
        initTabLayout(weChatChapterBeans);
    }

    private void initTabLayout(List<WeChatChapterBean> weChatChapterBeans) {
        PagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), initFragments(weChatChapterBeans));
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private List<FragmentInfoBean> initFragments(List<WeChatChapterBean> data) {
        if (data != null) {
            List<FragmentInfoBean> fragmentInfoBeans = new ArrayList<>(data.size());
            for (WeChatChapterBean childrenBean : data) {
                FragmentInfoBean info = new FragmentInfoBean(childrenBean.getName(), WeChatFragment.class);
                info.setId(childrenBean.getId());
                fragmentInfoBeans.add(info);
            }
            return fragmentInfoBeans;
        }
        return null;
    }
}
