package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;
import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.NavigationBean;

import io.reactivex.Observable;

/**
 * @author yuan
 * @date 2019/3/17
 **/
public interface NavigationContract extends BaseContract {
    interface NavigationView extends BaseView {
        void showNavigation(NavigationBean navigationBean);
    }

    interface NavigationModel extends BaseModel {
        Observable<BaseBean<NavigationBean>> getNavigation();
    }

    interface NavigationPresenter extends BasePresenter {
        void getNavigation();
    }
}
