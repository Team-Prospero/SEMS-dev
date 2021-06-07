package com.example.sems_dev.ui.sensor_water_warning;

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
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class ExpandAdapter extends BaseExpandableListAdapter {

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
    /*Button manageButton = convertView.findViewById(R.id.water_warning_manage_btn);
    Button lookupButton = convertView.findViewById(R.id.water_warning_lookup_btn);
    AlertDialog.Builder waterWarningDialog = new AlertDialog.Builder(context);

        manageButton.setOnClickListener(new View.OnClickListener() {

                View dialog = myinf.inflate(R.layout.dialog_warning_value_manage, null);

                Spinner equipNumber = dialog.findViewById(R.id.warn_val_equip_number);
                String[] equipItem = {"1번 장비", "2번 장비", "3번 장비", "4번 장비"};
                Spinner sensorNumber = dialog.findViewById(R.id.warn_val_sensor_number);
                String[] sensorItem = {"1구역", "2구역", "3구역", "4구역", "5구역", "6구역", "7구역", "8구역"};
                EditText sensorHigh = dialog.findViewById(R.id.sensor_high);
                EditText sensorLow = dialog.findViewById(R.id.sensor_low);

private void setAdapter() {
        ArrayAdapter<String> equipNumberAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, equipItem);
        equipNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        equipNumber.setAdapter(equipNumberAdapter);

        ArrayAdapter<String> sensorNumberAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, sensorItem);
        sensorNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sensorNumber.setAdapter(sensorNumberAdapter);

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
        }

@Override
public void onClick(View v) {
        setAdapter();
        waterWarningDialog.setTitle("음수 경고범위 변경");
        waterWarningDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(context, "설정되었습니다", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
        }
        });

        waterWarningDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(context, "취소하였습니다", Toast.LENGTH_SHORT).show();
        dialog.cancel();
        }
        });
        if (dialog.getParent() != null) { // 부모 뷰에 하위 뷰인 picker 가 여러번 띄워지는 것을 방지하는 코드
        ((ViewGroup) dialog.getParent()).removeView(dialog);
        }
        waterWarningDialog.setView(dialog);
        waterWarningDialog.show();
        }
        });*/