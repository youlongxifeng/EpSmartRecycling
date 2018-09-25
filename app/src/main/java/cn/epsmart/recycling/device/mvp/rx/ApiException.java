package cn.epsmart.recycling.device.mvp.rx;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import cn.epsmart.recycling.device.manage.UserInfoManager;
import retrofit2.HttpException;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 15:13
 * @description: （添加一句描述）
 */
public class ApiException extends RuntimeException {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    private int mCode;
    private String mMsg;

    public ApiException(String msg, int code) {
        mMsg = msg;
        mCode = code;
    }

    public ApiException(Throwable throwable) {
        super(throwable);
    }

    public int getCode() {
        return mCode;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

    public static ApiException handleException(Throwable e) {
        //        LogUtil.e(API_EXCEPTION, "handleException " + e);
        ApiException apiEx;
        if (e instanceof HttpException) { //HTTP错误
            HttpException httpEx = (HttpException) e;
            String msg = null;
            switch (httpEx.code()) {
                case UNAUTHORIZED:
                    msg = "请求未经授权，这个状态代码必须和WWW-Authenticate报头域一起使用";
                    break;
                case FORBIDDEN:
                    msg = "禁止访问，服务器收到请求，但是拒绝提供服务";
                    break;
                case NOT_FOUND:
                    msg = "可连接服务器，但服务器无法取得所请求的网页，请求资源不存在";
                    break;
                case REQUEST_TIMEOUT:
                    msg = "客户端没有在用户指定的饿时间内完成请求";
                    break;
                case GATEWAY_TIMEOUT:
                    msg = "服务器作为网关或代理，但是没有及时从上游服务器收到请求。 ";
                    break;
                case INTERNAL_SERVER_ERROR:
                    msg = "服务器遇到错误，无法完成请求";
                    break;
                case BAD_GATEWAY:
                    msg = "服务器作为网关或代理，从上游服务器收到无效响应。";
                    break;
                case SERVICE_UNAVAILABLE:
                    msg = "服务器目前无法使用（由于超载或停机维护）。通常，这只是暂时状态";
                    break;
                default:
                    msg = "网络异常，请检查网络后点击重试";
                    apiEx = new ApiException(msg, ServerConstant.ERROR_HTTP); // 均视为网络错误
                    break;
            }
            apiEx = new ApiException(msg, ServerConstant.ERROR_HTTP); // 均视为网络错误
            return apiEx;
        } else if (e instanceof ApiException) {
            //服务器返回的错误
            // 4019 token 失效，统一弹框处理
            if (ServerConstant.CODE_TOKEN_INVALID == ((ApiException) e).getCode()) {
                ((ApiException) e).setMsg("");
                UserInfoManager.getInstance().deleteUserBeanAsy();
            }
            return (ApiException) e;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof MalformedJsonException
                || e instanceof ParseException) {
            apiEx = new ApiException("解析错误", ServerConstant.ERROR_PARSE); // 均视为解析错误
            return apiEx;
        } else if (e instanceof ConnectException) {
            apiEx = new ApiException("网络异常，请检查网络后点击重试", ServerConstant.ERROR_NETWORK);// 均视为网络错误
            return apiEx;
        } else if (e instanceof SocketTimeoutException
                || e instanceof SocketException) {
            apiEx = new ApiException("网络异常，请检查网络后点击重试", ServerConstant.ERROR_NETWORK);// 均视为网络错误
            return apiEx;
        } else if (e instanceof UnknownHostException) {
            apiEx = new ApiException("网络异常，请检查网络后点击重试", ServerConstant.ERROR_NETWORK);// 均视为网络错误
            return apiEx;
        } else {
            e.printStackTrace();
            apiEx = new ApiException("服务器异常，请稍后重试.", ServerConstant.ERROR_UNKNOWN);
            return apiEx;
        }
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "mCode=" + mCode +
                ", mMsg='" + mMsg + '\'' +
                '}';
    }
}

