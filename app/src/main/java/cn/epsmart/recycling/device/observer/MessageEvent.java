package cn.epsmart.recycling.device.observer;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/20 13:55
 * @description: （添加一句描述）
 */
public class MessageEvent<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "t=" + t +
                '}';
    }
}
