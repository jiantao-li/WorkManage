package com.zbxh.workmanage.workpage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.zbxh.workmanage.R;
import com.zbxh.workmanage.baseclass.BaseActivity;
import com.zbxh.workmanage.basetools.Mtoast;
import com.zbxh.workmanage.workpage.bottomnavigation.BottomNavigationViewHelper;
import com.zbxh.workmanage.workpage.bottomnavigation.ViewPagerAdapter;
import com.zbxh.workmanage.workpage.fragment.ContactsPage;
import com.zbxh.workmanage.workpage.fragment.HomePage;
import com.zbxh.workmanage.workpage.fragment.ListPage;
import com.zbxh.workmanage.workpage.fragment.PersonPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljt on 2018-11-16 8:59.
 * Email: jiantaoli@yeah.net
 * QQ: 178771128
 * Company:
 * <p>
 * Description:主页面
 * <p>
 * UpdateLog:
 */

public class WorkActivity extends BaseActivity {


    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private MenuItem menuItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_work);

        bottomNavigationView = findViewById(R.id.workactivity_bottommenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        viewPager = findViewById(R.id.workactivity_viewpage);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        String user_name = "";
        String user_tel = "";
        Intent gint = getIntent();
        if (gint != null) {
            user_name = gint.getStringExtra("user_name");
            user_tel = gint.getStringExtra("user_tel");

        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        List<Fragment> list = new ArrayList<>();
        list.add(HomePage.getInstance());
        list.add(ListPage.getInstance());
        list.add(ContactsPage.getInstance());
        list.add(PersonPage.getInstance(user_name,user_tel));
        viewPagerAdapter.setList(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            if (((System.currentTimeMillis() - exitTime) > 2000)) {
                Mtoast.show(this, "再按一次退出系统");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.gc();
            }
            return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    menuItem = item;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            viewPager.setCurrentItem(0);
                            return true;
                        case R.id.navigation_list:
                            viewPager.setCurrentItem(1);
                            return true;
                        case R.id.navigation_phone:
                            viewPager.setCurrentItem(2);
                            return true;
                        case R.id.navigation_person:
                            viewPager.setCurrentItem(3);
                            return true;
                    }
                    return false;
                }
            };

}
