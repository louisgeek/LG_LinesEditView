package com.louisgeek.multiedittextviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ignoreCnOrEn 为false的时候
 * 1个中文算1个
 * 2个英文算1个
 * <p>
 * 另外：如：只有一个英文时也算1个
 * <p>
 * Created by louisgeek on 2016/9/19.
 */
public class MultiEditInputView extends LinearLayout {
    private Context mContext;
    private EditText id_et_input;
    private TextView id_tv_input;
    private static final int DEFAULT_MAX_COUNT = 240;
    private static final int DEFAULT_CONTENT_HEIGHT = 140;

    private int MAX_COUNT;
    private String hintText;
    private boolean ignoreCnOrEn;
    private String contentText;
    private float contentHeight;

    LinearLayout id_ll_multi;

    public MultiEditInputView(Context context) {
        this(context, null);
    }

    public MultiEditInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiEditInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MultiEditInputView);
        MAX_COUNT = typedArray.getInteger(R.styleable.MultiEditInputView_maxCount, DEFAULT_MAX_COUNT);
        ignoreCnOrEn = typedArray.getBoolean(R.styleable.MultiEditInputView_IgnoreCnOrEn, true);
        hintText = typedArray.getString(R.styleable.MultiEditInputView_hintText);
        contentText = typedArray.getString(R.styleable.MultiEditInputView_contentText);
        contentHeight = typedArray.getDimension(R.styleable.MultiEditInputView_contentHeight, DEFAULT_CONTENT_HEIGHT);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_multi_edit_input, this);
        id_ll_multi = (LinearLayout) view.findViewById(R.id.id_ll_multi);
        id_ll_multi.setBackgroundResource(R.drawable.selector_edittext_multi);
        id_et_input = (EditText) view.findViewById(R.id.id_et_input);
        id_tv_input = (TextView) view.findViewById(R.id.id_tv_input);

        id_et_input.addTextChangedListener(mTextWatcher);
        id_et_input.setHint(hintText);
        id_et_input.setText(contentText);
        id_et_input.setHeight((int) contentHeight);
        /**
         * 配合 id_tv_input xml的 android:focusable="true"
         android:focusableInTouchMode="true"

         在id_et_input设置完文本后

         不给id_et_input 焦点
         */
        id_tv_input.requestFocus();
        //init
        configCount();
        id_et_input.setSelection(id_et_input.length()); // 将光标移动最后一个字符后面
        /**
         * focus后给背景设置Selected
         */
        id_et_input.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                id_ll_multi.setSelected(b);
            }
        });
    }

    private TextWatcher mTextWatcher = new TextWatcher() {

        private int editStart;

        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            editStart = id_et_input.getSelectionStart();
            editEnd = id_et_input.getSelectionEnd();

            // 先去掉监听器，否则会出现栈溢出
            id_et_input.removeTextChangedListener(mTextWatcher);

            if (ignoreCnOrEn) {
                //当输入字符个数超过限制的大小时，进行截断操作
                while (calculateLengthIgnoreCnOrEn(editable.toString()) > MAX_COUNT) {
                    editable.delete(editStart - 1, editEnd);
                    editStart--;
                    editEnd--;
                }
            } else {
                // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
                while (calculateLength(editable.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
                    editable.delete(editStart - 1, editEnd);
                    editStart--;
                    editEnd--;
                }
            }

            id_et_input.setSelection(editStart);

            // 恢复监听器
            id_et_input.addTextChangedListener(mTextWatcher);

            //update
            configCount();
        }
    };


    private long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }

    private int calculateLengthIgnoreCnOrEn(CharSequence c) {
        int len = 0;
        for (int i = 0; i < c.length(); i++) {
            len++;
        }
        return len;
    }

    private void configCount() {
        if (ignoreCnOrEn) {
            int nowCount = calculateLengthIgnoreCnOrEn(id_et_input.getText().toString());
            //
            id_tv_input.setText(String.valueOf((MAX_COUNT - nowCount)) + "/" + MAX_COUNT);
        } else {
            long nowCount = calculateLength(id_et_input.getText().toString());
            //
            id_tv_input.setText(String.valueOf((MAX_COUNT - nowCount)) + "/" + MAX_COUNT);
        }

    }

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public void setContentText(String content) {
        contentText = content;
        if (id_et_input == null) {
            return;
        }
        id_et_input.setText(contentText);
    }

    public String getContentText() {
        if (id_et_input != null) {
            contentText = id_et_input.getText() == null ? "" : id_et_input.getText().toString();
        }
        return contentText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
        id_et_input.setHint(hintText);
    }

    public String getHintText() {
        if (id_et_input != null) {
            hintText = id_et_input.getHint() == null ? "" : id_et_input.getHint().toString();
        }
        return hintText;
    }
}
