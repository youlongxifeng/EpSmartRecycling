package cn.epsmart.recycling.device.ui.activity.signin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.company.project.android.utils.LogUtils;

import org.greenrobot.greendao.rx.RxDao;

import butterknife.BindView;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpActivity;
import cn.epsmart.recycling.device.constant.Constant;
import cn.epsmart.recycling.device.entity.UserBean;
import cn.epsmart.recycling.device.ui.activity.main.MainActivity;
import cn.epsmart.recycling.device.utils.ProgressWebView;
import cn.epsmart.recycling.device.widget.CustomVideoView;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 15:31
 * @description: （首页登录界面）
 */
public class SignInActivity extends BaseMvpActivity<SignInPresenter> implements ProgressWebView.WebCallBack, SignInContract.View {
    private final static String TAG = SignInActivity.class.getSimpleName();
    @BindView(R.id.sigin_progresswebview)
    ProgressWebView mProgresswebview;
    /* @BindView(R.id.videoview_advertisement)
     CustomVideoView mCustomVideoView;*/
    @BindView(R.id.btn_signin)
    Button mSignin;
    @BindView(R.id.btn_display_scavenging)
    Button mDisplayScavenging;
    @BindView(R.id.btn_hidden_scavenging)
    Button mHiddenScavenging;
    /**
     * 二维码地址
     */
    private String mQRCodeUrl = "http://192.168.0.42:8080/fxhb/device/qrcodeLogin";


    @Override
    public SignInPresenter setPresenter() {
        return new SignInPresenter();
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_sigin_main;
    }

    @Override
    public void initView() {
        initWebView();
        initAdvertisementView();

    }

    /**
     * 初始化广告界面
     */
    private void initAdvertisementView() {
      /*  mCustomVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sport));

        //播放
        mCustomVideoView.start();
        //循环播放
        mCustomVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mCustomVideoView.start();
            }
        });*/
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mProgresswebview.setWebContentsDebuggingEnabled(true);
        }
        WebSettings ws = mProgresswebview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        ws.setJavaScriptEnabled(true);
        mProgresswebview.setBackgroundColor(Color.parseColor("#00000000"));
        mProgresswebview.setWebCallBack(this);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);
        ws.setAppCacheEnabled(false);
        mProgresswebview.addJavascriptInterface(SignInActivity.this, "kndfunc");

        // 优先使用缓存，解决widget打开网页慢
       // ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        ws.setDefaultTextEncodingName("UTF-8");
        ws.setRenderPriority(WebSettings.RenderPriority.HIGH);  //提高渲染的优先级

        // 设置网页用webView来打开，覆盖默认使用第三方或系统默认浏览器打开网页的行为
        mProgresswebview.setWebViewClient(new WebViewClient() {
            //拦截 url 跳转,在里边添加点击链接跳转或者操作
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                LogUtils.i(TAG, "shouldOverrideUrlLoading url=" + url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                LogUtils.i(TAG, "onReceivedSslError error=" + error);
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();// 接受所有网站的证书
                //handleMessage(Message msg); 其他处理

            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                LogUtils.i(TAG, "onReceivedError failingUrl:" + failingUrl);
            }

            @Override
            public void onPageStarted(final WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.i(TAG, "onPageStarted  url:" + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtils.i(TAG, "onPageFinished  url:" + url);
            }
        });
        mProgresswebview.loadUrl(mQRCodeUrl);
    }

    @Override
    public void initDate() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mProgresswebview.canGoBack()) {
                mProgresswebview.goBack();//返回上一页面
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webviewDestroy();
    }

    /**
     * 销毁webview
     */
    private void webviewDestroy() {
        if (mProgresswebview != null) {
            mProgresswebview.removeAllViews();
            mProgresswebview.destroy();
            mProgresswebview.stopLoading();
            mProgresswebview.removeJavascriptInterface("kndfunc");
            // 移除绑定服务，否则某些特定系统会报错
            mProgresswebview.getSettings().setJavaScriptEnabled(false);
            mProgresswebview.clearHistory();
            mProgresswebview.clearCache(true);
            mProgresswebview.clearView();
            mProgresswebview.removeAllViews();
        }
    }

    @Override
    public void setTitle(String title) {

    }

    /**
     * 登陆成功
     *
     * @param success
     */
    @JavascriptInterface
    public void signInSuccess(String success) {
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivityForResult(intent, 1000);
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();

    }

    @JavascriptInterface
    public void getUserInfo(String userId, String accessToken) {
        LogUtils.i(TAG, "userId===" + userId + "  accessToken=" + accessToken);
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(Constant.USERID,userId);
        bundle.putString(Constant.ACCESSTOKEN,accessToken);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1000);
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }
    /**
     * 登录失败
     */
    @JavascriptInterface
    public void callback() {
        LogUtils.i(TAG,"==============callback=============");
    }

    @Override
    public void signInSuccess(UserBean userBean) {
        //保存数据到数据库或是什么地方
    }

    @Override
    public void signInFail() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mProgresswebview.loadUrl(mQRCodeUrl);
        LogUtils.i(TAG, "requestCode==" + requestCode + "   resultCode=" + resultCode);
    }
}
