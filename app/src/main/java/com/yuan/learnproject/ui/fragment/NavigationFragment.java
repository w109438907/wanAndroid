package com.yuan.learnproject.ui.fragment;

import android.widget.Toast;

import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.NavigationBean;
import com.yuan.learnproject.contract.NavigationContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerNavigationComponent;
import com.yuan.learnproject.di.module.NavigationModule;
import com.yuan.learnproject.presenter.NavigationPresenter;
import com.yuan.learnproject.ui.adapter.NavigationQuickAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import q.rorbin.verticaltablayout.TabAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author yuan
 * @date 2019/3/17
 **/
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.NavigationView {
    private final static String TAG = NavigationFragment.class.getCanonicalName();
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.tabLayout)
    VerticalTabLayout mTabLayout;

    private NavigationQuickAdapter mNavigationAdapter;
    private LinearLayoutManager mLayoutManager;
    private boolean needScroll = false;
    private boolean isTabClicked = false;
    private int index;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerNavigationComponent.builder()
                .appComponent(appComponent)
                .navigationModule(new NavigationModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.navigation_fragment;
    }

    @Override
    protected void init() {
        initRecycleView();
        mPresenter.getNavigation();
    }

    private void initRecycleView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNavigationAdapter = new NavigationQuickAdapter(getContext());
        mNavigationAdapter.bindToRecyclerView(mRecyclerView);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNavigation(List<NavigationBean> navigationBean) {
//        Log.e(TAG, "showNavigation: " + navigationBean.toString());
        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationBean == null ? 0 : navigationBean.size();
            }

            @Override
            public int getBadge(int position) {
                return 0;
            }

            @Override
            public QTabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public QTabView.TabTitle getTitle(int position) {
                return new QTabView.TabTitle
                        .Builder(getActivity())
                        .setContent(navigationBean.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.colorAccent),
                                ContextCompat.getColor(getActivity(), R.color.Grey500))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
        mNavigationAdapter.replaceData(navigationBean);
        leftRightLinkage();
    }

    private void leftRightLinkage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });

        mTabLayout.setOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                isTabClicked = true;
                selectTag(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    private void selectTag(int i) {
        index = i;
        mRecyclerView.stopScroll();
        smoothScrollToPosition(i);
    }

    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isTabClicked) {
                isTabClicked = false;
                return;
            }
            int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    private void setChecked(int position) {
        if (isTabClicked) {
            isTabClicked = false;
        } else {
            if (mTabLayout == null) {
                return;
            }
            mTabLayout.setTabSelected(index);
        }
        index = position;
    }

    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - mLayoutManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }
}
