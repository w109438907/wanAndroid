package com.yuan.learnproject.eventbus;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.NonNull;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class EventBusHelper {
    private static EventBusHelper INSTANCE = null;

    private EventBus mBus = null;

    private EventBusHelper() {
    }

    public static EventBusHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (EventBusHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EventBusHelper();
                }
            }
        }
        return INSTANCE;
    }

    public EventBus getEventBus() {
        if (mBus == null) {
            synchronized (EventBusHelper.class) {
                if (mBus == null) {
                    mBus = EventBus.getDefault();
                }
            }
        }
        return mBus;
    }

    public void register(@NonNull Object obj) {
        //need check obj
        getEventBus().register(obj);
    }

    public void unregister(@NonNull Object obj) {
        if (getEventBus().isRegistered(obj)) {
            getEventBus().unregister(obj);
        }
    }

    public void post(@NonNull Object event) {
        getEventBus().post(event);
    }

    public void postSticky(@NonNull Object event) {
        getEventBus().postSticky(event);
    }
}
