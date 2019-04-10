package com.yuan.learnproject.ui.adapter;

import android.content.Context;
import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuan.learnproject.R;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;

/**
 * @author yuan
 * @date 2019/4/9
 **/
public class WeChatQuickAdapter extends BaseQuickAdapter<MainArticleDataBean, BaseViewHolder> {
    private Context mContext;

    public WeChatQuickAdapter(Context context) {
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
        helper.setImageResource(R.id.collection, item.isCollect() ? R.drawable.ic_have_favorited : R.drawable.ic_action_favorite);
        helper.addOnClickListener(R.id.collection);
    }
}
