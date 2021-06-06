package com.example.sems_dev.ui.periodic_message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecyclerItem {
    private String farmNumber;
    private String msgTime_1, msgTime_2;

    public void setFarmNumber(String fNum) {
        farmNumber = fNum;
    }

    public String getFarmNumber() {
        return farmNumber;
    }

    public void setMsgTime_1(String hour, String minute) {
        msgTime_1 = hour+minute;
    }

    public String getMsgTime_1() {
        return msgTime_1;
    }

    public void setMsgTime_2(String hour, String minute) {
        msgTime_2 = hour+minute;
    }

    public String getMsgTime_2() {
        return msgTime_2;
    }


}
