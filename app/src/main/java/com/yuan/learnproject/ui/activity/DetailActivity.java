package com.yuan.learnproject.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yuan.learnproject.R;
import com.yuan.learnproject.base.BaseActivity;
import com.yuan.learnproject.base.BaseMvpActivity;
import com.yuan.learnproject.di.component.AppComponent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;

/**
 * @author yuan
 * @date 2019/3/3
 **/
public class DetailActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.title)
    TextView mTitle;

    @Override
    protected int getLayout() {
        return R.layout.item_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void init() {
        initWebView();
        initToolBar();
    }

    private void initToolBar() {
        mToolBar.inflateMenu(R.menu.detail_tool_menu);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    //start share activity
                    return true;
                }
                return false;
            }
        });
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
//        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        final String cachePath = getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        settings.setAppCachePath(cachePath);
        settings.setAppCacheMaxSize(5 * 1024 * 1024);
        settings.setDatabaseEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        final String dbPath = getApplicationContext().getDir("db", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dbPath);

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = savedInstanceState;
        }
        initData(bundle);
    }

    private String url;

    private void initData(Bundle bundle) {
        url = bundle.getString("url");
        mWebView.loadUrl(url);
    }


    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgress.setVisibility(View.GONE);
            } else {
                mProgress.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            mTitle.setText(view.getTitle());
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
//            mTitle.setText(view.getTitle());
            super.onPageFinished(view, url);
        }
    }
}
