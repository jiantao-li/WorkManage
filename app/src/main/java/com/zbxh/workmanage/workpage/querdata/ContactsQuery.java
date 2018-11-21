package com.zbxh.workmanage.workpage.querdata;

import android.util.Xml;

import com.zbxh.workmanage.baseclass.BaseWebService;
import com.zbxh.workmanage.basetools.Log;
import com.zbxh.workmanage.workpage.ItemContactsAdapter;

import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ljt on 2018-11-17 20:34.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:
 * <p>
 * UpdateLog:
 */

public class ContactsQuery extends BaseWebService {


    public static ContactsQuery newInstance() {

        return new ContactsQuery();
    }

    public interface ContactsQueryListener {

        void onQueryError();

        void onQueryOver(final List<ItemContactsAdapter.TContactData> data);
    }

    public void queryData(ContactsQueryListener event) {

        final String MethodName = "QueryData";
        String sql = "select * from CPSE_USER";
        Map<String, String> p = new HashMap<>();
        p.put("sql", sql);

        String str = getResult(MethodName, p);

        if (str != null && str.contains("Table")) {
            List<ItemContactsAdapter.TContactData> data = new ArrayList<>();
            decodeData(str, data);
            event.onQueryOver(data);
        } else {
            event.onQueryError();
        }
    }

    private void decodeData(String xmlStr, List<ItemContactsAdapter.TContactData> data) {

        data.clear();
        try {
            InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes("gb2312"));
            XmlPullParser xmlpull = Xml.newPullParser();
            xmlpull.setInput(inputStream, "gb2312");
            int eventCode = xmlpull.getEventType();
            ItemContactsAdapter.TContactData contactData = null;
            while (eventCode != XmlPullParser.END_DOCUMENT) {
                switch (eventCode) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:

                        String fieldName = xmlpull.getName();
                        if (fieldName == null || fieldName.equals("") || fieldName.equals("NewDataSet")) {
                            break;
                        }
                        if (fieldName.equals("Table")) {
                            contactData = new ItemContactsAdapter.TContactData();
                            break;
                        }

                        if (contactData != null) {
                            switch (fieldName) {

                                case "NAME":
                                    contactData.name = xmlpull.nextText();
                                    break;
                                case "TELNUM":
                                    contactData.telnum = xmlpull.nextText();
                                    break;
                                case "QQNUM":
                                    contactData.qqnum = xmlpull.nextText();
                                    break;
                                case "EMAIL":
                                    contactData.email = xmlpull.nextText();
                                    break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("Table".equals(xmlpull.getName())) {
                            data.add(contactData);
                        }
                        break;
                }
                eventCode = xmlpull.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
