package cn.epsmart.recycling.device.manage;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

import cn.epsmart.recycling.device.base.BaseApplication;
import cn.epsmart.recycling.device.entity.DaoSession;
import cn.epsmart.recycling.device.entity.UserBean;
import cn.epsmart.recycling.device.entity.UserBeanDao;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/25 10:19
 * @description: （账号管理类）
 */
public class UserInfoManager {

    private static UserInfoManager sUserBeanManger;

    UserBeanDao mUserBeanDao;
    private Query<UserBean> mUserBeanQuery;

    public static UserInfoManager getInstance() {
        if (sUserBeanManger == null) {
            synchronized (UserInfoManager.class) {
                if (sUserBeanManger == null) {
                    sUserBeanManger = new UserInfoManager();
                }
            }
        }
        return sUserBeanManger;
    }

    private UserInfoManager() {
        // get the note DAO
        DaoSession daoSession = BaseApplication.getContext().getDaoSession();
        mUserBeanDao = daoSession.getUserBeanDao();
    }

    /**
     * 同步写入账户信息
     */
    public void saveUser(UserBean userBean) {
        mUserBeanDao.delete(userBean);
        mUserBeanDao.insert(userBean);
    }

    /**
     * 同步删除用户信息
     */
    public void deleteUserBean(UserBean userBean) {
        if (mUserBeanDao != null) {
            mUserBeanDao.delete(userBean);
        }
    }

    /**
     * 删除所有账户信息
     */
    public void deleteUserBeanAsy(){
        ThreadFactoryManage.getNormaPool().execute(new Runnable() {
            @Override
            public void run() {
                mUserBeanDao.deleteAll();
            }
        });
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserBean getUserBean() {
        Query<UserBean> query = mUserBeanDao.queryBuilder().orderAsc(UserBeanDao.Properties.Userid).build();
        if (query != null && query.list().size() > 0) {
            return query.uniqueOrThrow();
        }
        return null;
    }

}
