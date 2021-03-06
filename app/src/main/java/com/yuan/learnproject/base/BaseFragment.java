package com.yuan.learnproject.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuan.learnproject.MainApplication;
import com.yuan.learnproject.di.component.AppComponent;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author yuan
 * @date 2019/2/24
 **/
public abstract class BaseFragment<T extends BaseMvpPresenter> extends Fragment implements BaseContract.BaseView {
    private Unbinder mUnbinder;
    private View mView;
    private MainApplication mApplication;

    @Inject
    public T mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mApplication = (MainApplication) getActivity().getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }

    public abstract void setupActivityComponent(AppComponent appComponent);
    protected abstract int getLayout();
    protected abstract void init();
}
