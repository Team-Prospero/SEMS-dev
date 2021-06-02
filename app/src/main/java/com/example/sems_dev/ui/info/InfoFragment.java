package com.example.sems_dev.ui.info;
/*
 * 기능설명 : */

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sems_dev.R;
import com.example.sems_dev.ui.emergency_call.EmergencyCallActivity;
import com.example.sems_dev.ui.periodic_message.PeriodicMessageActivity;
import com.example.sems_dev.ui.sensor_kind.SensorKindActivity;
import com.example.sems_dev.ui.sensor_on_off.SensorOnOffActivity;
import com.example.sems_dev.ui.sensor_warning.SensorWarningActivity;
import com.example.sems_dev.ui.sensor_water_warning.SensorWaterWarningActivity;

public class InfoFragment extends Fragment {
    static final String[] INFO_LIST = {"비상연락처 조회", "정규 문자 시간 조회", "센서 경고범위 조회", "센서종류 조회", "센서 사용유무조회", "음수량 경고범위 조회"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, null);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, INFO_LIST);
        ListView listview = view.findViewById(R.id.info_listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 리스트 별로 터치리스너 설정
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentEmergencyCall = new Intent(getActivity(), EmergencyCallActivity.class);
                        startActivity(intentEmergencyCall);
                        break;
                    case 1:
                        Intent intentPeriodicMessage = new Intent(getActivity(), PeriodicMessageActivity.class);
                        startActivity(intentPeriodicMessage);
                        break;
                    case 2:
                        Intent intentSensorWarningActivity = new Intent(getActivity(), SensorWarningActivity.class);
                        startActivity(intentSensorWarningActivity);
                        break;
                    case 3:
                        Intent intentSensorKindActivity = new Intent(getActivity(), SensorKindActivity.class);
                        startActivity(intentSensorKindActivity);
                        break;
                    case 4:
                        Intent intentSensorOnOffActivity = new Intent(getActivity(), SensorOnOffActivity.class);
                        startActivity(intentSensorOnOffActivity);
                        break;
                    case 5:
                        Intent intentSensorWaterWarningActivity = new Intent(getActivity(), SensorWaterWarningActivity.class);
                        startActivity(intentSensorWaterWarningActivity);
                        break;
                }
            }
        });
        return view;
    }
}