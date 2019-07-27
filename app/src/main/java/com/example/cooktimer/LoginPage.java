package com.example.cooktimer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {
    //현재 로그인 된 유저 정보를 담을 변수
    private FirebaseUser currentUser;
    //이메일 비밀번호 로그인 모듈 변수
    private FirebaseAuth firebaseAuth;

    private Button join;
    private  Button login;
    private EditText email_login;
    private EditText pwd_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        join = (Button) findViewById(R.id.signB);
        login = (Button) findViewById(R.id.loginB);
        email_login = (EditText) findViewById(R.id.edit_E);
        pwd_login = (EditText) findViewById(R.id.edit_P);

        firebaseAuth = firebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_login.getText().toString().trim();
                String pwd = pwd_login.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginPage.this, Custom_Alarm_List_Login.class);
                            Toast.makeText(LoginPage.this, "환영합니다 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginPage.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        //화면전환 버튼 ->회원가입 페이지
        Button signup = (Button) findViewById(R.id.signB);
        signup.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),SignUpPage.class);
                        startActivity(intent);
                    }
                });

        //화면전환 버튼 ->비밀번호 찾기 페이지
        Button passwordfinder = (Button) findViewById(R.id.findpass);
        passwordfinder.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        Intent intent = new Intent(getApplicationContext(),PassWordFinder.class);
                        startActivity(intent);
                    }
                });
    }

}
