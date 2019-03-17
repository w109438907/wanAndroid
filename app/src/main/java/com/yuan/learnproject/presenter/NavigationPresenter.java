package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.NavigationBean;
import com.yuan.learnproject.contract.NavigationContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import javax.inject.Inject;

/**
 * @author yuan
 * @date 2019/3/17
 **/
public class NavigationPresenter extends BaseMvpPresenter<NavigationContract.NavigationModel, NavigationContract.NavigationView>
        implements NavigationContract.NavigationPresenter {

    @Inject
    public NavigationPresenter(NavigationContract.NavigationModel model, NavigationContract.NavigationView view) {
        super(model, view);
    }

    @Override
    public void getNavigation() {
        mModel.getNavigation()
                .compose(RxHttpResponseTransformer.compatResult())
                .subscribe(new CommonSubscriber<NavigationBean>(mView) {
                    @Override
                    public void onNext(NavigationBean navigationBean) {
                        mView.showNavigation(navigationBean);
                    }
                });

    }
}
