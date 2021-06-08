package com.example.sems_dev.ui.sensor_on_off;

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
                s1_1 = "OFF";
                break;
            case "1":
                s1_1 = "ON";
                break;

        }
    }

    public String getS1_2() {
        return s1_2;
    }

    public void setS1_2(String s) {
        switch (s){
            case "0":
                s1_2 = "OFF";
                break;
            case "1":
                s1_2 = "ON";
                break;

        }
    }

    public String getS1_3() {
        return s1_3;
    }

    public void setS1_3(String s) {
        switch (s){
            case "0":
                s1_3 = "OFF";
                break;
            case "1":
                s1_3 = "ON";
                break;

        }
    }

    public String getS1_4() {
        return s1_4;
    }

    public void setS1_4(String s) {
        switch (s){
            case "0":
                s1_4 = "OFF";
                break;
            case "1":
                s1_4 = "ON";
                break;

        }
    }

    public String getS1_5() {
        return s1_5;
    }

    public void setS1_5(String s) {
        switch (s){
            case "0":
                s1_5 = "OFF";
                break;
            case "1":
                s1_5 = "ON";
                break;

        }
    }

    public String getS1_6() {
        return s1_6;
    }

    public void setS1_6(String s) {
        switch (s){
            case "0":
                s1_6 = "OFF";
                break;
            case "1":
                s1_6 = "ON";
                break;

        }
    }

    public String getS1_7() {
        return s1_7;
    }

    public void setS1_7(String s) {
        switch (s){
            case "0":
                s1_7 = "OFF";
                break;
            case "1":
                s1_7 = "ON";
                break;

        }
    }

    public String getS1_8() {
        return s1_8;
    }

    public void setS1_8(String s) {
        switch (s){
            case "0":
                s1_8 = "OFF";
                break;
            case "1":
                s1_8 = "ON";
                break;

        }
    }
}
