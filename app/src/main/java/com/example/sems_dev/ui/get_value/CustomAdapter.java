package com.example.sems_dev.ui.get_value;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sems_dev.R;

import java.util.ArrayList;


public class CustomAdapter extends Fragment {
    private EditText[] Editsensor_Name = new EditText[8];
    private TextView[] SensorData = new TextView[8];
    private TextView[] Temperature = new TextView[8];
    private String fileName = "";
    private String fileName1 = "";
    private int position;
    String text[] = new String[8];
    String temp;
    //default
    public CustomAdapter()
    {
        // required
    }
    //string params
    public CustomAdapter(String fileName,String fileName1, int position){
        this.fileName = fileName;
        this.fileName1 = fileName1;
        this.position = position;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FrameLayout layout = (FrameLayout)inflater.inflate(R.layout.fragment_get_val2,
                container, false);
        SensorData[0] = (TextView) layout.findViewById(R.id.SensorData1);
        SensorData[1] = (TextView) layout.findViewById(R.id.SensorData2);
        SensorData[2] = (TextView) layout.findViewById(R.id.SensorData3);
        SensorData[3] = (TextView) layout.findViewById(R.id.SensorData4);
        SensorData[4] = (TextView) layout.findViewById(R.id.SensorData5);
        SensorData[5] = (TextView) layout.findViewById(R.id.SensorData6);
        SensorData[6] = (TextView) layout.findViewById(R.id.SensorData7);
        SensorData[7] = (TextView) layout.findViewById(R.id.SensorData8);

        Temperature[0] = (TextView) layout.findViewById(R.id.Temperature1);
        Temperature[1] = (TextView) layout.findViewById(R.id.Temperature2);
        Temperature[2] = (TextView) layout.findViewById(R.id.Temperature3);
        Temperature[3] = (TextView) layout.findViewById(R.id.Temperature4);
        Temperature[4] = (TextView) layout.findViewById(R.id.Temperature5);
        Temperature[5] = (TextView) layout.findViewById(R.id.Temperature6);
        Temperature[6] = (TextView) layout.findViewById(R.id.Temperature7);
        Temperature[7] = (TextView) layout.findViewById(R.id.Temperature8);

        Editsensor_Name[0] = (EditText) layout.findViewById(R.id.Editsensor1_Name);
        Editsensor_Name[1] = (EditText) layout.findViewById(R.id.Editsensor2_Name);
        Editsensor_Name[2] = (EditText) layout.findViewById(R.id.Editsensor3_Name);
        Editsensor_Name[3] = (EditText) layout.findViewById(R.id.Editsensor4_Name);
        Editsensor_Name[4] = (EditText) layout.findViewById(R.id.Editsensor5_Name);
        Editsensor_Name[5] = (EditText) layout.findViewById(R.id.Editsensor6_Name);
        Editsensor_Name[6] = (EditText) layout.findViewById(R.id.Editsensor7_Name);
        Editsensor_Name[7] = (EditText) layout.findViewById(R.id.Editsensor8_Name);

        ArrayList<String> tempArr = new ArrayList<>();
        for(int i = 0; i < Editsensor_Name.length; i++) {
            //getSharedPreferences Edittext저장
            SharedPreferences sf = this.getActivity().getSharedPreferences(fileName,0);
            Editsensor_Name[i].setText(sf.getString(String.valueOf(i),""));
            Log.d("[sp]", "S" + position + "_" +(2)+"T");


            SharedPreferences sf1 = this.getActivity().getSharedPreferences(fileName1, 0);

            SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

                    switch (s){
                        case "S1_1T":
                            Temperature[0].setText(sf1.getString("S" + position + "_" +(1)+"T", ""));
                            break;
                        case "S1_2T":
                            Log.d("[spchange]", sf1.getString("S" + position + "_" +(2)+"T", ""));
                            Temperature[1].setText(sf1.getString("S" + position + "_" +(2)+"T", ""));
                            break;
                        case "S1_3T":
                            Temperature[2].setText(sf1.getString("S" + position + "_" +(3)+"T", ""));
                            break;
                        case "S1_4T":
                            Temperature[3].setText(sf1.getString("S" + position + "_" +(4)+"T", ""));
                            break;
                        case "S1_5T":
                            Temperature[4].setText("OFF");
                            break;
                        case "S1_c6T":
                            Temperature[5].setText("OFF");
                            break;
                        case "S1_7T":
                            Temperature[6].setText("OFF");
                            break;
                        case "S1_8T":
                            Temperature[7].setText("OFF");
                            break;
                    }
                }
            };
            sf1.registerOnSharedPreferenceChangeListener(listener);




            temp = sf1.getString("S" + position + "_" +(i+1)+"T", "");
//            String temp1 = sf1.getString("S" + position + "_" +(i+1)+"H", "");
//            String temp2 = sf1.getString("S" + position + "_" +(i+1)+"W", "");

            tempArr.add(temp);
            //tempArr.add(temp1);tempArr.add(temp2);
            Temperature[i].setText(temp);
//            Temperature[i].setText(temp1);
     //       Temperature[i].setText(temp2);


        }
//        for(int i = 0; i < Editsensor_Name.length; i++) {
//            if (Temperature[i].getText().toString().contains("T")) {
//                SensorData[i].setText("온도");
//            } else if (Temperature[i].getText().toString().contains("H")) {
//                SensorData[i].setText("습도");
//            } else if (Temperature[i].getText().toString().contains("W")) {
//                SensorData[i].setText("강수");
//            }
//        }
        return layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "onDestroy");
        for(int i = 0; i < Editsensor_Name.length; i++) {
            // Activity가 종료되기 전에 저장한다.
            //SharedPreferences를 sFile이름, 기본모드로 설정
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(fileName, 0);

            //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(String.valueOf(i),Editsensor_Name[i].getText().toString());
            // 사용자가 입력한 저장할 데이터
            editor.apply();

        }
    }

}