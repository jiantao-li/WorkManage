package com.zbxh.workmanage.workpage.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zbxh.workmanage.R;
import com.zbxh.workmanage.baseclass.BaseFragment;

/**
 * Created by ljt on 2018-11-16 20:32.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:我的
 * <p>
 * UpdateLog:
 */

public class PersonPage extends BaseFragment {


    private static PersonPage personPage = null;
    private static String user_name = null;
    private static String user_tel = null;

    public static PersonPage getInstance(String user_name, String user_tel) {

        PersonPage.user_name = user_name;
        PersonPage.user_tel = user_tel;
        if (personPage == null)
            return new PersonPage();
        return personPage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_personpage, container, false);

        final TextView tv_p = view.findViewById(R.id.frm_person_name);
        tv_p.setText(user_name + "\r\n" + user_tel);

        final ListView lv = view.findViewById(R.id.frm_person_lv);
        lv.post(new Runnable() {
            @Override
            public void run() {

                ItemAdapter itemAdapter = new ItemAdapter(getActivity());
                itemAdapter.itemHeight = tv_p.getHeight() / 3 * 2;
                lv.setAdapter(itemAdapter);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private class ItemAdapter extends BaseAdapter {

        public int itemHeight = 0;
        private LayoutInflater mInflater;

        public ItemAdapter(Context context) {

            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        private class ViewHolder {
            RelativeLayout layout;
            ImageView ico;
            TextView item;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = mInflater.inflate(R.layout.item_lv_person, null);
                holder = new ViewHolder();
                holder.layout = view.findViewById(R.id.item_lv_layout);
                holder.ico = view.findViewById(R.id.item_lv_person_ico);
                holder.item = view.findViewById(R.id.item_lv_person_item);
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

            switch (i) {

                case 0:
                    holder.ico.setImageDrawable(getResources().getDrawable(R.drawable.icon_contact_inco_qq));
                    holder.item.setText("密码修改");
                    break;
                case 1:
                    holder.ico.setImageDrawable(getResources().getDrawable(R.drawable.icon_contact_inco_qq));
                    holder.item.setText("问题反馈");
                    break;
                case 2:
                    holder.ico.setImageDrawable(getResources().getDrawable(R.drawable.icon_contact_inco_qq));
                    holder.item.setText("关于");
                    break;
                case 3:
                    holder.ico.setImageDrawable(getResources().getDrawable(R.drawable.icon_contact_inco_qq));
                    holder.item.setText("退出");
                    break;
            }
            return view;
        }
    }

}
