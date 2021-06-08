package com.example.sems_dev;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

public class CheckSMS extends Service {
    private int count;
    private String number[] = new String[5]; // 농장 전화번호
    private boolean check[] = new boolean[5]; // 저장된 농장 순서값
    private int farmNo; // 저장된 농장 순서 값
    private String section; // 장비 번호

    @Override
    public void onCreate(){
        for(int i = 0 ; i<5 ; i++){
            SharedPreferences sharedPreferences = getSharedPreferences(i + "_Farm", 0);
            count = sharedPreferences.getInt("count",0);
            number[i] = sharedPreferences.getString("number","");
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        init();
        String data[] = intent.getStringExtra("msg").split("@"); // 문자 발신 번호와 내용 저장

        if(data[0].length()!=0){ // 어플에 저장된 번호 중 몇 번째인지 순서값을 찾음
            for(int i = 0; i < 5 ; i++){
                if(data[0].equals(number[i])){
                    check[i] = true;
                    farmNo = i;
                }
            }
        }
        if(farmNo != -1){
            String temp[] = data[1].split("\n");
            if(data[1].contains("S1") || data[1].contains("S2")|| data[1].contains("S3")|| data[1].contains("S4")){
                section = temp[0].substring(1,2);
                if(data[1].contains("LM")){ //센서 경보범위
                    SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_LIMT", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    for(int i = 1 ; i<=8 ; i ++ ){
                        editor.putString("S" + section + "_" + i + "T_HIGH", temp[i].substring(0,2));
                        editor.putString("S" + section + "_" + i + "T_LOW", temp[i].substring(3,5));
                    }
                    editor.commit();
                    Toast.makeText(this.getApplicationContext(),"센서 경고 범위 수신 완료", Toast.LENGTH_LONG).show();
                }
                else if(data[1].contains("KIND")){ // 센서 종류
                    SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_KIND", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    for(int i = 1 ; i<=8 ; i ++ ){
                        editor.putString("S" + section + "_" + i + "T", temp[i].substring(0,1));
                    }
                    editor.commit();
                    Toast.makeText(this.getApplicationContext(),"센서 종류 수신 완료", Toast.LENGTH_LONG).show();
                }
                else if(data[1].contains("USE")){ // 센서 사용 유무
                    SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_USE", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    for(int i = 1 ; i<=8 ; i ++ ){
                        editor.putString("S" + section + "_" + i + "T", temp[i].substring(0,1));
                    }
                    editor.commit();
                    Toast.makeText(this.getApplicationContext(),"센서 사용 유무 수신 완료", Toast.LENGTH_LONG).show();
                }
                else if(data[1].contains("WA")){ // 음수량 경고범위
                    SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_WA", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    for(int i = 1 ; i<=8 ; i ++ ){
                        editor.putString("S" + section + "_" + i + "T_HIGH", temp[i].substring(0,2));
                        editor.putString("S" + section + "_" + i + "T_LOW", temp[i].substring(3,5));
                    }
                    editor.commit();
                    Toast.makeText(this.getApplicationContext(),"음수량 경고범위 수신 완료", Toast.LENGTH_LONG).show();
                }
                else{ // 현재 값 조회
                    SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_INFO", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    if(temp[0].contains("OK")){
                        editor.putString("S" + section + "_status", temp[0].substring(3));
                    }else{
                        editor.putString("S" + section + "_status", temp[0].substring(0,5));
                    }
                    for(int i = 0 ; i<temp.length ; i ++ ){
                        if(temp[i].contains("OFF")){
                            editor.putString("S" + section + "_" + i + "T", temp[i].substring(3));
                        }
                        else{
                            editor.putString("S" + section + "_" + i + "T", temp[i].substring(3));
                        }
                    }
                    editor.commit();
                    Toast.makeText(this.getApplicationContext(),"현재 값 수신 완료", Toast.LENGTH_LONG).show();
                }
            }
            else if(data[1].substring(0,2).equals("1:")){ // 비상연락처
                SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_NUM", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for(int i = 1 ; i<=5 ; i ++ ){
                    if(temp[i].length() > 3){
                        if(i==5){
                            editor.putString(Integer.toString(i), temp[i].replace("-", ""));
                        }
                        else{
                            String tmp = temp[i].replace("-", "");
                            editor.putString(Integer.toString(i), tmp.substring(0,tmp.length()-2));
                        }
                    }
                    else{
                        editor.putString(Integer.toString(i), "NULL");
                    }
                }
                editor.commit();
                Toast.makeText(this.getApplicationContext(),"비상연락처 수신 완료", Toast.LENGTH_LONG).show();
            }
            else{ // 정규 문자 시간
                SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_TIME", 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                for(int i = 1 ; i <= 2 ; i++){
                    if(temp[i].contains(",")){
                        editor.putString(i + "_Hour", temp[1].substring(0,2));
                        editor.putString(i + "_Min", temp[1].substring(2,5));
                    }
                    else{
                        editor.putString(i + "_Hour", "NULL");
                        editor.putString(i + "_Min", "NULL");
                    }
                }
                editor.commit();
                Toast.makeText(this.getApplicationContext(),"정규 문자 시간 수신 완료", Toast.LENGTH_LONG).show();
            }
        }

        Intent sendIntent = new Intent(this, MainActivity.class);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_SINGLE_TOP |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(sendIntent);

        return super.onStartCommand(intent,flags,startId);
    }

//    private void sendToActivity(Context context,String data){
//        Intent intent = new Intent(context, SmsActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                |Intent.FLAG_ACTIVITY_SINGLE_TOP
//                |Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("sender", sender);
//        intent.putExtra("content", content);
//        intent.putExtra("date", format.format(date));
//        context.startActivity(intent);
//    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    public void init(){
        count = 0;
        check[0] = false; // 저장된 농장 순서 값
        check[1] = false;
        check[2] = false;
        check[3] = false;
        check[4] = false;
        farmNo = -1 ; // 저장된 농장 순서 값
        section = ""; // 장비 번호
    }



    public void notification(String section, int t, int dif, String highLow) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, "default");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("경고");
            if (highLow.equals("HIGH")) {
                builder.setContentText(section + "번 장비 " + t + "구역의 온도가 설정 값보다 " + dif + "도 높습니다!");
            } else if (highLow.equals("LOW")) {
                builder.setContentText(section + "번 장비 " + t + "구역의 온도가 설정 값보다 " + dif + "도 낮습니다!");
            } else {
                builder.setContentText("문제가 발생했습니다 어플을 실행하여 확인하십시오.");
            }


            builder.setColor(Color.RED);
            //builder.setSmallIcon();

            // 사용자가 탭을 클릭하면 자동 제거
            builder.setAutoCancel(true);

            Intent intent = new Intent(this, MainActivity.class);
            intent.setAction(Intent.ACTION_MAIN)
                    .addCategory(Intent.CATEGORY_LAUNCHER)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(new NotificationChannel("default", "경보", NotificationManager.IMPORTANCE_HIGH));
            }

            // id값은
            // 정의해야하는 각 알림의 고유한 int값
            notificationManager.notify(1, builder.build());
        }
    }

    public void checkTemp(String section, int t, String temperature) {
        SharedPreferences sharedPreferences = getSharedPreferences(farmNo + "_LIMT", 0);
        String savedHighTemp = sharedPreferences.getString("S" + section + "_" + t + "T_HIGH", "");
        String savedLowTemp = sharedPreferences.getString("S" + section + "_" + t + "T_LOW", "");
        if(savedHighTemp.length()>0 && savedLowTemp.length()>0){
            // 메세지의 온도 값이 최대 값보다 높거나, 최소 값보다 낮은 경우
            if (Integer.parseInt(temperature) >= Integer.parseInt(savedHighTemp)) {
                notification(section, t, Integer.parseInt(temperature) - Integer.parseInt(savedHighTemp), "HIGH");
            } else if (Integer.parseInt(temperature) <= Integer.parseInt(savedLowTemp)) {
                notification(section, t, Integer.parseInt(savedLowTemp) - Integer.parseInt(temperature), "LOW");
            }
        }
    }

}
