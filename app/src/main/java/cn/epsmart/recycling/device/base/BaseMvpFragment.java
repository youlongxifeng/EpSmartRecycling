package cn.epsmart.recycling.device.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;
import cn.epsmart.recycling.device.widget.LoadContentView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:05
 * @description: （添加一句描述）
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends SupportFragment implements BaseView, View.OnTouchListener {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    protected P mPresenter;
    @Nullable
    protected BaseMvpActivity mBaseActivity;
    Unbinder mUnbinder;
    protected LayoutInflater mInflater;
    /**
     * 根view
     */
    protected ViewGroup mRootView;

    protected LoadContentView mLoadContentView;
    /**
     * 展示view的容器
     */
    private FrameLayout mContentView;
    /**
     * onCreateView()里返回的view
     */
    protected View mView;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        if (getActivity() instanceof BaseMvpActivity) {
            mBaseActivity = (BaseMvpActivity) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       /* super.onCreateView(inflater, container, savedInstanceState);
        mInflater = LayoutInflater.from(mBaseActivity);
        mRootView = (ViewGroup) mInflater.inflate(R.layout.fragment_base_main, container, false);
        mContentView = (FrameLayout) mRootView.findViewById(R.id.fl_content);
        mLoadContentView = mRootView.findViewById(R.id.view_load_content);
        mView = getRootView(container);
        mContentView.addView(mView);
        mUnbinder = ButterKnife.bind(this, mRootView);
        mRootView.setOnTouchListener(this);
        return mRootView;*/
       View view = inflater.inflate(getlayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initDate();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();
    }

    protected abstract P createPresenter();

    protected abstract int getlayoutId();

    protected abstract View getRootView(ViewGroup container);

    protected abstract void initView(View view);

    protected abstract void initDate();

    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }

    /**
     * 处理回退事件
     *
     * @return
     */
 /*   @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
        }
        return true;
    }*/
}
