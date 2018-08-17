package tsou.cn.lib_hxgdialog.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/7/4 0004.
 * 辅助类
 */

class DialogViewHelper {

    private View mContentView = null;
    //防止侧漏
    private SparseArray<WeakReference<View>> mViews;

    public DialogViewHelper(Context mContext, int layoutResId) {
        this();
        mContentView = LayoutInflater.from(mContext).inflate(layoutResId, null);
    }

    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        //减少findviewbyid的次数
        TextView tv = getView(viewId);
        if (tv != null) {
            tv.setText(text);
        }
    }

    /**
     * 显示或隐藏view
     *
     * @param viewId
     * @param aBoolean
     */
    public void setShowText(int viewId, Boolean aBoolean) {
        View view = getView(viewId);
        if (view != null) {
            if (aBoolean) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    public <T extends View> T getView(int viewId) {
        WeakReference<View> weakReference = mViews.get(viewId);
        View view = null;
        if (weakReference != null) {
            view = weakReference.get();
        }
        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null)
                mViews.put(viewId, new WeakReference<View>(view));
        }
        return (T) view;
    }

    /**
     * 设置点击
     *
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 返回布局
     *
     * @return
     */
    public View getContentView() {
        return mContentView;
    }


}
