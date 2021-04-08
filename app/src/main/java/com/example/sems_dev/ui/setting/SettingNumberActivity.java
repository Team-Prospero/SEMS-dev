package com.example.sems_dev.ui.setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class SettingNumberActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    ListView listView;

    Button addNum, deleteNum;
    EditText etName, etNum;

    LinearLayout dialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_number);

        addNum = findViewById(R.id.addNum);
        deleteNum = findViewById(R.id.deleteNum);

        items = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(SettingNumberActivity.this,android.R.layout.simple_list_item_checked);

        //전화번호 추가
        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다이얼로그 생성
                dialogView = (LinearLayout)View.inflate(SettingNumberActivity.this, R.layout.dialog_add_number, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingNumberActivity.this);
                builder.setTitle("전화번호 추가");
                builder.setView(dialogView);
                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etName = (EditText)findViewById(R.id.etName);
                        etNum = (EditText)findViewById(R.id.etNum);

                        //adapter.addAll(etName.getText().toString(), etNum.getText().toString());
                        //클릭 시 행동 미구현
                    }
                });
                builder.setNegativeButton("취소", null);
                builder.show();
            }
        });
        deleteNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //체크박스 보이기
                //검색, 추가 버튼 비활성화
            }
        });
    }
}
