package com.zbxh.workmanage.basetools;


import android.content.Context;
import android.widget.Toast;

/**
 * Created by ljt on 2017-08-15 10:37
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company: 北京中北信号软件有限公司
 * <p>
 * Description:Toast
 */

public class Mtoast {

    private static Toast toast = null;

    public static void show(Context context, String msg) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }
}
