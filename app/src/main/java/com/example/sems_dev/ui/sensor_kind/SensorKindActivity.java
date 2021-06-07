package com.example.sems_dev.ui.sensor_kind;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;

import java.util.ArrayList;

// sp에서 데이터 들고옴 -> ui에 출력
public class SensorKindActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerImageTextAdapter mAdapter;

    ArrayList<RecyclerItem> mList = new ArrayList<RecyclerItem>();

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_kind);

        sharedPref = this.getSharedPreferences("0_KIND", 0);
        editor = sharedPref.edit();
        mRecyclerView = findViewById(R.id.recycler3);

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        mAdapter = new RecyclerImageTextAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 아이템 추가.
        RecyclerItem item = new RecyclerItem();
        addItem(item.getS1_1(), item.getS1_2(), item.getS1_3(), item.getS1_4(), item.getS1_5(), item.getS1_6(), item.getS1_7(), item.getS1_8());
        mAdapter.notifyDataSetChanged();
    }

    public void addItem(String s1_1, String s1_2, String s1_3, String s1_4, String s1_5, String s1_6, String s1_7, String s1_8) {// 리사이클러뷰에 아이템을 추가하는 메소드
        RecyclerItem item = new RecyclerItem();
        item.setS1_1(sharedPref.getString("S1_1T","-"));
        item.setS1_2(sharedPref.getString("S1_2T","-"));
        item.setS1_3(sharedPref.getString("S1_3T","-"));
        item.setS1_4(sharedPref.getString("S1_4T","-"));
        item.setS1_5(sharedPref.getString("S1_5T","-"));
        item.setS1_6(sharedPref.getString("S1_6T","-"));
        item.setS1_7(sharedPref.getString("S1_7T","-"));
        item.setS1_8(sharedPref.getString("S1_8T","-"));
        mList.add(item);
    }

}
/*


    Button onoff_manage_btn = convertView.findViewById(R.id.on_off_manage_btn);
    Button onoff_lookup_btn = convertView.findViewById(R.id.on_off_lookup_btn);
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
        setAdapter();
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
*/
