package com.wjx.travelwithm_master.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wjx.travelwithm_master.R;

public class IdentifyingCodeView extends RelativeLayout {

    private LinearLayout containerEt;

    private EditText et;
    // 输入框数量
    private int mEtNumber;
    // 输入框的宽度
    private int mEtWidth;
    private int mEtHeight;
    //输入框分割线
    private Drawable mEtDividerDrawable;
    //输入框文字颜色
    private int mEtTextColor;
    //输入框文字大小
    private float mEtTextSize;
    //输入框获取焦点时背景
    private int mEtBackgroundDrawableFocus;
    //输入完成点击完成时的背景
    private int mEtBackgroundEnter = R.drawable.shape_icv_et_bg_enter;

    // 输入框没有焦点时背景
    private int mEtBackgroundDrawableNormal;
    //存储TextView的数据 数量由自定义控件的属性传入
    private TextView[] mTextViews;
    /**
     * edittext 软键盘监听
     * actionDone 完成 对应 EditorInfo.IME_ACTION_DONE
     * actionGo 前进 对应 EditorInfo.IME_ACTION_GO
     * actionNext 下一项 对应 EditorInfo.IME_ACTION_NEXT
     * actionNone 无动作 对应 EditorInfo.IME_ACTION_NONE
     * actionPrevious 上一项 对应 EditorInfo.IME_ACTION_PREVIOUS
     * actionSearch 搜索 对应 EditorInfo.IME_ACTION_SEARCH
     * actionUnspecified 未指定 对应 EditorInfo.IME_ACTION_UNSPECIFIED
     * actionSend 发送 对应 EditorInfo.IME_ACTION_SEND
     */
    private OnEditorActionListener actionListener;


    public IdentifyingCodeView(Context context) {
        this(context, null);
    }

    public IdentifyingCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdentifyingCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化 布局和属性
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.layout_identifying_code, this);
        containerEt = (LinearLayout) this.findViewById(R.id.container_et);
        et = (EditText) this.findViewById(R.id.et);
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return actionListener.onEditorAction(v, actionId, event);
            }
        });

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IdentifyingCodeView, defStyleAttr, 0);
        mEtNumber = typedArray.getInteger(R.styleable.IdentifyingCodeView_icv_et_number, 1);
        mEtWidth = typedArray.getDimensionPixelSize(R.styleable.IdentifyingCodeView_icv_et_width, 42);
        mEtHeight = typedArray.getDimensionPixelSize(R.styleable.IdentifyingCodeView_icv_et_height, 42);
        mEtDividerDrawable = typedArray.getDrawable(R.styleable.IdentifyingCodeView_icv_et_divider_drawable);
        mEtTextSize = typedArray.getDimensionPixelSize(R.styleable.IdentifyingCodeView_icv_et_text_size, 16);
        mEtTextColor = typedArray.getColor(R.styleable.IdentifyingCodeView_icv_et_text_color, Color.WHITE);
        mEtBackgroundDrawableFocus = typedArray.getResourceId(R.styleable.IdentifyingCodeView_icv_et_bg_focus, R.drawable.shape_icv_et_bg_normal);
        mEtBackgroundDrawableNormal = typedArray.getResourceId(R.styleable.IdentifyingCodeView_icv_et_bg_normal, R.drawable.shape_icv_et_bg_normal);
        //释放资源
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initTextViews(getContext(), mEtNumber, mEtWidth, mEtHeight, mEtDividerDrawable, mEtTextSize, mEtTextColor);
        initEtContainer(mTextViews);
        setListener();
    }


    /**
     * 初始化TextView
     * @param context
     * @param etNumber
     * @param etWidth
     * @param etHeight
     * @param etDividerDrawable
     * @param etTextSize
     * @param etTextColor
     */
    private void initTextViews(Context context, int etNumber, int etWidth, int etHeight, Drawable etDividerDrawable, float etTextSize, int etTextColor) {
        // 设置 editText 的输入长度
        et.setCursorVisible(false);//将光标隐藏
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(etNumber)}); //最大输入长度
        // 设置分割线的宽度
        if (etDividerDrawable != null) {
            etDividerDrawable.setBounds(0, 0, etDividerDrawable.getMinimumWidth(), etDividerDrawable.getMinimumHeight());
            containerEt.setDividerDrawable(etDividerDrawable);
        }
        mTextViews = new TextView[etNumber];
        for (int i = 0; i < mTextViews.length; i++) {
            TextView textView = new TextView(context);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, etTextSize);
            textView.setTextColor(etTextColor);
            textView.setWidth(etWidth);
            textView.setHeight(etHeight);
            if (i == 0) {
                textView.setBackgroundResource(mEtBackgroundDrawableFocus);
            } else {
                textView.setBackgroundResource(mEtBackgroundDrawableNormal);
            }
            textView.setGravity(Gravity.CENTER);

            textView.setFocusable(false);

            mTextViews[i] = textView;
        }
    }

    /**
     * 初始化存储TextView 的容器
     * @param mTextViews
     */
    private void initEtContainer(TextView[] mTextViews) {
        for (int i = 0; i < mTextViews.length; i++) {
            containerEt.addView(mTextViews[i]);
        }
    }


    private void setListener() {

        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (actionListener != null) {
                    actionListener.onTextChanged(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputStr = editable.toString();
                if (inputStr != null && !inputStr.equals("")) {
                    setText(inputStr);
                    et.setText("");
                }
            }
        });

        /**
         * 监听删除按键
         */
        et.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    onKeyDelete();
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * 给TextView 设置文字
     * @param inputContent
     */
    public void setText(String inputContent) {
        for (int i = 0; i < mTextViews.length; i++) {
            TextView tv = mTextViews[i];
            if (tv.getText().toString().trim().equals("")) {
                tv.setText(inputContent);
                // 添加输入完成的监听
                if (inputCompleteListener != null) {
                    inputCompleteListener.inputComplete();
                }
                tv.setBackgroundResource(mEtBackgroundDrawableNormal);
                if (i < mEtNumber - 1) {
                    mTextViews[i + 1].setBackgroundResource(mEtBackgroundDrawableFocus);
                }
                break;
            }
        }
    }

    /**
     * 监听删除
     */
    public void onKeyDelete() {
        for (int i = mTextViews.length - 1; i >= 0; i--) {
            TextView tv = mTextViews[i];
            if (!tv.getText().toString().trim().equals("")) {
                tv.setText("");
                // 添加删除完成监听
                if (inputCompleteListener != null) {
                    inputCompleteListener.deleteContent();
                }
                tv.setBackgroundResource(mEtBackgroundDrawableFocus);
                if (i < mEtNumber - 1) {
                    mTextViews[i + 1].setBackgroundResource(mEtBackgroundDrawableNormal);
                }
                break;
            }
        }
    }


    /**
     * 获取输入文本
     *
     * @return
     */
    public String getTextContent() {
        StringBuffer buffer = new StringBuffer();
        for (TextView tv : mTextViews) {
            buffer.append(tv.getText().toString().trim());
        }
        return buffer.toString();
    }

    /**
     * 删除所有内容
     */
    public void clearAllText() {
        for (int i = 0; i < mTextViews.length; i++) {
            if (i == 0) {
                mTextViews[i].setBackgroundResource(mEtBackgroundDrawableFocus);
            } else {
                mTextViews[i].setBackgroundResource(mEtBackgroundDrawableNormal);
            }
            mTextViews[i].setText("");
        }
    }


    /**
     * 获取输入的位数
     *
     * @return
     */
    public int getTextCount() {
        return mEtNumber;
    }

    /**
     * 输入完成 和 删除成功 的监听
     */
    private InputCompleteListener inputCompleteListener;

    public void setInputCompleteListener(InputCompleteListener inputCompleteListener) {
        this.inputCompleteListener = inputCompleteListener;
    }

    public interface InputCompleteListener {
        void inputComplete();

        void deleteContent();
    }

    public interface OnEditorActionListener {
        boolean onEditorAction(TextView v, int actionId, KeyEvent event);

        void onTextChanged(String s);
    }

    public void setOnEditorActionListener(OnEditorActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public void setEditable(boolean b) {
        this.et.setFocusable(b);
        this.et.setFocusableInTouchMode(b);
    }

    /**
     * 设置背景状态
     * @param b
     */
    public void setBackgroundEnter(boolean b) {
        if (b) {
            for (int i = 0; i < mTextViews.length; i++) {
                mTextViews[i].setBackgroundResource(mEtBackgroundEnter);
            }
        } else {
            boolean flag = false;
            for (int i = 0; i < mTextViews.length; i++) {
                String trim = mTextViews[i].getText().toString().trim();
                if (!TextUtils.isEmpty(trim)) {
                    mTextViews[i].setBackgroundResource(mEtBackgroundDrawableNormal);
                } else {
                    if (!flag) {
                        mTextViews[i].setBackgroundResource(mEtBackgroundDrawableFocus);
                        flag = true;
                    } else {
                        mTextViews[i].setBackgroundResource(mEtBackgroundDrawableNormal);
                    }
                }
            }
        }
    }
}