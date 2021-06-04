package com.example.sems_dev.ui.sensor_kind;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
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
    private ArrayList<RecyclerItem> mData = null;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RecyclerImageTextAdapter(ArrayList<RecyclerItem> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_item_sensor_kind, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        RecyclerItem item = mData.get(position);
        
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
        Button kind_lookup_btn, kind_manage_btn;
        SharedPreferences sp;
        SharedPreferences.Editor editor;
        AlertDialog.Builder sKindDialog = new AlertDialog.Builder(itemView.getContext());

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            farmNumber = itemView.findViewById(R.id.farmNumber);
            Area_1 = itemView.findViewById(R.id.area_1);
            Area_2 = itemView.findViewById(R.id.area_2);
            Area_3 = itemView.findViewById(R.id.area_3);
            Area_4 = itemView.findViewById(R.id.area_4);
            Area_5 = itemView.findViewById(R.id.area_5);
            Area_6 = itemView.findViewById(R.id.area_6);
            Area_7 = itemView.findViewById(R.id.area_7);
            Area_8 = itemView.findViewById(R.id.area_8);
            kind_lookup_btn = itemView.findViewById(R.id.kind_lookup_btn);
            kind_manage_btn = itemView.findViewById(R.id.kind_manage_btn);
            sp = itemView.getContext().getSharedPreferences("sensor_kind", 0);
            editor = sp.edit();
            kind_manage_btn.setOnClickListener(new View.OnClickListener() {
                View dialog = inflater.inflate(R.layout.dialog_kind_manage, null);
                Spinner equipNumber = dialog.findViewById(R.id.kind_equip_number);
                String[] equipItem = {"1번 장비","2번 장비","3번 장비","4번 장비"};
                Spinner sensorNumber = dialog.findViewById(R.id.kind_sensor_number);
                String[] sensorItem = {"1구역","2구역","3구역","4구역","5구역","6구역","7구역","8구역"};
                Spinner sensorKind = dialog.findViewById(R.id.sensor_kind);
                String[] kindItem = {"기타","온도","습도","음수"};

                String sKind = null;
                int col;
                TableRow selectedRow;

                private void setAdapter(){

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
                            switch (position){
                                case 0:
                                    col=1;
                                case 1:
                                    col=2;
                                case 2:
                                    col=3;
                                case 3:
                                    col=4;
                            }
                            Toast.makeText(itemView.getContext(), equipItem[position], Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
// col 1 2 3 4
// row 2 3 4 5 6 7 8 9
                    sensorNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position){
                                case 0:
                                    selectedRow=itemView.findViewById(R.id.area_1);
                                case 1:
                                    selectedRow=itemView.findViewById(R.id.area_2);
                                case 2:
                                    selectedRow=itemView.findViewById(R.id.area_3);
                                case 3:
                                    selectedRow=itemView.findViewById(R.id.area_4);
                                case 4:
                                    selectedRow=itemView.findViewById(R.id.area_5);
                                case 5:
                                    selectedRow=itemView.findViewById(R.id.area_6);
                                case 6:
                                    selectedRow=itemView.findViewById(R.id.area_7);
                                case 7:
                                    selectedRow=itemView.findViewById(R.id.area_8);
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
                            switch (position){
                                case 0:
                                    sKind="기타";
                                case 1:
                                    sKind="온도";
                                case 2:
                                    sKind="습도";
                                case 3:
                                    sKind="음수";
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
                            TextView textView = (TextView) selectedRow.getChildAt(col);
                            textView.setText(sKind);
                            Toast.makeText(itemView.getContext(), "설정되었습니다", Toast.LENGTH_SHORT).show();
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
                    if(dialog.getParent()!=null){ // 부모 뷰에 하위 뷰가 여러번 띄워지는 것을 방지
                        ((ViewGroup) dialog.getParent()).removeView(dialog);
                    }
                    sKindDialog.setView(dialog);
                    sKindDialog.show();
                }
            });
        }
    }
}
