package cn.epsmart.recycling.device.ui.fragment.home.adapter;


import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.util.List;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseRecyclerViewAdapter;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;
import cn.epsmart.recycling.device.utils.ImageUtil;
import cn.epsmart.recycling.device.utils.UIUtils;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 10:03
 * @description: （回收类型适配器）
 */
public class RecoveryTypeAdapter extends BaseRecyclerViewAdapter<RecoveryTypeBean> {
    private HomeFragment mHomeFragment;

    public RecoveryTypeAdapter(HomeFragment homeFragment, Context context, int layoutId, List<RecoveryTypeBean> data) {
        super(context, layoutId, data);
        this.mHomeFragment = homeFragment;
    }

    @Override
    protected void convert(ViewHolder holder, RecoveryTypeBean recoveryTypeBean, int position) {
        ImageView mRecoveryTypeIcon = holder.getView(R.id.iv_recovery_type_icon);
        TextView mRecoveryTypeTitle = holder.getView(R.id.tv_recovery_type_title);
        TextView mRecoveryTypePrice = holder.getView(R.id.tv_recovery_type_price);
        mRecoveryTypeTitle.setText(recoveryTypeBean.getName());
        if(recoveryTypeBean.getPrice()>0){
            mRecoveryTypePrice.setText(String.valueOf(recoveryTypeBean.getPrice())+ UIUtils.getString(R.string.catty_element_name));
        }else {
            mRecoveryTypePrice.setText(UIUtils.getString(R.string.public_welfare_recovery));
        }

        // Glide.with(mHomeFragment).load("http://img5.imgtn.bdimg.com/it/u=1317134202,1980401867&fm=27&gp=0.jpg").into(mRecoveryTypeIcon);
        //  ImageUtil.setImageViewResId(mRecoveryTypeIcon,"http://img5.imgtn.bdimg.com/it/u=1317134202,1980401867&fm=27&gp=0.jpg",R.mipmap.ic_launcher);
        ImageUtil.setImageViewResId(mRecoveryTypeIcon, recoveryTypeBean.getImgure(), R.mipmap.ic_launcher);
    }
}
