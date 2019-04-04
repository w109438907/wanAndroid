package com.yuan.learnproject.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.bean.FragmentInfoBean;
import com.yuan.learnproject.bean.knowledge.TreeChildrenBean;
import com.yuan.learnproject.bean.knowledge.TreesBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.ui.adapter.ViewPagerAdapter;
import com.yuan.learnproject.ui.fragment.KnowledgeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/14
 **/
public class KnowledgeActivity extends BaseActivity {
    private final static String TAG = KnowledgeActivity.class.getSimpleName();
    private TreesBean mTreesBean;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_knowledge;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String data = intent.getExtras().getString(GlobalConstant.CONTENT_DATA_KEY, null);
            if (data != null) {
                Log.e(TAG, "init: " + data);
                mTreesBean = mGson.fromJson(data, TreesBean.class);
                initTabLayout();
            }
        }
    }

    private void initTabLayout() {
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragments());
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private List<FragmentInfoBean> initFragments() {
        List<TreeChildrenBean> data = mTreesBean.getChildren();
        if (data != null) {
            List<FragmentInfoBean> fragmentInfoBeans = new ArrayList<>(data.size());
            for (TreeChildrenBean childrenBean : data) {
                FragmentInfoBean info = new FragmentInfoBean(childrenBean.getName(), KnowledgeFragment.class);
                info.setId(childrenBean.getId());
                fragmentInfoBeans.add(info);
            }
            return fragmentInfoBeans;
        }
        return null;
    }
}
