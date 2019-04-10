package com.yuan.learnproject.ui.adapter;

import com.yuan.learnproject.bean.FragmentInfoBean;
import com.yuan.learnproject.ui.fragment.KnowledgeFragment;
import com.yuan.learnproject.ui.fragment.ProjectFragment;
import com.yuan.learnproject.ui.fragment.WeChatFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<FragmentInfoBean> mFragments;

    public ViewPagerAdapter(@NonNull FragmentManager fm, List<FragmentInfoBean> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        try {
            Fragment fragment = (Fragment) mFragments.get(position).getFragment().newInstance();
            if (fragment instanceof KnowledgeFragment) {
                ((KnowledgeFragment)fragment).setCid(mFragments.get(position).getId());
            }else if (fragment instanceof ProjectFragment) {
                ((ProjectFragment)fragment).setCid(mFragments.get(position).getId());
            }else if (fragment instanceof WeChatFragment) {
                ((WeChatFragment)fragment).setId(mFragments.get(position).getId());
            }
            return fragment;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments == null ? super.getPageTitle(position) : mFragments.get(position).getTitle();
    }

}