package com.example.sems_dev.ui.emergency_expList;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class EmergencyCallActivity extends AppCompatActivity {
    private ExpandableListView listView;
    private int last_expanded = -1;

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
        temp.child.add("1번째 비상연락처");
        temp.child.add("2번째 비상연락처");
        temp.child.add("3번째 비상연락처");
        temp.child.add("4번째 비상연락처");
        temp.child.add("5번째 비상연락처");
        DataList.add(temp);
        temp = new GroupList("2번 농장");
        temp.child.add("1번째 비상연락처");
        temp.child.add("2번째 비상연락처");
        temp.child.add("3번째 비상연락처");
        temp.child.add("4번째 비상연락처");
        temp.child.add("5번째 비상연락처");
        DataList.add(temp);
        temp = new GroupList("3번 농장");
        temp.child.add("1번째 비상연락처");
        temp.child.add("2번째 비상연락처");
        temp.child.add("3번째 비상연락처");
        temp.child.add("4번째 비상연락처");
        temp.child.add("5번째 비상연락처");
        DataList.add(temp);
        temp = new GroupList("4번 농장");
        temp.child.add("1번째 비상연락처");
        temp.child.add("2번째 비상연락처");
        temp.child.add("3번째 비상연락처");
        temp.child.add("4번째 비상연락처");
        temp.child.add("5번째 비상연락처");
        DataList.add(temp);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // 그룹 클릭리스너
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Boolean isExpanded = (!listView.isGroupExpanded(groupPosition)); //선택한 그룹 포지션의 펼침/닫힘 상태체크
                listView.collapseGroup(last_expanded); //이전에 열려있던 그룹 닫기
                if(isExpanded){
                    listView.expandGroup(groupPosition); // 현재 선택한 그룹이 있으면 펼쳐줌
                }
                last_expanded=groupPosition; // 현재 선택한 그룹을 last_expanded 로 설정
                return true;
            }
        });


        ExpandAdapter adapter = new ExpandAdapter(getApplicationContext(), R.layout.explist_row, R.layout.explist_childrow, DataList);
        listView.setIndicatorBounds(width - 50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}
