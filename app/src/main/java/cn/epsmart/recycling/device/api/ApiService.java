package cn.epsmart.recycling.device.api;

import java.util.List;

import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.entity.UserBean;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 14:15
 * @description: （添加一句描述）
 */
public interface ApiService {
    @GET("api/data/Android/10/{page}")
    Observable<List<RecoveryTypeBean>> getGank(@Path("page") String page);

    /**
     * 登录方法
     */
    @FormUrlEncoded
    @POST("faxianhuanbao/login")
    Flowable<ResponseBean<UserBean>> userLogin(@Field("username") String username, @Field("password") String password);
}
