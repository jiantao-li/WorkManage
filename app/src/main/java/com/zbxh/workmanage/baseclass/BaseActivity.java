package com.zbxh.workmanage.baseclass;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ljt on 2017-08-15 09:26
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company: 北京中北信号软件有限公司
 * <p>
 * Description:
 */

public class BaseActivity extends AppCompatActivity {

    protected long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 为子类提供一个权限检查方法
     *
     * @param permissions permissions
     * @return boolean
     */
    public boolean HasPermission(String... permissions) {

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求方法
     *
     * @param code        code
     * @param permissions permissions
     */
    public void RequestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 回调
     *
     * @param requestCode  requestCode
     * @param permissions  permissions
     * @param grantResults 权限获取结果 0成功  -1失败
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        OnPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 申请权限完成后执行
     */
    public void OnPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }


}
