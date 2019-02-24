package com.yuan.learnproject.ui.activity;

import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.contract.MainContract;
import com.yuan.learnproject.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView{

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }
}
