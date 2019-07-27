package com.example.cooktimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

//간단한 식재료만 표시 ex)감자,당근,파스타면 등등
//어댑터 만들기 +리사이클러 상속
public class MainActivity extends AppCompatActivity {
    //뒤로가기 제어
    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;

    //현재 로그인 된 유저 정보를 담을 변수
    private FirebaseUser currentUser;
    //이메일 비밀번호 로그인 모듈 변수
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = firebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        RecyclerView recyclerView = findViewById(R.id.MainRecyclerView);

        //화면전환 버튼 -> 내 알람 화면(로그인 확인 분기)
        ImageButton myalarmB = (ImageButton) findViewById(R.id.myalarmB);
        myalarmB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        //check if user is signed in (non-null) and update UI accordingly.
                        if(currentUser!=null){
                            startActivity(new Intent(MainActivity.this,Custom_Alarm_List_Login.class));
                            Log.i("태그", "내 알람 화면 이동 로그인");

                        }else if(currentUser==null) {
                            Intent intent = new Intent(getApplicationContext(), Custom_Alarm_List_Logout.class);
                            Log.i("태그", "내 알람 화면 이동 비로그인");
                            startActivity(intent);
                        }
                    }
                });
        //화면전환 버튼 -> 북마크 화면(로그인 확인 분기)
        ImageButton bookmarkB = (ImageButton) findViewById(R.id.bookHB);
        bookmarkB.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        //check if user is signed in (non-null) and update UI accordingly.
                        if(currentUser!=null){
                            startActivity(new Intent(MainActivity.this,Bookmark_Alarm_List_Login.class));
                            Log.i("태그", "북마크 화면 이동 로그인");
                        }else if(currentUser==null) {
                            Intent intent = new Intent(getApplicationContext(), Bookmark_Alarm_List_Logout.class);
                            Log.i("태그", "북마크 화면 이동 비로그인");
                            startActivity(intent);
                        }
                    }
                });
    }
@Override
public void onResume(){
        super.onResume();

}
    //뒤로가기 방지
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
