package com.example.sems_dev.ui.sensor_kind;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;

import java.util.ArrayList;

// sp에서 데이터 들고옴 -> ui에 출력
public class SensorKindActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerImageTextAdapter mAdapter;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();
    String msgbody = "1\n1\n2\n3\n2\n1\n0\n3";

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ArrayList<String> sKind = extractSensorKind(msgbody);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_kind);

        sharedPref = this.getSharedPreferences("1_KIND", 0);
        editor = sharedPref.edit();
        /// 코드 계속 ...
        mRecyclerView = findViewById(R.id.recycler3);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /// ... 코드 계속.
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();
        addItem(item.getS1_1(), item.getS1_2(), item.getS1_3(), item.getS1_4(), item.getS1_5(), item.getS1_6(), item.getS1_7(), item.getS1_8());
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String s1_1, String s1_2, String s1_3, String s1_4, String s1_5, String s1_6, String s1_7, String s1_8) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        item.setS1_1(sharedPref.getString("S1_1T","-"));
        item.setS1_2(sharedPref.getString("S1_2T","-"));
        item.setS1_3(sharedPref.getString("S1_3T","-"));
        item.setS1_4(sharedPref.getString("S1_4T","-"));
        item.setS1_5(sharedPref.getString("S1_5T","-"));
        item.setS1_6(sharedPref.getString("S1_6T","-"));
        item.setS1_7(sharedPref.getString("S1_7T","-"));
        item.setS1_8(sharedPref.getString("S1_8T","-"));

        mList.add(item);
    }

    public ArrayList<String> extractSensorKind(String msgBody) { // 메시지 내용에서 센서종류만 추출하는 메소드
        ArrayList<String> processed = new ArrayList<>();
        for (String s : msgBody.split("\n")) {
                processed.add(s);
        }
        return processed;
    }
}
