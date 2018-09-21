package cn.epsmart.recycling.device.manage.serialport;

import cn.epsmart.recycling.device.utils.TimeUtil;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 10:21
 * @description: （收到的日志）
 */

public class RecvMessage implements IMessage {

    private String command;
    private String message;

    public RecvMessage(String command) {
        this.command = command;
        this.message = TimeUtil.currentTime() + "    收到命令：" + command;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isToSend() {
        return false;
    }
}