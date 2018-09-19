package cn.epsmart.recycling.device.utils;

import android.content.Context;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 11:21
 * @description: （添加一句描述）
 */
public class CommonUtil {

    /**
     * APP尺寸
     * @param context
     * @param dimenId
     * @return
     */
    public static int getDimens(Context context, int dimenId) {
        return context.getResources().getDimensionPixelOffset(dimenId);
    }

}
