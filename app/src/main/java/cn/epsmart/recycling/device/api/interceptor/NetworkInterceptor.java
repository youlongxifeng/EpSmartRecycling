package cn.epsmart.recycling.device.api.interceptor;

import com.company.project.android.utils.LogUtils;
import com.company.project.android.utils.NetWorkUtil;

import java.io.IOException;

import cn.epsmart.recycling.device.base.BaseApplication;
import cn.epsmart.recycling.device.constant.Constant;
import cn.epsmart.recycling.device.entity.UserBean;
import cn.epsmart.recycling.device.manage.UserInfoManager;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 14:13
 * @description: （添加一句描述）
 */
public class NetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        UserBean userBean = UserInfoManager.getInstance().getUserBean();
        request.newBuilder().addHeader(Constant.TIME_TOKEN, String.valueOf(System.currentTimeMillis()));//添加服务器访问时间
        if (userBean != null) {
            request.newBuilder().addHeader(Constant.USERID, userBean.getUserid())//用户名
                    .addHeader(Constant.ACCESSTOKEN, userBean.getAccessToken());//token
             }
            boolean isConnected = NetWorkUtil.isConnected(BaseApplication.getContext());
            //无网络时强制使用缓存
            if (!isConnected) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            String method = request.method();
            if ("POST".equalsIgnoreCase(method)) {
                LogUtils.i("POST 请求方式  url=" + request.url());
            } else if ("GET".equalsIgnoreCase(method)) {
                LogUtils.i("GET 请求方式  url=" + request.url());
            }
            Response response = chain.proceed(request);
       /* String cookie = response.header("Set-Cookie");
        if (cookie != null) {
            SharedPrefercesUtils.saveCookiePreference(BaseApplication.getContext().getBaseContext(), cookie);
        }*/
            if (isConnected) {
                // 有网络时，设置超时为0
                int maxStale = 0;
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxStale)
                        // .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为3周
                int maxStale = 60 * 60 * 24 * 21;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        // .removeHeader("Pragma")
                        .build();
            }

            return response;
        }
    }

