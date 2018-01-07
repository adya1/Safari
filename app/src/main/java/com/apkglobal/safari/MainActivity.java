package com.apkglobal.safari;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView tv_title;
    Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_title=(TextView)findViewById(R.id.tv_title);
        String path="font/pacifico.ttf";
        tf=Typeface.createFromAsset(getAssets(),path);
        tv_title.setTypeface(tf);
    }
}
