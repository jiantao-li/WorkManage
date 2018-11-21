package com.zbxh.workmanage.login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zbxh.workmanage.baseclass.BaseWebService;
import com.zbxh.workmanage.basetools.Log;
import com.zbxh.workmanage.basetools.Sdcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ljt on 2018-11-15 10:56.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:
 * <p>
 * UpdateLog:
 */

public class LoginQuerData extends BaseWebService {


    /**
     * 登录
     *
     * @param user 用户名  姓名/手机号/电子邮件
     * @param psw  密码
     * @return
     */
    public int login(String user, String psw, String[] name) {


        String field = "NAME";
        if (user.contains("@")) {

            //email
            field = "EMAIL";
        } else if (user.length() == 11) {
            //phone
            field = "TELNUM";
        }

        final String MethodName = "QueryData";
        String sql = "select NAME,TELNUM from CPSE_USER where " + field + " = '" + user + "' and PSW = '" + psw + "'";
        Map<String, String> p = new HashMap<>();
        p.put("sql", sql);
        String str = getResult(MethodName, p);
        //Log.e(sql + "          " + str);
        if (str.equals("-1")) {
            return -1;
        }

        if (str.equals("0")) {
            return 0;
        }

        if (str.contains("NAME>")) {

            str = str.substring(str.indexOf("NAME>") + 5, str.length());
            name[0] = str.substring(0, str.indexOf("</NAME"));

            str = str.substring(str.indexOf("TELNUM>") + 7, str.length());
            name[1] = str.substring(0, str.indexOf("</TELNUM"));

            return 1;
        }
        return 0;
    }


    public void write() {

        String ff = Sdcard.getPath() + "zbxh/esdata.db";

        SQLiteDatabase mDb = null;
        try {
            mDb = SQLiteDatabase.openDatabase(ff, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            e.printStackTrace();

        }

        List<String> sqlList = new ArrayList<>();
        sqlList.clear();
        String sql = "select 姓名,手机,QQ from abs";
        Cursor cursor = mDb.rawQuery(sql, null);
        if (cursor != null) {

            while (cursor.moveToNext()) {

                String name = cursor.getString(0);
                String tel = cursor.getString(1);
                String qq = cursor.getString(2);

                String[] aa = name.split(" ");
                name = "";
                for (int i = 0; i < aa.length; i++) {
                    name += (aa[i].trim());
                }
                name = name.trim();

                if (tel.contains(".0"))
                    tel = tel.substring(0, tel.length() - 2);

                if (qq.contains(".0"))
                    qq = qq.substring(0, qq.length() - 2);

                String pguid = java.util.UUID.randomUUID().toString();
                sql = "insert into CPSE_USER (NAME,TELNUM,QQNUM,EMAIL,S_UDTIME,PGUID) values ('" + name + "','" + tel + "','" + qq + "','" + qq + "@qq.com','2018-11-17 20:09:11','" + pguid + "')";
                sqlList.add(sql);
            }

            cursor.close();
        }


        final String MethodName = "ExecuteSql";

        for (String s : sqlList) {
            Map<String, String> p = new HashMap<>();
            p.put("sql", s);
            String str = getResult(MethodName, p);
            Log.w(s + "        " + str);
        }

        // Log.w(s + "        " + str);
    }

}
