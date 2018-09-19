package cn.epsmart.recycling.device.base;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.company.project.android.utils.LogUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.utils.UIUtils;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 10:06
 * @description: （添加一句描述）
 */

public abstract class BaseRecyclerViewAdapter<T> extends CommonAdapter<T> {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    protected final HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    protected final LoadMoreWrapper mLoadMoreWrapper;
    private final ProgressBar mPbLoading;
    private final TextView mLoadingText;
    private final Resources mRes;
    private RecyclerView mRecyclerView;
    private LoadMoreWrapper.OnLoadMoreListener mOnLoadMoreListener1;

    public BaseRecyclerViewAdapter(Context context, int layoutId, List<T> data) {
        this(context, layoutId, data, VERTICAL);
    }

    public BaseRecyclerViewAdapter(Context context, int layoutId, List<T> data, int orientation) {
        super(context, layoutId, data);
        mRes = context.getResources();
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(this);
        mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("invalid orientation:" + orientation);
        }
        View view = View.inflate(context, orientation == HORIZONTAL ? R.layout.default_loading_horizontal : R.layout.default_loading, null);
        mPbLoading = (ProgressBar) view.findViewById(R.id.pb_loading);
        mLoadingText = (TextView) view.findViewById(R.id.loading_text);
        mLoadMoreWrapper.setLoadMoreView(view);
    }

    public void setDatas(List<T> data) {
        if (data == null) {
            data = Collections.emptyList();
        }
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
        // UIUtils.postTaskSafely(this::notifyDataSetChanged);
    }

    public void addData(List<T> data) {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        mDatas.addAll(data);
    }

    public void remove(T data) {
        mDatas.remove(data);
    }

    public void clear() {
        if (mDatas != null) {
            mDatas.clear();
        }
    }

    public void remove(int pos) {
        mDatas.remove(pos);
    }

    /**
     * 获取上啦加载的adapter recyclerView.setAdapter()对象为此。
     *
     * @return
     */
    public LoadMoreWrapper getLoadMoreAdapter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0:
                        if (mCurState == STATE_EMPTY && mOnLoadMoreListener1 != null) {
                            resetLoadMoreState();
                        }
                        break;
                    case 1:
                        break;
                }
            }
        });

        return mLoadMoreWrapper;
    }

    /**
     * 上啦加载的监听
     *
     * @param onLoadMoreListener
     */
    public void setOnLoadMoreListener(final LoadMoreWrapper.OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener1 = new LoadMoreWrapper.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mCurState == STATE_EMPTY) {
                    return;
                }
                mPbLoading.setIndeterminateDrawable(ContextCompat.getDrawable(mContext, R.drawable.indicate));
                mPbLoading.setProgressDrawable(ContextCompat.getDrawable(mContext, R.drawable.indicate));
                mPbLoading.setVisibility(View.VISIBLE);
                mLoadingText.setText(mRes.getString(R.string.loading_data));
                mLoadingText.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                onLoadMoreListener.onLoadMoreRequested();
                mCurState = STATE_LOADING;
            }
        };
        mLoadMoreWrapper.setOnLoadMoreListener(mOnLoadMoreListener1);

    }


    /**
     * 上啦加载失败的界面
     */
    public synchronized void loadMoreDataFailure() {
        mPbLoading.setProgressDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        mPbLoading.setIndeterminateDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        mLoadingText.setText("加载数据失败");
        resetLoadMoreState();
        mCurState = STATE_ERROR;
    }

    /**
     * 加载数据为空时
     */
    public synchronized void loadMoreDataEmpty() {
        mPbLoading.setIndeterminateDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        mPbLoading.setProgressDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        mLoadingText.setText("已经到底啦");
        mPbLoading.setVisibility(View.GONE);
        mLoadingText.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        mLoadMoreWrapper.setOnLoadMoreListener(null);
        resetLoadMoreState();
        mCurState = STATE_EMPTY;
    }

    /**
     * 上啦加载成功的界面
     */
    public synchronized void loadMoreDataSucceed() {
        mPbLoading.setIndeterminateDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        mPbLoading.setProgressDrawable(ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher));
        mLoadingText.setText("加载数据成功");
        resetLoadMoreState();
        mCurState = STATE_SUCCESS;
    }

    /**
     * 添加头尾的item
     *
     * @return
     */
    public HeaderAndFooterWrapper getHeaderAndFooterWrapper() {

        return mHeaderAndFooterWrapper;
    }

    /**
     * 数据更新
     */
    public void notifyDataChanged() {
        UIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                mLoadMoreWrapper.notifyDataSetChanged();
                notifyDataSetChanged();
                mCurState = STATE_SUCCESS;
            }
        });
    }

    public void notifyRangeChanged(final int positionStart, final int itemCount) {
        UIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                mLoadMoreWrapper.notifyDataSetChanged();
                notifyItemRangeChanged(positionStart, itemCount);
                mCurState = STATE_SUCCESS;
            }
        });
    }


    /**
     * 获取position对应的view
     *
     * @param index
     * @return
     */
    private View getView(RecyclerView recyclerView, int index) {
        int count = recyclerView.getChildCount();
        int i = 0;
        for (int x = 0; x < count; x++) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(x));
            if (childAdapterPosition == index) {
                i = x;
                break;
            }
        }
        return recyclerView.getChildAt(i);
    }

    /**
     * 重置上拉加载状态
     */
    private synchronized void resetLoadMoreState() {
        if (true || ANIMATOR_STATE != STATE__IDLE) {//去掉回弹
            return;
        }

        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        int itemCount = layoutManager.getItemCount();
        if (lastVisibleItemPosition == itemCount - 1) {
            ANIMATOR_STATE = STATE_FOOTER;
            //            View view = getView(mRecyclerView, lastVisibleItemPosition);
            View view = mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1);
            if (view != null) {
                int measuredHeight = mRecyclerView.getMeasuredHeight() - mRecyclerView.getPaddingTop()
                        - mRecyclerView.getPaddingBottom();
                int bottom = view.getBottom();
                int viewMeasuredHeight = view.getMeasuredHeight();
                final int offset = viewMeasuredHeight - (bottom - measuredHeight);
                LogUtils.i("Tag", "measuredHeight:" + measuredHeight + "  top:" + view.getTop() + " bottom:" + view.getBottom() + "  viewMeasuredHeight:" + viewMeasuredHeight);
                UIUtils.getMainThreadHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doFooterAnimator(0, -(offset + 5), mRecyclerView);
                    }
                }, 400);
            }
        }
    }

    public static final int STATE_NONE = -1;            // 默认状态
    public static final int STATE_LOADING = 0;            // 加载中
    public static final int STATE_ERROR = 1;            // 错误
    public static final int STATE_EMPTY = 2;            // 空
    public static final int STATE_SUCCESS = 3;            // 成功

    protected int mCurState = STATE_NONE;

    private int ANIMATOR_STATE = STATE__IDLE;
    private static int STATE_FOOTER = 1;//上拉加载
    private static int STATE__IDLE = 0;//默认状态


    private void doFooterAnimator(int start, int end, final View view) {
        ValueAnimator.ofInt(1, 2);
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(300);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ANIMATOR_STATE = STATE__IDLE;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            int temp;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 每次数据改变的回调
                int value = (Integer) animation.getAnimatedValue();
                mRecyclerView.scrollBy(0, value - temp);
                temp = value;
            }
        });
        animator.start();
    }
}
