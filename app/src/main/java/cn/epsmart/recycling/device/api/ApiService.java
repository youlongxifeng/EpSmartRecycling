package cn.epsmart.recycling.device.api;

import java.util.List;

import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 14:15
 * @description: （添加一句描述）
 */
public interface ApiService {
    @GET("api/data/Android/10/{page}")
    Observable<List<RecoveryTypeBean>> getGank(@Path("page") String page);
}
