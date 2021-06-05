package com.example.sems_dev;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

public class CheckSMS extends Service {
    private int count=0;
    private String number[] = new String[5];
    private boolean check=false;

    @Override
    public void onCreate(){
        for(int i = 0 ; i<5 ; i++){
            SharedPreferences sharedPreferences = getSharedPreferences("Farm"+i, 0);
            count = sharedPreferences.getInt("count",0);
            number[i] = sharedPreferences.getString("number","");
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        String data[] = intent.getStringExtra("msg").split("@"); // 문자 발신 번호와 내용 저장

        if(data[0].length()!=0){
            for(int i = 0; i <5 ; i++){
                if(data[0].equals(number[i])){
                    check = true;
                        Toast.makeText(this.getApplicationContext(),"true", Toast.LENGTH_LONG).show();
                }
            }
        }
        if(check == true){
            if(data[1].contains("S1")){
                if(data[1].contains("LM")){ //센서 경보범위

                }
                else if(data[1].contains("KIND")){ // 센서 종류

                }
                else if(data[1].contains("USE")){ // 센서 사용 유무

                }
                else if(data[1].contains("WA")){ // 음수량 경고범위

                }
                else{ // 현재 값 조회
                    String temp[] = data[1].split(":");
                    //Toast.makeText(this.getApplicationContext(),temp[3], Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("0_value", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //editor.putString("status", temp[3]);
                    editor.putString("0", temp[2].substring(0,2));
                    editor.putString("1", temp[3].substring(0,2));
                    editor.putString("2", temp[4].substring(0,2));
                    editor.putString("3", temp[5].substring(0,2));
                    editor.putString("4", temp[6].substring(0,2));
                    editor.putString("5", temp[7].substring(0,2));
                    editor.putString("6", temp[8].substring(0,2));
                    editor.putString("7", temp[9].substring(0,2));
                    editor.commit();
                }
            }
            else if(data[1].substring(0,1)=="1:"){ // 비상연락처

            }
            else{ // 정규 문자 시간

            }


        }
        else{

        }

        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

}
