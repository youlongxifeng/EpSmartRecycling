package cn.epsmart.recycling.device;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.epsmart.recycling.device.base.BaseMvpActivity;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.ui.fragment.ContextFragment;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseMvpActivity {
    @Override
    public BasePresenter setPresenter() {
        return null;
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if (findFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_container, ContextFragment.newInstance());
        }


    }

    @Override
    public void initDate() {

    }

    @Override
    public void onBackPressedSupport() {
        // 主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
