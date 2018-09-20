package cn.epsmart.recycling.device.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.company.project.android.utils.LogUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.ui.fragment.ContextFragment;
import cn.epsmart.recycling.device.ui.fragment.articlesettlement.ArticleSettlementFragment;
import cn.epsmart.recycling.device.ui.fragment.home.adapter.RecoveryTypeAdapter;
import cn.epsmart.recycling.device.widget.RecItemDecoration;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:04
 * @description: （显示首页内容）
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {
    private final static String TAG = HomeFragment.class.getSimpleName();
    private static final int REQ_MODIFY_FRAGMENT = 100;
    private static final String ARG_TITLE = "arg_title";
    public static final String KEY_RESULT_TITLE = "title";
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

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        mRcvRecoveryTypeList.setLayoutManager(manager);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.custom_divider));
        // mRcvRecoveryTypeList.addItemDecoration(divider);
        mRcvRecoveryTypeList.addItemDecoration(new RecItemDecoration(getActivity(), R.color.color_22ffffff));


    }

    @Override
    protected void initDate() {
        mPresenter.getDeliveryData(null);
    }

    @Override
    public void setDeliveryDataSucceed(List<RecoveryTypeBean> active) {
        LogUtils.i(TAG, "active=" + active);
        mRecoveryTypeBeanList.clear();
        mRecoveryTypeBeanList.addAll(active);
        mRecoveryTypeAdapter = new RecoveryTypeAdapter(getActivity(), R.layout.recovery_type_item, mRecoveryTypeBeanList);
        mRcvRecoveryTypeList.setAdapter(mRecoveryTypeAdapter);
        mRecoveryTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                RecoveryTypeBean recoveryTypeBean=   mRecoveryTypeAdapter.getDatas().get(position);
                ((ContextFragment) getParentFragment()).startForResult(ArticleSettlementFragment.newInstance(recoveryTypeBean), REQ_MODIFY_FRAGMENT);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        LogUtils.i(TAG,"===========resultCode="+resultCode);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            String mTitle = data.getString(KEY_RESULT_TITLE);

            Toast.makeText(_mActivity, "返回参数 mTitle="+mTitle, Toast.LENGTH_SHORT).show();
        }
    }
}
