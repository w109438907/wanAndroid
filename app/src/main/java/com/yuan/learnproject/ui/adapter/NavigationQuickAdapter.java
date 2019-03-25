package com.yuan.learnproject.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuan.learnproject.R;
import com.yuan.learnproject.bean.NavigationBean;
import com.yuan.learnproject.bean.articles.MainArticleDataBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.ui.activity.DetailActivity;
import com.yuan.learnproject.utils.CommonUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * @author yuan
 * @date 2019/3/17
 **/
public class NavigationQuickAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder> {

    private Context mContext;

    public NavigationQuickAdapter(Context context) {
        super(R.layout.navigation_fragment_item);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {
        helper.setText(R.id.item_navigation_tv, item.getName());
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);
        List<MainArticleDataBean> articles = item.getArticles();
        mTagFlowLayout.setAdapter(new TagAdapter<MainArticleDataBean>(articles){
            @Override
            public View getView(FlowLayout parent, int position, MainArticleDataBean article) {
                if (article == null) {
                    return null;
                }
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout_tv, parent, false);
                tv.setText(article.getTitle());
                tv.setTextColor(CommonUtil.randomColor());
                return tv;
            }
        });
        mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                MainArticleDataBean article = articles.get(position);
                if (article == null) {
                    return false;
                }
                Intent intent = new Intent(parent.getContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(GlobalConstant.CONTENT_URL_KEY, article.getLink());
                bundle.putInt(GlobalConstant.CONTENT_ID_KEY, article.getId());
                bundle.putString(GlobalConstant.CONTENT_TITLE_KEY, article.getTitle());
                intent.putExtras(bundle);
                parent.getContext().startActivity(intent);
                return true;
            }
        });
    }
}
