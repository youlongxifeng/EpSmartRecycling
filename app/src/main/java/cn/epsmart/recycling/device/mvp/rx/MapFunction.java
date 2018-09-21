package cn.epsmart.recycling.device.mvp.rx;

import cn.epsmart.recycling.device.entity.ResponseBean;
import io.reactivex.functions.Function;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 15:00
 * @description: （添加一句描述）
 */
public class MapFunction<F> implements Function<ResponseBean<F>, F> {
    @Override
    public F apply(ResponseBean<F> fResponseBean) throws Exception {
        int code = fResponseBean.getCode();
        if (code == ResponseBean.CODE_SUCCESS || code == ResponseBean.CODE_SUCCESS0) {
            return fResponseBean.getData();
        } else {
           // throw new ApiException(f.getMsg(), f.getCode());
            return null;
        }
    }
}
