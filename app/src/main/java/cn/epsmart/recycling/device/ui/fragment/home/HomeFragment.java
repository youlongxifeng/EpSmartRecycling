package cn.epsmart.recycling.device.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.company.project.android.utils.LogUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.entity.RecoveryProceedsBean;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.UserBeanDao;
import cn.epsmart.recycling.device.observer.SmartEvents;
import cn.epsmart.recycling.device.ui.fragment.articlesettlement.ArticleSettlementFragment;
import cn.epsmart.recycling.device.ui.fragment.home.adapter.RecoveryTypeAdapter;
import cn.epsmart.recycling.device.ui.fragment.home.adapter.TotalRriceAdapter;
import cn.epsmart.recycling.device.widget.CustomVideoView;
import cn.epsmart.recycling.device.widget.LoadContentView;
import cn.epsmart.recycling.device.widget.LoadControlView;
import cn.epsmart.recycling.device.widget.RecItemDecoration;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:04
 * @description: （显示首页内容）
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View ,LoadControlView.OnLoadClickListener {
    private final static String TAG = HomeFragment.class.getSimpleName();
    private static final int REQ_MODIFY_FRAGMENT = 100;
    private static final String ARG_TITLE = "arg_title";
    public static final String KEY_RESULT_TITLE = "title";
    @BindView(R.id.bt_exit_logon)
    Button mExitLogon;
    @BindView(R.id.rcv_recovery_type_list)
    RecyclerView mRcvRecoveryTypeList;
    @BindView(R.id.tx_total_price)
    TextView mTotalPrice;
    @BindView(R.id.home_content_view)
    LoadControlView mLoadContentView;
    /**
     * 回收类型适配器
     */
    private RecoveryTypeAdapter mRecoveryTypeAdapter;
    /**
     * 收益总额
     */
    private double mTotalPriceValue;
    private List<RecoveryTypeBean> mRecoveryTypeBeanList;
    /**
     * 收益明细
     */
    private List<RecoveryProceedsBean> mTotalRriceBeanList = new ArrayList<>();
    private UserBeanDao userBeanDao;

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
    protected View getRootView(ViewGroup container) {
        return mInflater.inflate(R.layout.fragment_home_main, container, false);
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
        mLoadContentView.showLoadView();
        mLoadContentView.setLoadOnClickListener(this);
        SmartEvents.register(this);
        mPresenter.getDeliveryData(null);
    }

    @OnClick({R.id.bt_exit_logon})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.bt_exit_logon:
                mPresenter.exitLogon();
                break;
            default:
                break;
        }
    }
    @Override
    public void OnLoadClick(View view) {
        mPresenter.getDeliveryData(null);
    }


    @Override
    public void exitLogon() {
        _mActivity.finish();
    }

    @Override
    public void setDeliveryDataSucceed(List<RecoveryTypeBean> active) {
        mLoadContentView.showSucceedView();
        LogUtils.i(TAG, "active=" + active);
        mRecoveryTypeBeanList.clear();
        mRecoveryTypeBeanList.addAll(active);
        mRecoveryTypeAdapter = new RecoveryTypeAdapter(this, getActivity(), R.layout.recovery_type_item, mRecoveryTypeBeanList);
        mRcvRecoveryTypeList.setAdapter(mRecoveryTypeAdapter);
        mRecoveryTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                RecoveryTypeBean recoveryTypeBean = mRecoveryTypeAdapter.getDatas().get(position);
                startForResult(ArticleSettlementFragment.newInstance(recoveryTypeBean), REQ_MODIFY_FRAGMENT);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void setDeliveryDataFail(String fail) {
        mLoadContentView.showErrorView();
         }




    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        LogUtils.i(TAG, "===========resultCode=" + resultCode+"  requestCode="+requestCode);
        if (requestCode == REQ_MODIFY_FRAGMENT && resultCode == RESULT_OK && data != null) {
            String mTitle = data.getString(KEY_RESULT_TITLE);
            Toast.makeText(_mActivity, "返回参数 mTitle=" + mTitle, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SmartEvents.unRegister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RecoveryProceedsBean event) {
        updateTotalPrice(event);
        mTotalRriceBeanList.add(event);
        LogUtils.i(TAG, " mTotalRriceBeanList=" + mTotalRriceBeanList.size() + "  event=" + event);
    }

    /**
     * 更新总价格
     */
    private void updateTotalPrice(RecoveryProceedsBean recoveryTypeBean) {
        LogUtils.i(TAG,"recoveryTypeBean=="+recoveryTypeBean);
        mTotalPriceValue +=Integer.valueOf(recoveryTypeBean.getProceeds());
        mTotalPrice.setText(mTotalPriceValue + getString(R.string.element_name));
    }



}
