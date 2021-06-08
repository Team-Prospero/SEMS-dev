package com.example.sems_dev.ui.sensor_warning;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        View view = inflater.inflate(R.layout.recycler_item_sensor_warning, parent, false);
        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {
        RecyclerItem item = mData.get(position);
        holder.farmNumber.setText(item.getFarmNumber() + " 농장");
        holder.s1_1_H.setText(item.getS1_1_H());
        holder.s1_2_H.setText(item.getS1_2_H());
        holder.s1_3_H.setText(item.getS1_3_H());
        holder.s1_4_H.setText(item.getS1_4_H());
        holder.s1_5_H.setText(item.getS1_5_H());
        holder.s1_6_H.setText(item.getS1_6_H());
        holder.s1_7_H.setText(item.getS1_7_H());
        holder.s1_8_H.setText(item.getS1_8_H());

        holder.s1_1_L.setText(item.getS1_1_L());
        holder.s1_2_L.setText(item.getS1_2_L());
        holder.s1_3_L.setText(item.getS1_3_L());
        holder.s1_4_L.setText(item.getS1_4_L());
        holder.s1_5_L.setText(item.getS1_5_L());
        holder.s1_6_L.setText(item.getS1_6_L());
        holder.s1_7_L.setText(item.getS1_7_L());
        holder.s1_8_L.setText(item.getS1_8_L());
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
        TextView s1_1_H, s1_2_H, s1_3_H, s1_4_H, s1_5_H, s1_6_H, s1_7_H, s1_8_H,
                s1_1_L, s1_2_L, s1_3_L, s1_4_L, s1_5_L, s1_6_L, s1_7_L, s1_8_L;
        Button warning_lookup_btn, warning_manage_btn;
        SharedPreferences sp, msg_sp;
        SharedPreferences.Editor editor;
        AlertDialog.Builder warningDialog = new AlertDialog.Builder(itemView.getContext());

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            farmNumber = itemView.findViewById(R.id.sw_farmNumber);

            Area_1 = itemView.findViewById(R.id.lm_area_1);
            Area_2 = itemView.findViewById(R.id.lm_area_2);
            Area_3 = itemView.findViewById(R.id.lm_area_3);
            Area_4 = itemView.findViewById(R.id.lm_area_4);
            Area_5 = itemView.findViewById(R.id.lm_area_5);
            Area_6 = itemView.findViewById(R.id.lm_area_6);
            Area_7 = itemView.findViewById(R.id.lm_area_7);
            Area_8 = itemView.findViewById(R.id.lm_area_8);

            s1_1_H = itemView.findViewById(R.id.S1_1T_H);
            s1_2_H = itemView.findViewById(R.id.S1_2T_H);
            s1_3_H = itemView.findViewById(R.id.S1_3T_H);
            s1_4_H = itemView.findViewById(R.id.S1_4T_H);
            s1_5_H = itemView.findViewById(R.id.S1_5T_H);
            s1_6_H = itemView.findViewById(R.id.S1_6T_H);
            s1_7_H = itemView.findViewById(R.id.S1_7T_H);
            s1_8_H = itemView.findViewById(R.id.S1_8T_H);

            s1_1_L = itemView.findViewById(R.id.S1_1T_L);
            s1_2_L = itemView.findViewById(R.id.S1_2T_L);
            s1_3_L = itemView.findViewById(R.id.S1_3T_L);
            s1_4_L = itemView.findViewById(R.id.S1_4T_L);
            s1_5_L = itemView.findViewById(R.id.S1_5T_L);
            s1_6_L = itemView.findViewById(R.id.S1_6T_L);
            s1_7_L = itemView.findViewById(R.id.S1_7T_L);
            s1_8_L = itemView.findViewById(R.id.S1_8T_L);

            warning_manage_btn = itemView.findViewById(R.id.warning_manage_btn);
            warning_lookup_btn = itemView.findViewById(R.id.warning_lookup_btn);
            sp = itemView.getContext().getSharedPreferences("0_LIMT", 0);
            msg_sp = itemView.getContext().getSharedPreferences("0_Farm", 0);
            editor = sp.edit();
            AlertDialog.Builder sensorWarningDialog = new AlertDialog.Builder(itemView.getContext());


            SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    // S1_1T_HIGH -> S(장비번호)_(구역번호)T_(HIGH or LOW)
                    switch (key) {
                        case "S1_1_HIGH":
                            s1_1_H.setText(sp.getString("S1_1_HIGH", "-"));
                            break;
                        case "S1_2_HIGH":
                            s1_2_H.setText(sp.getString("S1_2_HIGH", "-"));
                            break;
                        case "S1_3_HIGH":
                            s1_3_H.setText(sp.getString("S1_3_HIGH", "-"));
                            break;
                        case "S1_4_HIGH":
                            s1_4_H.setText(sp.getString("S1_4_HIGH", "-"));
                            break;
                        case "S1_5_HIGH":
                            s1_5_H.setText(sp.getString("S1_5_HIGH", "-"));
                            break;
                        case "S1_6_HIGH":
                            s1_6_H.setText(sp.getString("S1_6_HIGH", "-"));
                            break;
                        case "S1_7_HIGH":
                            s1_7_H.setText(sp.getString("S1_7_HIGH", "-"));
                            break;
                        case "S1_8_HIGH":
                            s1_8_H.setText(sp.getString("S1_8_HIGH", "-"));
                            break;

                        case "S1_1_LOW":
                            s1_1_L.setText(sp.getString("S1_1_LOW", "-"));
                            break;
                        case "S1_2_LOW":
                            s1_2_L.setText(sp.getString("S1_2_LOW", "-"));
                            break;
                        case "S1_3_LOW":
                            s1_3_L.setText(sp.getString("S1_3_LOW", "-"));
                            break;
                        case "S1_4_LOW":
                            s1_4_L.setText(sp.getString("S1_4_LOW", "-"));
                            break;
                        case "S1_5_LOW":
                            s1_5_L.setText(sp.getString("S1_5_LOW", "-"));
                            break;
                        case "S1_6_LOW":
                            s1_6_L.setText(sp.getString("S1_6_LOW", "-"));
                            break;
                        case "S1_7_LOW":
                            s1_7_L.setText(sp.getString("S1_7_LOW", "-"));
                            break;
                        case "S1_8_LOW":
                            s1_8_L.setText(sp.getString("S1_8_LOW", "-"));
                            break;
                    }
                }
            };

            warning_manage_btn.setOnClickListener(new View.OnClickListener() {

                View dialog = inflater.inflate(R.layout.dialog_warning_value_manage, null);

                Spinner equipNumber = dialog.findViewById(R.id.warn_val_equip_number);
                String[] equipItem = {"1번 장비"};
                Spinner sensorNumber = dialog.findViewById(R.id.warn_val_sensor_number);
                String[] sensorItem = {"1구역", "2구역", "3구역", "4구역", "5구역", "6구역", "7구역", "8구역"};
                EditText sensorHigh = dialog.findViewById(R.id.sensor_high);
                EditText sensorLow = dialog.findViewById(R.id.sensor_low);
                String msgbody, phone = msg_sp.getString("number", "01220788729");
                String high, low;
                int col, row;
                TableRow selectedRow;

                private void setAdapter() {

                    ArrayAdapter<String> equipNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, equipItem);
                    equipNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    equipNumber.setAdapter(equipNumberAdapter);

                    ArrayAdapter<String> sensorNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, sensorItem);
                    sensorNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sensorNumber.setAdapter(sensorNumberAdapter);

                    equipNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    col = 1;
                                    break;
                                case 1:
                                    col = 3;
                                    break;
                                case 2:
                                    col = 5;
                                    break;
                                case 3:
                                    col = 7;
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
                                    row = 1;
                                    selectedRow = itemView.findViewById(R.id.lm_area_1);
                                    break;
                                case 1:
                                    row = 2;
                                    selectedRow = itemView.findViewById(R.id.lm_area_2);
                                    break;
                                case 2:
                                    row = 3;
                                    selectedRow = itemView.findViewById(R.id.lm_area_3);
                                    break;
                                case 3:
                                    row = 4;
                                    selectedRow = itemView.findViewById(R.id.lm_area_4);
                                    break;
                                case 4:
                                    row = 5;
                                    selectedRow = itemView.findViewById(R.id.lm_area_5);
                                    break;
                                case 5:
                                    row = 6;
                                    selectedRow = itemView.findViewById(R.id.lm_area_6);
                                    break;
                                case 6:
                                    row = 7;
                                    selectedRow = itemView.findViewById(R.id.lm_area_7);
                                    break;
                                case 7:
                                    row = 8;
                                    selectedRow = itemView.findViewById(R.id.lm_area_8);
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                @Override
                public void onClick(View v) {
                    setAdapter();
                    sensorWarningDialog.setTitle("센서종류 변경");
                    sensorWarningDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String tag;
                            String limitHigh, limitLow;
                            // SET LIMT(장비)-(구역):(상위)(하위)
                            // S(장비번호)_(구역번호)T_(HIGH or LOW)
                            limitHigh = String.valueOf(sensorHigh.getText());
                            limitLow = String.valueOf(sensorLow.getText());
                            msgbody = "SET LIMT" + col + "-" + row + ":" + limitHigh + limitLow;
                            Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                            intent.putExtra("number", phone);
                            intent.putExtra("data", msgbody);
                            itemView.getContext().startActivity(intent);
                            sp.registerOnSharedPreferenceChangeListener(listener);
                            dialog.dismiss();
                        }
                    });
                    sensorWarningDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    if (dialog.getParent() != null) { // 부모 뷰에 하위 뷰가 여러번 띄워지는 것을 방지
                        ((ViewGroup) dialog.getParent()).removeView(dialog);
                    }
                    sensorWarningDialog.setView(dialog);
                    sensorWarningDialog.show();
                }
            });
            warning_lookup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                    intent.putExtra("number", msg_sp.getString("number", "-"));
                    intent.putExtra("data", "GET LIMT1");
                    itemView.getContext().startActivity(intent);
                    sp.registerOnSharedPreferenceChangeListener(listener);
                }
            });
        }
    }
}