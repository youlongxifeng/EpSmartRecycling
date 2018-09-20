package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.company.project.android.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.SettlementBean;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;

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

    private RecoveryTypeBean mRecoveryTypeBean;

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
    protected void initView(View view) {
        mRecoveryTypeBean = (RecoveryTypeBean) getArguments().getSerializable(ARTICLETYPE);
        if (mRecoveryTypeBean != null) {
            mProductType.setText(mRecoveryTypeBean.getmRecoveryType());
            mProductWeight.setText("0");
            mProductPrice.setText("0");
            mPresenter.getInitWeightParameter(mRecoveryTypeBean);
        }
    }

    @Override
    protected void initDate() {

    }

    @OnClick({R.id.bt_settlement, R.id.bt_delivery_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_settlement:
              /*  Bundle bundle = new Bundle();
                bundle.putString(HomeFragment.KEY_RESULT_TITLE, "返回有用的数据");
                setFragmentResult(RESULT_OK, bundle); */
                LogUtils.i(TAG, "===================");

                if (mRecoveryTypeBean != null) {
                    mPresenter.getCurrentArticleWeight(mRecoveryTypeBean);
                }
                break;
            case R.id.bt_delivery_other:
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


    @Override
    public void InitWeightSuccess(String str) {
       /* mProductType.setText(str);
        mProductWeight.setText(str);
        mProductPrice.setText(str);*/
    }

    @Override
    public void InitWeightFailed(String str) {

    }

    /**
     * 获取重量成功
     * @param settlementBean
     */
    @Override
    public void ArticleWeightSuccess(SettlementBean settlementBean) {
        LogUtils.i(TAG, "ArticleWeightSuccess ===" + settlementBean);
        mProductType.setText(mRecoveryTypeBean.getmRecoveryType());
        mProductWeight.setText(settlementBean.getWeight() + getString(R.string.catty_name));
        mProductPrice.setText(settlementBean.getPrice() + getString(R.string.element_name));
        mPresenter.updateProceeds();
    }

    @Override
    public void ArticleWeightFailed(String str) {

    }

    @Override
    public void updateProceedsSuccess(String str) {
        LogUtils.i("updateProceedsSuccess str=" + str);
        mBtnSettlement.setVisibility(View.GONE);
        mBtnDeliveryOther.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateProceedsFailed(String str) {

    }
}
