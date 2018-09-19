package cn.epsmart.recycling.device.ui.fragment.home.adapter;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseRecyclerViewAdapter;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;

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
        ImageView mRecoveryTypeIcon = holder.getView(R.id.iv_recovery_type_icon);
        TextView mRecoveryTypeTitle = holder.getView(R.id.tv_recovery_type_title);
        TextView mRecoveryTypePrice = holder.getView(R.id.tv_recovery_type_price);
        mRecoveryTypeTitle.setText(recoveryTypeBean.getmRecoveryType());
        mRecoveryTypePrice.setText(recoveryTypeBean.getmRecoveryPrice());
        //Glide.with(HomeFragment.this).load("http://goo.gl/gEgYUd").into(mRecoveryTypeIcon);
    }
}
