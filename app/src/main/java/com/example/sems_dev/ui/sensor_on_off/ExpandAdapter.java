package com.example.sems_dev.ui.sensor_on_off;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sems_dev.R;
import java.util.ArrayList;

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
        TextView groupName = (TextView) convertView.findViewById(R.id.on_off_groupName);
        groupName.setText(DataList.get(groupPosition).groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView==null){
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.explist_sensor_onoff_childrow, null);
        }
        Button managerButton = convertView.findViewById(R.id.on_off_manage_btn);
        Button lookupButton = convertView.findViewById(R.id.on_off_lookup_btn);
        AlertDialog.Builder onOffDialog = new AlertDialog.Builder(context);

        managerButton.setOnClickListener(new View.OnClickListener() {
            View dialog = myinf.inflate(R.layout.dialog_onoff_manage, null);

            Spinner equipNumber = dialog.findViewById(R.id.on_off_equip_number);
            String[] equipItem = {"1번 장비","2번 장비","3번 장비","4번 장비"};
            Spinner sensorNumber = dialog.findViewById(R.id.on_off_sensor_number);
            String[] sensorItem = {"1구역","2구역","3구역","4구역","5구역","6구역","7구역","8구역"};
            Spinner sensorOnOff = dialog.findViewById(R.id.sensor_on_off);
            String[] onOffItem = {"사용","미사용"};

            private void setAdapter(){
                ArrayAdapter<String> equipNumberAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, equipItem);
                equipNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                equipNumber.setAdapter(equipNumberAdapter);

                ArrayAdapter<String> sensorNumberAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, sensorItem);
                sensorNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sensorNumber.setAdapter(sensorNumberAdapter);

                ArrayAdapter<String> sensorOnOffAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, onOffItem);
                sensorOnOffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sensorOnOff.setAdapter(sensorOnOffAdapter);

                equipNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, equipItem[position], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sensorNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, sensorItem[position], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                sensorOnOff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, onOffItem[position], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onClick(View v) {
                onOffDialog.setTitle("센서 사용유무 변경");
                onOffDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "설정되었습니다", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                onOffDialog.setNegativeButton("CENCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "취소하였습니다", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                if (dialog.getParent() != null) { // 부모 뷰에 하위 뷰인 picker 가 여러번 띄워지는 것을 방지하는 코드
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                onOffDialog.setView(dialog);
                onOffDialog.show();
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