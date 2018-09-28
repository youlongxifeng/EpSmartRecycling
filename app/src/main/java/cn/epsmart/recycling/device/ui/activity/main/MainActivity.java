package cn.epsmart.recycling.device.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;

import com.company.project.android.utils.LogUtils;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseApplication;
import cn.epsmart.recycling.device.base.BaseMvpActivity;
import cn.epsmart.recycling.device.constant.Constant;
import cn.epsmart.recycling.device.entity.UserBean;
import cn.epsmart.recycling.device.entity.UserBeanDao;
import cn.epsmart.recycling.device.manage.MessageQueuingManager;
import cn.epsmart.recycling.device.manage.serialport.Device;
import cn.epsmart.recycling.device.manage.serialport.HardwareDefineInfo;
import cn.epsmart.recycling.device.manage.serialport.SerialPortManager;
import cn.epsmart.recycling.device.service.GuardService;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 9:03
 * @description: （添加一句描述）
 */
public class MainActivity extends BaseMvpActivity<MainPresenter> {
    private final static String TAG = MainActivity.class.getSimpleName();
    private String mUserId;
    private String mAccessToken;
    private UserBeanDao userBeanDao;

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
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
    }

    int count = 0;
    MessageQueuingManager messageQueuingManager = new MessageQueuingManager();

    @Override
    public void initDate() {
       /* mUserId = getIntent().getExtras().getString(Constant.USERID);
        mAccessToken = getIntent().getExtras().getString(Constant.ACCESSTOKEN);*/
        userBeanDao = BaseApplication.getContext().getDaoSession().getUserBeanDao();
        UserBean userBean = new UserBean();
        if(mUserId==null){
            mUserId="123456";
        }
      //  userBean.set_id(1);
        userBean.setUserid(mUserId);
        userBean.setUsername("张三");
        userBean.setPassword("123456");
        if(mAccessToken==null){
            mAccessToken="123456";
        }
        userBean.setAccessToken(mAccessToken);
        if(userBeanDao!=null){
            userBeanDao.update(userBean);
        }
        LogUtils.i(TAG,"userBeanDao===" + userBeanDao );
        messageQueuingManager.start();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        LogUtils.i("screenWidth===" + screenWidth + "   screenHeight=" + screenHeight);
        mPresenter.updatePosition();
        Device mDevice = new Device(HardwareDefineInfo.mainSerialPortName, HardwareDefineInfo.headSerialPortBaudRate);
        SerialPortManager.instance().open(mDevice);//打开串口
        // SerialPortManager.instance().sendCommand(text);//发送串口数据

        startService(new Intent(MainActivity.this,GuardService.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userBeanDao.deleteAll();
        setResult(1000, null);
        SerialPortManager.instance().close();//关闭串口
    }
}
