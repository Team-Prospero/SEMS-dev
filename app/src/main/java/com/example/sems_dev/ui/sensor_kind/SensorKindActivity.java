package com.example.sems_dev.ui.sensor_kind;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;

import java.util.ArrayList;


public class SensorKindActivity extends AppCompatActivity {
    RecyclerView mRecyclerView = null;
    RecyclerImageTextAdapter mAdapter = null;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();
    String msgbody = "S1-KIND\n1:3\n2:1\n3:1\n4:1\n5:1\n6:1\n7:1\n8:1";

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ArrayList<String> sKind = extractSensorKind(msgbody);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_kind);

        sharedPref = this.getSharedPreferences("sensor_kind", 0);
        editor = sharedPref.edit();
        for (int i = 0; i < sKind.size(); i++) {
            editor.putString("sKind_" + Integer.toString(i), sKind.get(i));
        }
        editor.apply();
        /// 코드 계속 ...
        mRecyclerView = findViewById(R.id.recycler3);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /// ... 코드 계속.
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();


        mAdapter.notifyDataSetChanged();
    }

    public void addItem(ArrayList<String> equip1_kind, ArrayList<String> equip2_kind, ArrayList<String> equip3_kind, ArrayList<String> equip4_kind) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        switch (msgbody.substring(0,2)){
            case "S1":

            case "S2":

            case "S3":

            case "S4":

        }
        mList.add(item);
    }

    public ArrayList<String> extractSensorKind(String msgBody) { // 메시지 내용에서 센서종류만 추출하는 메소드
        ArrayList<String> processed = new ArrayList<>();
        for (String s : msgBody.split("\n")) {
            if (s.length() == 3) {
                processed.add(Character.toString(s.charAt(s.length())));
            }
        }
        return processed;
    }
}
