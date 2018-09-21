package cn.epsmart.recycling.device.manage.serialport;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 10:21
 * @description: （日志消息数据接口）
 */
public interface IMessage {
    /**
     * 消息文本
     *
     * @return
     */
    String getMessage();

    /**
     * 是否发送的消息
     *
     * @return
     */
    boolean isToSend();
}
