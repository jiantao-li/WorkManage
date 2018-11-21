package com.zbxh.chart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljt on 2018-11-16 16:34.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:
 * <p>
 * UpdateLog:
 */

public class UDrawPie {


    public static class TPieData {

        public String xValue;
        public int yValue;
        public int color;

    }

    public static UDrawPie newInstance() {
        return new UDrawPie();
    }

    private UDrawPie() {
    }

    public void DrawPie(PieChart pieChart, List<TPieData> data) {

        if (data == null || data.size() == 0)
            return;

        List xValues = new ArrayList(); //xVals用来表示每个饼块上的内容
        List yValues = new ArrayList();//yVals用来表示封装每个饼块的实际数据
        List colors = new ArrayList();//每块颜色
        xValues.clear();
        yValues.clear();
        colors.clear();
        for (int i = 0; i < data.size(); i++) {
            TPieData pieData = data.get(i);
            xValues.add(pieData.xValue);
            yValues.add(new Entry(pieData.yValue, i));
            colors.add(pieData.color);
        }

        //设置数据
        PieDataSet pieDataSet = new PieDataSet(yValues, "");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        pieDataSet.setColors(colors);

        pieDataSet.setSelectionShift(5f); // 选中状态多出的长度
        PieData pieData = new PieData(xValues, pieDataSet);

        //画图
        pieChart.setBackgroundColor(Color.rgb(236, 247, 247));
        pieChart.setExtraOffsets(0, 30, 0, 0);//设置图表外，布局内显示的偏移量
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(30f); //设置掏空部分的圆的半径
        //pieChart.setHoleColor(Color.GREEN);//设置中间掏空部分的颜色。
        pieChart.setTransparentCircleRadius(255); // 透明度  越大越透
        //pieChart.setHoleRadius(0); //实心圆
        pieChart.setDescription("");//描述
        //pieChart.setDrawCenterText(true); //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90); // 初始旋转角度
        pieChart.setRotationEnabled(true); // 可以手动旋转
        //pieChart.setCenterText("我的工程"); //饼状图中间的文字

        //设置数据
        pieChart.setData(pieData);
        Legend mLegend = pieChart.getLegend(); //设置图例
        mLegend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        // mLegend.setForm(LegendForm.LINE); //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(3f);
        pieChart.animateXY(1000, 1000); //设置动画
    }
}
