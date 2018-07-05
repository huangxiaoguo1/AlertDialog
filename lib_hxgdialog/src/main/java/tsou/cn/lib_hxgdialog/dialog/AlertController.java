package tsou.cn.lib_hxgdialog.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/7/4 0004.
 * 数据存储
 */

class AlertController {
    private AlertDialog mDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public AlertController(AlertDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public void setViewHelper(DialogViewHelper viewHelper) {
        this.mViewHelper = viewHelper;

    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }

    /**
     * 设置点击
     *
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mViewHelper.setOnClickListener(viewId, listener);
    }

    public AlertDialog getDialog() {
        return mDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        //点击空白是否能够取消（默认取消）
        public boolean mCancelable = true;
        //dialog 取消监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        //dialog 消失监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        //dialog 按键监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        //布局
        public View mView;
        //布局layout 的id
        public int mViewLayoutResId;
        //存放字体的修改
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        //存放点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        //宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        //动画
        public int mAnimation = 0;
        public int mGravity = Gravity.CENTER;
        //高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

        public AlertParams(Context context, int themResId) {
            this.mContext = context;
            this.mThemeResId = themResId;
        }

        /**
         * 绑定和设置参数
         *
         * @param mAlert
         */
        public void apply(AlertController mAlert) {
            DialogViewHelper viewHelper = null;
            if (mViewLayoutResId != 0) {
                viewHelper = new DialogViewHelper(mContext, mViewLayoutResId);
            }
            if (mView != null) {
                viewHelper = new DialogViewHelper();
                viewHelper.setContentView(mView);
            }
            if (viewHelper == null) {
                throw new IllegalArgumentException("请先传入布局—>setContentView方法");
            }
            /**
             * 给dialog设置布局
             */
            mAlert.getDialog().setContentView(viewHelper.getContentView());

            for (int i = 0; i < mTextArray.size(); i++) {
                viewHelper.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }
            for (int i = 0; i < mClickArray.size(); i++) {
                viewHelper.setOnClickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }
            /**
             * 设置Controller辅助类
             */
            mAlert.setViewHelper(viewHelper);

            /**
             * 设置dialog样式
             */
            Window window = mAlert.getWindow();
            //设置位置
            window.setGravity(mGravity);
            //设置动画
            if (mAnimation != 0) {
                window.setWindowAnimations(mAnimation);
            }
            //设置宽高
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
        }
    }
}
