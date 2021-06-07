package com.example.sems_dev.ui.sensor_water_warning;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_item_sensor_onoff, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerItem item = mData.get(position);
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
        TableLayout dataTable;
        TableRow Area_1, Area_2, Area_3, Area_4, Area_5, Area_6, Area_7, Area_8;
        TextView s1_1, s1_2, s1_3, s1_4, s1_5, s1_6, s1_7, s1_8;
        Button onoff_lookup_btn, onoff_manage_btn;
        AlertDialog.Builder onOffDialog = new AlertDialog.Builder(itemView.getContext());

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            farmNumber = itemView.findViewById(R.id.farmNumber);
            Area_1 = itemView.findViewById(R.id.of_area_1);
            Area_2 = itemView.findViewById(R.id.of_area_2);
            Area_3 = itemView.findViewById(R.id.of_area_3);
            Area_4 = itemView.findViewById(R.id.of_area_4);
            Area_5 = itemView.findViewById(R.id.of_area_5);
            Area_6 = itemView.findViewById(R.id.of_area_6);
            Area_7 = itemView.findViewById(R.id.of_area_7);
            Area_8 = itemView.findViewById(R.id.of_area_8);
            s1_1 = itemView.findViewById(R.id.of1_1);
            s1_2 = itemView.findViewById(R.id.of1_2);
            s1_3 = itemView.findViewById(R.id.of1_3);
            s1_4 = itemView.findViewById(R.id.of1_4);
            s1_5 = itemView.findViewById(R.id.of1_5);
            s1_6 = itemView.findViewById(R.id.of1_6);
            s1_7 = itemView.findViewById(R.id.of1_7);
            s1_8 = itemView.findViewById(R.id.of1_8);
            onoff_manage_btn = itemView.findViewById(R.id.on_off_manage_btn);
            onoff_lookup_btn = itemView.findViewById(R.id.on_off_lookup_btn);
            sp = itemView.getContext().getSharedPreferences("0_USE", 0);
            editor = sp.edit();

            onoff_manage_btn.setOnClickListener(new View.OnClickListener() {
                View dialog = inflater.inflate(R.layout.dialog_onoff_manage, null);
                Spinner equipNumber = dialog.findViewById(R.id.on_off_equip_number);
                String[] equipItem = {"1번 장비"};
                Spinner sensorNumber = dialog.findViewById(R.id.on_off_sensor_number);
                String[] sensorItem = {"1구역","2구역","3구역","4구역","5구역","6구역","7구역","8구역"};
                Spinner sensorOnOff = dialog.findViewById(R.id.sensor_on_off);
                String[] onOffItem = {"사용","미사용"};

                String isOnOff = null;
                int col;
                TableRow selectedRow;

                private void setAdapter(){

                    ArrayAdapter<String> equipNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, equipItem);
                    equipNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    equipNumber.setAdapter(equipNumberAdapter);

                    ArrayAdapter<String> sensorNumberAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, sensorItem);
                    sensorNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sensorNumber.setAdapter(sensorNumberAdapter);

                    ArrayAdapter<String> sensorOnOffAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, onOffItem);
                    sensorOnOffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sensorOnOff.setAdapter(sensorOnOffAdapter);

                    equipNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position){
                                case 0:
                                    col=1;
                                    break;
                                case 1:
                                    col=2;
                                    break;
                                case 2:
                                    col=3;
                                    break;
                                case 3:
                                    col=4;
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
                                    selectedRow=itemView.findViewById(R.id.of_area_1);
                                    break;
                                case 1:
                                    selectedRow=itemView.findViewById(R.id.of_area_2);
                                    break;
                                case 2:
                                    selectedRow=itemView.findViewById(R.id.of_area_3);
                                    break;
                                case 3:
                                    selectedRow=itemView.findViewById(R.id.of_area_4);
                                    break;
                                case 4:
                                    selectedRow=itemView.findViewById(R.id.of_area_5);
                                    break;
                                case 5:
                                    selectedRow=itemView.findViewById(R.id.of_area_6);
                                    break;
                                case 6:
                                    selectedRow=itemView.findViewById(R.id.of_area_7);
                                    break;
                                case 7:
                                    selectedRow=itemView.findViewById(R.id.of_area_8);
                                    break;
                            }
                            Toast.makeText(itemView.getContext(), sensorItem[position], Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    sensorOnOff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position){
                                case 0:
                                    isOnOff="ON";
                                    break;
                                case 1:
                                    isOnOff="OFF";
                                    break;
                            }
                            Toast.makeText(itemView.getContext(), onOffItem[position], Toast.LENGTH_SHORT).show();
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
                            TextView textView = (TextView) selectedRow.getChildAt(col);
                            textView.setText(isOnOff);
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
            onoff_lookup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecyclerItem item = new RecyclerItem();
                    item.setS1_1(sp.getString("S1_1T","-"));
                    item.setS1_2(sp.getString("S1_2T","-"));
                    item.setS1_3(sp.getString("S1_3T","-"));
                    item.setS1_4(sp.getString("S1_4T","-"));
                    item.setS1_5(sp.getString("S1_5T","-"));
                    item.setS1_6(sp.getString("S1_6T","-"));
                    item.setS1_7(sp.getString("S1_7T","-"));
                    item.setS1_8(sp.getString("S1_8T","-"));
                    s1_1.setText(item.getS1_1());
                    s1_2.setText(item.getS1_2());
                    s1_3.setText(item.getS1_3());
                    s1_4.setText(item.getS1_4());
                    s1_5.setText(item.getS1_5());
                    s1_6.setText(item.getS1_6());
                    s1_7.setText(item.getS1_7());
                    s1_8.setText(item.getS1_8());
                    Toast.makeText(itemView.getContext(), "갱신완료", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
