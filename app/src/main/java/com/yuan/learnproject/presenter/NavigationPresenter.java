package com.yuan.learnproject.presenter;

import com.yuan.learnproject.base.BaseMvpPresenter;
import com.yuan.learnproject.bean.NavigationBean;
import com.yuan.learnproject.contract.NavigationContract;
import com.yuan.learnproject.rx.CommonSubscriber;
import com.yuan.learnproject.rx.RxHttpResponseTransformer;

import java.util.List;

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
        if (isViewAttached()) {
            mModel.getNavigation()
                    .compose(RxHttpResponseTransformer.compatResult())
                    .subscribe(new CommonSubscriber<List<NavigationBean>>(mView) {
                        @Override
                        public void onNext(List<NavigationBean> navigationBeans) {
                            mView.showNavigation(navigationBeans);
                        }
                    });
        }
    }
}
