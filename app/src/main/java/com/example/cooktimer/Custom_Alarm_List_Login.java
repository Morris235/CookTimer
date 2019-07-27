package com.example.cooktimer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//어댑터 만들기 +리사이클러 상속
public class Custom_Alarm_List_Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_alarm_list);

        firebaseAuth = FirebaseAuth.getInstance();

        //화면전환 버튼 -> 알람만들기 팝업(로그인)
        ImageButton addalarm = (ImageButton) findViewById(R.id.addalarmB);
        addalarm.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),MakingAlarmPopup.class);
                        startActivity(intent);
                    }
                });
        //화면전환 버튼 -> 북마크 (로그인)
        ImageButton bookB = (ImageButton)findViewById(R.id.bookmarkB);
        bookB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Bookmark_Alarm_List_Login.class);
                startActivity(intent);
                finish();
            }
        });

        //화면전환 버튼 -> 메인화면 (로그인)
        ImageButton homeB = (ImageButton)findViewById(R.id.homeB);
        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //로그아웃 처리
        Button logoutB = (Button)findViewById(R.id.logoutB);
        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut(); finish(); Intent intent1 = new Intent(getApplicationContext(),Custom_Alarm_List_Logout.class); startActivity(intent1);
                Toast.makeText(Custom_Alarm_List_Login.this,"로그아웃 되었습니다..",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        //회원탈퇴 처리
        Button unsignB = (Button)findViewById(R.id.unsignB);
        unsignB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(Custom_Alarm_List_Login.this);
                alert_confirm.setMessage("계정을 삭제 하시겠습니까?").setCancelable(false).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(Custom_Alarm_List_Login.this,"계정이 삭제 되었습니다.",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(),Custom_Alarm_List_Logout.class));
                                finish();
                            }
                        });
                    }
                });
                alert_confirm.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Custom_Alarm_List_Login.this,"취소",Toast.LENGTH_LONG).show();
                    }
                });
                alert_confirm.show();
            }
        });
    }
}
