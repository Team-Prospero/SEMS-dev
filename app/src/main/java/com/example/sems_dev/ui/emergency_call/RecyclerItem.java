package com.example.sems_dev.ui.emergency_call;

import android.widget.Button;
import android.widget.TextView;

public class RecyclerItem {

    private String pNum_1, pNum_2, pNum_3, pNum_4, pNum_5;
    private String farmNumber;
    public void setFarmNumber(String fNum) {
        farmNumber=fNum;
    }
    public String getFarmNumber() {
        return this.farmNumber;
    }
    public void setpNum_1(String pNum) {
        pNum_1=pNum;
    }

    public void setpNum_2(String pNum) {
        pNum_2=pNum;
    }

    public void setpNum_3(String pNum) {
        pNum_3=pNum;
    }

    public void setpNum_4(String pNum) {
        pNum_4=pNum;
    }

    public void setpNum_5(String pNum) {
        pNum_5=pNum;
    }



    public String getpNum_1() {
        return this.pNum_1;
    }

    public String getpNum_2() {
        return this.pNum_2;
    }

    public String getpNum_3() {
        return this.pNum_3;
    }

    public String getpNum_4() {
        return this.pNum_4;
    }

    public String getpNum_5() {
        return this.pNum_5;
    }
}
