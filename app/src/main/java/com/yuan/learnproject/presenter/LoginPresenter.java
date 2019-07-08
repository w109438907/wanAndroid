package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.contract.LoginContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class LoginPresenter extends BaseMvpPresenter<LoginContract.LoginModel, LoginContract.LoginView> implements LoginContract.LoginPresenter {

    @Inject
    public LoginPresenter(LoginContract.LoginModel model, LoginContract.LoginView view) {
        super(model, view);
    }

    @Override
    public void doLogin(String username, String password) {
        if (isViewAttached()) {
            mModel.doLogin(username, password)
                    .compose(RxHttpResponseTransformer.compatResult())
                    .subscribe(new CommonSubscriber<LoginResponseBean>(mView) {
                        @Override
                        public void onSubscribe(Disposable d) {
                            super.onSubscribe(d);
                            mView.showProgress(true);
                        }

                        @Override
                        public void onNext(LoginResponseBean loginResponseBean) {
                            mView.onLoginSuccess(loginResponseBean);
                        }

                        @Override
                        public void onComplete() {
                            super.onComplete();
                            mView.showProgress(false);
                        }
                    });
        }
    }
}
