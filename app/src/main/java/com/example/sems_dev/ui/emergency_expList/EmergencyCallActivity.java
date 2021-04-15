package com.example.sems_dev.ui.emergency_expList;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class EmergencyCallActivity extends AppCompatActivity {
    private ExpandableListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        newDisplay.getSize(size);
        int width = size.x;

        ArrayList<GroupList> DataList = new ArrayList<GroupList>();
        listView = findViewById(R.id.emergency_expList);
        GroupList temp = new GroupList("1번 농장");
        temp.child.add("1-1 비상연락처");
        temp.child.add("1-2 비상연락처");
        temp.child.add("1-3 비상연락처");
        temp.child.add("1-4 비상연락처");
        temp.child.add("1-5 비상연락처");
        DataList.add(temp);
        temp = new GroupList("2번 농장");
        temp.child.add("2-1 비상연락처");
        temp.child.add("2-2 비상연락처");
        temp.child.add("2-3 비상연락처");
        temp.child.add("2-4 비상연락처");
        temp.child.add("2-5 비상연락처");
        DataList.add(temp);
        temp = new GroupList("3번 농장");
        temp.child.add("3-1 비상연락처");
        temp.child.add("3-2 비상연락처");
        temp.child.add("3-3 비상연락처");
        temp.child.add("3-4 비상연락처");
        temp.child.add("3-5 비상연락처");
        DataList.add(temp);
        temp = new GroupList("4번 농장");
        temp.child.add("4-1 비상연락처");
        temp.child.add("4-2 비상연락처");
        temp.child.add("4-3 비상연락처");
        temp.child.add("4-4 비상연락처");
        temp.child.add("4-5 비상연락처");
        DataList.add(temp);

        ExpandAdapter adapter = new ExpandAdapter(getApplicationContext(), R.layout.explist_row, R.layout.explist_childrow, DataList);
        listView.setIndicatorBounds(width - 50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}
