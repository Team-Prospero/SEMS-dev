package com.example.sems_dev.ui.periodic_message;

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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sems_dev.R;
import com.example.sems_dev.SendSMS;

import java.util.ArrayList;

public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> mData = null;
    public String msgbody = null;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    RecyclerImageTextAdapter(ArrayList<RecyclerItem> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_item_periodic_messege, parent, false);
        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {

        RecyclerItem item = mData.get(position);
        holder.farmNumber.setText(item.getFarmNumber() + " 농장");
        holder.pd_msg_1.setText(holder.sp.getString("1_Hour", "-") + " : " + holder.sp.getString("1_Min", "-"));
        holder.pd_msg_2.setText(holder.sp.getString("2_Hour", "-") + " : " + holder.sp.getString("2_Min", "-"));
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutInflater inflater = (LayoutInflater) itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView farmNumber;
        TextView pd_msg_1, pd_msg_2;
        Button pd_msg_edit, pd_msg_lookup;
        SharedPreferences sp, msg_sp;
        SharedPreferences.Editor editor;
        AlertDialog.Builder pdMsgDialog = new AlertDialog.Builder(itemView.getContext());

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            farmNumber = itemView.findViewById(R.id.pd_farmNumber);
            pd_msg_1 = itemView.findViewById(R.id.pd_msg_1);
            pd_msg_2 = itemView.findViewById(R.id.pd_msg_2);
            pd_msg_edit = itemView.findViewById(R.id.pd_msg_edit);
            pd_msg_lookup = itemView.findViewById(R.id.pd_msg_lookup);
            sp = itemView.getContext().getSharedPreferences("0_TIME", 0);
            msg_sp = itemView.getContext().getSharedPreferences("0_Farm", 0);
            editor = sp.edit();

            SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    switch (key) {
                        case "1_Hour":
                        case "1_Min":
                            pd_msg_1.setText(sharedPreferences.getString("1_Hour", "-") + " : " + sharedPreferences.getString("1_Min", "-"));
                            Log.d("[spChanged]", sharedPreferences.getString("1_Hour", "-") + " : " + sharedPreferences.getString("1_Min", "-"));
                            break;

                        case "2_Hour":
                        case "2_Min":
                            pd_msg_2.setText(sharedPreferences.getString("2_Hour", "-") + " : " + sharedPreferences.getString("2_Min", "-"));
                            Log.d("[spChanged]", sharedPreferences.getString("2_Hour", "-") + " : " + sharedPreferences.getString("2_Min", "-"));
                            break;
                    }
                }
            };

            pd_msg_edit.setOnClickListener(new View.OnClickListener() {
                View dialog = inflater.inflate(R.layout.dialog_periodic_message, null);
                Spinner pdMsg_spinner = dialog.findViewById(R.id.pd_msg_spinner);
                TimePicker pdMsg_timepicker = dialog.findViewById(R.id.pd_msg_timepicker);
                String[] pdMsg_item = {"1번 문자시간", "2번 문자시간"};
                String saveResult, pos, phone = msg_sp.getString("number", "01220788729");

                @Override
                public void onClick(View v) {
                    setAdapter();
                    pdMsgDialog.setTitle("정규문자시간 변경");
                    pdMsgDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveResult = setMsgTime(pdMsg_timepicker.getHour(), pdMsg_timepicker.getMinute());

                            switch (pos) {
                                case "1_HourN1_Min":
                                    msgbody = "SET TIME1:" + saveResult;
                                    break;

                                case "2_HourN2_Min":
                                    msgbody = "SET TIME2:" + saveResult;
                                    break;
                            }
                            Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                            intent.putExtra("number", phone);
                            intent.putExtra("data", msgbody);
                            itemView.getContext().startActivity(intent);
                            sp.registerOnSharedPreferenceChangeListener(listener);
                            dialog.dismiss();
                        }
                    });
                    pdMsgDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    if (dialog.getParent() != null) { // 부모 뷰에 하위 뷰가 여러번 띄워지는 것을 방지
                        ((ViewGroup) dialog.getParent()).removeView(dialog);
                    }
                    pdMsgDialog.setView(dialog);
                    pdMsgDialog.show();
                }

                private void setAdapter() {
                    ArrayAdapter<String> pdMsgAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, pdMsg_item);
                    pdMsgAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    pdMsg_spinner.setAdapter(pdMsgAdapter);

                    pdMsg_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    pos = "1_HourN1_Min";
                                    break;
                                case 1:
                                    pos = "2_HourN2_Min";
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

                public String setMsgTime(int hour, int minute) {
                    String h = "", m = "";
                    h = Integer.toString(hour);
                    m = Integer.toString(minute);
                    if (hour == 0) {
                        h = "12";
                    }
                    if (h.length() == 1) {
                        h = "0" + Integer.toString(hour);
                    }
                    if (m.length() == 1) {
                        m = "0" + Integer.toString(minute);
                    }
                    return h + m;
                }
            });

            pd_msg_lookup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                    intent.putExtra("number", "01220788729");
                    intent.putExtra("data", "GET TIME");
                    itemView.getContext().startActivity(intent);
                    sp.registerOnSharedPreferenceChangeListener(listener);
                }
            });
        }
    }
}
