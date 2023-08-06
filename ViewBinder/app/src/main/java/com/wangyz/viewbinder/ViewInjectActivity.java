package com.wangyz.viewbinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wangyz.annotation.view.BindView;
import com.wangyz.binder.ViewBinder;


public class ViewInjectActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////调用自己定义的ViewBinder
        ViewBinder.bind(this);
        tv.setText("Hi,ViewBinder!");
    }
}
