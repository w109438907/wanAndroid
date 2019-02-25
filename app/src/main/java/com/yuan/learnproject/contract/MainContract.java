package com.yuan.learnproject.contract;

import com.yuan.learnproject.base.BaseContract;

/**
 * @author yuan
 * @date 2019/1/27
 **/
public interface MainContract extends BaseContract {
    interface MainView extends BaseView {
        // view 操作
    }

    interface MainModel extends BaseModel {
        // 获取数据
    }
}
