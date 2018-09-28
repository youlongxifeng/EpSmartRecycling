package cn.epsmart.recycling.device.manage.serialport;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/28 9:31
 * @description: （串口通信功能码）
 */
public class FunctionCode {
    /**
     * 读取数据
     */
    public final static int READ=0x0001;
    /**
     * 写入数据
     */
    public final static int WRITE=0x0002;
    /**
     * 清除数据
     */
    public final static int ERASE=0x0003;
    /**
     * 称重校准
     */
    public final static int CALIBRATION=0x0004;
    /***=====================主机请求的数据定义=========start================**/
    /**
     * 计数器1 00 00:读最后一次的计数值
     */
    public final static int COUNTER_ONE=0x0001;
    /**
     * 计数器2 00 00:读最后一次的计数值
     */
    public final static int COUNTER_TWO=0x0002;
    /**
     * 人体检测 读取状态
     */
    public final static int HUMAN_BODY_DETECTION=0x0003;
    /**
     * DOOR_SENSOR 读取状态
     */
    public final static int DOOR_SENSOR=0x0004;
    /**
     * DOOR_LOCK 00 00:无动作    00 01:开锁
     */
    public final static int DOOR_LOCK=0x0005;
    /**
     * WINDOW_LOCK 读取状态(投放窗口锁)
     */
    public final static int WINDOW_LOCK=0x0006;
    /**
     *超声波距离 先发功能码:write开始测距,后发read读距离
     */
    public final static int ULTRASONIC_DISTANCE=0x0007;
    /**
     *称重传感器 先发功能码:write开始称重,后发read读重量
     */
    public final static int WEIGHING_SENSOR=0x0008;
    /**
     * 设备地址 编码地址:1,2,4,8,16,32,64,128
     */
    public final static int DEVICE_ADDRESS=0x0009;
    /**
     * 电机1  0000:停止 00 01:正转 00 10:反转 00 11:停止
     */
    public final static int ELECTRIC_MACHINERY_ONE=0x000A;
    /**
     * 电机2 0000:停止 00 01:正转 00 10:反转 00 11:停止
     */
    public final static int ELECTRIC_MACHINERY_TWO=0x000B;
    /**
     * 电机3 0000:停止 00 01:正转 00 10:反转 00 11:停止
     */
    public final static int ELECTRIC_MACHINERY_THREE=0x000C;
    /**
     *开限位器1 功能码:READ读取状态 WRITE:清状态0
     */
    public final static int OPEN_LIMIT_DEVICE_ONE=0x000D;
    /**
     * 关限位器1 功能码:READ读取状态 WRITE:清状态0
     */
    public final static int CLOSE_LIMIT_DEVICE_ONE=0x000E;
    /**
     * 开限位器2 功能码:READ读取状态 WRITE:清状态0
     */
    public final static int OPEN_LIMIT_DEVICE_TWO=0x000F;
    /**
     * 关限位器2 功能码:READ读取状态 WRITE:清状态0
     */
    public final static int CLOSE_LIMIT_DEVICE_TWO=0x0010;
    /**
     * 开限位器3 功能码:READ读取状态 WRITE:清状态0
     */
    public final static int OPEN_LIMIT_DEVICE_THREE=0x0011;
    /**
     * 关限位器3 功能码:READ读取状态 WRITE:清状态0
     */
    public final static int CLOSE_LIMIT_DEVICE_THREE=0x0012;
    /**
     * 蜂鸣器 00 00:关闭    00 01:开启?????
     */
    public final static int BUZZER=0x0013;
    /**
     * 心跳跳报 心跳
     */
    public final static int HEARTBEAT=0x0014;
    /**
     * LED1_CTRL 00 00:关灯   00 01:开灯  (默认关灯)
     */
    public final static int LED1_CTRL=0x0015;
    /**
     * LED2_CTRL 00 00:关灯   00 01:开灯  (默认关灯)
     */
    public final static int LED2_CTRL=0x0016;
    /**
     * LED3_CTRL 00 00:关灯   00 01:开灯  (默认关灯)
     */
    public final static int LED3_CTRL=0x0017;
    /***=====================主机请求的数据定义=========end================**/

    /**======================从机应答数据定义:==========start==============**/

    /**======================从机应答数据定义:==========end==============**/


}
