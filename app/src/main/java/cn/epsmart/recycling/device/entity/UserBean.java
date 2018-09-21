package cn.epsmart.recycling.device.entity;

import java.io.Serializable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 15:27
 * @description: （添加一句描述）
 */
public class UserBean extends BaseBean {
    /**
     * 用户ID
     */
    private String userid;
    /**
     * 用户账号（当前只支持手机号码)
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

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
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
