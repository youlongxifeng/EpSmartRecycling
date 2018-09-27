package cn.epsmart.recycling.device.api;

import java.util.List;

import cn.epsmart.recycling.device.entity.RecoveryProceedsBean;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.entity.SettlementBean;
import cn.epsmart.recycling.device.entity.UserBean;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 14:15
 * @description: （添加一句描述）
 */
public interface ApiService {

    /**
     * 登录方法
     */
    @FormUrlEncoded
    @POST("faxianhuanbao/login")
    Flowable<ResponseBean<UserBean>> userLogin(@Field("username") String username, @Field("password") String password);

    /**
     * 获取回收类型列表
     * @param pageSize
     * @param curPage
     * @return
     */
    @GET("faxianhuanbao/recoverytype")
    Observable<List<RecoveryTypeBean>> getrecoveryTypesData(@Query("page_size") int pageSize, @Query("page") int curPage);

    /**
     * 上报回收物品重量
     * @param weight
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("faxianhuanbao/updateweight")
    Observable<ResponseBean<RecoveryProceedsBean>> updateWeight(@Field("weight") String weight, @Field("type") String type,@Field("oldweight") String oldweight);




}
