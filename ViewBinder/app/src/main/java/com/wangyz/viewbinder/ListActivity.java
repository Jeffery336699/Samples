package com.wangyz.viewbinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class ListActivity extends android.app.ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Arrays.asList(
                "View注入",
                "Permission申请"
        )));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Class<? extends Activity> clazz = null;
        if (position == 0) {
            clazz = ViewInjectActivity.class;
        } else if (position == 1) {
            clazz = PermissionGrantActivity.class;
        }
        startActivity(new Intent(this, clazz));
    }
}