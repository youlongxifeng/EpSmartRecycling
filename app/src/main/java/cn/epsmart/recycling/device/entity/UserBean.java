package cn.epsmart.recycling.device.entity;

import java.io.Serializable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 15:27
 * @description: （添加一句描述）
 */
public class UserBean implements Serializable {
    /**
     * 用户账号（当前只支持手机号码)
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
