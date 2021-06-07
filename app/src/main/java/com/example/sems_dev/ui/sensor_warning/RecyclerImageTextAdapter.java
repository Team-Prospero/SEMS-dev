package com.example.sems_dev.ui.sensor_warning;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> mData;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
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
        AlertDialog.Builder onOffDialog = new AlertDialog.Builder(itemView.getContext());

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            farmNumber = itemView.findViewById(R.id.farmNumber);

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

            sp = itemView.getContext().getSharedPreferences("0_LIMT", 0);
            editor = sp.edit();
            warning_manage_btn = itemView.findViewById(R.id.warning_manage_btn);
            warning_lookup_btn = itemView.findViewById(R.id.warning_lookup_btn);
            AlertDialog.Builder sensorWarningdialog = new AlertDialog.Builder(itemView.getContext());

            warning_manage_btn.setOnClickListener(new View.OnClickListener() {

                View dialog = inflater.inflate(R.layout.dialog_warning_value_manage, null);

                Spinner equipNumber = dialog.findViewById(R.id.warn_val_equip_number);
                String[] equipItem = {"1번 장비"};
                Spinner sensorNumber = dialog.findViewById(R.id.warn_val_sensor_number);
                String[] sensorItem = {"1구역", "2구역", "3구역", "4구역", "5구역", "6구역", "7구역", "8구역"};
                EditText sensorHigh = dialog.findViewById(R.id.sensor_high);
                EditText sensorLow = dialog.findViewById(R.id.sensor_low);

                String high, low;
                int col, row;
                TableRow selectedRow;

                private void setAdapter(){

                    ArrayAdapter<String> equipNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, equipItem);
                    equipNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    equipNumber.setAdapter(equipNumberAdapter);

                    ArrayAdapter<String> sensorNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, sensorItem);
                    sensorNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sensorNumber.setAdapter(sensorNumberAdapter);

                    equipNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position){
                                case 0:
                                    col=1;
                                    break;
                                case 1:
                                    col=3;
                                    break;
                                case 2:
                                    col=5;
                                    break;
                                case 3:
                                    col=7;
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
                            switch (position){
                                case 0:
                                    row=1;
                                    selectedRow=itemView.findViewById(R.id.lm_area_1);
                                    break;
                                case 1:
                                    row=2;
                                    selectedRow=itemView.findViewById(R.id.lm_area_2);
                                    break;
                                case 2:
                                    row=3;
                                    selectedRow=itemView.findViewById(R.id.lm_area_3);
                                    break;
                                case 3:
                                    row=4;
                                    selectedRow=itemView.findViewById(R.id.lm_area_4);
                                    break;
                                case 4:
                                    row=5;
                                    selectedRow=itemView.findViewById(R.id.lm_area_5);
                                    break;
                                case 5:
                                    row=6;
                                    selectedRow=itemView.findViewById(R.id.lm_area_6);
                                    break;
                                case 6:
                                    row=7;
                                    selectedRow=itemView.findViewById(R.id.lm_area_7);
                                    break;
                                case 7:
                                    row=8;
                                    selectedRow=itemView.findViewById(R.id.lm_area_8);
                                    break;
                            }
                            Toast.makeText(itemView.getContext(), "S1_"+row+"T_HIGH : S1_"+row+"T_LOW ", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                @Override
                public void onClick(View v) {
                    setAdapter();
                    onOffDialog.setTitle("센서종류 변경");
                    onOffDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String tag;
                            TextView limitHigh = (TextView) selectedRow.getChildAt(col);
                            TextView limitLow = (TextView) selectedRow.getChildAt(col+1);
                            limitHigh.setText(sensorHigh.getText());
                            limitLow.setText(sensorLow.getText());
                            switch (col){
                                case 1:
                                    editor.putString("S1_"+row+"T_HIGH", String.valueOf(sensorHigh.getText()));
                                    editor.putString("S1_"+row+"T_LOW",String.valueOf(sensorLow.getText()));
                                    editor.apply();
                                    break;
                                case 3:
                                    editor.putString("S2_"+row+"T_HIGH", String.valueOf(sensorHigh.getText()));
                                    editor.putString("S2_"+row+"T_LOW",String.valueOf(sensorLow.getText()));
                                    editor.apply();
                                    break;
                                case 5:
                                    editor.putString("S3_"+row+"T_HIGH", String.valueOf(sensorHigh.getText()));
                                    editor.putString("S3_"+row+"T_LOW",String.valueOf(sensorLow.getText()));
                                    editor.apply();
                                    break;
                                case 7:
                                    editor.putString("S4_"+row+"T_HIGH", String.valueOf(sensorHigh.getText()));
                                    editor.putString("S4_"+row+"T_LOW",String.valueOf(sensorLow.getText()));
                                    editor.apply();
                                    break;
                            }
                            Toast.makeText(itemView.getContext(), "설정되었습니다", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    onOffDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    if(dialog.getParent()!=null){ // 부모 뷰에 하위 뷰가 여러번 띄워지는 것을 방지
                        ((ViewGroup) dialog.getParent()).removeView(dialog);
                    }
                    onOffDialog.setView(dialog);
                    onOffDialog.show();
                }
            });
            warning_lookup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecyclerItem item = new RecyclerItem();
                    item.setS1_1_H(sp.getString("S1_1T_HIGH","-"));
                    item.setS1_2_H(sp.getString("S1_2T_HIGH","-"));
                    item.setS1_3_H(sp.getString("S1_3T_HIGH","-"));
                    item.setS1_4_H(sp.getString("S1_4T_HIGH","-"));
                    item.setS1_5_H(sp.getString("S1_5T_HIGH","-"));
                    item.setS1_6_H(sp.getString("S1_6T_HIGH","-"));
                    item.setS1_7_H(sp.getString("S1_7T_HIGH","-"));
                    item.setS1_8_H(sp.getString("S1_8T_HIGH","-"));

                    item.setS1_1_L(sp.getString("S1_1T_LOW","-"));
                    item.setS1_2_L(sp.getString("S1_2T_LOW","-"));
                    item.setS1_3_L(sp.getString("S1_3T_LOW","-"));
                    item.setS1_4_L(sp.getString("S1_4T_LOW","-"));
                    item.setS1_5_L(sp.getString("S1_5T_LOW","-"));
                    item.setS1_6_L(sp.getString("S1_6T_LOW","-"));
                    item.setS1_7_L(sp.getString("S1_7T_LOW","-"));
                    item.setS1_8_L(sp.getString("S1_8T_LOW","-"));

                    s1_1_H.setText(item.getS1_1_H());
                    s1_2_H.setText(item.getS1_2_H());
                    s1_3_H.setText(item.getS1_3_H());
                    s1_4_H.setText(item.getS1_4_H());
                    s1_5_H.setText(item.getS1_5_H());
                    s1_6_H.setText(item.getS1_6_H());
                    s1_7_H.setText(item.getS1_7_H());
                    s1_8_H.setText(item.getS1_8_H());

                    s1_1_L.setText(item.getS1_1_L());
                    s1_2_L.setText(item.getS1_2_L());
                    s1_3_L.setText(item.getS1_3_L());
                    s1_4_L.setText(item.getS1_4_L());
                    s1_5_L.setText(item.getS1_5_L());
                    s1_6_L.setText(item.getS1_6_L());
                    s1_7_L.setText(item.getS1_7_L());
                    s1_8_L.setText(item.getS1_8_L());

                    Toast.makeText(itemView.getContext(), "갱신완료", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}