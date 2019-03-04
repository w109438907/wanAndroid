package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.LoginResponseBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public interface RegisterContract extends BaseContract {
    interface RegisterView extends BaseView {
        void onSuccess(LoginResponseBean loginResponseBean);
        void showProgress(boolean isShow);
    }

    interface RegisterModel extends BaseModel {
        Observable<BaseBean<LoginResponseBean>> register(String username, String password, String rePassword);
    }

    interface RegisterPresenter extends BasePresenter {
        void doRegister(String username, String password, String rePassword);
    }
}
