package com.example.sems_dev.ui.periodic_message;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class PeriodicMessageActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null;
    RecyclerImageTextAdapter mAdapter = null;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_message);

        sharedPref = this.getSharedPreferences("0_TIME", 0);
        editor = sharedPref.edit();
        /// 코드 계속 ...
        mRecyclerView = findViewById(R.id.recycler2);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /// ... 코드 계속.
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();
        addItem(item.getFarmNumber(), item.getMsgTime_1(), item.getMsgTime_2());
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String fNum, String msgtime_1, String msgtime_2) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        item.setFarmNumber("EXPO 시연");
        item.setMsgTime_1("0","0");
        item.setMsgTime_2("0","0");
        mList.add(item);
    }
}
