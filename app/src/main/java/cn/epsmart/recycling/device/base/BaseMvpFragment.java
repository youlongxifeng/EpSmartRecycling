package cn.epsmart.recycling.device.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:05
 * @description: （添加一句描述）
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends SupportFragment implements BaseView {
    protected P mPresenter;
    Unbinder mUnbinder;
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
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

    protected abstract void initView(View view);

    protected abstract void initDate();

    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}
