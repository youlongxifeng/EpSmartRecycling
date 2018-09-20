package cn.epsmart.recycling.device.ui.fragment.home.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseRecyclerViewAdapter;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/20 11:08
 * @description: （收益明细）
 */
public class TotalRriceAdapter extends BaseRecyclerViewAdapter<RecoveryTypeBean> {
    public TotalRriceAdapter(Context context, int layoutId, List<RecoveryTypeBean> data) {
        super(context, layoutId, data);
    }

    @Override
    protected void convert(ViewHolder holder, RecoveryTypeBean recoveryTypeBean, int position) {
        TextView recovery_type_title=holder.getView(R.id.tv_price_detail_title);
        TextView price_detail=holder.getView(R.id.tv_price_detail);
        recovery_type_title.setText(recoveryTypeBean.getmRecoveryType()+"");
        price_detail.setText(String.valueOf(recoveryTypeBean.getmRecoveryPrice()+""));

    }
}
