package com.zbxh.workmanage.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.zbxh.workmanage.R;
import com.zbxh.workmanage.baseclass.BaseActivity;
import com.zbxh.workmanage.basetools.Mtoast;
import com.zbxh.workmanage.workpage.activity.WorkActivity;

import org.w3c.dom.Text;

import static com.zbxh.workmanage.baseclass.ZTAG.SPreferName;

/**
 * Created by ljt on 2018-11-14 21:53.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:
 * <p>
 * UpdateLog:
 */

public class LoginActivity extends BaseActivity {


    private Button btn_login = null;
    private EditText edt_user, edt_psw;
    private String user, psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.login_btn);
        edt_user = findViewById(R.id.login_edt_user);
        edt_psw = findViewById(R.id.login_edt_psw);

        edt_user.addTextChangedListener(EditChangeListener);
        edt_psw.addTextChangedListener(EditChangeListener);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //login

                //隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                onLoad();
            }
        });


        //获取上次登录的账号
        SharedPreferences sp = getSharedPreferences(SPreferName, Context.MODE_PRIVATE); // 私有数据
        String s_user = sp.getString("s_user", "");
        if (!TextUtils.isEmpty(s_user)) {
            //auto login
            edt_user.setText(s_user);
            // edt_psw.setText("000000");
            //btn_login.callOnClick();
        } else {
            edt_user.setText("");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if (((System.currentTimeMillis() - exitTime) > 2000)) {
                Mtoast.show(this, "再按一次退出系统");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.gc();
            }
            return true;
        }
        return false;
    }

    private void onLoad() {

        final ProgressDialog dialog = ProgressDialog.show(LoginActivity.this, "请稍后", "正在登陆....");
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String[] name = {"", ""};
                LoginQuerData mQuer = new LoginQuerData();
                int bRet = mQuer.login(user, psw, name);
                final String pname = name[0];
                final String tel = name[1];
                //  bRet = 1;
                switch (bRet) {
                    case -1:
                        dialog.dismiss();
                        btn_login.post(new Runnable() {
                            @Override
                            public void run() {
                                Mtoast.show(LoginActivity.this, "登录失败");
                            }
                        });
                        break;

                    case 0:
                        dialog.dismiss();
                        btn_login.post(new Runnable() {
                            @Override
                            public void run() {
                                Mtoast.show(LoginActivity.this, "用户名或密码错误");
                            }
                        });
                        break;

                    case 1:
                        dialog.dismiss();
                        btn_login.post(new Runnable() {
                            @Override
                            public void run() {
                                Mtoast.show(LoginActivity.this, "登录成功");

                                //保存用户名
                                SharedPreferences sp = getSharedPreferences(SPreferName, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("s_user", user);
                                editor.apply();//  提交修改


                                //打开主页面
                                OpenMainActivity(pname, tel);
                            }
                        });
                        break;

                }
            }
        }).start();
    }

    private void OpenMainActivity(String name, String tel) {

        try {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, WorkActivity.class);
            intent.putExtra("user_name", name);
            intent.putExtra("user_tel", tel);
            startActivity(intent);

            LoginActivity.this.finish();
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private TextWatcher EditChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            setButtonState();
        }
    };


    private void setButtonState() {

        user = edt_user.getText().toString().trim();
        psw = edt_psw.getText().toString().trim();

        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(psw)) {

            btn_login.setEnabled(true);
        } else {
            btn_login.setEnabled(false);
        }
    }

}
