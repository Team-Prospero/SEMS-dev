package com.example.sems_dev.ui.emergency_call;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

        View view = inflater.inflate(R.layout.recycler_item_emergency_call, parent, false);
        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {

        RecyclerItem item = mData.get(position);
        holder.farmNumber.setText(item.getFarmNumber() + "농장");
        holder.pNum_1.setText(item.getpNum_1());
        holder.pNum_2.setText(item.getpNum_2());
        holder.pNum_3.setText(item.getpNum_3());
        holder.pNum_4.setText(item.getpNum_4());
        holder.pNum_5.setText(item.getpNum_5());
    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutInflater inflater = (LayoutInflater) itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView farmNumber;
        TextView pNum_1, pNum_2, pNum_3, pNum_4, pNum_5;
        Button phonenumber_edit, phonenumber_lookup;
        SharedPreferences sp, msg_sp;
        SharedPreferences.Editor editor, msg_editor;
        AlertDialog.Builder emCallDialog = new AlertDialog.Builder(itemView.getContext());

        ViewHolder(View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            farmNumber = itemView.findViewById(R.id.farmNumber);
            pNum_1 = itemView.findViewById(R.id.pNum_1);
            pNum_2 = itemView.findViewById(R.id.pNum_2);
            pNum_3 = itemView.findViewById(R.id.pNum_3);
            pNum_4 = itemView.findViewById(R.id.pNum_4);
            pNum_5 = itemView.findViewById(R.id.pNum_5);
            phonenumber_edit = itemView.findViewById(R.id.phonenumber_edit);
            phonenumber_lookup = itemView.findViewById(R.id.phonenumber_lookup);
            sp = itemView.getContext().getSharedPreferences("0_NUM", 0);
            msg_sp = itemView.getContext().getSharedPreferences("0_FARM", 0);
            editor = sp.edit();


            SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    String temp;
                    switch (key) {
                        case "1":
                            temp = sharedPreferences.getString("1", "-");
                            if (temp.equals("NULL"))
                                pNum_1.setText("-");
                            else
                                pNum_1.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7, temp.length()));
                            break;
                        case "2":
                            temp = sharedPreferences.getString("2", "-");
                            if (temp.equals("NULL"))
                                pNum_2.setText("-");
                            else
                                pNum_2.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7, temp.length()));
                            break;
                        case "3":
                            temp = sharedPreferences.getString("3", "-");
                            if (temp.equals("NULL"))
                                pNum_3.setText("-");
                            else
                                pNum_3.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7, temp.length()));
                            break;
                        case "4":
                            temp = sharedPreferences.getString("4", "-");
                            if (temp.equals("NULL"))
                                pNum_4.setText("-");
                            else
                                pNum_4.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7, temp.length()));
                            break;
                        case "5":
                            temp = sharedPreferences.getString("5", "-");
                            if (temp.equals("NULL"))
                                pNum_5.setText("-");
                            else
                                pNum_5.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7, temp.length()));
                            break;
                    }
                }
            };


            phonenumber_edit.setOnClickListener(new View.OnClickListener() {
                View dialog = inflater.inflate(R.layout.dialog_emergency_call, null);
                Spinner emCall_spinner = dialog.findViewById(R.id.em_call_spinner);
                EditText emCall_edit = dialog.findViewById(R.id.em_call_edit);
                String[] emCall_item = {"1번 연락처", "2번 연락처", "3번 연락처", "4번 연락처", "5번 연락처"};
                String phoneNumber, saveResult, pos, phone = msg_sp.getString("number", "01220788729");
                ;

                @Override
                public void onClick(View v) {
                    setAdapter();
                    emCallDialog.setTitle("비상전화번호 변경");
                    emCallDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            phoneNumber = emCall_edit.getText().toString();
                            Log.d("[phone]", phoneNumber);
                            saveResult = phoneNumber;
                            editor.putString(pos, saveResult);
                            editor.apply();
                            String temp;
                            switch (pos) {
                                case "1":
                                    temp = sp.getString(pos, "-");
                                    pNum_1.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7));
                                    msgbody = "SET 1:" + temp;
                                    break;
                                case "2":
                                    temp = sp.getString(pos, "-");
                                    pNum_2.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7));
                                    msgbody = "SET 2:" + temp;
                                    break;
                                case "3":
                                    temp = sp.getString(pos, "-");
                                    pNum_3.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7));
                                    msgbody = "SET 3:" + temp;
                                    break;
                                case "4":
                                    temp = sp.getString(pos, "-");
                                    pNum_4.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7));
                                    msgbody = "SET 4:" + temp;
                                    break;
                                case "5":
                                    temp = sp.getString(pos, "-");
                                    pNum_5.setText(temp.substring(0, 3) + "-" + temp.substring(4, 8) + "-" + temp.substring(7));
                                    msgbody = "SET 5:" + temp;
                                    break;
                            }
                            Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                            intent.putExtra("number", phone);
                            intent.putExtra("data", msgbody);
                            itemView.getContext().startActivity(intent);
                            sp.registerOnSharedPreferenceChangeListener(listener);
                            emCall_edit.setText("");
                            dialog.dismiss();
                        }
                    });
                    emCallDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(), "취소하였습니다", Toast.LENGTH_SHORT).show();
                            emCall_edit.setText("");
                            dialog.cancel();
                        }
                    });
                    if (dialog.getParent() != null) { // 부모 뷰에 하위 뷰가 여러번 띄워지는 것을 방지
                        ((ViewGroup) dialog.getParent()).removeView(dialog);
                    }
                    emCallDialog.setView(dialog);
                    emCallDialog.show();
                }

                private void setAdapter() {
                    ArrayAdapter<String> emCallAdapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_item, emCall_item);
                    emCallAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    emCall_spinner.setAdapter(emCallAdapter);

                    emCall_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {
                                case 0:
                                    pos = "1";
                                    break;
                                case 1:
                                    pos = "2";
                                    break;
                                case 2:
                                    pos = "3";
                                    break;
                                case 3:
                                    pos = "4";
                                    break;
                                case 4:
                                    pos = "5";
                                    break;
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            });
            phonenumber_lookup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), SendSMS.class);
                    intent.putExtra("number", "01220788729");
                    intent.putExtra("data", "GET NUM");
                    itemView.getContext().startActivity(intent);
                    sp.registerOnSharedPreferenceChangeListener(listener);
                }
            });
        }
    }
}
