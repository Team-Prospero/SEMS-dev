package com.example.sems_dev.ui.get_value;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sems_dev.R;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends Fragment {
    private EditText[] Editsensor_Name = new EditText[8];
    SharedPreferences sp;
    String text[] = new String[8];
    public CustomAdapter()
    {
        // required
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,

                             @Nullable Bundle savedInstanceState) {
        FrameLayout layout = (FrameLayout)inflater.inflate(R.layout.fragment_get_val2,

                container, false);

        Editsensor_Name[0] = (EditText) layout.findViewById(R.id.Editsensor1_Name);
        Editsensor_Name[1] = (EditText) layout.findViewById(R.id.Editsensor2_Name);
        Editsensor_Name[2] = (EditText) layout.findViewById(R.id.Editsensor3_Name);
        Editsensor_Name[3] = (EditText) layout.findViewById(R.id.Editsensor4_Name);
        Editsensor_Name[4] = (EditText) layout.findViewById(R.id.Editsensor5_Name);
        Editsensor_Name[5] = (EditText) layout.findViewById(R.id.Editsensor6_Name);
        Editsensor_Name[6] = (EditText) layout.findViewById(R.id.Editsensor7_Name);
        Editsensor_Name[7] = (EditText) layout.findViewById(R.id.Editsensor8_Name);
        for(int i = 1; i < Editsensor_Name.length; i++) {
            //getSharedPreferences Edittext저장
            SharedPreferences sf = this.getContext().getSharedPreferences("sFile",MODE_PRIVATE);
            text[i] = sf.getString("text"+i,"");
            Editsensor_Name[i].setText(text[i]);

        }
        return layout;

    }



    @Override
     public void onStop() {
        super.onStop();
        for(int i = 1; i < Editsensor_Name.length; i++) {
        // Activity가 종료되기 전에 저장한다.
        //SharedPreferences를 sFile이름, 기본모드로 설정
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("sFile", MODE_PRIVATE);

        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        text[i] = Editsensor_Name[i].getText().toString();
        // 사용자가 입력한 저장할 데이터
        editor.putString("text"+i, text[i]);
        editor.commit();
        }
    }
}