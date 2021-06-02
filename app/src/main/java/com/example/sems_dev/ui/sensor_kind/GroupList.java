package com.example.sems_dev.ui.sensor_kind;

import java.util.ArrayList;

public class GroupList {
    public ArrayList<String> child;
    public String groupName;

    GroupList(String name) {
        groupName = name;
        child = new ArrayList<String>();
    }
}
