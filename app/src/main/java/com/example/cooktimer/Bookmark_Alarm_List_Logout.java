package com.example.cooktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Bookmark_Alarm_List_Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_alarm_list_logout);

        //화면전환 -> 메인화면
        ImageButton homeB =(ImageButton)findViewById(R.id.homeB);
        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Log.i("태그", "메인 페이지 이동");
                startActivity(intent);
                finish();
            }
        });
        //화면전환 버튼 -> 내 알람 화면(비로그인)
        ImageButton customB = (ImageButton) findViewById(R.id.myalamBB);
        customB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                            Intent intent = new Intent(getApplicationContext(), Custom_Alarm_List_Logout.class);
                            Log.i("태그", "내 알람 화면 이동");
                            startActivity(intent);
                            finish();
                        }
                    });
    }
//    //로그인 되어있으면 메인페이지로 이동 - 생명주기 활용 - 메인페이지로 이동 크리에이터에 넣는것도 고려
//    @Override
//    public void onStart(){
//        super.onStart();
//        //check if user is signed in (non-null) and update UI accordingly.
//        currentUser = firebaseAuth.getCurrentUser();
//        if(currentUser!=null){
//            startActivity(new Intent(Bookmark_Alarm_List_Logout.this,Bookmark_Alarm_List_Login.class));
//            finish();
//        }
//    }
}
