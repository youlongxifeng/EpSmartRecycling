package cn.epsmart.recycling.device.mvp.rx;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 15:13
 * @description: （添加一句描述）
 */
public class ApiException  extends RuntimeException {

    public static final String API_EXCEPTION = "api_exception";

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
            switch (httpEx.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    String msg = "网络异常，请检查网络后点击重试";
                    apiEx = new ApiException(msg, ServerConstant.ERROR_HTTP); // 均视为网络错误
                    break;
            }
            return apiEx;
        } else if (e instanceof ApiException) {
            //服务器返回的错误
            // 4019 token 失效，统一弹框处理
            if (ServerConstant.CODE_TOKEN_INVALID == ((ApiException) e).getCode()) {
                ((ApiException) e).setMsg("");
              /*  //删除userBean
                UserManager.getInstance().deleteUserBeanAsy();*/
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

