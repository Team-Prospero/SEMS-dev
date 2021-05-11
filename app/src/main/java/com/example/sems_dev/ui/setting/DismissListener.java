package com.example.sems_dev.ui.setting;

import android.content.DialogInterface;

import java.util.HashMap;

public abstract class DismissListener implements DialogInterface.OnDismissListener {
    private HashMap<String, String> mStrMap;

    public void setValue(String key, String value) {
        if(mStrMap == null)
            mStrMap = new HashMap<String, String>();

        mStrMap.put(key, value);
    }

    public String getValueForStr(String key) {
        if(mStrMap == null)
            return null;
        else
            return mStrMap.get(key);
    }

}
