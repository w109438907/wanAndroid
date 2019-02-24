package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.contract.MainContract;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/1/27
 **/
public class MainPresenter extends BaseMvpPresenter<MainContract.MainModel, MainContract.MainView>  {

    @Inject
    public MainPresenter(MainContract.MainModel model, MainContract.MainView view) {
        super(model, view);
    }
}
