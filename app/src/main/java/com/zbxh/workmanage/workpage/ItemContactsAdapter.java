package com.zbxh.workmanage.workpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zbxh.workmanage.R;

import java.util.List;

/**
 * Created by ljt on 2018-11-16 19:47.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:
 * <p>
 * UpdateLog:
 */

public class ItemContactsAdapter extends BaseAdapter {

    public static class TContactData {

        public String name;
        public String branch;//部门
        public String telnum;
        public String qqnum;
        public String email;
    }


    public int itemHeight = 0;
    private LayoutInflater mInflater;
    private Context context = null;
    private List<TContactData> contactDataList = null;

    public ItemContactsAdapter(Context context, List<TContactData> contactData) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.contactDataList = contactData;
    }

    @Override
    public int getCount() {

        if (contactDataList != null)
            return contactDataList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {

        if (contactDataList != null)
            return contactDataList.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        RelativeLayout layout;
        ImageView img_photo;
        TextView tv_name;
        TextView tv_num;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_lv_contact, null);
            holder = new ViewHolder();
            holder.layout = view.findViewById(R.id.item_lv_layout);
            holder.img_photo = view.findViewById(R.id.item_lv_contact_photo);
            holder.tv_name = view.findViewById(R.id.item_lv_contact_name);
            holder.tv_num = view.findViewById(R.id.item_lv_contact_num);
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

        TContactData contactData = (TContactData) getItem(position);
        if (contactData != null) {
            holder.tv_name.setText(contactData.name);
            holder.tv_num.setText(contactData.telnum);
           /* if (!TextUtils.isEmpty(contactData.telnum) && !TextUtils.isEmpty(contactData.email)) {
                holder.tv_num.setText("电话：" + contactData.telnum + "          邮箱：" + contactData.email);
            } else if (TextUtils.isEmpty(contactData.telnum) && !TextUtils.isEmpty(contactData.email)) {
                holder.tv_num.setText("邮箱：" + contactData.email);
            } else if (!TextUtils.isEmpty(contactData.telnum) && TextUtils.isEmpty(contactData.email)) {
                holder.tv_num.setText("电话：" + contactData.telnum);
            }*/
            // holder.img_photo.setImageDrawable(context.getResources().getDrawable(menuData.ico));
        }
        return view;
    }

}
