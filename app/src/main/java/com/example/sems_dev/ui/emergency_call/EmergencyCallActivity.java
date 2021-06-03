package com.example.sems_dev.ui.emergency_call;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class EmergencyCallActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null;
    RecyclerImageTextAdapter mAdapter = null;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();
    String msgbody = "1:010-9375-7063\n2:-\n3:-\n4:-\n5:-";
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ArrayList<String> pNumber = extractPhoneNumber(msgbody);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);

        sharedPref = this.getSharedPreferences("emergency_call", 0);
        editor = sharedPref.edit();
        /// 코드 계속 ...
        mRecyclerView = findViewById(R.id.recycler1);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /// ... 코드 계속.
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();
        addItem(item.getFarmNumber(), item.getpNum_1(), item.getpNum_2(),
                item.getpNum_3(), item.getpNum_4(), item.getpNum_5());
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String fNum, String pNum1, String pNum2, String pNum3, String pNum4, String pNum5) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        item.setFarmNumber("test 농장");

        String temp = sharedPref.getString("em_call_1", "-");
        item.setpNum_1(temp.substring(0,3)+"-"+temp.substring(4,8)+"-"+temp.substring(7,temp.length()));

        temp = sharedPref.getString("em_call_2", "-");
        item.setpNum_2(temp.substring(0,3)+"-"+temp.substring(4,8)+"-"+temp.substring(7,temp.length()));

        temp = sharedPref.getString("em_call_3", "-");
        item.setpNum_3(temp.substring(0,3)+"-"+temp.substring(4,8)+"-"+temp.substring(7,temp.length()));

        temp = sharedPref.getString("em_call_4", "-");
        item.setpNum_4(temp.substring(0,3)+"-"+temp.substring(4,8)+"-"+temp.substring(7,temp.length()));

        temp = sharedPref.getString("em_call_5", "-");
        item.setpNum_5(temp.substring(0,3)+"-"+temp.substring(4,8)+"-"+temp.substring(7,temp.length()));

        mList.add(item);
    }

    public ArrayList<String> extractPhoneNumber(String msgBody) { // 메시지 내용에서 전화번호만 추출하는 메소드
        ArrayList<String> processed = new ArrayList<>();
        for (String s : msgBody.split("\n"))
            processed.add(s.substring(2, s.length()));
        return processed;
    }
}
