package cn.epsmart.recycling.device.entity;

import java.io.Serializable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/20 9:16
 * @description: （结算实体类）
 */
public class SettlementBean implements Serializable {
    /**
     * 重量
     */
    private String weight;
    /**
     * 价格
     */
    private String price;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SettlementBean{" +
                "weight='" + weight + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
