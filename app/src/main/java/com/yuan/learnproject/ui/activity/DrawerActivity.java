package com.yuan.learnproject.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.bean.FragmentInfoBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.ui.adapter.ViewPagerAdapter;
import com.yuan.learnproject.ui.fragment.CollectFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/4/3
 **/
public class DrawerActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_drawer;
    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        int id = intent.getExtras().getInt(GlobalConstant.KEY_TYPE_ID, -1);
        switch (id) {
            case R.id.collect:
                mTitle.setText("Collection");
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new CollectFragment()).commit();
                break;
            default:
                break;
        }
    }
}
