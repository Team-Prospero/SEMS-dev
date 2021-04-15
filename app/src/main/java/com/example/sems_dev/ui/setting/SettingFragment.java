package com.example.sems_dev.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sems_dev.R;

public class SettingFragment extends Fragment {
    static final String[] SETTING_LIST = {"기계 설정 시 비밀번호", "SEMS 경보기 켜기/끄기", "경보기 전화번호 설정"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, SETTING_LIST);
        ListView listview = view.findViewById(R.id.setting_listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //기계 설정 시 비밀번호
                        Toast.makeText(view.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                    case 1: //SEMS 경보기 켜기/끄기
                        Toast.makeText(view.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                    case 2: //경보기 전화번호 설정
                        Intent intent = new Intent(getActivity(), SettingNumberActivity.class); // SettingNumberActivity로 이동
                        startActivity(intent);
                }
            }
        });
        return view;
    }
}