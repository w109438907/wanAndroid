package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.contract.RegisterContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class RegisterPresenter extends
        BaseMvpPresenter<RegisterContract.RegisterModel, RegisterContract.RegisterView>
        implements RegisterContract.RegisterPresenter{

    @Inject
    public RegisterPresenter(RegisterContract.RegisterModel model, RegisterContract.RegisterView view) {
        super(model, view);
    }

    @Override
    public void doRegister(String username, String password, String rePassword) {
        mModel.register(username, password, rePassword)
                .compose(RxHttpResponseTransformer.compatResult())
                .subscribe(new CommonSubscriber<LoginResponseBean>(mView) {
                    @Override
                    public void onNext(LoginResponseBean loginResponseBean) {
                        mView.onSuccess(loginResponseBean);
                    }
                });
    }
}
