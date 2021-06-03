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
    String msgbody = "TIME1:21,42\nTIME2:-";
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ArrayList<String> msgTime = extractMessageTime(msgbody);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic_message);

        sharedPref = this.getSharedPreferences("periodic_message", 0);
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
        item.setFarmNumber("test");
        item.setMsgTime_1(sharedPref.getString("pd_msg_1", "-").replace(","," : "));
        item.setMsgTime_2(sharedPref.getString("pd_msg_2", "-").replace(","," : "));
        mList.add(item);
    }

    public ArrayList<String> extractMessageTime(String msgBody) { // 메시지 내용에서 전화번호만 추출하는 메소드
        ArrayList<String> processed = new ArrayList<>();
        for (String s : msgBody.split("\n")) {
            processed.add(s.substring(6, s.length()).replace(",",""));
        }
        return processed;
    }
}
