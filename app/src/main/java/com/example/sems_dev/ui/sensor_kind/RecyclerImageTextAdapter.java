package com.example.sems_dev.ui.sensor_kind;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;
import com.example.sems_dev.SendSMS;

import java.util.ArrayList;

public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> mData;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RecyclerImageTextAdapter(ArrayList<RecyclerItem> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_item_sensor_kind, parent, false);
        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {
        RecyclerItem item = mData.get(position);
        holder.farmNumber.setText(item.getFarmNumber() + " 농장");
        holder.s1_1.setText(item.getS1_1());
        holder.s1_2.setText(item.getS1_2());
        holder.s1_3.setText(item.getS1_3());
        holder.s1_4.setText(item.getS1_4());
        holder.s1_5.setText(item.getS1_5());
        holder.s1_6.setText(item.getS1_6());
        holder.s1_7.setText(item.getS1_7());
        holder.s1_8.setText(item.getS1_8());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutInflater inflater = (LayoutInflater) itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView farmNumber;
        TableRow Area_1, Area_2, Area_3, Area_4, Area_5, Area_6, Area_7, Area_8;
        TextView s1_1, s1_2, s1_3, s1_4, s1_5, s1_6, s1_7, s1_8;
        Button kind_lookup_btn, kind_manage_btn;
        SharedPreferences sp, msg_sp;
        SharedPreferences.Editor editor;
        AlertDialog.Builder sKindDialog = new AlertDialog.Builder(itemView.getContext());

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            farmNumber = itemView.findViewById(R.id.sk_farmNumber);
            Area_1 = itemView.findViewById(R.id.area_1);
            Area_2 = itemView.findViewById(R.id.area_2);
            Area_3 = itemView.findViewById(R.id.area_3);
            Area_4 = itemView.findViewById(R.id.area_4);
            Area_5 = itemView.findViewById(R.id.area_5);
            Area_6 = itemView.findViewById(R.id.area_6);
            Area_7 = itemView.findViewById(R.id.area_7);
            Area_8 = itemView.findViewById(R.id.area_8);
            s1_1 = itemView.findViewById(R.id.s1_1);
            s1_2 = itemView.findViewById(R.id.s1_2);
            s1_3 = itemView.findViewById(R.id.s1_3);
            s1_4 = itemView.findViewById(R.id.s1_4);
            s1_5 = itemView.findViewById(R.id.s1_5);
            s1_6 = itemView.findViewById(R.id.s1_6);
            s1_7 = itemView.findViewById(R.id.s1_7);
            s1_8 = itemView.findViewById(R.id.s1_8);
            kind_lookup_btn = itemView.findViewById(R.id.kind_lookup_btn);
            kind_manage_btn = itemView.findViewById(R.id.kind_manage_btn);
            sp = itemView.getContext().getSharedPreferences("0_KIND", 0);
            msg_sp = itemView.getContext().getSharedPreferences("0_Farm", 0);
            editor = sp.edit();


            SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    String temp;
                    switch (key) {
                        case "S1_1":
                            temp = sharedPreferences.getString("S1_1", "-");
                            if (temp.equals("0"))
                                s1_1.setText("기타");
                            if (temp.equals("1"))
                                s1_1.setText("온도");
                            if (temp.equals("2"))
                                s1_1.setText("습도");
                            if (temp.equals("3"))
                                s1_1.setText("음수");
                            break;
                        case "S1_2":
                            temp = sharedPreferences.getString("S1_2", "-");
                            if (temp.equals("0"))
                                s1_2.setText("기타");
                            if (temp.equals("1"))
                                s1_2.setText("온도");
                            if (temp.equals("2"))
                                s1_2.setText("습도");
                            if (temp.equals("3"))
                                s1_2.setText("음수");
                            break;
                        case "S1_3":
                            temp = sharedPreferences.getString("S1_3", "-");
                            if (temp.equals("0"))
                                s1_3.setText("기타");
                            if (temp.equals("1"))
                                s1_3.setText("온도");
                            if (temp.equals("2"))
                                s1_3.setText("습도");
                            if (temp.equals("3"))
                                s1_3.setText("음수");
                            break;
                        case "S1_4":
                            temp = sharedPreferences.getString("S1_4", "-");
                            if (temp.equals("0"))
                                s1_4.setText("기타");
                            if (temp.equals("1"))
                                s1_4.setText("온도");
                            if (temp.equals("2"))
                                s1_4.setText("습도");
                            if (temp.equals("3"))
                                s1_4.setText("음수");
                            break;
                        case "S1_5":
                            temp = sharedPreferences.getString("S1_5", "-");
                            if (temp.equals("0"))
                                s1_5.setText("기타");
                            if (temp.equals("1"))
                                s1_5.setText("온도");
                            if (temp.equals("2"))
                                s1_5.setText("습도");
                            if (temp.equals("3"))
                                s1_5.setText("음수");
                            break;
                        case "S1_6":
                            temp = sharedPreferences.getString("S1_6", "-");
                            if (temp.equals("0"))
                                s1_6.setText("기타");
                            if (temp.equals("1"))
                                s1_6.setText("온도");
                            if (temp.equals("2"))
                                s1_6.setText("습도");
                            if (temp.equals("3"))
                                s1_6.setText("음수");
                            break;
                        case "S1_7":
                            temp = sharedPreferences.getString("S1_7", "-");
                            if (temp.equals("0"))
                                s1_7.setText("기타");
                            if (temp.equals("1"))
                                s1_7.setText("온도");
                            if (temp.equals("2"))
                                s1_7.setText("습도");
                            if (temp.equals("3"))
                                s1_7.setText("음수");
                            break;
                        case "S1_8":
                            temp = sharedPreferences.getString("S1_8", "0");
                            if (temp.equals("0"))
                                s1_8.setText("기타");
                            if (temp.equals("1"))
                                s1_8.setText("온도");
                            if (temp.equals("2"))
                                s1_8.setText("습도");
                            if (temp.equals("3"))
                                s1_8.setText("음수");
                            break;
                    }
                }
            };


            kind_manage_btn.setOnClickListener(new View.OnClickListener() {
                View dialog = inflater.inflate(R.layout.dialog_kind_manage, null);
                Spinner equipNumber = dialog.findViewById(R.id.kind_equip_number);
                String[] equipItem = {"1번 장비"};
                Spinner sensorNumber = dialog.findViewById(R.id.kind_sensor_number);
                String[] sensorItem = {"1구역", "2구역", "3구역", "4구역", "5구역", "6구역", "7구역", "8구역"};
                Spinner sensorKind = dialog.findViewById(R.id.sensor_kind);
                String[] kindItem = {"기타", "온도", "습도", "음수"};

                String msgbody, sKind = null, phone = msg_sp.getString("number", "01220788729");
                int col, row, pos;
                TableRow selectedRow;

                private void setAdapter() {

                    ArrayAdapter<String> equipNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, equipItem);
                    equipNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    equipNumber.setAdapter(equipNumberAdapter);

                    ArrayAdapter<String> sensorNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, sensorItem);
                    sensorNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sensorNumber.setAdapter(sensorNumberAdapter);

                    ArrayAdapter<String> sensorKindAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, kindItem);
                    sensorKindAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sensorKind.setAdapter(sensorKindAdapter);

                    equipNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    col = 1;
                                    break;
                                case 1:
                                    col = 2;
                                    break;
                                case 2:
                                    col = 3;
                                    break;
                                case 3:
                                    col = 4;
                                    break;
                            }
                            Toast.makeText(itemView.getContext(), equipItem[position], Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    sensorNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    selectedRow = itemView.findViewById(R.id.area_1);
                                    row = 1;
                                    break;
                                case 1:
                                    selectedRow = itemView.findViewById(R.id.area_2);
                                    row = 2;
                                    break;
                                case 2:
                                    selectedRow = itemView.findViewById(R.id.area_3);
                                    row = 3;
                                    break;
                                case 3:
                                    selectedRow = itemView.findViewById(R.id.area_4);
                                    row = 4;
                                    break;
                                case 4:
                                    selectedRow = itemView.findViewById(R.id.area_5);
                                    row = 5;
                                    break;
                                case 5:
                                    selectedRow = itemView.findViewById(R.id.area_6);
                                    row = 6;
                                    break;
                                case 6:
                                    selectedRow = itemView.findViewById(R.id.area_7);
                                    row = 7;
                                    break;
                                case 7:
                                    selectedRow = itemView.findViewById(R.id.area_8);
                                    row = 8;
                                    break;
                            }
                            Toast.makeText(itemView.getContext(), sensorItem[position], Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    sensorKind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            pos = position;
                            switch (pos) {
                                case 0:
                                    sKind = "기타";
                                    break;
                                case 1:
                                    sKind = "온도";
                                    break;
                                case 2:
                                    sKind = "습도";
                                    break;
                                case 3:
                                    sKind = "음수";
                                    break;
                            }
                            Toast.makeText(itemView.getContext(), kindItem[position], Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                @Override
                public void onClick(View v) {
                    setAdapter();
                    sKindDialog.setTitle("센서종류 변경");
                    sKindDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            msgbody = "SET KIND" + col + "-" + row + ":" + pos;
                            Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                            intent.putExtra("number", phone);
                            intent.putExtra("data", msgbody);
                            itemView.getContext().startActivity(intent);
                            sp.registerOnSharedPreferenceChangeListener(listener);

                            dialog.dismiss();
                        }
                    });
                    sKindDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    if (dialog.getParent() != null) { // 부모 뷰에 하위 뷰가 여러번 띄워지는 것을 방지
                        ((ViewGroup) dialog.getParent()).removeView(dialog);
                    }
                    sKindDialog.setView(dialog);
                    sKindDialog.show();
                }
            });
            kind_lookup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                    intent.putExtra("number", "01220788729");
                    intent.putExtra("data", "GET KIND1");
                    itemView.getContext().startActivity(intent);
                    sp.registerOnSharedPreferenceChangeListener(listener);
                }
            });
        }
    }
}
