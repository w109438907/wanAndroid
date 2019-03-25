package com.yuan.learnproject.api;

import com.yuan.learnproject.bean.BaseBean;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.bean.NavigationBean;
import com.yuan.learnproject.bean.articles.MainArticleBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.bean.articles.MainBannerBean;
import com.yuan.learnproject.bean.knowledge.TreesBean;
import com.yuan.learnproject.bean.project.ProjectTreeBean;

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

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param pageIndex
     */
    @GET("article/list/{pageIndex}/json")
    Observable<BaseBean<MainArticleBean>> getMainArticles(@Path("pageIndex") int pageIndex);

    /**
     * 获取首页置顶文章列表
     * http://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    Observable<BaseBean<List<MainArticleDataBean>>> getTopArticles();

    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    Observable<BaseBean<List<MainBannerBean>>> getBanner();

    /**
     * 登录
     * http://www.wanandroid.com/user/login
     *
     * @param username
     * @param password
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseBean<LoginResponseBean>> login(@Field("username") String username,
                                                  @Field("password") String password);

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username
     * @param password
     * @param rePassword
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseBean<LoginResponseBean>> register(@Field("username") String username,
                                                     @Field("password") String password,
                                                     @Field("repassword") String rePassword);

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     */
    @GET("user/logout/json")
    Observable<BaseBean<Object>> logout();

    /**
     * 收藏站内文章
     * http://www.wanandroid.com/lg/collect/1165/json
     * @param articleId
     */
    @POST("lg/collect/{articleId}/json")
    Observable<BaseBean<Object>> collectArticle(@Path("articleId") int articleId);

    /**
     * 文章列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     * @param articleId
     */
    @POST("lg/uncollect_originId/{articleId}/json")
    Observable<BaseBean<Object>> cancelCollectArticle(@Path("articleId") int articleId);

    /**
     * 收藏列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect/2805/json
     * @param articleId
     * @param originId
     */
    @POST("lg/uncollect/{articleId}/json")
    Observable<BaseBean<Object>> deleteCollectArticle(@Path("articleId") int articleId,
                                                      @Field("originId") int originId);
    /**
     * 收藏站外文章
     * http://www.wanandroid.com/lg/collect/add/json
     * @param title
     * @param author
     * @param link
     */
    @POST("lg/collect/add/json")
    Observable<BaseBean<Object>> collectOutSideArticle(@Field("title") String title,
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
     *
     * @param page
     * @param cid
     */
    @GET("article/list/{page}/json")
    Observable<BaseBean<MainArticleBean>> getKnowledgeList(@Path("page") int page,
                                                           @Query("cid") int cid);

    /**
     * 导航数据
     * http://www.wanandroid.com/navi/json
     */
    @GET("navi/json")
    Observable<BaseBean<List<NavigationBean>>> getNavigation();

    /**
     * 项目数据
     * http://www.wanandroid.com/project/tree/json
     */
    @GET("project/tree/json")
    Observable<BaseBean<List<ProjectTreeBean>>> getProjectTree();

    /**
     * 项目列表数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     * @param page
     * @param cid
     */
    @GET("project/list/{page}/json")
    Observable<BaseBean<MainArticleBean>> getProjectList(@Path("page") int page, @Query("cid") int cid);

}
