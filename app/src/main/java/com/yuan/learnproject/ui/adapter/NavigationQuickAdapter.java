package com.yuan.learnproject.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuan.learnproject.R;
import com.yuan.learnproject.bean.NavigationBean;
import com.zhy.view.flowlayout.TagFlowLayout;

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
    }
}
