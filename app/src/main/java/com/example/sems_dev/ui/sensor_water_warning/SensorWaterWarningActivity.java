package com.example.sems_dev.ui.sensor_water_warning;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class SensorWaterWarningActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerImageTextAdapter mAdapter;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_water_warning);
        sharedPref = this.getSharedPreferences("0_WA", 0);
        editor = sharedPref.edit();
        mRecyclerView = findViewById(R.id.recycler6);
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();
        addItem(item.getS1_1_H(), item.getS1_1_L(), item.getS1_2_H(), item.getS1_2_L(), item.getS1_3_H(), item.getS1_3_L(), item.getS1_4_H(), item.getS1_4_L(),
                item.getS1_5_H(), item.getS1_5_L(), item.getS1_6_H(), item.getS1_6_L(), item.getS1_7_H(), item.getS1_7_L(), item.getS1_8_H(), item.getS1_8_L());
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String s1_1_H, String s1_1_L, String s1_2_H, String s1_2_L, String s1_3_H, String s1_3_L, String s1_4_H, String s1_4_L,
                        String s1_5_H, String s1_5_L, String s1_6_H, String s1_6_L, String s1_7_H, String s1_7_L, String s1_8_H, String s1_8_L) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        item.setS1_1_H(sharedPref.getString("S1_1T_HIGH","-"));
        item.setS1_2_H(sharedPref.getString("S1_2T_HIGH","-"));
        item.setS1_3_H(sharedPref.getString("S1_3T_HIGH","-"));
        item.setS1_4_H(sharedPref.getString("S1_4T_HIGH","-"));
        item.setS1_5_H(sharedPref.getString("S1_5T_HIGH","-"));
        item.setS1_6_H(sharedPref.getString("S1_6T_HIGH","-"));
        item.setS1_7_H(sharedPref.getString("S1_7T_HIGH","-"));
        item.setS1_8_H(sharedPref.getString("S1_8T_HIGH","-"));

        item.setS1_1_L(sharedPref.getString("S1_1T_LOW","-"));
        item.setS1_2_L(sharedPref.getString("S1_2T_LOW","-"));
        item.setS1_3_L(sharedPref.getString("S1_3T_LOW","-"));
        item.setS1_4_L(sharedPref.getString("S1_4T_LOW","-"));
        item.setS1_5_L(sharedPref.getString("S1_5T_LOW","-"));
        item.setS1_6_L(sharedPref.getString("S1_6T_LOW","-"));
        item.setS1_7_L(sharedPref.getString("S1_7T_LOW","-"));
        item.setS1_8_L(sharedPref.getString("S1_8T_LOW","-"));
        mList.add(item);
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
