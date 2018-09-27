package cn.epsmart.recycling.device.entity;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/27 12:38
 * @description: （回收收益）
 */
public class RecoveryProceedsBean extends BaseBean {
    /**
     * 收益指数
     */
    private String mProceeds;
    /**
     * 价格单位
     */
    private String mCompany;

    public String getmProceeds() {
        return mProceeds;
    }

    public void setmProceeds(String mProceeds) {
        this.mProceeds = mProceeds;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    @Override
    public String toString() {
        return "RecoveryProceedsBean{" +
                "mProceeds='" + mProceeds + '\'' +
                ", mCompany='" + mCompany + '\'' +
                '}';
    }
}
