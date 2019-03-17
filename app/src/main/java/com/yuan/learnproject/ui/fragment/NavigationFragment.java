package com.yuan.learnproject.ui.fragment;

import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseFragment;
import com.yuan.learnproject.bean.NavigationBean;
import com.yuan.learnproject.contract.NavigationContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerNavigationComponent;
import com.yuan.learnproject.di.module.NavigationModule;
import com.yuan.learnproject.presenter.NavigationPresenter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/17
 **/
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.NavigationView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerNavigationComponent.builder()
                .appComponent(appComponent)
                .navigationModule(new NavigationModule(this))
                .build();
    }

    @Override
    protected int getLayout() {
        return R.layout.navigation_fragment;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showNavigation(NavigationBean navigationBean) {

    }
}
