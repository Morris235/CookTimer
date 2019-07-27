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

public class SignUpPage extends AppCompatActivity {
    private EditText email_join;
    private EditText pwd_join;
    private Button btn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        email_join = (EditText) findViewById(R.id.edit_email);
        pwd_join = (EditText) findViewById(R.id.edit_passwords);
        btn = (Button) findViewById(R.id.SignedUp);
        //firebaseAuth의 인스턴스를 가져온다.
        firebaseAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String email = email_join.getText().toString().trim();
                String pwd = pwd_join.getText().toString().trim();
                //이메일 주소와 비밀번호를 createUserWithEmailAndPassword에 전달하여 신규 계정을 생성한다.
                firebaseAuth.createUserWithEmailAndPassword(email,pwd)
                        .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //회원가입 성공시 메인 화면으로 전환되고
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(SignUpPage.this, Custom_Alarm_List_Login.class);
                                    startActivity(intent);
                                    Toast.makeText(SignUpPage.this,"회원가입 완료",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    //실패시 오류 토스트메세지를 띄어 오류알림
                                    Toast.makeText(SignUpPage.this,"등록 에러: 이메일 형식과 비밀번호 형식을 지켜주세요",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });
    }
}
