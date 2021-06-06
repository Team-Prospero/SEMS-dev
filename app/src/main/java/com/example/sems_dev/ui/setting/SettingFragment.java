package com.example.sems_dev.ui.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment{

    ArrayList<SettingNumberClass> settingNumber;
    ListView listView;
    private static SettingNumberAdapter settingNumberAdapter;

    private Button addNum, delNum;
    private boolean index[] = {false, false, false, false, false};
    private int currentIndex = 0;
    private int count = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);

        settingNumber = new ArrayList<>();

        listView = (ListView)view.findViewById(R.id.numberList);
        settingNumberAdapter = new SettingNumberAdapter(getContext(),settingNumber);
        listView.setAdapter(settingNumberAdapter);

        addNum = view.findViewById(R.id.addNum);
        delNum = view.findViewById(R.id.delNum);

        loadData();
        loadCount();

        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingNumberDialogFragment settingNumberDialogFragment = new SettingNumberDialogFragment(count);
                settingNumberDialogFragment.setDismissListener(new MyDismissListener());
                settingNumberDialogFragment.show(getFragmentManager(), "추가");
            }
        });

        delNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    public void loadData(){
        for(int i = 0 ; i<=5 ; i++){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(i+ "_Farm", 0);
        String name = sharedPreferences.getString("name","");
        String number = sharedPreferences.getString("number","");
            if(name.length() != 0 || number.length() != 0) {
            settingNumber.add(new SettingNumberClass(name, number));
            }
        }
    }
    public void saveData(String name, String number){
        if(count <= 5){
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(count + "_Farm", 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putString("number",number);
            editor.commit();
            saveCount();
        }
        else{
            Toast.makeText(getActivity(),"더이상 저장할 수 없습니다.", Toast.LENGTH_LONG).show();
        }

    }
    public void loadCount(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("COUNT", 0);
        for(int i = 0 ; i<=4 ; i++){
            index[i] = sharedPreferences.getBoolean("index" + i, false);
        }
        count = sharedPreferences.getInt("count", 0);
        currentIndex = sharedPreferences.getInt("currentIndex", 0);
    }
    public void saveCount(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("COUNT", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        index[currentIndex] = true;
        editor.putBoolean("index" + currentIndex, index[currentIndex]);
        count++;
        for(int i = 0 ; i<=5 ; i++){
            if(index[i]==false){
                currentIndex = i;
                break;
            }
            else{
                currentIndex = -1;
            }
        }
        editor.putInt("count", count);
        editor.putInt("currentIndex", currentIndex);
        editor.commit();
    }



    public class MyDismissListener extends DismissListener{
        @Override
        public void onDismiss(DialogInterface dialog) {
            String name = getValueForStr("name");
            String number = getValueForStr("number");
            if(name.length() != 0 || number.length() != 0) {
                settingNumber.add(new SettingNumberClass(name, number));
                saveData(name, number);
            }
        }
    }


}
