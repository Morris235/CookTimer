package com.example.cooktimer;

import android.util.Log;

public class Data {
//    public Data (){
//
//    }
    public Data(int hourvalue, int minvalue, int secvalue, String food, String exp) {
        this.hourvalue = hourvalue;
        this.minvalue = minvalue;
        this.secvalue = secvalue;
        this.food = food;
        this.exp = exp;
    }

    //어댑터에 보낼 데이터들
   private int hourvalue; //시간
   private int minvalue; //분
   private int secvalue; //초
   private String food; //식재료명
   private String exp; //조리설명

    public int getHourvalue() {
        return hourvalue;
    }

    public void setHourvalue(int hourvalue) {
        this.hourvalue = hourvalue;
    }

    public int getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(int minvalue) {
        this.minvalue = minvalue;
    }

    public int getSecvalue() {
        return secvalue;
    }

    public void setSecvalue(int secvalue) {
        this.secvalue = secvalue;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
