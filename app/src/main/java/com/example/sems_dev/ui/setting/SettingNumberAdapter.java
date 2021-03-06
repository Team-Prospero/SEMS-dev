package com.example.sems_dev.ui.setting;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sems_dev.R;

import java.util.ArrayList;
import java.util.List;


public class SettingNumberAdapter extends ArrayAdapter {

    private Context context;
    private List list;
    private boolean index[] = {false, false, false, false, false};
    private int currentIndex = 0;
    private int count = 0;

    class ViewHolder {
        public TextView tvName;
        public TextView tvNumber;
        public Button btnOnOff;
        public Button btnDelete;
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
//        viewHolder.btnOnOff = (Button) convertView.findViewById(R.id.btnListViewOnOff);
        viewHolder.btnDelete = (Button) convertView.findViewById(R.id.btnListViewDelete);

        final SettingNumberClass settingNumberClass = (SettingNumberClass) list.get(position);
        viewHolder.tvName.setText(settingNumberClass.getName());
        viewHolder.tvNumber.setText(settingNumberClass.getNumber());
/*        viewHolder.btnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button click", Toast.LENGTH_SHORT).show();
            }
        });*/
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "?????????????????? ?????? ???????????????.", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.btnDelete.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("??????").setMessage("????????? ?????????????????????????");
                builder.setPositiveButton("???", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // ????????? index??? ?????? ?????? ?????????
                        String command[] = {"_Farm", "_NUM", "_TIME", "_LIMT", "_KIND", "_USE", "_WA"};
                        for(int i = 0; i<7; i++){
                            SharedPreferences sharedPreferences = context.getSharedPreferences(settingNumberClass.getIndex() + command[i], 0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                        }
                        // ????????? COUNT ????????????
                        SharedPreferences sharedPreferences = context.getSharedPreferences("COUNT", 0);
                        for(int i = 0 ; i<=4 ; i++){
                            index[i] = sharedPreferences.getBoolean("index" + i, false);
                        }

                        count = sharedPreferences.getInt("count", 0);
                        currentIndex = sharedPreferences.getInt("currentIndex", 0);

                        // ?????? COUNT ????????????
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        index[settingNumberClass.getIndex()] = false; // ????????? index false

                        editor.putBoolean("index" + settingNumberClass.getIndex(), index[settingNumberClass.getIndex()]);
                        count--;
                        for(int i = 0 ; i<=4 ; i++){
                            if(index[i]==false){ // 0????????? ???????????? ??? index ??????
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

                        list.remove(list.get(position));
                        notifyDataSetChanged();
                        Toast.makeText(context, "?????????????????????.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("?????????", null);
                builder.create();
                builder.show();

                return false;
            }
        });
        viewHolder.tvName.setTag(settingNumberClass.getName());
        
        return convertView;
    }

}