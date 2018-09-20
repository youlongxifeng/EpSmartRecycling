package cn.epsmart.recycling.device.entity;

import java.io.Serializable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 10:05
 * @description: （回收类型实体类）
 */
public class RecoveryTypeBean implements Serializable {
    private long id;
    private String mRecoveryType;
    private float mRecoveryPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getmRecoveryType() {
        return mRecoveryType;
    }

    public void setmRecoveryType(String mRecoveryType) {
        this.mRecoveryType = mRecoveryType;
    }

    public float getmRecoveryPrice() {
        return mRecoveryPrice;
    }

    public void setmRecoveryPrice(int mRecoveryPrice) {
        this.mRecoveryPrice = mRecoveryPrice;
    }

    @Override
    public String toString() {
        return "RecoveryTypeBean{" +
                "id=" + id +
                ", mRecoveryType='" + mRecoveryType + '\'' +
                ", mRecoveryPrice='" + mRecoveryPrice + '\'' +
                '}';
    }
}
