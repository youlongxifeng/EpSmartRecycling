package cn.epsmart.recycling.device.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.epsmart.recycling.device.R;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/27 14:38
 * @description: （添加一句描述）
 */

public class LoadControlView extends FrameLayout {


    RelativeLayout mLoadingView;
    RelativeLayout mErrorView;
    RelativeLayout mEmptyView;
    private TextView mBtReload;
    private View mContentView;
    private ImageView mErrorImageView;
    //protected TextView tv_autoTest;
    //protected LinearLayout ll_error;
    /**
     * 记录点击的时间
     */
    private long _time;
    private TextView mEmptyText;

    public interface OnLoadClickListener {
        void OnLoadClick(View view);
    }

    OnLoadClickListener mOnClickListener;

    public void setLoadOnClickListener(OnLoadClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public LoadControlView(@NonNull Context context) {
        this(context, null);
    }

    public LoadControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadControlView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mLoadingView = (RelativeLayout) findViewById(R.id.loading);
        mErrorView = (RelativeLayout) findViewById(R.id.error);
        mEmptyView = (RelativeLayout) findViewById(R.id.empty);
        //    tv_autoTest = (TextView) findViewById(R.id.tv_autoTest);
        // ll_error = (LinearLayout) findViewById(R.id.ll_error);
        mEmptyText = (TextView) findViewById(R.id.tv_empty);
        mContentView = getChildAt(0);
        if (mContentView == null) {
            throw new RuntimeException("缺少View");
        }

        mBtReload = (TextView) findViewById(R.id.tv_reloads);
        mBtReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - _time > 1000) {
                    if (mOnClickListener != null) {
                        showLoadView();
                        mOnClickListener.OnLoadClick(v);
                    }
                }

            }
        });
        mErrorImageView = (ImageView) findViewById(R.id.iv_error_load_icon);
        mErrorImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - _time > 1000) {

                    if (mOnClickListener != null) {
                        showLoadView();
                        mOnClickListener.OnLoadClick(v);
                    }
                    _time = System.currentTimeMillis();
                }
            }
        });
    }

    /**
     * 显示加载页面
     */
    public void showLoadView() {
        mContentView.setVisibility(GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    /**
     * 设置特殊错误信息
     */
    public void setErrorText(String msg) {
       /* if (mBtReload != null && !TextUtils.isEmpty(msg)) {
            mBtReload.setText(msg);
        }*/
    }

    /**
     * 显示加载错误的界面
     */
    public void showErrorView() {
        mContentView.setVisibility(GONE);
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
       /* tv_autoTest.setVisibility(GONE);
        ll_error.setVisibility(VISIBLE);*/

    }

    /**
     * 显示加载成功的界面
     */
    public void showSucceedView() {
        mContentView.setVisibility(VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
    }

    /**
     * 显示加载为空的界面
     */
    public void showEmptyView() {
        showEmptyView(null);
    }

    /**
     * 显示加载为空的界面
     */
    public void showEmptyView(String str) {
        mContentView.setVisibility(GONE);
        mLoadingView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
        setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(str)) {
            mEmptyText.setText(str);
        }
    }


}
