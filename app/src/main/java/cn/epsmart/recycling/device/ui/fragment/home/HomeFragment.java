package cn.epsmart.recycling.device.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.ui.fragment.ContextFragment;
import cn.epsmart.recycling.device.ui.fragment.articlesettlement.ArticleSettlementFragment;
import cn.epsmart.recycling.device.ui.fragment.delivery.DeliveryFragment;
import cn.epsmart.recycling.device.ui.fragment.home.adapter.RecoveryTypeAdapter;
import cn.epsmart.recycling.device.widget.RecItemDecoration;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:04
 * @description: （显示首页内容）
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> {
    @BindView(R.id.rcv_recovery_type_list)
    RecyclerView mRcvRecoveryTypeList;
    /**
     * 回收类型适配器
     */
    RecoveryTypeAdapter mRecoveryTypeAdapter;

    private List<RecoveryTypeBean> mRecoveryTypeBeanList;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_home_main;
    }

    @Override
    protected void initView(View view) {
        mRecoveryTypeBeanList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            RecoveryTypeBean recoveryTypeBean = new RecoveryTypeBean();
            recoveryTypeBean.setId(i);
            recoveryTypeBean.setmRecoveryPrice("10元一斤");
            recoveryTypeBean.setmRecoveryType("纸类");
            mRecoveryTypeBeanList.add(recoveryTypeBean);
        }
        mRecoveryTypeAdapter = new RecoveryTypeAdapter(getActivity(), R.layout.recovery_type_item, mRecoveryTypeBeanList);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        mRcvRecoveryTypeList.setLayoutManager(manager);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.custom_divider));
       // mRcvRecoveryTypeList.addItemDecoration(divider);
        mRcvRecoveryTypeList.addItemDecoration(new RecItemDecoration(getActivity(),R.color.color_22ffffff));

        mRcvRecoveryTypeList.setAdapter(mRecoveryTypeAdapter);
        mRecoveryTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ((ContextFragment) getParentFragment()).startBrotherFragment(ArticleSettlementFragment.newInstance());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void initDate() {

    }

}
