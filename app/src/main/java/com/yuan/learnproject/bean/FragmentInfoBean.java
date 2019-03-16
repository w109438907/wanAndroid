package com.yuan.learnproject.bean;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class FragmentInfoBean {
    private String title;

    private Class fragment;
    private int id;

    public FragmentInfoBean(String title, Class fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
