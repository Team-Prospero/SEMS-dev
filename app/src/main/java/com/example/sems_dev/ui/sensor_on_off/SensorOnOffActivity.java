package com.example.sems_dev.ui.sensor_on_off;

import android.content.SharedPreferences;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sems_dev.R;

import java.util.ArrayList;

public class SensorOnOffActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerImageTextAdapter mAdapter;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_on_off);

        sharedPref = this.getSharedPreferences("0_USE", 0);
        editor = sharedPref.edit();
        mRecyclerView = findViewById(R.id.recycler4);
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();
        addItem(item.getS1_1(), item.getS1_2(), item.getS1_3(), item.getS1_4(), item.getS1_5(), item.getS1_6(), item.getS1_7(), item.getS1_8());
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String s1_1, String s1_2, String s1_3, String s1_4, String s1_5, String s1_6, String s1_7, String s1_8) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        item.setFarmNumber("EXPO 시연");
        item.setS1_1(sharedPref.getString("S1_1","-"));
        item.setS1_2(sharedPref.getString("S1_2","-"));
        item.setS1_3(sharedPref.getString("S1_3","-"));
        item.setS1_4(sharedPref.getString("S1_4","-"));
        item.setS1_5(sharedPref.getString("S1_5","-"));
        item.setS1_6(sharedPref.getString("S1_6","-"));
        item.setS1_7(sharedPref.getString("S1_7","-"));
        item.setS1_8(sharedPref.getString("S1_8","-"));
        mList.add(item);
    }

}
