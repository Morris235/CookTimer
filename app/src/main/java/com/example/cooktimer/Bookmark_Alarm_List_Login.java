package com.example.cooktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

//어댑터 만들기 +리사이클러 상속
public class Bookmark_Alarm_List_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_alarm_list);

        //화면전환 버튼 -> 메인화면 (로그인)
        ImageButton homeB= (ImageButton)findViewById(R.id.homeB);
        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //화면전환 버튼 -> 내 알람 화면 (로그인)
        ImageButton bookB  = (ImageButton)findViewById(R.id.myalamB);
        bookB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Custom_Alarm_List_Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
