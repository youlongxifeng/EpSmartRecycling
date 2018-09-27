package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.company.project.android.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseApplication;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.entity.RecoveryProceedsBean;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.SettlementBean;
import cn.epsmart.recycling.device.entity.UserBean;
import cn.epsmart.recycling.device.entity.UserBeanDao;
import cn.epsmart.recycling.device.observer.MessageEvent;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;
import cn.epsmart.recycling.device.widget.LoadingDialog;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 19:17
 * @description: （物品结算界面）
 */
public class ArticleSettlementFragment extends BaseMvpFragment<ArticleSettlementPresenter> implements ArticleSettlementContract.View {
    private final static String TAG = ArticleSettlementFragment.class.getSimpleName();
    private final static String ARTICLETYPE = "article_type_parameter";
    /**
     * 结算
     */
    @BindView(R.id.bt_settlement)
    Button mBtnSettlement;
    /**
     * 投递其他回收物
     */
    @BindView(R.id.bt_delivery_other)
    Button mBtnDeliveryOther;
    @BindView(R.id.tx_product_type)
    TextView mProductType;
    @BindView(R.id.tx_product_weight)
    TextView mProductWeight;
    @BindView(R.id.tx_product_price)
    TextView mProductPrice;

    private  LoadingDialog mLoadingDialog;

    private RecoveryTypeBean mRecoveryTypeBean;

    private UserBeanDao userBeanDao;
    private UserBean userBean;
    /**
     * 物品重量
     */
    private String mWeightGoods;
    private String mRecoveryType;


    public static ArticleSettlementFragment newInstance(RecoveryTypeBean articleType) {
        Bundle args = new Bundle();
        args.putSerializable(ARTICLETYPE, articleType);
        ArticleSettlementFragment fragment = new ArticleSettlementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ArticleSettlementPresenter createPresenter() {
        return new ArticleSettlementPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_articlesettlement_main;
    }

    @Override
    protected View getRootView(ViewGroup container) {
        return mInflater.inflate(R.layout.fragment_articlesettlement_main, container, false);
    }


    @Override
    protected void initView(View view) {
        mRecoveryTypeBean = (RecoveryTypeBean) getArguments().getSerializable(ARTICLETYPE);
        if (mRecoveryTypeBean != null) {
            mRecoveryType=mRecoveryTypeBean.getmRecoveryType();
            mProductType.setText(mRecoveryTypeBean.getmRecoveryType());
            mProductWeight.setText("0");
            mProductPrice.setText("0");
            mPresenter.getHistoryWeightParameter(mRecoveryTypeBean);
        }
        LoadingDialog.Builder builder1=new LoadingDialog.Builder(getActivity())
                .setMessage(getString(R.string.loading_data))
                .setCancelable(false);
        mLoadingDialog=builder1.create();
    }

    @Override
    protected void initDate() {
        userBeanDao = BaseApplication.getContext().getDaoSession().getUserBeanDao();
        QueryBuilder<UserBean> queryBuilder = userBeanDao.queryBuilder();
        if (queryBuilder != null && queryBuilder.list().size() > 0) {
            userBean = queryBuilder.list().get(0);
        }


    }

    @OnClick({R.id.bt_settlement, R.id.bt_delivery_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_settlement://结算
                mBtnSettlement.setEnabled(false);
                if (mRecoveryTypeBean != null) {//上传物品重量获取当前产品收益
                    mPresenter.getCurrentArticleWeight(mRecoveryTypeBean);
                    mLoadingDialog.show();
                }
                break;
            case R.id.bt_delivery_other://返回继续投递
               /* MessageEvent<RecoveryTypeBean> messageEvent = new MessageEvent();
                messageEvent.setT(mRecoveryTypeBean);
                EventBus.getDefault().post(mRecoveryTypeBean);*/
                /*Bundle bundle = new Bundle();//当前环境已经可以反悔信息了
                bundle.putString(HomeFragment.KEY_RESULT_TITLE, "返回有用的数据");
                setFragmentResult(RESULT_OK, bundle);*/
                pop();
                break;
            default:
                break;
        }
    }


    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        hideSoftInput();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(TAG, "=======onDestroyView============");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "=======onDestroy============");
    }

    /**
     * 第一次获取产品历史重量成功
     * @param settlenent
     */
    @Override
    public void historyWeightSuccess(SettlementBean settlenent) {
        LogUtils.i(TAG, "strWeight===" + settlenent);
        mWeightGoods=settlenent.getWeight();
        mProductWeight.setText(mWeightGoods);//显示历史重量
        mProductPrice.setText(settlenent.getPrice());
    }

    /**
     * 第一次获取产品历史重量失败
     * @param str
     */
    @Override
    public void historyWeightFailed(String str) {

    }

    /**
     * 第二次获取新添加后的产品重量获取重量成功
     * @param settlementBean
     */
    @Override
    public void presentArticleWeightSuccess(SettlementBean settlementBean) {
        LogUtils.i(TAG, "ArticleWeightSuccess ===" + settlementBean);
        mProductType.setText(mRecoveryTypeBean.getmRecoveryType());
        mProductWeight.setText(settlementBean.getWeight() + getString(R.string.catty_name)); //获取当前新的重量
        mPresenter.updateWeight(settlementBean.getWeight(),String.valueOf(mWeightGoods),mRecoveryType);//数据获取成功后，需要上传当前重量到服务器，返回此次回收产生的收益
    }

    /**
     * 第二次获取新添加后的产品重量获取重量失败
     * @param str
     */
    @Override
    public void presentArticleWeightFailed(String str) {
        mBtnSettlement.setEnabled(true);
    }

    @Override
    public void updateProceedsSuccess(RecoveryProceedsBean proceedsBean) {
        LogUtils.i("updateProceedsSuccess str=" + proceedsBean);
        mProductPrice.setText(proceedsBean.getProceeds()+proceedsBean.getCompany());
        mLoadingDialog.dismiss();
        mBtnSettlement.setEnabled(true);
        mBtnSettlement.setVisibility(View.GONE);
        mBtnDeliveryOther.setVisibility(View.VISIBLE);
        EventBus.getDefault().post(proceedsBean);
    }

    @Override
    public void updateProceedsFailed(String str) {

    }
}
