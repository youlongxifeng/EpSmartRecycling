package cn.epsmart.recycling.device.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.company.project.android.utils.LogUtils;

import java.util.List;

import cn.epsmart.recycling.device.R;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/25 14:30
 * @description: （添加一句描述）
 */
public class LoadContentView extends FrameLayout {
    private static final String TAG = LoadContentView.class.getSimpleName();
    public static final int NETWORK_LOADING = 1; // 加载中
    public static final int NODATA = 2; // 没有数据
    public static final int NETWORK_ERROR = 3; // 网络错误
    public static final int HIDE_LAYOUT = 4; // 隐藏
    public static final String CONTENT = "type_content";//数据加载成功
    public static final String LOADING = "type_loading";//数据加载中
    public static final String EMPTY = "type_empty";//当前数据为空
    public static final String ERROR = "type_error";//数据加载错误


    private String mErrorState = CONTENT;//初始化为加载状态
    private FrameLayout mRecyclerState;
    private RelativeLayout mLoadingView;//数据加载中
    private RelativeLayout mErrorView;//数据加载失败
    private RelativeLayout mEmptyView;//数据加载为空
    private View mContentView;//数据加载成功
    /**
     * 数据为空
     */
    private TextView mEmptyTextView;//
    /**
     * 数据错误
     */
    private TextView mReloadsTextView;
    /**
     *
     */

    LayoutInflater inflater;
    View rootView;

    public LoadContentView(@NonNull Context context) {
        super(context);
        initView();
    }

    public LoadContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadContentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflater =LayoutInflater.from(getContext());// (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.load_content_view, this,false);
        mRecyclerState=rootView.findViewById(R.id.fl_recycler_state);
        mLoadingView = rootView.findViewById(R.id.data_loading);
        mErrorView = rootView.findViewById(R.id.data_error);
        mEmptyView = rootView.findViewById(R.id.data_empty);
    }

    /**
     * 某个view的全部子孙view都实例化完以后，会调用该view的onFinishInflate()
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = getChildAt(0);
        if (mContentView == null) {
            throw new RuntimeException("缺少View");
        }
    }

    /**
     * 显示加载成功的界面
     */
    public void showSucceedView() {
        switchState(CONTENT, null);
    }

    /**
     * 显示加载页面
     */
    public void showLoadView() {
        switchState(LOADING, null);
    }

    /**
     * 显示加载数据错误
     */
    public void showErrorView() {
        switchState(ERROR, null);
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
        switchState(EMPTY, str);
    }

    /**
     * @param state
     * @param errorText
     */
    private void switchState(String state, String errorText) {
        this.mErrorState = state;
        switch (state) {
            case CONTENT://数据加载成功
                mContentView.setVisibility(VISIBLE);
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mEmptyView.setVisibility(GONE);
                break;
            case LOADING://数据加载中
                mContentView.setVisibility(GONE);
                mLoadingView.setVisibility(VISIBLE);
                mErrorView.setVisibility(GONE);
                mEmptyView.setVisibility(GONE);
                break;
            case EMPTY://加载数据为空
                mContentView.setVisibility(GONE);
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mEmptyView.setVisibility(VISIBLE);
                break;
            case ERROR://数据加载错误
                mContentView.setVisibility(GONE);
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(VISIBLE);
                mEmptyView.setVisibility(GONE);
                LogUtils.i(TAG,"mErrorView==="+mErrorView.getVisibility()+"  mContentView="+mContentView.getVisibility());
                break;
            default:
                break;

        }
    }
}
