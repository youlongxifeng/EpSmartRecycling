package cn.epsmart.recycling.device.manage.serialport;

import java.io.Serializable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 10:13
 * @description: （添加一句描述）
 */
public class Device implements Serializable{
    private String path;
    private int baudrate;

    public Device() {
    }

    public Device(String path, int baudrate) {
        this.path = path;
        this.baudrate = baudrate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(int baudrate) {
        this.baudrate = baudrate;
    }

    @Override
    public String toString() {
        return "Device{" + "path='" + path + '\'' + ", baudrate='" + baudrate + '\'' + '}';
    }
}
