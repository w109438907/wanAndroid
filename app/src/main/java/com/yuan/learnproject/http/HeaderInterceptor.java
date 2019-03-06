package com.yuan.learnproject.http;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.yuan.learnproject.constant.GlobalConstant;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author yuan
 * @date 2019/3/5
 **/
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet<String>) SPUtils.getInstance(GlobalConstant.CONFIG_SHARED_PREFERENCE)
                .getStringSet(GlobalConstant.KEY_COOKIE, null);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
