package cn.epsmart.recycling.device.entity;

import java.io.Serializable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 10:05
 * @description: （回收类型实体类）
 */
public class RecoveryTypeBean implements Serializable {
    private long id;
    /**
     * 回收类型
     */
    private String type;
    /**
     * 回收价格
     */
    private double price;
    /**
     * 价格单位
     */
    private String company;
    /**
     * 回收图标
     */
    private String imgure;
    /**
     * 回收名称
     */
    private String name;
    private String mRecoveryType;
    private int mIcon;
    private float mRecoveryPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImgure() {
        return imgure;
    }

    public void setImgure(String imgure) {
        this.imgure = imgure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getmRecoveryType() {
        return mRecoveryType;
    }

    public void setmRecoveryType(String mRecoveryType) {
        this.mRecoveryType = mRecoveryType;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public float getmRecoveryPrice() {
        return mRecoveryPrice;
    }

    public void setmRecoveryPrice(float mRecoveryPrice) {
        this.mRecoveryPrice = mRecoveryPrice;
    }

    @Override
    public String toString() {
        return "RecoveryTypeBean{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", company='" + company + '\'' +
                ", imgure='" + imgure + '\'' +
                ", name='" + name + '\'' +
                ", mRecoveryType='" + mRecoveryType + '\'' +
                ", mIcon=" + mIcon +
                ", mRecoveryPrice=" + mRecoveryPrice +
                '}';
    }
}
