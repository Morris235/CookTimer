package com.example.cooktimer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingPage extends AppCompatActivity {

    ProgressBar androidProgressBar;
    int progressStatusCounter = 0;
    TextView textView;
    Handler progressHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        androidProgressBar =(ProgressBar) findViewById(R.id.progressBar);
        //start progressing
        new Thread(new Runnable() {
            @Override
            public void run() {
           while (progressStatusCounter <100){
               progressStatusCounter +=10;
               progressHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       androidProgressBar.setProgress(progressStatusCounter);
                   }
               });
               try{
                   Thread.sleep(300);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
            }
        }).start();
    }
}
