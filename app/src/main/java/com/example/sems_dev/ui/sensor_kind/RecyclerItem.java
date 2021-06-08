package com.example.sems_dev.ui.sensor_kind;

import android.content.SharedPreferences;
import android.widget.TextView;

import com.example.sems_dev.R;

import java.util.ArrayList;

public class RecyclerItem {
    String s1_1, s1_2, s1_3, s1_4, s1_5, s1_6, s1_7, s1_8;
    private String farmNumber;
    public void setFarmNumber(String fNum) {
        farmNumber=fNum;
    }
    public String getFarmNumber() {
        return this.farmNumber;
    }
    public String getS1_1() {
        return s1_1;
    }

    public void setS1_1(String s) {
        switch (s){
            case "0":
                s1_1 = "기타";
                break;
            case "1":
                s1_1 = "온도";
                break;
            case "2":
                s1_1 = "습도";
                break;
            case "3":
                s1_1 = "음수";
                break;
        }
    }

    public String getS1_2() {
        return s1_2;
    }

    public void setS1_2(String s) {
        switch (s){
            case "0":
                s1_2 = "기타";
                break;
            case "1":
                s1_2 = "온도";
                break;
            case "2":
                s1_2 = "습도";
                break;
            case "3":
                s1_2 = "음수";
                break;
        }
    }

    public String getS1_3() {
        return s1_3;
    }

    public void setS1_3(String s) {
        switch (s){
            case "0":
                s1_3 = "기타";
                break;
            case "1":
                s1_3 = "온도";
                break;
            case "2":
                s1_3 = "습도";
                break;
            case "3":
                s1_3 = "음수";
                break;
        }
    }

    public String getS1_4() {
        return s1_4;
    }

    public void setS1_4(String s) {
        switch (s){
            case "0":
                s1_4 = "기타";
                break;
            case "1":
                s1_4 = "온도";
                break;
            case "2":
                s1_4 = "습도";
                break;
            case "3":
                s1_4 = "음수";
                break;
        }
    }

    public String getS1_5() {
        return s1_5;
    }

    public void setS1_5(String s) {
        switch (s){
            case "0":
                s1_5 = "기타";
                break;
            case "1":
                s1_5 = "온도";
                break;
            case "2":
                s1_5 = "습도";
                break;
            case "3":
                s1_5 = "음수";
                break;
        }
    }

    public String getS1_6() {
        return s1_6;
    }

    public void setS1_6(String s) {
        switch (s){
            case "0":
                s1_6 = "기타";
                break;
            case "1":
                s1_6 = "온도";
                break;
            case "2":
                s1_6 = "습도";
                break;
            case "3":
                s1_6 = "음수";
                break;
        }
    }

    public String getS1_7() {
        return s1_7;
    }

    public void setS1_7(String s) {
        switch (s){
            case "0":
                s1_7 = "기타";
                break;
            case "1":
                s1_7 = "온도";
                break;
            case "2":
                s1_7 = "습도";
                break;
            case "3":
                s1_7 = "음수";
                break;
        }
    }

    public String getS1_8() {
        return s1_8;
    }

    public void setS1_8(String s) {
        switch (s){
            case "0":
                s1_8 = "기타";
                break;
            case "1":
                s1_8 = "온도";
                break;
            case "2":
                s1_8 = "습도";
                break;
            case "3":
                s1_8 = "음수";
                break;
        }
    }
}
