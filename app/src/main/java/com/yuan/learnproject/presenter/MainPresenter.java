package com.yuan.learnproject.presenter;

import android.Manifest;

import com.blankj.utilcode.util.PermissionUtils;
import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/1/27
 **/
public class MainPresenter extends BaseMvpPresenter<MainContract.MainModel, MainContract.MainView>
        implements MainContract.MainPresenter {

    @Inject
    public MainPresenter(MainContract.MainModel model, MainContract.MainView view) {
        super(model, view);
    }

    private String[] permissions = new String[]{};

    @Override
    public void requestPermission() {
        PermissionUtils.permission(permissions)
                .callback(
                new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        mView.requestPermissionSuccess();
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        mView.requestPermissionFail();
                    }
                }
        ).request();
    }
}
