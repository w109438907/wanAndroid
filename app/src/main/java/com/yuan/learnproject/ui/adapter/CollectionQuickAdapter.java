package com.yuan.learnproject.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuan.learnproject.R;
import com.yuan.learnproject.bean.articles.CollectionArticleDataBean;

/**
 * @author yuan
 * @date 2019/4/3
 **/
public class CollectionQuickAdapter extends BaseQuickAdapter<CollectionArticleDataBean, BaseViewHolder> {

    private Context mContext;

    public CollectionQuickAdapter(Context context) {
        super(R.layout.project_fragment);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionArticleDataBean item) {
        helper.setText(R.id.title, Html.fromHtml(item.getTitle(), Html.FROM_HTML_MODE_LEGACY).toString())
                .setText(R.id.author, String.format(mContext.getString(R.string.article_author),
                        Html.fromHtml(item.getAuthor(), Html.FROM_HTML_MODE_LEGACY).toString()))
                .setText(R.id.time, item.getNiceDate())
                .setText(R.id.description, item.getDesc());
        helper.setImageResource(R.id.collection, R.drawable.ic_have_favorited);
        helper.addOnClickListener(R.id.collection);
        Glide.with(mContext).load(item.getEnvelopePic()).into((ImageView) helper.getView(R.id.imageView));
    }
}
