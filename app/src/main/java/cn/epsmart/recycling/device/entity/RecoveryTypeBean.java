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
    private String categoryId;
    /**
     * 回收名称
     */
    private String name;
    /**
     * 回收价格
     */
    private double price;
    /**
     * 容量  1表示100%  ，0.5表示50%
     */
    private double capacity;
    /**
     * 价格单位
     */
    private String company;
    /**
     * 回收图标
     */
    private String imgure;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
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

    @Override
    public String toString() {
        return "RecoveryTypeBean{" +
                "id=" + id +
                ", categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", company='" + company + '\'' +
                ", imgure=" + imgure +
                '}';
    }
}
