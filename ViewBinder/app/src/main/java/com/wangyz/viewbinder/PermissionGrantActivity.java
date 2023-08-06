package com.wangyz.viewbinder;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.circle.permission.MPermissions;
import com.wangyz.annotation.permission.PermissionDenied;
import com.wangyz.annotation.permission.PermissionGrant;
import com.wangyz.annotation.permission.ShowRequestPermissionRationale;


public class PermissionGrantActivity extends AppCompatActivity {

    private Button mBtnSdcard, mBtnCallPhone;
    private static final int REQUECT_CODE_SDCARD = 2;
    private static final int REQUECT_CODE_CALL_PHONE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_grant);

        mBtnSdcard = (Button) findViewById(R.id.id_btn_sdcard);
        mBtnCallPhone = (Button) findViewById(R.id.id_btn_callphone);
        /**
         * ActivityCompat.shouldShowRequestPermissionRationale(activity,permission) 这个方法有点细节
         * 相当于一个查询方法,查询之前是否被拒绝过该权限的申请,同时是没有点击不再提示的(但是特别注意:用户没有点击以后再不提醒,否则再怎么样都无事于补了,onRequestPermissionsResult返回result为没授权)
         */
        mBtnSdcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** 这里引入shouldShowRequestPermissionRationale逻辑,只处理false的情况(表示还是个初,之前没有被拒绝过);
                 *  那拒绝过呢,但是又没有点击不再提示这种情况不处理了吗?别急,这个里面处理了后续继续申请的逻辑(eg whyNeedSdCard方法里就再次申请了权限)
                 * */
                if (!MPermissions.shouldShowRequestPermissionRationale(PermissionGrantActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUECT_CODE_SDCARD)) {
                    MPermissions.requestPermissions(PermissionGrantActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            }
        });

        mBtnCallPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MPermissions.requestPermissions(PermissionGrantActivity.this, REQUECT_CODE_CALL_PHONE, Manifest.permission.CALL_PHONE);
            }
        });
    }

    @ShowRequestPermissionRationale(REQUECT_CODE_SDCARD)
    public void whyNeedSdCard() {
        Toast.makeText(this, "I need write news to sdcard!", Toast.LENGTH_SHORT).show();
        MPermissions.requestPermissions(PermissionGrantActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess() {
        Toast.makeText(this, "GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed() {
        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }

    @PermissionGrant(REQUECT_CODE_CALL_PHONE)
    public void requestCallPhoneSuccess() {
        Toast.makeText(this, "GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_CALL_PHONE)
    public void requestCallPhoneFailed() {
        Toast.makeText(this, "DENY ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }


}
