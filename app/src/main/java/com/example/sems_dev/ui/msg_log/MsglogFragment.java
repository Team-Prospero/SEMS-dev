package com.example.sems_dev.ui.msg_log;

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

public class MsglogFragment extends Fragment {
    static final String[] MSGLOG_LIST = {"메시지 1", "메시지 2", "메시지 3"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_log, null);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, MSGLOG_LIST);
        ListView listview = view.findViewById(R.id.msg_log_listview);
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
