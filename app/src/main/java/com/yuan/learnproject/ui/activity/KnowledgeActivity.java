package com.yuan.learnproject.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.constant.GlobalConstant;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/14
 **/
public class KnowledgeActivity extends BaseActivity {
    private final static String TAG = KnowledgeActivity.class.getSimpleName();

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
            }
        }
    }
}
