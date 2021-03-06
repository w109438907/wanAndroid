package com.yuan.learnproject.api;

import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.bean.articles.MainBannerBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author yuan
 * @date 2019/1/27
 **/
public interface ApiService {
    String BASE_URL = "http://www.wanandroid.com";

    @GET("article/list/{pageIndex}/json")
    Observable<BaseBean<MainArticleBean>> getMainArticles(@Path("pageIndex") int pageIndex);

    @GET("article/top/json")
    Observable<BaseBean<List<MainArticleDataBean>>> getTopArticles();

    @GET("banner/json")
    Observable<BaseBean<List<MainBannerBean>>> getBanner();

    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseBean<LoginResponseBean>> login(@Field("username") String username,
                                                  @Field("password") String password);

    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseBean<LoginResponseBean>> register(@Field("username") String username,
                                                     @Field("password") String password,
                                                     @Field("repassword") String rePassword);

    @POST("lg/collect/{articleId}/json")
    Observable<BaseBean<?>> collectArticle (@Path("articleId") int articleId);
}
