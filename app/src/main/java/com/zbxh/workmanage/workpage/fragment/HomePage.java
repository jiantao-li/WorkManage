package com.zbxh.workmanage.workpage.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.github.mikephil.charting.charts.PieChart;
import com.zbxh.chart.UDrawPie;
import com.zbxh.workmanage.R;
import com.zbxh.workmanage.baseclass.BaseFragment;
import com.zbxh.workmanage.workpage.ItemMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljt on 2018-11-16 9:16.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:
 * <p>
 * UpdateLog:
 */

public class HomePage extends BaseFragment {


    private static HomePage homePage = null;

    public static HomePage getInstance() {

        if (homePage == null)
            return new HomePage();
        return homePage;
    }


    private PieChart mPieChart = null;
    private GridView mGridView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_homepage, container, false);

        mPieChart = view.findViewById(R.id.frm_homepage_pie);
        mGridView = view.findViewById(R.id.frm_homepage_menu);
        mGridView.post(new Runnable() {
            @Override
            public void run() {
                showView();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void showView() {

        //查询数据
        List<UDrawPie.TPieData> dataList = new ArrayList<>();
        UDrawPie.TPieData mpiedata = new UDrawPie.TPieData();
        mpiedata.xValue = "未响应工单";
        mpiedata.yValue = 3;
        mpiedata.color = Color.RED;
        dataList.add(mpiedata);

        mpiedata = new UDrawPie.TPieData();
        mpiedata.xValue = "进行中工单";
        mpiedata.yValue = 1;
        mpiedata.color = Color.MAGENTA;
        dataList.add(mpiedata);

        mpiedata = new UDrawPie.TPieData();
        mpiedata.xValue = "已完成工单";
        mpiedata.yValue = 13;
        mpiedata.color = Color.GREEN;
        dataList.add(mpiedata);

        mpiedata = new UDrawPie.TPieData();
        mpiedata.xValue = "已取消工单";
        mpiedata.yValue = 9;
        mpiedata.color = Color.GRAY;
        dataList.add(mpiedata);

        mpiedata = new UDrawPie.TPieData();
        mpiedata.xValue = "其他工单";
        mpiedata.yValue = 3;
        mpiedata.color = Color.YELLOW;
        dataList.add(mpiedata);

        //显示饼状图
        showPie(dataList);

        //显示功能菜单
        showMenuList();
    }

    private void showPie(List<UDrawPie.TPieData> data) {

        UDrawPie.newInstance().DrawPie(mPieChart, data);
    }

    private void showMenuList() {

        List<ItemMenuAdapter.TMenuData> menuDataList = new ArrayList<>();
        ItemMenuAdapter.TMenuData menuData = new ItemMenuAdapter.TMenuData();
        menuData.title = "未响应工单";
        menuData.value = "3";
        menuData.ico = R.drawable.ic_menu_detail_normal;
        menuData.bgColor = Color.rgb(17, 199, 177);
        menuData.titleColor = Color.rgb(15, 179, 159);
        menuDataList.add(menuData);

        menuData = new ItemMenuAdapter.TMenuData();
        menuData.title = "进行中工单";
        menuData.value = "1";
        menuData.ico = R.drawable.ic_menu_detail_normal;
        menuData.bgColor = Color.rgb(17, 199, 177);
        menuData.titleColor = Color.rgb(15, 179, 159);
        menuDataList.add(menuData);

        menuData = new ItemMenuAdapter.TMenuData();
        menuData.title = "已完成工单";
        menuData.value = "13";
        menuData.ico = R.drawable.ic_menu_detail_normal;
        menuData.bgColor = Color.rgb(17, 199, 177);
        menuData.titleColor = Color.rgb(15, 179, 159);
        menuDataList.add(menuData);

        menuData = new ItemMenuAdapter.TMenuData();
        menuData.title = "已取消工单";
        menuData.value = "9";
        menuData.ico = R.drawable.ic_menu_detail_normal;
        menuData.bgColor = Color.rgb(17, 199, 177);
        menuData.titleColor = Color.rgb(15, 179, 159);
        menuDataList.add(menuData);

        menuData = new ItemMenuAdapter.TMenuData();
        menuData.title = "其他工单";
        menuData.value = "3";
        menuData.ico = R.drawable.ic_menu_detail_normal;
        menuData.bgColor = Color.rgb(17, 199, 177);
        menuData.titleColor = Color.rgb(15, 179, 159);
        menuDataList.add(menuData);

        ItemMenuAdapter menuAdapter = new ItemMenuAdapter(getActivity(), menuDataList);
        menuAdapter.itemHeight = mPieChart.getHeight() / 3;
        mGridView.setAdapter(menuAdapter);
    }
}
