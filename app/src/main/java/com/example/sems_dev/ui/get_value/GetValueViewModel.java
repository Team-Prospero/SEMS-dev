package com.example.sems_dev.ui.get_value;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GetValueViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GetValueViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("값 조회 프래그먼트");
    }

    public LiveData<String> getText() {
        return mText;
    }
}