package cn.epsmart.recycling.device.manage.serialport;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/28 11:08
 * @description: （添加一句描述）
 */
public interface HardwareDefineInfo {
    //// return code start ////
    public static final int CODE_OK = 0;                    // ok

    public static final int CODE_ERROR_FILE_NOT_FOUND = -1; // file no found

    public static final int CODE_ERROR_FILE_IO = -2;        // io

    public static final int CODE_ERROR_FILE_OCCUPIED = -3;  // Added for occupation while updating
    //// return code END ////

    //// serial port name start ////
    public static final String wakeupSerialPortName = "/dev/ttyS1";

    public static final String mainSerialPortName = "/dev/ttyS3";

    public static final String headSerialPortName = "/dev/ttyS4";
    //// serial port name END ////

    //// serial port baud rate start ////
    public static final int wakeupSerialPortBaudRate = 115200;

    public static final int mainSerialPortBaudRate = 9600;

    public static final int headSerialPortBaudRate = 9600;
    //// serial port baud rate END ////

    // heartbeat time span
    public static final long heartbeatTimespan = 500;   // ms

    // get wake up angle time span
    public static final long getAngleTimespan = 500;    // ms

    // wake up angle limit
    public static final int wakeupAngleLimit = 20;      // degree

}
