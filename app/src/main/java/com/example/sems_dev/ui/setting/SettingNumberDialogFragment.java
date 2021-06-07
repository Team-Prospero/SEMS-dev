   package com.example.sems_dev.ui.setting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.sems_dev.MainActivity;
import com.example.sems_dev.R;

public class SettingNumberDialogFragment extends DialogFragment{

    public static final String KEY_RESULT = "Result";
    private DismissListener dismissListener;
    private int count = 0;

    public SettingNumberDialogFragment(int count) {
        this.count = count;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        setCancelable(false);
        builder.setView(inflater.inflate(R.layout.dialogfragment_add_number, null));
        builder.setTitle("전화번호 추가");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etName = (EditText) getDialog().findViewById(R.id.etName);
                        EditText etNumber = (EditText) getDialog().findViewById(R.id.etNum);

                        String name = new String();
                        String number = new String();

                        if (etName.length() == 0) { // 이름 입력 안했을 때 자동 지정
                            name = Integer.toString(count) + "번";
                        }

                        if (etNumber.length() < 11 ) {
                            Toast.makeText(getActivity(), "올바른 전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                            etName.setText("");
                            etNumber.setText("");
                            dismissListener.setValue("name", "");
                            dismissListener.setValue("number", "");
                        } else {
                            name = etName.getText().toString();
                            number = etNumber.getText().toString();
                            etName.setText("");
                            etNumber.setText("");
                            if (dismissListener != null) {
                                dismissListener.setValue("name", name);
                                dismissListener.setValue("number", number);

                            }
                        }
                    }
                }
        );
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText etName = (EditText) getDialog().findViewById(R.id.etName);
                EditText etNumber = (EditText) getDialog().findViewById(R.id.etNum);
                etName.setText("");
                etNumber.setText("");
                dismissListener.setValue("name", "");
                dismissListener.setValue("number", "");
                Toast.makeText(getActivity(), "취소하였습니다.", Toast.LENGTH_SHORT).show();

            }
        });

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        //받은 리스너를 등록한다
        getDialog().setOnDismissListener(dismissListener);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        //반드시 여기서도 종료를 해주어야 한다. DismissListener를 등록할 경우 back키를 누르면 종료되지 않고 cancel만 되기 때문이다
        dismiss();
    }

    //리스너를 받는다
    public void setDismissListener(DismissListener listener) {

        dismissListener = listener;

    }

}