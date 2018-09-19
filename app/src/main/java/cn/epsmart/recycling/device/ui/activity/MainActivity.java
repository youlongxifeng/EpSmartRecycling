package cn.epsmart.recycling.device.ui.activity;

import android.os.Bundle;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpActivity;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.ui.fragment.ContextFragment;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 9:03
 * @description: （添加一句描述）
 */
public class MainActivity  extends BaseMvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*HomeFragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_fragment, fragment,null);
        transaction.commit();*/
    }

    @Override
    public BasePresenter setPresenter() {
        return null;
    }

    @Override
    public int getlayoutId() {
        return 0;
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
}
