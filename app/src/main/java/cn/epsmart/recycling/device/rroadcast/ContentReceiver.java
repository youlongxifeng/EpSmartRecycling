package cn.epsmart.recycling.device.rroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import cn.epsmart.recycling.device.ui.activity.main.MainActivity;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/27 20:02
 * @description: （开机启动广播）
 */
public class ContentReceiver extends BroadcastReceiver {
    static final String action_boot = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(action_boot)) {
            Intent it = new Intent(context, MainActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(it);
        }
    }
}
