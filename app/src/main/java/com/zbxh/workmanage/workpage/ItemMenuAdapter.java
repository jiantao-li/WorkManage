package com.zbxh.workmanage.workpage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

public class ItemMenuAdapter extends BaseAdapter {

    public static class TMenuData {

        public String title;
        public String value;
        public int ico;
        public int bgColor;
        public int titleColor;
    }


    public int itemHeight = 0;
    private LayoutInflater mInflater;
    private Context context = null;
    private List<TMenuData> menuDataList = null;

    public ItemMenuAdapter(Context context, List<TMenuData> menuDataList) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.menuDataList = menuDataList;
    }

    @Override
    public int getCount() {

        if (menuDataList != null)
            return menuDataList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {

        if (menuDataList != null)
            return menuDataList.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        RelativeLayout layout;
        RelativeLayout layout_title;
        ImageView ico;
        TextView tv_value;
        TextView tv_title;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_gv_menu, null);
            holder = new ViewHolder();
            holder.layout = view.findViewById(R.id.item_gv_layout);
            holder.layout_title = view.findViewById(R.id.item_gv_layout_title);
            holder.ico = view.findViewById(R.id.item_gv_ico);
            holder.tv_value = view.findViewById(R.id.item_gv_value);
            holder.tv_title = view.findViewById(R.id.item_gv_title);
            if (itemHeight != 0) {
                int magin = itemHeight / 13;
                RelativeLayout.LayoutParams lp;
                lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.height = itemHeight;
                lp.setMargins(magin, magin, magin, magin);
                holder.layout.setLayoutParams(lp);
            }
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        TMenuData menuData = (TMenuData) getItem(position);
        if (menuData != null) {
            holder.tv_title.setText(menuData.title);
            holder.tv_value.setText(menuData.value);
            holder.ico.setImageDrawable(context.getResources().getDrawable(menuData.ico));

            //设置背景色
            GradientDrawable myGrad = (GradientDrawable) holder.layout.getBackground();
            myGrad.setColor(menuData.bgColor);
            myGrad = (GradientDrawable) holder.layout_title.getBackground();
            myGrad.setColor(menuData.titleColor);
        }
        return view;
    }

}
