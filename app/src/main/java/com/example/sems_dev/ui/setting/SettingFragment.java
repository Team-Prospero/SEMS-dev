package com.example.sems_dev.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class SettingFragment extends Fragment implements SettingNumberDialogFragment.MyDialogListener {

    ArrayList<SettingNumberClass> settingNumber;
    ListView listView;
    private static SettingNumberAdapter settingNumberAdapter;

    private Button addNum, delNum;
    private int count = 0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);

        settingNumber = new ArrayList<>();

        settingNumber.add(new SettingNumberClass("홍길동","010-1234-5678"));

        listView = (ListView)view.findViewById(R.id.numberList);
        settingNumberAdapter = new SettingNumberAdapter(getContext(),settingNumber);
        listView.setAdapter(settingNumberAdapter);

        addNum = view.findViewById(R.id.addNum);
        delNum = view.findViewById(R.id.delNum);

        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingNumberDialogFragment settingNumberDialogFragment = new SettingNumberDialogFragment(count);
                settingNumberDialogFragment.show(getFragmentManager(), "추가");

            }
        });

        return view;
    }

    @Override
    public void myCallback(String name, String number) {
        settingNumber.add(new SettingNumberClass(name,number));
    }

    public void loadData(){

    }
    public void saveData(){

    }
}
