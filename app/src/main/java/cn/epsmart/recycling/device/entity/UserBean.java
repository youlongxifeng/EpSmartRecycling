package cn.epsmart.recycling.device.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 15:27
 * @description: （添加一句描述）
 */
@Entity(nameInDb = "UserBean", indexes = {@Index(value = "_id DESC", unique = true)})
public class UserBean extends BaseBean {
    @Id
    private long _id;
    /**
     * 用户ID
     */
    @NotNull
    private String userid;
    /**
     * 用户账号（当前只支持手机号码)
     */
    @NotNull
    private String username;
    /**
     * 用户密码
     */
    @NotNull
    private String password;
    @NotNull
    private String accessToken;
    @Generated(hash = 117343974)
    public UserBean(long _id, @NotNull String userid, @NotNull String username,
            @NotNull String password, @NotNull String accessToken) {
        this._id = _id;
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public long get_id() {
        return this._id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "_id=" + _id +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
