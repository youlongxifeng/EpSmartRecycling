package cn.epsmart.recycling.device.ui.activity.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationQualityReport;
import com.company.project.android.utils.LogUtils;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpActivity;
import cn.epsmart.recycling.device.manage.MessageQueuingManager;
import cn.epsmart.recycling.device.manage.serialport.Device;
import cn.epsmart.recycling.device.manage.serialport.SerialPortManager;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.ui.fragment.ContextFragment;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;
import cn.epsmart.recycling.device.utils.Utils;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 9:03
 * @description: （添加一句描述）
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> {


    @Override
    public MainPresenter setPresenter() {
        return new MainPresenter();
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

    int count = 0;
    MessageQueuingManager messageQueuingManager = new MessageQueuingManager();

    @Override
    public void initDate() {
     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        ++count;
                        messageQueuingManager.put("zhangsan"+count);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
        messageQueuingManager.start();

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        LogUtils.i("screenWidth==="+screenWidth+"   screenHeight="+screenHeight);
        mPresenter.updatePosition();
        Device mDevice = new Device("dev/ttyS3","9600");
        SerialPortManager.instance().open(mDevice);//打开串口
       // SerialPortManager.instance().sendCommand(text);//发送串口数据

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(1000, null);
        SerialPortManager.instance().close();//关闭串口
    }
}
