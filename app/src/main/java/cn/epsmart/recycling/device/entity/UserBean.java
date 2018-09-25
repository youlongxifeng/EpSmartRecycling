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
@Entity(nameInDb = "user_info", indexes = {@Index(value = "userid DESC", unique = true)})
public class UserBean extends BaseBean {
    @Id
    private Long id;
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

    @Generated(hash = 1290821070)
    public UserBean(Long id, @NotNull String userid, @NotNull String username,
                    @NotNull String password) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
