package com.example.sems_dev.ui.screen_slide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.sems_dev.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

class ScreenSlideFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.fragment_slide_page, container, false);
    }
}