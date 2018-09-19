package cn.epsmart.recycling.device.ui.fragment.home.adapter;


import android.content.Context;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.epsmart.recycling.device.base.BaseRecyclerViewAdapter;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 10:03
 * @description: （回收类型适配器）
 */
public class RecoveryTypeAdapter extends BaseRecyclerViewAdapter<RecoveryTypeBean> {
    public RecoveryTypeAdapter(Context context, int layoutId, List<RecoveryTypeBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, RecoveryTypeBean recoveryTypeBean, int position) {

    }
}
