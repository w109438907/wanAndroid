package com.yuan.learnproject.bean.articles;

import java.io.Serializable;

/**
 * @author yuan
 * @date 2019/4/3
 **/
public class CollectionArticleDataBean implements Serializable {

    /**
     * author : sunnnydaydev
     * chapterId : 294
     * chapterName : 完整项目
     * courseId : 13
     * desc : 防电商app大部分功能的简单实现。
     文章发布：https://blog.csdn.net/qq_38350635/article/details/88830452
     * envelopePic : https://www.wanandroid.com/blogimgs/af1cb19d-bde0-434e-b6c6-79a28a75e3ad.png
     * id : 55607
     * link : http://www.wanandroid.com/blog/show/2540
     * niceDate : 2小时前
     * origin :
     * originId : 8145
     * publishTime : 1554356630000
     * title : 简单的电商app练习
     * userId : 17109
     * visible : 0
     * zan : 0
     */

    private String author;
    private int chapterId;
    private String chapterName;
    private int courseId;
    private String desc;
    private String envelopePic;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private int originId = -1;
    private long publishTime;
    private String title;
    private int userId;
    private int visible;
    private int zan;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    @Override
    public String toString() {
        return "CollectionArticleBean{" +
                "author='" + author + '\'' +
                ", chapterId=" + chapterId +
                ", chapterName='" + chapterName + '\'' +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", id=" + id +
                ", link='" + link + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", origin='" + origin + '\'' +
                ", originId=" + originId +
                ", publishTime=" + publishTime +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                ", visible=" + visible +
                ", zan=" + zan +
                '}';
    }
}
