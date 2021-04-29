package com.example.sems_dev.ui.periodic_message;


import android.app.TimePickerDialog;
import android.content.Context;
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

import java.util.ArrayList;

import com.example.sems_dev.R;

public class ExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int chlidLayout = 0;
    private ArrayList<GroupList> DataList;
    private LayoutInflater myinf = null;
    private ExpandableListView lv;
    private PeriodicMessageActivity periodicMessageActivity;

    public ExpandAdapter(Context context, int groupLay, int chlidLay, ArrayList<GroupList> DataList) {
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.chlidLayout = chlidLay;
        this.context = context;
        this.myinf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView groupName = (TextView) convertView.findViewById(R.id.periodic_exp_groupName);
        // TODO 2021-04-25 | NullPointerException occur -> solved
        groupName.setText(DataList.get(groupPosition).groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.explist_periodicmessage_childrow, null);
        }
        ToggleButton msgtime_edit = convertView.findViewById(R.id.msgtime_edit);
//        Button msgtime_edit = convertView.findViewById(R.id.msgtime_edit);
        EditText msgtime = convertView.findViewById(R.id.msgtime);
        TextView childName = convertView.findViewById(R.id.periodic_exp_childName);
        childName.setText(DataList.get(groupPosition).child.get(childPosition));

        msgtime_edit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // EditText OnOff로 구현한 코드 (구현완료)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    msgtime.setEnabled(true);
                    Toast.makeText(context, "수정하고 버튼을 한번더 눌러 수정을 완료하세요", Toast.LENGTH_SHORT).show();
                } else {
                    msgtime.setEnabled(false);
                    Toast.makeText(context, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

/*        msgtime_edit.setOnClickListener(new View.OnClickListener() { // timepicker 다이얼로그 띄우는 거로 구현한 코드 (구현중)
            @Override
            public void onClick(View v) {
*//*                DialogFragment timepicker = new TimepickerDialogFragment();
                //TODO IllegalStateException | not associated with a fragment manager
                timepicker.show(timepicker.getParentFragmentManager(), "time picker");*//*

            }
        });*/
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
