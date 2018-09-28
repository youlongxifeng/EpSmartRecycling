package cn.epsmart.recycling.device.entity;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/20 8:47
 * @description: （数据响应实体类）
 */
public class ResponseBean<T> {
    public final static int CODE_SUCCESS0 = 0;     // 服务端返回码，成功（兼容以前 api）
    public final static int CODE_SUCCESS = 200;     // 服务端返回码，成功
    private int code; // code==200为成功 code >=4000 <=4999请求类型错误 code >=5000 <=5999 服务器类型错误
    private String msg;
    private String status;
    private long server_time; // 单位 s
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getServer_time() {
        return server_time;
    }

    public void setServer_time(long server_time) {
        this.server_time = server_time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", status='" + status + '\'' +
                ", server_time=" + server_time +
                ", data=" + data +
                '}';
    }
}
