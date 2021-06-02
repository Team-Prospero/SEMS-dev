package com.example.sems_dev.ui.msg_log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MsglogViewModel {
    private MutableLiveData<String> mText;

    public MsglogViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("메시지 기록 프래그먼트");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
