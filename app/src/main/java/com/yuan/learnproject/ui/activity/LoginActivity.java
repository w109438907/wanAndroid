package com.yuan.learnproject.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseMvpActivity;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.contract.LoginContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerLoginComponent;
import com.yuan.learnproject.di.module.LoginModule;
import com.yuan.learnproject.eventbus.EventBusHelper;
import com.yuan.learnproject.eventbus.LoginEvent;
import com.yuan.learnproject.presenter.LoginPresenter;

import androidx.appcompat.widget.AppCompatButton;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.LoginView {

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.input_password)
    EditText mInputPassword;
    @BindView(R.id.error_msg)
    TextView mErrorMsg;
    @BindView(R.id.btn_login)
    AppCompatButton mBtnLogin;
    @BindView(R.id.signup)
    TextView mSignup;

    private ProgressDialog mProgressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        mProgressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        mProgressDialog.setIndeterminate(true);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onError(String msg) {
        showProgress(false);
        mErrorMsg.setText(msg);
    }

    @Override
    public void onLoginSuccess(LoginResponseBean loginResponseBean) {
        SPUtils.getInstance(GlobalConstant.COMMON_SHARED_PREFERENCE)
                .put(GlobalConstant.USER_INFO, mGson.toJson(loginResponseBean, LoginResponseBean.class));
        EventBusHelper.getInstance().post(new LoginEvent(true));
        finish();
    }

    @Override
    public void showProgress(boolean isShow) {
        if (isShow) {
            mProgressDialog.show();
        } else {
            mProgressDialog.hide();
        }
    }

    @OnClick({R.id.btn_login, R.id.signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (isValidate()) {
                    mPresenter.doLogin(mUserName.getText().toString(), mInputPassword.getText().toString());
                }
                break;
            case R.id.signup:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            default:
                break;
        }
    }

    private boolean isValidate() {
        mErrorMsg.setText("");
        if ("".equals(mUserName.getText().toString())) {
            mUserName.setError(getString(R.string.empty_username));
            return false;
        }
        if ("".equals(mInputPassword.getText().toString())) {
            mInputPassword.setError(getString(R.string.empty_password));
            return false;
        }
        return true;
    }
}
