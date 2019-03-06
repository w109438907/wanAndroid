package com.yuan.learnproject.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.yuan.learnproject.api.ApiService;
import com.yuan.learnproject.http.CookieInterceptor;
import com.yuan.learnproject.http.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author yuan
 * @date 2019/1/27
 **/
@Module
public class HttpModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application, Gson gson) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new CookieInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);
        return builder.build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
