package com.example.cooktimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

//스피너에 의한 가변적 데이터 넣기
public class Food_Item_For_Start_Alarm extends AppCompatActivity{
    public int hour,min,sec;
    ArrayAdapter<CharSequence> adspin;
    TextView FoodName; //검색되어서 나오는 재료 데이터 장착
    TextView TimerNum; //검색되어서 나오는 재료 데이터에 맞는 조리시간 데이터 장착

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_for_start_alarm);

        Calendar mcalendar = Calendar.getInstance();
        mcalendar.set(Calendar.HOUR_OF_DAY,hour);
        mcalendar.set(Calendar.MINUTE,min);
        mcalendar.set(Calendar.SECOND,sec);

        final Button start = (Button) findViewById(R.id.startB);
        final Button stop = (Button) findViewById(R.id.stopB);
//        final Spinner CookTypeSpin=(Spinner) findViewById(R.id.spinner);

//        //스피너 설정 = 재료에 따른 조리시간을 불러와야함
//        adspin = ArrayAdapter.createFromResource(this,R.array.cooktype,android.R.layout.simple_spinner_dropdown_item);
//        CookTypeSpin.setAdapter(adspin);
//        CookTypeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(adspin.getItem(position).equals("굽기")){
//                    //TODO 굽기에 대한 조리시간 입력
//                }else if(adspin.getItem(position).equals("삶기")){
//                    //TODO 삶기에 대한 조리시간 입력
//                }else if(adspin.getItem(position).equals("찜")){
//                    //TODO 찌기에 대한 조리시간 입력
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }
}
