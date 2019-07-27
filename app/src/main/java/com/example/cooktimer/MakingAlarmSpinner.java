package com.example.cooktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.List;

public class MakingAlarmSpinner extends AppCompatActivity {
    List<Data> listdata = new ArrayList<>();

    //어댑터에 보낼 데이터들
    int hourvalue; //시간
    int minvalue; //분
    int secvalue; //초
    String food; //식재료명
    String exp; //조리설명

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_alarm_spinner);


        EditText foodname = (EditText)findViewById(R.id.foodname);
        EditText explain = (EditText)findViewById(R.id.explain);
        Button ok = (Button)findViewById(R.id.okB);
        Button cancel = (Button)findViewById(R.id.cancelB);

        //식재료명
       food = foodname.getText().toString();
       //조리설명
       exp = explain.getText().toString();

        //시간 세팅
        NumberPicker hourpicker =(NumberPicker) findViewById(R.id.hour);
        hourpicker.setMinValue(0);
        hourpicker.setMaxValue(12);
        hourpicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        //시간 값 받아오기 메소드
        hourpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                hourvalue = newVal;
                Log.d("태그","시간 값 확인: "+hourvalue);
            }
        });

        //분 세팅
        NumberPicker minpicker = (NumberPicker) findViewById(R.id.min);
        minpicker.setMinValue(0);
        minpicker.setMaxValue(59);
        minpicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        //분 값 받아오기 메소드
        minpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minvalue = newVal;
                Log.d("태그","분 값 확인: "+minvalue);
            }
        });

        //초 세팅
        NumberPicker secpicker = (NumberPicker) findViewById(R.id.sec);
        secpicker.setMinValue(0);
        secpicker.setMaxValue(59);
        secpicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });
        //초값 받아오기 메소드
        secpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                secvalue = newVal;
                Log.d("태그","초 값 확인: "+secvalue);
            }
        });

        //완료버튼
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 음식명,조리설명 메이킹 아이템에 전달. 시간,분,초 값 스타트아이템타이머에 입력값 전달하기
                //값을 넣기 위한 Data 객체 생성자
                Data data = new Data(hourvalue,minvalue,secvalue,food,exp);
                data.setFood(food);
                data.setExp(exp);
                data.setHourvalue(hourvalue);
                data.setMinvalue(minvalue);
                data.setSecvalue(secvalue);

                //어댑터에 값들 넣기
                Intent intent = new Intent(getApplicationContext(),MakingAlarmSpinner.class);
                intent.putExtra("index",0);
                Log.d("태그","data 값 food 확인: "+data.getFood().toString());
                Log.d("태그","시간 값 확인"+data.getHourvalue());
                Log.d("태그","분 값 확인"+data.getMinvalue());
                Log.d("태그","초 값 확인"+data.getSecvalue());
                finish();
            }
        });

        //취소버튼
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    private class MyListener implements NumberPicker.OnValueChangeListener{
//        @Override
//        public void onValueChange(NumberPicker picker, int oldVal, int newVal){
//            int value = newVal;
//        }
//    }
}
