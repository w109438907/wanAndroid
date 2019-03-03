package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.LoginResponseBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public interface LoginContract extends BaseContract {
    interface LoginView extends BaseView {
        void onLoginSuccess(LoginResponseBean loginResponseBean);
        void showProgress(boolean isShow);
    }

    interface LoginModel extends BaseModel {
        Observable<BaseBean<LoginResponseBean>> doLogin(String username, String password);
    }

    interface LoginPresenter extends BasePresenter {
        void doLogin(String username, String password);
    }
}