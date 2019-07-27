package com.example.cooktimer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

//데이터는 당연히 한 클래스 내에서 모두 정리한다.
//1.굽기 2.삶기 3.찜
//검색데이터
public class CookTime {
    //재료를 검색을 해야하기 때문에 리스트 형태의 객체를 선언
    //검색 변수를 선언하고 그 변수를 이용해 FoodDictionary 대조 탐색
    //리스트 길이 측정 후 반복문으로 검색
    //변수를 선언후 그 변수에 검색된 음식이름 널고 CookTimeData 메소드에 FoodName 인자로 전달
//ArrayList<FoodDictionary> FoodList;
////인덱스 검색에 용이한 hashset으로 변환
//HashSet FoodNameSet = new HashSet(FoodList);
//Iterator<String> iterator= FoodNameSet.iterator();
//public String FoodName;
//
////int counter = FoodNameSet.size();
//
////음식재료명 검색 메소드
//public String FoodSearcher (String UserInputSearch){
//    if (FoodNameSet.isEmpty() == false) {
//        //배열의 처음부터 끝까지 검색(선형검색)
//        while (FoodNameSet.iterator().hasNext()){
//
//        }
//        }
//    return FoodName;
//}
//
////<검색>에 의한 FoodName을 메소드에 입력 = 검색은 스트링값만 검색해서 넣기
//    public int CookTiemData(String FoodName,String CookType){
//        int CookTime=0;
//        //CookTime = sec
//        int RoastTime;
//        int BoilTime;
//        int SteamTIme;
//
//        //감자 조리시간
//        if(FoodName == "potato"){
//            //CookTime
//            RoastTime=300;
//            BoilTime=2100;
//            SteamTIme=2400;
//
//            if(CookType == "굽기"){
//                CookTime = RoastTime;
//            }else if(CookType == "삶기"){
//                CookTime = BoilTime;
//            }else if(CookType == "찜") {
//                CookTime = SteamTIme;
//            }
//        }
//        //당근 조리시간
//        if(FoodName == "carrot"){
//            //CookTime
//            RoastTime=2400;
//            BoilTime=1200;
//            SteamTIme=720;
//
//            if(CookType == "굽기"){
//                CookTime = RoastTime;
//            }else if(CookType == "삶기"){
//                CookTime = BoilTime;
//            }else if(CookType == "찜") {
//                CookTime = SteamTIme;
//            }
//        }
//return CookTime;
//    }
////    public int CustomCookTimeData (){
////
////    }
}
