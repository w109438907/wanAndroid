package com.yuan.learnproject.ui.adapter;

import android.content.Context;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuan.learnproject.R;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class ArticleQuickAdapter extends BaseQuickAdapter<MainArticleDataBean, BaseViewHolder> {

    private Context mContext;

    public ArticleQuickAdapter(Context context) {
        super(R.layout.main_article_item);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainArticleDataBean item) {
        helper.setText(R.id.title, Html.fromHtml(item.getTitle(), Html.FROM_HTML_MODE_LEGACY).toString())
                .setText(R.id.author, String.format(mContext.getString(R.string.article_author),
                        Html.fromHtml(item.getAuthor(), Html.FROM_HTML_MODE_LEGACY).toString()))
                .setText(R.id.classify, String.format(mContext.getString(R.string.article_classify),
                        Html.fromHtml(item.getSuperChapterName(), Html.FROM_HTML_MODE_LEGACY).toString(),
                        Html.fromHtml(item.getChapterName(), Html.FROM_HTML_MODE_LEGACY).toString()))
                .setText(R.id.time, item.getNiceDate());
        if (item.getTop() == 1) {
            helper.setVisible(R.id.article_tag_1, true);
            helper.setText(R.id.article_tag_1, mContext.getString(R.string.article_tag_top));
        } else if (item.isFresh()) {
            helper.setVisible(R.id.article_tag_1, true);
            helper.setText(R.id.article_tag_1, mContext.getString(R.string.article_tag_new));
        } else {
            helper.setGone(R.id.article_tag_1, false);
        }

        if (item.getTags().size() > 0) {
            helper.setVisible(R.id.article_tag_2, true);
            helper.setText(R.id.article_tag_2, item.getTags().get(0).getName());
        } else {
            helper.setGone(R.id.article_tag_2, false);
        }
    }
}
