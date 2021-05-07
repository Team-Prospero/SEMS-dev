package com.example.sems_dev.ui.emergency_call;

import android.content.Context;
import android.support.v4.app.INotificationSideChannel;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import com.example.sems_dev.R;

import static android.text.InputType.TYPE_NULL;

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<GroupList> DataList;
    private LayoutInflater myinf = null;
    private ExpandableListView lv;

    public ExpandAdapter(Context context, int groupLay, int chlidLay, ArrayList<GroupList> DataList) {
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if (convertView == null) {
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView groupName = (TextView) convertView.findViewById(R.id.groupName);
        groupName.setText(DataList.get(groupPosition).groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView==null){
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.explist_emargencycall_childrow, null);
        }
        ToggleButton pNumberEdit_OnOff = convertView.findViewById(R.id.phonenumber_edit); // 수정 On/Off 토글버튼
        EditText pNum1 = convertView.findViewById(R.id.pNum1); // 첫 숫자 3자리
        EditText pNum2 = convertView.findViewById(R.id.pNum2); // 두번째 숫자 4자리
        EditText pNum3 = convertView.findViewById(R.id.pNum3); // 세번째 숫자 4자리
        TextView childName = convertView.findViewById(R.id.childName);
        childName.setText(DataList.get(groupPosition).child.get(childPosition));
        pNumberEdit_OnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) { // 토글버튼 On 시 수행
                    Toast.makeText(context, "수정하고 버튼을 한번더 눌러 수정을 완료하세요", Toast.LENGTH_SHORT).show();
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    pNum1.setEnabled(true);
                    pNum2.setEnabled(true);
                    pNum3.setEnabled(true);
                    pNum1.requestFocus(); // 첫 숫자 3자리에 포커스
                    imm.showSoftInput(pNum1, 0); // 소프트 키보드 띄움
                    pNum1.addTextChangedListener(new TextWatcher() { // 텍스트 변경 검사
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(s.length()==3){
                                pNum2.requestFocus(); // 3자리면 다음 자리로 포커스 이동
                            }
                        }
                    });

                    pNum2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(s.length()==4){
                                pNum3.requestFocus(); // 4자리면 다음 자리로 포커스 이동
                            }
                        }
                    });

                    pNum3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(s.length()==4&&imm.isAcceptingText()){
                                imm.hideSoftInputFromWindow(pNum3.getWindowToken(),0); // 마지막 자리이므로 소프트키보드 닫음
                            }
                        }
                    });

                } else { // 토글버튼 Off 일 경우
                    Toast.makeText(context, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    pNum1.setEnabled(false);
                    pNum2.setEnabled(false);
                    pNum3.setEnabled(false);
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition).child.size();
    }

    @Override
    public GroupList getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return DataList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return DataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

}