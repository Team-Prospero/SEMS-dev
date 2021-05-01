package com.example.sems_dev.ui.setting;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sems_dev.R;

import java.util.ArrayList;
import java.util.List;

public class SettingNumberAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private List list;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
    }

    class ViewHolder {
        public TextView tvName;
        public TextView tvNumber;
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

        final SettingNumberClass settingNumberClass = (SettingNumberClass) list.get(position);
        viewHolder.tvName.setText(settingNumberClass.getName());
        viewHolder.tvNumber.setText(settingNumberClass.getNumber());

        viewHolder.tvName.setTag(settingNumberClass.getName());
        
        return convertView;
    }
}