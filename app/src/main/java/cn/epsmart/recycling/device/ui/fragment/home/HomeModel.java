package cn.epsmart.recycling.device.ui.fragment.home;

import java.util.List;

import cn.epsmart.recycling.device.api.ApiEngine;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 14:07
 * @description: （添加一句描述）
 */
public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<List<RecoveryTypeBean>> getDeliveryData( int pageSize, int curPage) {
        return ApiEngine.getInstance().getApiService().getrecoveryTypesData(   pageSize,   curPage);
    }
}
