package com.example.sems_dev.ui.get_value;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sems_dev.R;

public class GetValueFragment extends Fragment {

    private GetValueViewModel getValueViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getValueViewModel =
                new ViewModelProvider(this).get(GetValueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_get_val, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        getValueViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}