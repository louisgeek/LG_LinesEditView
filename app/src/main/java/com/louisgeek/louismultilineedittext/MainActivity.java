package com.louisgeek.louismultilineedittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 1个中文算1个
 * 2个英文算1个
 *
 * 另外：如：只有一个英文时也算1个
 */
public class MainActivity extends AppCompatActivity {

    private static final int MAX_COUNT = 240;
    EditText id_et_input;
    TextView id_tv_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_tv_input = (TextView) findViewById(R.id.id_tv_input);
        id_et_input= (EditText) findViewById(R.id.id_et_input);

        id_et_input.addTextChangedListener(mTextWatcher);

        //init
        configCount();
        id_et_input.setSelection(id_et_input.length()); // 将光标移动最后一个字符后面
    }

    private void configCount() {
        long nowCount=calculateLength(id_et_input.getText().toString());
        //
        id_tv_input.setText(String.valueOf((MAX_COUNT - nowCount))+"/"+MAX_COUNT);
    }


    private TextWatcher mTextWatcher =new TextWatcher() {

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

            // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
            while (calculateLength(editable.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
                editable.delete(editStart - 1, editEnd);
                editStart--;
                editEnd--;
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


}
