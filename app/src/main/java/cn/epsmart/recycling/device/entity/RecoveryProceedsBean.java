package cn.epsmart.recycling.device.entity;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/27 12:38
 * @description: （回收收益）
 */
public class RecoveryProceedsBean extends BaseBean {
    /**
     * 类型
     */
    private String type;
    /**
     * 收益指数
     */
    private String proceeds;
    /**
     * 价格单位
     */
    private String company;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProceeds() {
        return proceeds;
    }

    public void setProceeds(String proceeds) {
        this.proceeds = proceeds;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "RecoveryProceedsBean{" +
                "type='" + type + '\'' +
                ", proceeds='" + proceeds + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
