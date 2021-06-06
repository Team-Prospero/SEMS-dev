package com.example.sems_dev.ui.setting;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sems_dev.R;
import com.example.sems_dev.SendSMS;

import java.util.ArrayList;
import java.util.List;


public class SettingNumberAdapter extends ArrayAdapter {

    private Context context;
    private List list;

    class ViewHolder {
        public TextView tvName;
        public TextView tvNumber;
        public Button btnOnOff;
    }

    public SettingNumberAdapter(Context context, ArrayList list){
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.listview_custom, parent, false);
        }

        viewHolder = new ViewHolder();
        viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvListViewName);
        viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tvListViewNumber);
        viewHolder.btnOnOff = (Button) convertView.findViewById(R.id.btnListViewOnOff);

        final SettingNumberClass settingNumberClass = (SettingNumberClass) list.get(position);
        viewHolder.tvName.setText(settingNumberClass.getName());
        viewHolder.tvNumber.setText(settingNumberClass.getNumber());
        viewHolder.btnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button click", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.tvName.setTag(settingNumberClass.getName());
        
        return convertView;
    }


}