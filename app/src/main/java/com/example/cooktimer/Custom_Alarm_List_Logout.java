package com.example.cooktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Custom_Alarm_List_Logout extends AppCompatActivity {
    //현재 로그인 된 유저 정보를 담을 변수
    private FirebaseUser currentUser;
    //이메일 비밀번호 로그인 모듈 변수
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_alarm_list_logout);

        firebaseAuth = firebaseAuth.getInstance();

        currentUser = firebaseAuth.getCurrentUser();
        //check if user is signed in (non-null) and update UI accordingly.
        if(currentUser!=null){
            startActivity(new Intent(Custom_Alarm_List_Logout.this,Custom_Alarm_List_Login.class));
            finish();
        }
        //화면전환 -> 로그인 페이지
        Button loginB = (Button)findViewById(R.id.loginB);
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                Log.i("태그", "로그인 페이지 이동");
                startActivity(intent);
            }
        });
        //화면전환 -> 북마크 화면(비로그인)
        ImageButton bookB = (ImageButton)findViewById(R.id.bookCB);
        bookB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Bookmark_Alarm_List_Logout.class);
                Log.i("태그", "북마크 이동");
                startActivity(intent);
                finish();
            }
        });
        //화면전환 -> 홈화면
        ImageButton homeB =(ImageButton)findViewById(R.id.homeB);
        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Log.i("태그", "로그인 페이지 이동");
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
//            startActivity(new Intent(Custom_Alarm_List_Logout.this,Custom_Alarm_List_Login.class));
//            finish();
//        }
//    }
}
