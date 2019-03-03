package com.yuan.learnproject.eventbus;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class LoginEvent {
    boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public LoginEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }
}

