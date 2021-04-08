package com.example.sems_dev.ui.get_value;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import com.example.sems_dev.R;

public class GetValueFragment extends Fragment {

    private ToggleButton equipment1, equipment2, equipment3, equipment4;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_get_val, container, false);


        equipment1 =  v.findViewById(R.id.equipment1);
        equipment2 =  v.findViewById(R.id.equipment2);
        equipment3 =  v.findViewById(R.id.equipment3);
        equipment4 =  v.findViewById(R.id.equipment4);

        equipment1.setOnClickListener(onToggleClick);
        equipment2.setOnClickListener(onToggleClick);
        equipment3.setOnClickListener(onToggleClick);
        equipment4.setOnClickListener(onToggleClick);

        return v;
    }

    //onClickListener
    private View.OnClickListener onToggleClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();

            if (((ToggleButton) view).isChecked()){switch(id){
                case R.id.equipment1:
                    Toast.makeText(GetValueFragment.this.getContext(), "equipment1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.equipment2:
                    Toast.makeText(GetValueFragment.this.getContext(), "equipment2", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.equipment3:
                    Toast.makeText(GetValueFragment.this.getContext(), "equipment3", Toast.LENGTH_SHORT).show();

                    break;

                case R.id.equipment4:
                    Toast.makeText(GetValueFragment.this.getContext(), "equipment4", Toast.LENGTH_SHORT).show();
                    break;
            }
                equipment1.setBackground(ContextCompat.getDrawable(GetValueFragment.this.getContext(), R.drawable.button));
                equipment2.setBackground(ContextCompat.getDrawable(GetValueFragment.this.getContext(), R.drawable.button));
                equipment3.setBackground(ContextCompat.getDrawable(GetValueFragment.this.getContext(), R.drawable.button));
                equipment4.setBackground(ContextCompat.getDrawable(GetValueFragment.this.getContext(), R.drawable.button));
            }
           else {
                switch(id){
                    case R.id.equipment1:
                        Toast.makeText(GetValueFragment.this.getContext(), "equipment1", Toast.LENGTH_SHORT).show();
                        equipment1.setBackgroundColor((Color.parseColor("#fccaca")));
                        break;
                    case R.id.equipment2:
                        Toast.makeText(GetValueFragment.this.getContext(), "equipment2", Toast.LENGTH_SHORT).show();
                        equipment2.setBackgroundColor((Color.parseColor("#fccaca")));
                        break;

                    case R.id.equipment3:
                        Toast.makeText(GetValueFragment.this.getContext(), "equipment3", Toast.LENGTH_SHORT).show();
                        equipment3.setBackgroundColor((Color.parseColor("#fccaca")));
                        break;

                    case R.id.equipment4:
                        Toast.makeText(GetValueFragment.this.getContext(), "equipment4", Toast.LENGTH_SHORT).show();
                        equipment4.setBackgroundColor((Color.parseColor("#fccaca")));
                        break;
                }

            }
        }
    };


}