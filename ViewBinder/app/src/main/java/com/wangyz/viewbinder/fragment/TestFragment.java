package com.wangyz.viewbinder.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.circle.permission.MPermissions;
import com.wangyz.annotation.permission.PermissionDenied;
import com.wangyz.annotation.permission.PermissionGrant;
import com.wangyz.viewbinder.R;

/**
 * Created by zhy on 16/2/21.
 */
public class TestFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        view.findViewById(R.id.id_btn_contact).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MPermissions.requestPermissions(TestFragment.this, 4, Manifest.permission.WRITE_CONTACTS);
            }
        });
    }

    @PermissionGrant(4)
    public void requestContactSuccess()
    {
        Toast.makeText(getActivity(), "GRANT ACCESS CONTACTS!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(4)
    public void requestContactFailed()
    {
        Toast.makeText(getActivity(), "DENY ACCESS CONTACTS!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        /**
         * 同时也是MPermissions框架也接管权限申请后的结果,内部会调用到指定的方法
         */
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
