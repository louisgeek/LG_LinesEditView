package com.louisgeek.louismultilineedittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.louisgeek.multiedittextviewlib.MultiEditInputView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiEditInputView idmeiv = (MultiEditInputView) findViewById(R.id.id_meiv);
        idmeiv.setHintText("hintText");
        idmeiv.setContentText("ContentText");

        Log.i(TAG, "onCreate: getHintText"+idmeiv.getHintText());
        Log.i(TAG, "onCreate: getContentText"+idmeiv.getContentText());
    }
}
