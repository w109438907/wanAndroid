package com.yuan.learnproject.api;

import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.bean.articles.MainBannerBean;
import com.yuan.learnproject.bean.knowledge.TreesBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Observable<BaseBean<Object>> collectArticle (@Path("articleId") int articleId);

    @POST("lg/uncollect_originId/{articleId}/json")
    Observable<BaseBean<Object>> cancelCollectArticle (@Path("articleId") int articleId);

    @POST("lg/uncollect/{articleId}/json")
    Observable<BaseBean<Object>> deleteCollectArticle (@Path("articleId") int articleId,
                                                       @Field("originId") int originId);

    @POST("lg/collect/add/json")
    Observable<BaseBean<Object>> collectOutSideArticle (@Field("title") String title,
                                                        @Field("author") String author,
                                                        @Field("link") String link);

    /**
     * 获取知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    Observable<BaseBean<List<TreesBean>>> getKnowledgeTree();

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     */
    @GET("article/list/{page}/json")
    Observable<BaseBean<MainArticleBean>> getKnowledgeList(@Path("page") int page,
                                                           @Query("cid") int cid);
}
