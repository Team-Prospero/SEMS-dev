package com.example.sems_dev.ui.get_value;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.sems_dev.SendSMS;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetValueFragment extends Fragment{
    private static final String TAG = "GetValueFragment";
    public Button[] equipment = new Button[4];
    ViewPager pager;
    private int farmNo;
    public static Context mContext;
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
        pagerAdapter pagerAdapter = new pagerAdapter(getFragmentManager());
        pagerAdapter.setSpinnerPosition(spinner.getSelectedItemPosition());

        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(0);
        SharedPreferences sf = this.getActivity().getSharedPreferences("nFile",0);
        text = sf.getString("nFile","");
        LastViewTime.setText("최근 조회 시간 : "+text);

        int num = 0;
        SharedPreferences sf1 = null;
        List<HashMap<String, String>> spinnerArray = new ArrayList<>();
        List<String> titleList = new ArrayList<String>();
        while(true){
            sf1 = null;
            String name = num + "_Farm";
            sf1 = this.getActivity().getSharedPreferences(name, 0);

            if(sf1.getAll().size() > 0) {
                HashMap<String, String> map = new HashMap<>();
                map.put("sf", name);
                map.put("number", sf1.getString("number", ""));
                spinnerArray.add(map);
                titleList.add(sf1.getString("name", ""));
                num ++;
            } else {
                break;
            }
        }



        // final String[] country = {"-농장 선택-","test", "농장2","농장3","농장4"};
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

                Intent intent = new Intent(getActivity(), SendSMS.class);
                intent.putExtra("number", getActivity().getSharedPreferences(spinnerArray.get(spinner.getSelectedItemPosition()).get("sf"), 0).getString("number", ""));
                intent.putExtra("data", "INFO");
                startActivity(intent);
            }
        });
        //농장 선택 Adapter

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, titleList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setGravity(Gravity.CENTER);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                pager = (ViewPager) v.findViewById(R.id.pager1);
                pagerAdapter pagerAdapter = new pagerAdapter(getFragmentManager());
                pagerAdapter.setSpinnerPosition(spinner.getSelectedItemPosition());
                pager.setAdapter(pagerAdapter);
                pager.setCurrentItem(0);
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
                view.setBackgroundColor(Color.parseColor("#C69FFF"));
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
                btn.setBackgroundColor(Color.parseColor("#8A3CFF"));
                btn.setSelected(false);
            } else {
                btn.setBackgroundColor(Color.parseColor("#C69FFF"));
                btn.setSelected(true);
            }
        }
    }
    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        private int spinnerPosition = -1;

        public pagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            equipment[position].setSelected(true);
            equipment[position].setBackgroundColor(Color.parseColor("#8A3CFF"));
            return new CustomAdapter("sFile"+position,spinnerPosition+"_INFO", position+1);
        }

        @Override
        public int getCount() {
            // total page count
            return 4;
        }

        public void setSpinnerPosition(int position){
            spinnerPosition = position;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    private void setButtonsPink() {
        for (int i = 0; i < equipment.length; i++) {
            // 버튼의 포지션(배열에서의 index)를 태그로 저장
            equipment[i].setBackgroundColor(Color.parseColor("#8A3CFF"));
        }
    }

    public void updateUI(){
        Log.e(TAG, "startUpdate");



    }

}

