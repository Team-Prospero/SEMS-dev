package com.example.sems_dev.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sems_dev.R;

public class InfoFragment extends Fragment {
    static final String[] INFO_LIST = {"비상연락처 조회", "정규 문자 시간 조회", "센서 경고범위 조회","센서종류 조회","센서 사용유무조회","음수량 경고범위 조회"};

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, null);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, INFO_LIST);
        ListView listview = view.findViewById(R.id.info_listview);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(view.getContext(),parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}