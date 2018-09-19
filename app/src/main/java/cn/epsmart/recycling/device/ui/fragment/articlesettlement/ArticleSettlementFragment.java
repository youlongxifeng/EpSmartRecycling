package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import android.os.Bundle;
import android.view.View;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 19:17
 * @description: （物品结算界面）
 */
public class ArticleSettlementFragment extends BaseMvpFragment<ArticleSettlementPresenter>{

    public static ArticleSettlementFragment newInstance( ) {

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
}
