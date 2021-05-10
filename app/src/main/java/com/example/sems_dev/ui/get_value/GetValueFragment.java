package com.example.sems_dev.ui.get_value;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.example.sems_dev.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetValueFragment extends Fragment{
    private static final String TAG = "GetValueFragment";
    public Button[] equipment = new Button[4];
    ViewPager pager;
    private Spinner spinner;
    public SimpleDateFormat simpleDateFormat,simpleHoursFormat;
    LinearLayout Sams_data_table;
    TextView LastViewTime;
    Button currentVal;
    private View v ;
    long mNow;
    Date mDate;
    String format_time,text;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(v != null){
            return v;
        }
        v = inflater.inflate(R.layout.fragment_get_val, container, false);
        pager = (ViewPager) v.findViewById(R.id.pager1);
        Sams_data_table = (LinearLayout)v.findViewById(R.id.Sams_data_table);
        equipment[0] = v.findViewById(R.id.equipment1);
        equipment[1] = v.findViewById(R.id.equipment2);
        equipment[2] = v.findViewById(R.id.equipment3);
        equipment[3] = v.findViewById(R.id.equipment4);
        spinner = (Spinner)v.findViewById(R.id.farmName);
        currentVal =(Button)v.findViewById(R.id.currentVal);
        LastViewTime = (TextView)v.findViewById(R.id.LastViewTime);
        pager.setAdapter(new pagerAdapter(getFragmentManager()));
        pager.setCurrentItem(0);

        SharedPreferences sf = this.getActivity().getSharedPreferences("nFile",0);
        text = sf.getString("nFile","");
        LastViewTime.setText("최근 조회 시간 : "+text);
        final String[] country = {"-농장 선택-","농장1", "농장2","농장3","농장4"};
        //현재값 조회
        currentVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNow = System.currentTimeMillis();
                mDate = new Date(mNow);
                format_time = mFormat.format(mDate);
                SharedPreferences.Editor editor = sf.edit();
                editor.putString("nFile",format_time);
                editor.commit();
                text = sf.getString("nFile","");
                LastViewTime.setText("최근 조회 시간 : "+text);
            }
        });
        //농장 선택 Adapter
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setGravity(Gravity.CENTER);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (spinner.getSelectedItemPosition() == 0) {
                    Sams_data_table.setBackgroundColor(Color.parseColor("#ff00ddff"));
                } else if (spinner.getSelectedItemPosition() == 1){
                    Sams_data_table.setBackgroundColor(Color.parseColor("#7cfc00"));
                  //  Toast.makeText(getContext(), "Selected Country: " + country[position], Toast.LENGTH_SHORT).show();
                }
                else if (spinner.getSelectedItemPosition() == 2){
                    Sams_data_table.setBackgroundColor(Color.parseColor("#7FFFD4"));
                }
                else if (spinner.getSelectedItemPosition() == 3){
                    Sams_data_table.setBackgroundColor(Color.parseColor("#B0C4DE"));
                }
                else if (spinner.getSelectedItemPosition() == 4){
                    Sams_data_table.setBackgroundColor(Color.parseColor("#8A2BE2"));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        pager.setOnPageChangeListener(onPageChangeListener); //디프리케이트됨

        //뷰페이저
        View.OnClickListener movePageListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                int tag = (int)view.getTag();
                setButtonsPink();
                view.setBackgroundColor(Color.parseColor("#fccaca"));
                pager.setCurrentItem(tag,true);
            }
        };
        for (int i = 0; i < equipment.length; i++) {
            // 버튼의 포지션(배열에서의 index)를 태그로 저장
            equipment[i].setOnClickListener(movePageListener);
            equipment[i].setTag(i);
        }

        return v;
    }
   /* private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }*/
    //onPageChangeListener
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //Button 색상 변경
            setButtonColor(position);

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    //setButtonColor
    private void setButtonColor(int position){
        for(Button btn : equipment){
            if(Integer.parseInt(btn.getTag().toString()) != position){
                btn.setBackgroundColor(Color.parseColor("#B3E5FC"));
                btn.setSelected(false);
            } else {
                btn.setBackgroundColor(Color.parseColor("#fccaca"));
                btn.setSelected(true);
            }
        }
    }
    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            equipment[position].setSelected(true);
            equipment[position].setBackgroundColor(Color.parseColor("#B3E5FC"));
            return new CustomAdapter("sFile"+position);
        }

        @Override
        public int getCount() {
            // total page count
            return 4;
        }
    }
    private void setButtonsPink() {
        for (int i = 0; i < equipment.length; i++) {
            // 버튼의 포지션(배열에서의 index)를 태그로 저장
            equipment[i].setBackgroundColor(Color.parseColor("#B3E5FC"));
        }
    }

}

