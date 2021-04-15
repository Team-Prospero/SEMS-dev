package com.example.sems_dev.ui.setting;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NumberListItem> listItems = new ArrayList<NumberListItem>();

    public ListViewAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public  Object getItem(int i){
        return listItems.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // listview_custom.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_custom, parent, false);
        }

        // item.xml 의 참조 획득
        TextView tvListViewName = (TextView)convertView.findViewById(R.id.tvListViewName);
        TextView tvListViewNumber = (TextView)convertView.findViewById(R.id.tvListViewNumber);
        //Switch swListViewOnOff = (Switch) convertView.findViewById(R.id.swListViewOnOff);

        NumberListItem listItem = listItems.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        tvListViewName.setText(listItem.getName());
        tvListViewNumber.setText(listItem.getNumber());


        return convertView;
    }

    public void addItem(String name, String number){
        NumberListItem listItem = new NumberListItem();

        listItem.setName(name);
        listItem.setNumber(number);

        listItems.add(listItem);
    }
}
