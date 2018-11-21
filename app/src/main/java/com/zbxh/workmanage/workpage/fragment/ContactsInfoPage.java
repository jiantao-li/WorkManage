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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zbxh.workmanage.R;
import com.zbxh.workmanage.baseclass.BaseActivity;
import com.zbxh.workmanage.workpage.ItemContactsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljt on 2018-11-16 20:32.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:通讯录详细信息界面
 * <p>
 * UpdateLog:
 */

public class ContactsInfoPage extends BaseActivity {


    private class TData {

        public int ico;
        public String value;

    }

    private class DataAdapter extends BaseAdapter {

        public int itemHeight = 0;
        private LayoutInflater mInflater;
        private Context context = null;
        private List<TData> mDataList = null;

        public DataAdapter(Context context, List<TData> menuDataList) {

            this.mInflater = LayoutInflater.from(context);
            this.context = context;
            this.mDataList = menuDataList;
        }

        @Override
        public int getCount() {
            if (mDataList != null)
                return mDataList.size();
            return 0;
        }

        @Override
        public Object getItem(int i) {
            if (mDataList != null)
                return mDataList.get(i);
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        class ViewHolder {
            RelativeLayout layout;
            ImageView ico;
            TextView tv_value;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = mInflater.inflate(R.layout.item_lv_contact_info, null);
                holder = new ViewHolder();
                holder.layout = view.findViewById(R.id.item_lv_layout);
                holder.ico = view.findViewById(R.id.item_lv_contact_photo);
                holder.tv_value = view.findViewById(R.id.item_lv_contact_name);
                if (itemHeight != 0) {
                    RelativeLayout.LayoutParams lp;
                    lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.height = itemHeight;
                    holder.layout.setLayoutParams(lp);
                }
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            TData menuData = (TData) getItem(position);
            if (menuData != null) {
                holder.tv_value.setText(menuData.value);
                holder.ico.setImageDrawable(context.getResources().getDrawable(menuData.ico));
            }
            return view;
        }
    }

    private ListView lv = null;
    private TextView tv_name = null;
    private List<TData> dataList = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contactsinfo);
        lv = findViewById(R.id.contact_info_lv);
        lv.post(new Runnable() {
            @Override
            public void run() {

                String[] values = new String[4];
                Intent intent = getIntent();
                values[0] = intent.getStringExtra("name");
                values[1] = intent.getStringExtra("tel");
                values[2] = intent.getStringExtra("qq");
                values[3] = intent.getStringExtra("email");

                tv_name = findViewById(R.id.contact_info_name);
                tv_name.setText(values[0]);
                LoadData(values);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                TData cd = dataList.get(i);

                switch (i) {

                    case 0://tel
                        try {
                            //直接拨打
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + cd.value));
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //不直接拨打  调用拨号界面
           /* Intent intent1 = new Intent(Intent.ACTION_DIAL);
            intent1.setData(Uri.parse("tel:400-8181800"));
            startActivity(intent1);

<uses - permission android:name = "android.permission.CALL_PRIVILEGED" / >*/

                        break;
                    case 1://qq
                        try {
                            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + cd.value;
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2://email
                        break;
                }

            }
        });

        ImageView imBack = findViewById(R.id.contact_info_back);
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactsInfoPage.this.finish();
                System.gc();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void LoadData(String[] values) {

        dataList = new ArrayList<>();
        dataList.clear();
        TData n = new TData();
        n.ico = R.drawable.icon_contact_inco_tel;
        n.value = values[1];
        dataList.add(n);
        n = new TData();
        n.ico = R.drawable.icon_contact_inco_qq;
        n.value = values[2];
        dataList.add(n);
        n = new TData();
        n.ico = R.drawable.icon_contact_inco_email;
        n.value = values[3];
        dataList.add(n);

        DataAdapter mDataAdapter = new DataAdapter(this, dataList);
        mDataAdapter.itemHeight = tv_name.getHeight() * 2;
        lv.setAdapter(mDataAdapter);
    }
}
