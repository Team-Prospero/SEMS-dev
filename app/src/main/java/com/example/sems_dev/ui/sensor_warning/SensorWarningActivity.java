package com.example.sems_dev.ui.sensor_warning;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class SensorWarningActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerImageTextAdapter mAdapter;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_warning);
        sharedPref = this.getSharedPreferences("0_LIMT", 0);
        editor = sharedPref.edit();
        mRecyclerView = findViewById(R.id.recycler5);
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();
        addItem(item.getS1_1_H(), item.getS1_1_L(), item.getS1_2_H(), item.getS1_2_L(), item.getS1_3_H(), item.getS1_3_L(), item.getS1_4_H(), item.getS1_4_L(),
                item.getS1_5_H(), item.getS1_5_L(), item.getS1_6_H(), item.getS1_6_L(), item.getS1_7_H(), item.getS1_7_L(), item.getS1_8_H(), item.getS1_8_L());
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String s1_1_H, String s1_1_L, String s1_2_H, String s1_2_L, String s1_3_H, String s1_3_L, String s1_4_H, String s1_4_L,
                        String s1_5_H, String s1_5_L, String s1_6_H, String s1_6_L, String s1_7_H, String s1_7_L, String s1_8_H, String s1_8_L) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        item.setS1_1_H(sharedPref.getString("S1_1_HIGH","-"));
        item.setS1_2_H(sharedPref.getString("S1_2_HIGH","-"));
        item.setS1_3_H(sharedPref.getString("S1_3_HIGH","-"));
        item.setS1_4_H(sharedPref.getString("S1_4_HIGH","-"));
        item.setS1_5_H(sharedPref.getString("S1_5_HIGH","-"));
        item.setS1_6_H(sharedPref.getString("S1_6_HIGH","-"));
        item.setS1_7_H(sharedPref.getString("S1_7_HIGH","-"));
        item.setS1_8_H(sharedPref.getString("S1_8_HIGH","-"));

        item.setS1_1_L(sharedPref.getString("S1_1_LOW","-"));
        item.setS1_2_L(sharedPref.getString("S1_2_LOW","-"));
        item.setS1_3_L(sharedPref.getString("S1_3_LOW","-"));
        item.setS1_4_L(sharedPref.getString("S1_4_LOW","-"));
        item.setS1_5_L(sharedPref.getString("S1_5_LOW","-"));
        item.setS1_6_L(sharedPref.getString("S1_6_LOW","-"));
        item.setS1_7_L(sharedPref.getString("S1_7_LOW","-"));
        item.setS1_8_L(sharedPref.getString("S1_8_LOW","-"));
        mList.add(item);
    }
}
