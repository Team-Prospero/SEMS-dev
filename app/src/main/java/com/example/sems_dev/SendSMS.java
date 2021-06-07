package com.example.sems_dev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.sems_dev.ui.get_value.GetValueFragment;


public class SendSMS extends Activity{

    private String number;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_sendsms);

        Button btnCancel = (Button)findViewById(R.id.btnCancel);
        Button btnSend = (Button)findViewById(R.id.btnSend);

        Intent intent = getIntent();

        number = intent.getStringExtra("number");
        data = intent.getStringExtra("data");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(number, null, data, null, null);
                Toast.makeText(getApplicationContext(),"메세지를 전송 하였습니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }


}
