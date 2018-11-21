package com.zbxh.workmanage.workpage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ListPage extends BaseFragment {


    private static ListPage listPage = null;

    public static ListPage getInstance() {

        if (listPage == null)
            return new ListPage();
        return listPage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_personpage, container, false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
