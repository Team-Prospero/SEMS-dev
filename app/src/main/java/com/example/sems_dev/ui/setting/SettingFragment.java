package com.example.sems_dev.ui.setting;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sems_dev.R;
import com.example.sems_dev.SendSMS;

import java.util.ArrayList;

public class SettingFragment extends Fragment{

    ArrayList<SettingNumberClass> settingNumber;
    ListView listView;
    private static SettingNumberAdapter settingNumberAdapter;

    private Button addNum, delNum;
    private boolean index[] = {false, false, false, false, false}; // 5개중 몇번 째가 비어있는지 확인하는 배열
    private int currentIndex = 0; // 저장할 때 몇 번째에 넣어줄지 확인하는 변수
    private int count = 0; // 전체 개수

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);

        settingNumber = new ArrayList<>();

        listView = (ListView)view.findViewById(R.id.numberList);
        settingNumberAdapter = new SettingNumberAdapter(getContext(),settingNumber);
        listView.setAdapter(settingNumberAdapter);

        addNum = view.findViewById(R.id.addNum);
//        delNum = view.findViewById(R.id.delNum);

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

/*        delNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return view;
    }

    /** 어플 실행시 1회 실행되는 저장소의 데이터를 불러오가*/
    public void loadData(){
        for(int i = 0 ; i<=5 ; i++){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(i+ "_Farm", 0);
        String name = sharedPreferences.getString("name","");
        String number = sharedPreferences.getString("number","");
            if(name.length() != 0 || number.length() != 0) {
            settingNumber.add(new SettingNumberClass(name, number, i));
            }
        }
    }
    /** 데이터를 저장소에 저장 */
    public void saveData(String name, String number){
        loadCount();
        if(count <= 5){
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(currentIndex + "_Farm", 0);
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
    /**  */
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
        index[currentIndex] = true; // 현재 index가 사용중임을 알림
        editor.putBoolean("index" + currentIndex, index[currentIndex]);
        count++;
        for(int i = 0 ; i<=4 ; i++){
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
                settingNumber.add(new SettingNumberClass(name, number, currentIndex));
                settingNumberAdapter.notifyDataSetChanged();
                saveData(name, number);
            }
        }
    }

}
