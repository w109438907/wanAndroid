package com.yuan.learnproject.bean.articles;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuan
 * @date 2019/3/1
 **/
public class MainArticleBean implements Serializable {
    /**
     * curPage : 1
     * datas : [{"apkLink":"","author":" kinsomy","chapterId":375,"chapterName":"Flutter","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":7909,"link":"https://juejin.im/post/5bac9b1de51d450e452ac1c7","niceDate":"16小时前","origin":"","projectLink":"","publishTime":1548683234000,"superChapterId":375,"superChapterName":"跨平台","tags":[],"title":"Flutter在混合项目中的构建和集成","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"susion哒哒","chapterId":173,"chapterName":"Choreographer","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":true,"id":7908,"link":"https://www.jianshu.com/p/9751c6a9906d","niceDate":"17小时前","origin":"","projectLink":"","publishTime":1548682898000,"superChapterId":173,"superChapterName":"framework","tags":[],"title":"Android的UI显示原理总结","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"承香墨影","chapterId":411,"chapterName":"承香墨影","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7910,"link":"https://mp.weixin.qq.com/s/whYh__5bLKe7aDYm73LCgA","niceDate":"1天前","origin":"","projectLink":"","publishTime":1548604800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/411/1"}],"title":"设计师小姐姐给的 Lottie 动画中&ldquo;带图片&rdquo;，如何预览？","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"susion随心","chapterId":427,"chapterName":"susion随心","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7911,"link":"https://mp.weixin.qq.com/s/n2ngtHc-s4_GghCkx3qukg","niceDate":"1天前","origin":"","projectLink":"","publishTime":1548604800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/427/1"}],"title":"Android的UI显示原理总结","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7913,"link":"https://mp.weixin.qq.com/s/Ew6gHeHp7rFuy-4RfU7RPQ","niceDate":"1天前","origin":"","projectLink":"","publishTime":1548604800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"通用的Android练习项目模板配置「常用工具类，项目结构，模板使用」","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7915,"link":"https://mp.weixin.qq.com/s/Uu_n4GIJSqMGrbEghAw2IA","niceDate":"1天前","origin":"","projectLink":"","publishTime":1548604800000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"Android自定义IM聊天界面","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"浪淘沙xud","chapterId":268,"chapterName":"优秀的设计","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7906,"link":"https://juejin.im/post/5c482f0ae51d4567680e429a","niceDate":"2天前","origin":"","projectLink":"","publishTime":1548488848000,"superChapterId":135,"superChapterName":"项目必备","tags":[],"title":"如何将你的服务优雅的暴露出去","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"AICareless","chapterId":402,"chapterName":"跨平台应用","collect":false,"courseId":13,"desc":"极客日报的flutter版本","envelopePic":"http://www.wanandroid.com/blogimgs/0be29fb4-558a-4294-96d7-17398937569a.png","fresh":false,"id":7905,"link":"http://www.wanandroid.com/blog/show/2494","niceDate":"2天前","origin":"","projectLink":"https://github.com/AICareless/GitClub-Flutter","publishTime":1548488478000,"superChapterId":294,"superChapterName":"开源项目主Tab","tags":[{"name":"项目","url":"/project/list/1?cid=402"}],"title":"极客日报的flutter版本","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"星星y","chapterId":429,"chapterName":"ffmpeg","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7904,"link":"https://www.jianshu.com/p/9cda76b7f63b","niceDate":"2天前","origin":"","projectLink":"","publishTime":1548488151000,"superChapterId":95,"superChapterName":"多媒体技术","tags":[],"title":"FFmpeg音频播放器合集","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"请叫我大苏","chapterId":173,"chapterName":"Choreographer","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7839,"link":"https://www.cnblogs.com/dasusu/p/8311324.html","niceDate":"2019-01-26","origin":"","projectLink":"","publishTime":1548480523000,"superChapterId":173,"superChapterName":"framework","tags":[],"title":"Android 屏幕刷新机制","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":" 升级之路","chapterId":375,"chapterName":"Flutter","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7903,"link":"https://juejin.im/post/5c206b4ff265da61327f52f4","niceDate":"2019-01-25","origin":"","projectLink":"","publishTime":1548405592000,"superChapterId":375,"superChapterName":"跨平台","tags":[],"title":"Flutter 插件使用必知必会","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"DateBro","chapterId":375,"chapterName":"Flutter","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7902,"link":"https://juejin.im/post/5b5d782ae51d45191c7e7fb3","niceDate":"2019-01-25","origin":"","projectLink":"","publishTime":1548392667000,"superChapterId":375,"superChapterName":"跨平台","tags":[],"title":"[译]在 Flutter 中解析复杂的 JSON","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7912,"link":"https://mp.weixin.qq.com/s/AUEDB--AHy4kLUHnMzjFYg","niceDate":"2019-01-25","origin":"","projectLink":"","publishTime":1548345600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"图文搞懂 RecyclerView 刷新机制 | 源码分析","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"郭霖","chapterId":409,"chapterName":"郭霖","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7914,"link":"https://mp.weixin.qq.com/s/fUnIB0WciCmxogffiCjCpQ","niceDate":"2019-01-25","origin":"","projectLink":"","publishTime":1548345600000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/409/1"}],"title":"一种新的架构设计思路，用户行为驱动开发","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"鸿洋","chapterId":408,"chapterName":"鸿洋","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7900,"link":"https://mp.weixin.qq.com/s/adZC0N5Fd4X9FjxUrdlS1w","niceDate":"2019-01-24","origin":"","projectLink":"","publishTime":1548259200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/408/1"}],"title":"全面了解HTTP和HTTPS（开发人员必备）","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"code小生","chapterId":414,"chapterName":"code小生","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7901,"link":"https://mp.weixin.qq.com/s/GQA0T4goTBWu83tyJuWdEw","niceDate":"2019-01-24","origin":"","projectLink":"","publishTime":1548259200000,"superChapterId":408,"superChapterName":"公众号","tags":[{"name":"公众号","url":"/wxarticle/list/414/1"}],"title":"玩转通讯录备份(JNI实战)","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"YoKey","chapterId":124,"chapterName":"Fragment","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7898,"link":"https://www.jianshu.com/p/c12a98a36b2b","niceDate":"2019-01-23","origin":"","projectLink":"","publishTime":1548258546000,"superChapterId":26,"superChapterName":"常用控件","tags":[],"title":"9行代码让你App内的Fragment对重叠说再见","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"lijiankun24","chapterId":173,"chapterName":"Choreographer","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7897,"link":"https://www.jianshu.com/p/9e244d13b866","niceDate":"2019-01-23","origin":"","projectLink":"","publishTime":1548258451000,"superChapterId":173,"superChapterName":"framework","tags":[],"title":"深入理解 WindowManagerService","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"milter","chapterId":78,"chapterName":"性能优化","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7896,"link":"https://www.jianshu.com/p/a769a6028e51","niceDate":"2019-01-23","origin":"","projectLink":"","publishTime":1548258148000,"superChapterId":197,"superChapterName":"热门专题","tags":[],"title":"破译Android性能优化中的16ms问题","type":0,"userId":-1,"visible":1,"zan":0},{"apkLink":"","author":"请叫我大苏","chapterId":171,"chapterName":"binder","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":7895,"link":"https://www.cnblogs.com/dasusu/category/1330070.html","niceDate":"2019-01-23","origin":"","projectLink":"","publishTime":1548257640000,"superChapterId":173,"superChapterName":"framework","tags":[],"title":"大苏的Android 源码分析合集","type":0,"userId":-1,"visible":1,"zan":0}]
     * offset : 0
     * over : false
     * pageCount : 302
     * size : 20
     * total : 6034
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<MainArticleDataBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MainArticleDataBean> getDatas() {
        return datas;
    }

    public void setDatas(List<MainArticleDataBean> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "MainArticleBean{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                ", pageCount=" + pageCount +
                ", size=" + size +
                ", total=" + total +
                ", datas=" + datas +
                '}';
    }
}