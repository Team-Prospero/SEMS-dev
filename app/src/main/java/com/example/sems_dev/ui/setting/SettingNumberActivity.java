package com.example.sems_dev.ui.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sems_dev.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SettingNumberActivity extends AppCompatActivity {

    private ListViewAdapter adapter;
    private ListView listView;

    private Button addNum, deleteNum;
    private EditText etName, etNum1, etNum2, etNum3;

    private String numberList[] = new String[100];

    private int count = 1;
    String Name = new String();
    String Number = new String();
    private LinearLayout dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_number);



        addNum = (Button) findViewById(R.id.addNum);
        deleteNum = (Button) findViewById(R.id.deleteNum);
        listView = (ListView) findViewById(R.id.numberList);

        etName = (EditText) findViewById(R.id.etName);
        etNum1 = (EditText) findViewById(R.id.etNum1); // 전화번호 제일 앞
        etNum2 = (EditText) findViewById(R.id.etNum2); // 전화번호 중간
        etNum3 = (EditText) findViewById(R.id.etNum3); // 전화번호 끝

        adapter = new ListViewAdapter(SettingNumberActivity.this);
        listView.setAdapter(adapter);

/*        String data = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput("number.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
            data = bufferedReader.readLine();
                data = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        addNum.setOnClickListener(new View.OnClickListener() { // 전화번호 추가 버튼
            @Override
            public void onClick(View v) {
                //다이얼로그 생성
                dialogView = (LinearLayout) View.inflate(SettingNumberActivity.this, R.layout.dialog_add_number, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingNumberActivity.this);
                builder.setTitle("전화번호 추가");
                builder.setView(dialogView);
                builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (etName.length() == 0) {
                            Name = Integer.toString(count) + "번";
                        }
                        if (etNum1.length() == 0 || etNum2.length() == 0 || etNum3.length() == 0) {
                            Toast.makeText(getApplicationContext(), "올바른 전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            Name = etName.getText().toString();
                            Number = etNum1.getText().toString() + "-" + etNum2.getText().toString() + "-" + etNum3.getText().toString();

                            etName.setText("");
                            etNum1.setText("");
                            etNum2.setText("");
                            etNum3.setText("");

/*                            *//*파일 저장*//*
                            FileOutputStream fos = null;
                            try {
                                String a = "\r\n";
                                fos = openFileOutput("number.txt", Context.MODE_PRIVATE);
                                for (int i = 0; i <= 99; i++) {
                                    fos.write(numberList[i].getBytes());
                                    fos.write(a.getBytes());
                                }
                                fos.close();
                                Toast.makeText(SettingNumberActivity.this, "저장완료", Toast.LENGTH_LONG).show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();

                                adapter.addItem(Name, Number);
                                count++;
                                adapter.notifyDataSetChanged();
                            }*/
                        }


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
