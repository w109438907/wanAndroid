package com.yuan.learnproject.ui.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuan.learnproject.R;
import com.yuan.learnproject.bean.knowledge.TreeChildrenBean;
import com.yuan.learnproject.bean.knowledge.TreesBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author yuan
 * @date 2019/3/8
 **/
public class KnowledgeTreeQuickAdapter extends BaseQuickAdapter<TreesBean, BaseViewHolder>{

    private Context mContext;

    public KnowledgeTreeQuickAdapter(Context context) {
        super(R.layout.knowledge_item);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TreesBean item) {
        helper.setText(R.id.title_first, item.getName());
        List<TreeChildrenBean> data = item.getChildren();
        StringBuilder stringBuilder = new StringBuilder();
        for (TreeChildrenBean bean : data) {
            stringBuilder.append(bean.getName());
            stringBuilder.append("    ");
        }
        helper.setText(R.id.title_second, stringBuilder.toString());
    }
}
