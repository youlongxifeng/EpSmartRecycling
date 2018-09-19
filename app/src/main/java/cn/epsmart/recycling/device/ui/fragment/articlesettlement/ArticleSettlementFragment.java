package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.project.android.utils.LogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 19:17
 * @description: （物品结算界面）
 */
public class ArticleSettlementFragment extends BaseMvpFragment<ArticleSettlementPresenter> {
    private final static String TAG = ArticleSettlementFragment.class.getSimpleName();
    /**
     * 结算
     */
    @BindView(R.id.bt_settlement)
    Button mBtnSettlement;

    public static ArticleSettlementFragment newInstance() {
        Bundle args = new Bundle();
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

    }

    @Override
    protected void initDate() {

    }

    @OnClick({R.id.bt_settlement})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_settlement:
                Bundle bundle = new Bundle();
                bundle.putString(HomeFragment.KEY_RESULT_TITLE, "返回有用的数据");
                setFragmentResult(RESULT_OK, bundle);
              //  pop();
                LogUtils.i(TAG,"===================");
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
        LogUtils.i(TAG,"=======onDestroyView============");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG,"=======onDestroy============");
    }


}
