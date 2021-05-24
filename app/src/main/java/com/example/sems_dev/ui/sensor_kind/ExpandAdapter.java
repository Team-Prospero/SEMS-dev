package com.example.sems_dev.ui.sensor_kind;

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
        TextView groupName = (TextView) convertView.findViewById(R.id.sensor_kind_groupName);
        groupName.setText(DataList.get(groupPosition).groupName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView==null){
            LayoutInflater infalInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.explist_sensor_kind_childrow, null);
        }
        Button manageButton = convertView.findViewById(R.id.kind_manage_btn);
        Button lookupButton = convertView.findViewById(R.id.kind_lookup_btn);
        AlertDialog.Builder sensorKindDialog = new AlertDialog.Builder(context);



        manageButton.setOnClickListener(new View.OnClickListener() {

            View dialog = myinf.inflate(R.layout.dialog_kind_manage, null);

            Spinner equipNumber = dialog.findViewById(R.id.kind_equip_number);
            String[] equipItem = {"1번 장비","2번 장비","3번 장비","4번 장비"};
            Spinner sensorNumber = dialog.findViewById(R.id.kind_sensor_number);
            String[] sensorItem = {"1구역","2구역","3구역","4구역","5구역","6구역","7구역","8구역"};
            Spinner sensorKind = dialog.findViewById(R.id.sensor_kind);
            String[] kindItem = {"기타","온도","습도","음수"};

            private void setAdapter(){
                ArrayAdapter<String> equipNumberAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, equipItem);
                equipNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                equipNumber.setAdapter(equipNumberAdapter);

                ArrayAdapter<String> sensorNumberAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, sensorItem);
                sensorNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sensorNumber.setAdapter(sensorNumberAdapter);

                ArrayAdapter<String> sensorKindAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, kindItem);
                sensorKindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sensorKind.setAdapter(sensorKindAdapter);

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

                sensorKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, kindItem[position], Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onClick(View v) {
                setAdapter();
                sensorKindDialog.setTitle("센서종류 변경");
                sensorKindDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "설정되었습니다", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                sensorKindDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "취소하였습니다", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                if (dialog.getParent() != null) { // 부모 뷰에 하위 뷰인 picker 가 여러번 띄워지는 것을 방지하는 코드
                    ((ViewGroup) dialog.getParent()).removeView(dialog);
                }
                sensorKindDialog.setView(dialog);
                sensorKindDialog.show();
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
