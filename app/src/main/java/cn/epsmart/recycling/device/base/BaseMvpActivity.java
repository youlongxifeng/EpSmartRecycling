package cn.epsmart.recycling.device.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import me.yokeyword.fragmentation.SupportActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 8:47
 * @description: （添加一句描述）
 */
public abstract class BaseMvpActivity <P extends BasePresenter> extends SupportActivity implements BaseView {
    public P mPresenter;
    Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = getlayoutId();
        if (layoutResID != 0) {
            setContentView(layoutResID);
            mUnbinder = ButterKnife.bind(this);
            mPresenter = setPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        }
        initView();
        initDate();

    }

    //由于某些工具类，需要在setContentView的顺序前或者后来编辑
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    public abstract P setPresenter();

    public abstract int getlayoutId();

    public abstract void initView();

    public abstract void initDate();


     @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unSubscribe();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


}

