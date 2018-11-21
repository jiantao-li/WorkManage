package com.zbxh.workmanage.workpage.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zbxh.workmanage.R;
import com.zbxh.workmanage.baseclass.BaseFragment;
import com.zbxh.workmanage.basetools.Mtoast;
import com.zbxh.workmanage.workpage.ItemContactsAdapter;
import com.zbxh.workmanage.workpage.querdata.ContactsQuery;

import java.util.List;

/**
 * Created by ljt on 2018-11-16 20:32.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:通讯录
 * <p>
 * UpdateLog:
 */

public class ContactsPage extends BaseFragment {


    private static ContactsPage contactsPage = null;

    public static ContactsPage getInstance() {

        if (contactsPage == null)
            return new ContactsPage();
        return contactsPage;
    }


    private ListView lv_list = null;
    private RelativeLayout ly = null;
    private List<ItemContactsAdapter.TContactData> contactData = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_contactspage, container, false);

        ly = view.findViewById(R.id.frm_contacts_title);
        lv_list = view.findViewById(R.id.frm_contacts_lv);
        lv_list.setOnItemClickListener(itemClickListener);
        new Thread(LoadData).start();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private Runnable LoadData = new Runnable() {
        @Override
        public void run() {

            ContactsQuery.newInstance().queryData(mContactsQueryListener);
        }
    };

    private ContactsQuery.ContactsQueryListener mContactsQueryListener = new ContactsQuery.ContactsQueryListener() {
        @Override
        public void onQueryError() {

            Mtoast.show(getActivity(), "加载失败，请检查网络");
        }

        @Override
        public void onQueryOver(final List<ItemContactsAdapter.TContactData> data) {

            contactData = data;
            lv_list.post(new Runnable() {
                @Override
                public void run() {
                    ItemContactsAdapter contactsAdapter = new ItemContactsAdapter(getActivity(), contactData);
                    contactsAdapter.itemHeight = ly.getHeight();
                    lv_list.setAdapter(contactsAdapter);
                }
            });
        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            ItemContactsAdapter.TContactData cd = contactData.get(i);

            //打开详细界面
            try {
                Intent ain = new Intent();
                ain.setClass(getActivity(), ContactsInfoPage.class);
                ain.putExtra("name", cd.name);
                ain.putExtra("tel", cd.telnum);
                ain.putExtra("qq", cd.qqnum);
                ain.putExtra("email", cd.email);
                getActivity().startActivity(ain);
            } catch (Exception e) {
                e.printStackTrace();
            }



        }
    };
}
