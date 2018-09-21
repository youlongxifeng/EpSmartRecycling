package cn.epsmart.recycling.device.manage.serialport;

import cn.epsmart.recycling.device.utils.TimeUtil;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 10:25
 * @description: （发送的日志）
 */

public class SendMessage implements IMessage {

    private String command;
    private String message;

    public SendMessage(String command) {
        this.command = command;
        this.message = TimeUtil.currentTime() + "    发送命令：" + command;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isToSend() {
        return true;
    }
}