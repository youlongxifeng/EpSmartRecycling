package cn.epsmart.recycling.device.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;

import cn.epsmart.recycling.device.R;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 15:35
 * @description: （添加一句描述）
 */
public class ProgressWebView extends WebView {
    private static final String TAG = ProgressWebView.class.getSimpleName();
    private ProgressBar progressbar;
    private WebCallBack webCallBack;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                UIUtils.dip2px(context, 3), 0, 0));

        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        setWebChromeClient(new WebChromeClient());
    }

    public void setWebCallBack(WebCallBack webCallBack) {
        this.webCallBack = webCallBack;
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.d(TAG, "onProgressChanged: " + newProgress);
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (webCallBack != null) {
                webCallBack.setTitle(title);
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface WebCallBack {
        void setTitle(String title);
    }
}
