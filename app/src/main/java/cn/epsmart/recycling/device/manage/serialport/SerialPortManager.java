package cn.epsmart.recycling.device.manage.serialport;

import android.content.Context;
import android.os.HandlerThread;
import android.serialport.SerialPort;
import android.serialport.SerialPortFinder;

import com.company.project.android.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.epsmart.recycling.device.utils.ByteUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 10:10
 * @description: （串口管理工具）
 */
public class SerialPortManager {
    private final static String TAG = SerialPortManager.class.getSimpleName();
    private SerialPort mSerialPort;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    private SerialReadThread mReadThread;
    private HandlerThread mWriteThread;
    private Scheduler mSendScheduler;

    protected static final int BUFFER_SIZE = 1024;
    protected byte[] mUartReadBuffer = new byte[BUFFER_SIZE];
    protected byte[] mUartBuffer = new byte[BUFFER_SIZE];
    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    String[] entries = mSerialPortFinder.getAllDevices();
    String[] entryValues = mSerialPortFinder.getAllDevicesPath();
    private boolean testResult;
    private String failStr = "";
    private Context mContext;


    private static class InstanceHolder {

        public static SerialPortManager sManager = new SerialPortManager();
    }

    public static SerialPortManager instance() {
        return InstanceHolder.sManager;
    }

    private SerialPortManager() {
    }

    /**
     * 打开串口
     *
     * @param device
     * @return
     */
    public SerialPort open(Device device) {
        return open(device.getPath(), device.getBaudrate());
    }

    /**
     * 打开串口
     *
     * @param devicePath
     * @param baudrateString
     * @return
     */
    public SerialPort open(String devicePath, int baudrateString) {
        if (mSerialPort != null) {
            close();
        }

        try {
            File device = new File(devicePath);
            int baurate =baudrateString;// Integer.parseInt(baudrateString);
            LogUtils.i(TAG, "打开串口成功  device="+device+"  baurate="+baurate);
            mSerialPort = new SerialPort(device, baurate);

            mReadThread = new SerialReadThread(mSerialPort.getInputStream());
            mReadThread.start();
            mInputStream = mSerialPort.getInputStream();
            mOutputStream = mSerialPort.getOutputStream();
            mWriteThread = new HandlerThread("write-thread");
            mWriteThread.start();
            mSendScheduler = AndroidSchedulers.from(mWriteThread.getLooper());
            LogUtils.i(TAG, "打开串口成功");
            return mSerialPort;
        } catch (Throwable tr) {
            LogUtils.i(TAG, "打开串口失败");
            close();
            return null;
        }
    }

    /**
     * 关闭串口
     */
    public void close() {
        if (mReadThread != null) {
            mReadThread.close();
        }
        if (mOutputStream != null) {
            try {
                mOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (mWriteThread != null) {
            mWriteThread.quit();
        }

        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
    }

    /**
     * 发送数据
     *
     * @param datas
     * @return
     */
    private void sendData(byte[] datas) throws Exception {
        mOutputStream.write(datas);
    }

    /**
     * (rx包裹)发送数据
     *
     * @param datas
     * @return
     */
    private Observable<Object> rxSendData(final byte[] datas) {

        return Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                try {
                    sendData(datas);
                    emitter.onNext(new Object());
                } catch (Exception e) {

                    LogUtils.e("发送：" + ByteUtil.bytes2HexStr(datas) + " 失败");

                    if (!emitter.isDisposed()) {
                        emitter.onError(e);
                        return;
                    }
                }
                emitter.onComplete();
            }
        });
    }

    /**
     * 发送命令包
     */
    public void sendCommand(final String command) {

        // TODO: 2018/3/22
        LogUtils.i("发送命令：" + command);

        byte[] bytes = ByteUtil.hexStr2bytes(command);
        rxSendData(bytes).subscribeOn(mSendScheduler).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                LogManager.instance().post(new SendMessage(command));
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("发送失败");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
