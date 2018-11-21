package com.zbxh.workmanage.basetools;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by jiantaoli on 2017/11/30 15:07 .
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company: 北京中北信号软件有限公司
 * <p>
 * Description:
 */

public class Sdcard {

    /***
     * 获取sd目录
     **/
    public static String getPath() {

        String sdDir = null;
        // 判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            // 获取根目录
            sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            File file = Environment.getExternalStoragePublicDirectory(Context.AUDIO_SERVICE);
            String str = file.getParent();
            file = new File(str);
            if (file.exists()) {
                if (file.canWrite() && file.canRead()) {
                    return file.toString();
                }
            }
            file = new File("/mnt/sdcard-ext"); // 中兴手机情况
            if (file.exists()) {
                if (file.canWrite() && file.canRead()) {
                    return file.toString();
                }
            }
        }
        String sRet = null;
        if (sdDir != null) {
            sRet = sdDir;
        }
        if (sRet == null) sRet = "";
        if (sRet.lastIndexOf("/") != sRet.length()) {
            sRet += "/";
        }
        return sRet;
    }
}
