package com.yuan.learnproject.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.bean.FragmentInfoBean;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.contract.MainContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerMainActivityComponent;
import com.yuan.learnproject.di.module.MainActivityModule;
import com.yuan.learnproject.eventbus.EventBusHelper;
import com.yuan.learnproject.eventbus.LoginEvent;
import com.yuan.learnproject.presenter.MainPresenter;
import com.yuan.learnproject.ui.adapter.ViewPagerAdapter;
import com.yuan.learnproject.ui.fragment.KnowledgeTreeFragment;
import com.yuan.learnproject.ui.fragment.MainArticleFragment;
import com.yuan.learnproject.ui.widget.CustomScrollViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.view_pager)
    CustomScrollViewPager mViewPager;
    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mPresenter.requestPermission();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    private View headView;
    private ImageView mUserHeadView;
    private TextView mTextUserName;
    private LinearLayout mUserLayout;
    public static boolean hasLogin = false;

    private void initDrawerLayout() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);
        mNavigationView.getMenu().findItem(R.id.logout).setVisible(hasLogin);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        headView = mNavigationView.getHeaderView(0);
        mUserHeadView = headView.findViewById(R.id.img_avatar);
        mTextUserName = headView.findViewById(R.id.username);
        mUserLayout = headView.findViewById(R.id.user);
        mUserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login
                if (!hasLogin) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        });
    }

    private void initToolBar() {
        mToolBar.inflateMenu(R.menu.tool_menu);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //start search activity
                return item.getItemId() == R.id.action_search;
            }
        });
    }

    private void initTabLayout() {
        //ViewPager and tab layout
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragments());
        mViewPager.setScrollable(false);
        mViewPager.setOffscreenPageLimit(adapter.getCount());
        mViewPager.setAdapter(adapter);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home_page:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_knowledge_system:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_navigation_page:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.action_project_page:
                        mViewPager.setCurrentItem(3);
                        break;
                    default:
                        mViewPager.setCurrentItem(0);
                        break;
                }
                return true;
            }
        });
    }

    private List<FragmentInfoBean> initFragments() {
        List<FragmentInfoBean> mFragments = new ArrayList<>(4);

        mFragments.add(new FragmentInfoBean("推荐", MainArticleFragment.class));
        mFragments.add(new FragmentInfoBean("项目", KnowledgeTreeFragment.class));


        mFragments.add(new FragmentInfoBean("公众号", Fragment.class));
        mFragments.add(new FragmentInfoBean("知识体系", Fragment.class));

        return mFragments;
    }

    @Override
    public void requestPermissionSuccess() {
        EventBusHelper.getInstance().register(MainActivity.this);
        initToolBar();
        initTabLayout();
        initDrawerLayout();
        String userInfo = SPUtils.getInstance(GlobalConstant.COMMON_SHARED_PREFERENCE)
                .getString(GlobalConstant.USER_INFO, "");
        LoginResponseBean bean = mGson.fromJson(userInfo, LoginResponseBean.class);
        if (bean != null) {
            hasLogin = true;
            if (mTextUserName != null) {
                mTextUserName.setText(bean.getUsername());
            }
        }
    }

    @Override
    public void requestPermissionFail() {

    }

    @Override
    public void onError(String msg) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateResolutionPref(LoginEvent loginEvent){
        if (loginEvent.isLogin()) {
            hasLogin = true;
            String userInfo = SPUtils.getInstance(GlobalConstant.COMMON_SHARED_PREFERENCE)
                    .getString(GlobalConstant.USER_INFO, "");
            LoginResponseBean bean = mGson.fromJson(userInfo, LoginResponseBean.class);
            if (mTextUserName != null && bean != null) {
                mTextUserName.setText(bean.getUsername());
            }
            Log.e("derek", "updateResolutionPref: " );
        }
    }

    @Override
    protected void onDestroy() {
        EventBusHelper.getInstance().unregister(MainActivity.this);
        super.onDestroy();
    }
}
