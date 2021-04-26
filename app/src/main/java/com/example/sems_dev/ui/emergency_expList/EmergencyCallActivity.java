package com.example.sems_dev.ui.emergency_expList;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sems_dev.R;
import java.util.ArrayList;

public class EmergencyCallActivity extends AppCompatActivity {
    public static Context context;
    private ExpandableListView listView;
    private int last_expanded = -1;
    private ToggleButton phoneNumber_edit_button;
    private EditText phoneNumber_editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call);
        phoneNumber_edit_button = findViewById(R.id.phonenumber_edit);
        phoneNumber_editText = findViewById(R.id.pnumber);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        newDisplay.getSize(size);
        int width = size.x;

        ArrayList<GroupList> DataList = new ArrayList<GroupList>();
        listView = findViewById(R.id.emergency_expList);
        GroupList temp = new GroupList("농장 1");
        temp.child.add("비상연락처 1");
        temp.child.add("비상연락처 2");
        temp.child.add("비상연락처 3");
        temp.child.add("비상연락처 4");
        temp.child.add("비상연락처 5");
        DataList.add(temp);
        temp = new GroupList("농장 2");
        temp.child.add("비상연락처 1");
        temp.child.add("비상연락처 2");
        temp.child.add("비상연락처 3");
        temp.child.add("비상연락처 4");
        temp.child.add("비상연락처 5");
        DataList.add(temp);
        temp = new GroupList("농장 3");
        temp.child.add("비상연락처 1");
        temp.child.add("비상연락처 2");
        temp.child.add("비상연락처 3");
        temp.child.add("비상연락처 4");
        temp.child.add("비상연락처 5");
        DataList.add(temp);
        temp = new GroupList("농장 4");
        temp.child.add("비상연락처 1");
        temp.child.add("비상연락처 2");
        temp.child.add("비상연락처 3");
        temp.child.add("비상연락처 4");
        temp.child.add("비상연락처 5");
        DataList.add(temp);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() { // 그룹 클릭리스너
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean isExpanded = (!listView.isGroupExpanded(groupPosition)); //선택한 그룹 포지션의 펼침/닫힘 상태체크
                listView.collapseGroup(last_expanded); //이전에 열려있던 그룹 닫기
                if(isExpanded){
                    listView.expandGroup(groupPosition); // 현재 선택한 그룹이 있으면 펼쳐줌
                }
                last_expanded=groupPosition; // 현재 선택한 그룹을 last_expanded 로 설정
                return true;
            }
        });



        ExpandAdapter adapter = new ExpandAdapter(getApplicationContext(), R.layout.explist_emargencycall_row, R.layout.explist_emargencycall_childrow, DataList);
        Drawable icon = getDrawable(R.drawable.arrow_down_black_24);
        listView.setIndicatorBounds(width - 50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setGroupIndicator(icon);
        listView.setAdapter(adapter);
    }
}
