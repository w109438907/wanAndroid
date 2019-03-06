package com.yuan.learnproject.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.yuan.learnproject.constant.GlobalConstant;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yuan
 * @date 2019/3/5
 **/
public class CookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        String requestUrl = request.url().toString();
        String domain = request.url().host();
        if ((requestUrl.contains("user/login") || requestUrl.contains("user/register"))
                && !response.headers("set-cookie").isEmpty()) {
            List<String> cookies = response.headers("set-cookie");
            HashSet<String> myCookies = new HashSet<>(cookies);
            SPUtils.getInstance(GlobalConstant.CONFIG_SHARED_PREFERENCE).put(GlobalConstant.KEY_COOKIE, myCookies);
        }
        return response;
    }
}
