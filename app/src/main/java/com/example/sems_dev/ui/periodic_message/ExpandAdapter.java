package com.example.sems_dev.ui.periodic_message;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.sems_dev.R;

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<GroupList> DataList;
    private LayoutInflater myinf = null;
    private ExpandableListView lv;
    private Activity mActivity;

    public ExpandAdapter(Context context, int groupLay, int chlidLay, ArrayList<GroupList> DataList) {
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) { // 리스트 상위항목(농장1, 농장2... 설정)
        if (convertView == null) {
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView groupName = (TextView) convertView.findViewById(R.id.periodic_exp_groupName);
        groupName.setText(DataList.get(groupPosition).groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) { // 리스트 하위항목 (구역별 설정/조회)
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.explist_periodicmessage_childrow, null);
        }

        Button msgtime_edit = convertView.findViewById(R.id.msgtime_edit);
        TextView msgtime = convertView.findViewById(R.id.msgtime);

        TextView childName = convertView.findViewById(R.id.periodic_exp_childName);
        childName.setText(DataList.get(groupPosition).child.get(childPosition));
        AlertDialog.Builder timePickerDialog = new AlertDialog.Builder(context);

        msgtime_edit.setOnClickListener(new View.OnClickListener() { // 수정하기 버튼을 눌렀을 때 시간설정 다이얼로그 띄움
            View picker = myinf.inflate(R.layout.dialog_timepicker, null); // picker 뷰에 timepicker 띄움
            TimePicker timePicker = picker.findViewById(R.id.timepicker); // picker 뷰에서 timepicker 가져옴
            String hour = "";
            String minute = "";

            private void setTimePicker(TimePicker timePicker) {// timepicker 24시간 뷰로 설정하는 메소드
                timePicker.setIs24HourView(false);
            }

            private String getAmPm(int hr) {
                if (hr <= 12) {
                    return "오전 ";
                } else {
                    return "오후 ";
                }
            }

            private String setMinute(int min) {
                if (min >= 10)
                    return Integer.toString(min);
                else
                    return ("0" + Integer.toString(min));
            }

            @Override
            public void onClick(View v) { // 버튼 누를 시 다이얼로그 빌더로 다이얼로그 생성

                timePickerDialog.setTitle("시간 설정");
                setTimePicker(timePicker);
                timePickerDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() { // 설정하기 버튼 눌렸을 때 실행
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = timePicker.getHour();
                        int minute = timePicker.getMinute();
                        if (getAmPm(hour).equals("오후 ")) {
                            msgtime.setText(getAmPm(hour) + hour + " : " + setMinute(minute));
                        }
                        if (timePicker.getHour() == 0) {
                            msgtime.setText(getAmPm(hour) + (hour + 12) + " : " + setMinute(minute));
                        } else {
                            msgtime.setText(getAmPm(hour) + hour + " : " + setMinute(minute));
                        }
                        Toast.makeText(context, "설정되었습니다", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                timePickerDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() { // 취소하기 버튼 눌렸을 때 실행
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "취소하였습니다", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                if (picker.getParent() != null) { // 부모 뷰에 하위 뷰인 picker 가 여러번 띄워지는 것을 방지하는 코드
                    ((ViewGroup) picker.getParent()).removeView(picker);
                }
                timePickerDialog.setView(picker);
                timePickerDialog.show();

            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return DataList.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return DataList.get(groupPosition).child.size();
    }

    @Override
    public GroupList getGroup(int groupPosition) {
        return DataList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return DataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

}
