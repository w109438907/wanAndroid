package com.yuan.learnproject.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.bean.LoginResponseBean;
import com.yuan.learnproject.constant.GlobalConstant;
import com.yuan.learnproject.contract.RegisterContract;
import com.yuan.learnproject.di.component.AppComponent;
import com.yuan.learnproject.di.component.DaggerRegisterComponent;
import com.yuan.learnproject.di.module.RegisterModule;
import com.yuan.learnproject.eventbus.EventBusHelper;
import com.yuan.learnproject.eventbus.LoginEvent;
import com.yuan.learnproject.presenter.RegisterPresenter;

import androidx.appcompat.widget.AppCompatButton;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.RegisterView {

    @BindView(R.id.input_name)
    EditText mInputName;
    @BindView(R.id.input_password)
    EditText mInputPassword;
    @BindView(R.id.input_confirm_password)
    EditText mInputConfirmPassword;
    @BindView(R.id.error_msg)
    TextView mErrorMsg;
    @BindView(R.id.btn_signup)
    AppCompatButton mBtnSignup;
    @BindView(R.id.link_login)
    TextView mLinkLogin;

    private ProgressDialog mProgressDialog;

    @Override
    protected int getLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected void init() {
        mProgressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        mProgressDialog.setIndeterminate(true);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerRegisterComponent.builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onSuccess(LoginResponseBean loginResponseBean) {
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

    @Override
    public void onError(String msg) {
        showProgress(false);
        mErrorMsg.setText(msg);
    }

    @OnClick({R.id.btn_signup, R.id.link_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                if (isValidate()) {
                    mPresenter.doRegister(mInputName.getText().toString(),
                            mInputPassword.getText().toString(),
                            mInputConfirmPassword.getText().toString());
                }
                break;
            case R.id.link_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            default:
                break;
        }
    }

    private boolean isValidate() {
        mErrorMsg.setText("");
        if ("".equals(mInputName.getText().toString())) {
            mInputName.setError(getString(R.string.empty_username));
            return false;
        }
        if ("".equals(mInputPassword.getText().toString())) {
            mInputPassword.setError(getString(R.string.empty_password));
            return false;
        }
        if ("".equals(mInputConfirmPassword.getText().toString())) {
            mInputConfirmPassword.setError(getString(R.string.empty_password));
            return false;
        }

        if (!mInputPassword.getText().toString().equals(mInputConfirmPassword.getText().toString())) {
            mInputConfirmPassword.setError(getString(R.string.mismatch_password));
            return false;
        }
        return true;
    }
}
