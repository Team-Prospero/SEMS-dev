package com.example.sems_dev.ui.setting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.sems_dev.R;

public class SettingNumberDialogFragment extends DialogFragment {

    private MyDialogListener myListener;
    private int count = 0;

    public interface MyDialogListener {
        public void myCallback(String name, String number);
    }

    public SettingNumberDialogFragment (int count) { this.count = count; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            myListener = (MyDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialogfragment_add_number, null));
        builder.setTitle("전화번호 추가");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        EditText etName = (EditText)getDialog().findViewById(R.id.etName);
                        EditText etNum1 = (EditText)getDialog().findViewById(R.id.etNum1);
                        EditText etNum2 = (EditText)getDialog().findViewById(R.id.etNum2);
                        EditText etNum3 = (EditText)getDialog().findViewById(R.id.etNum3);

                        String name = new String();
                        String number = new String();

                        if(etName.length() == 0){
                            name = Integer.toString(count) + "번";
                        }
                        if(etNum1.length() == 0 || etNum2.length() == 0 || etNum3.length() == 0){
                            Toast.makeText(getActivity(), "올바른 전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                            etName.setText("");
                            etNum1.setText("");
                            etNum2.setText("");
                            etNum3.setText("");
                        }
                        else{
                            name = etName.getText().toString();
                            number = etNum1.getText().toString() + '-' + etNum2.getText().toString() + '-' + etNum3.getText().toString();
                            etName.setText("");
                            etNum1.setText("");
                            etNum2.setText("");
                            etNum3.setText("");
                        }
                        myListener.myCallback(name, number);
                    }
                }
                );
        builder.setNegativeButton("취소", null);

        return builder.create();

    }


}