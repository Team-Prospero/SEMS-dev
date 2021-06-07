package com.example.sems_dev.ui.setting;

public class SettingNumberClass {
        private String name;
        private String number;
        private int index;

        public SettingNumberClass(String name, String number, int index){
            this.name = name;
            this.number = number;
            this.index = index;
        }

        public String getName(){
            return name;
        }

        public String getNumber(){
            return  number;
        }

        public int getIndex() {return index;}

}
