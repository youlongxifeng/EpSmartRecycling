package cn.epsmart.recycling.device.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.utils.CommonUtil;
import cn.epsmart.recycling.device.utils.UIUtils;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 11:17
 * @description: （RecyclerView分割线）
 */
public class RecItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private final Paint mPaint;
    private final Resources mRes;

    public RecItemDecoration(Context context) {
        mContext = context;
        mRes = mContext.getResources();
        mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mRes.getColor(R.color.color_22ffffff));
    }

    public RecItemDecoration(Context context,int color) {
        mContext = context;
        mRes = mContext.getResources();
        mPaint = new Paint();
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        mPaint.setColor(ContextCompat.getColor(mContext, color));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int count = parent.getChildCount();
        for (int i = 1; i < count; i++) {
            View view = parent.getChildAt(i);
            c.drawLine(parent.getPaddingLeft(), view.getTop(),
                    parent.getMeasuredWidth() - parent.getPaddingRight(), view.getTop(), mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = CommonUtil.getDimens(mContext, R.dimen.x_20);
        outRect.right = UIUtils.getResources().getDimensionPixelOffset(R.dimen.x_20);
        outRect.left = UIUtils.getResources().getDimensionPixelOffset(R.dimen.x_20);

    }
}

