package com.example.cooktimer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MakingAlarmPopup_Adapter extends RecyclerView.Adapter<MakingAlarmPopup_Adapter.ItemViewHolder> {
//private Context context;
//private List<String> items;
//private int itemLayout;

//adapter에 들어갈 list
private  Context context;
private final List<Data> mlistData;
private int itemlayout;
    //외부에서 데이터를 받을 수 있도록 생성자를 만든다. Data 클래스 형태의 리스트를 다루겠다.
public MakingAlarmPopup_Adapter(Context context,int itemlayout ,List<Data> listData){
    this.context=context;
    this.mlistData = listData;
    this.itemlayout = itemlayout;
}

// 1.MakingAlarmPopupAdapter의 생성자 컨텍스트와 아이템 데이터 그리고 아이템을 보여주기 위한 아이템 레이아웃을 전달 받는다.
//public MakingAlarmPopup_Adapter(Context context, List<String> items,int itemLayout){
//    this.context=context;
//    this.items=items;
//    this.itemLayout=itemLayout;
//}

    //뷰홀더를 만드는 부분 리턴을 하게 되면 onBindViewHolder로 뷰홀더가 들어간다.
    @NonNull
    @Override
    public MakingAlarmPopup_Adapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
                                                               //R.layout.activity_food_item_for_making
    View view = LayoutInflater.from(parent.getContext()).inflate(itemlayout,parent,false);
        return new ItemViewHolder(view);
    }

    //ViewHolder 내부 클래스
  public static class ItemViewHolder extends RecyclerView.ViewHolder{
    //ItemViewHolder에 들어갈 데이터 선언
    private TextView foodname;
    private TextView cookexplain;
   private TextView hour;
   private TextView min;
   private TextView sec;

    ItemViewHolder(View itemView){
        super(itemView);
        foodname = itemView.findViewById(R.id.foodname);
        cookexplain = itemView.findViewById(R.id.cookexplain);
        hour = itemView.findViewById(R.id.hour);
        min = itemView.findViewById(R.id.min);
        sec = itemView.findViewById(R.id.sec);
    }

    //onBindViewHolder 에 들어갈 메소드
//        void onBind(Data data){
//
//        foodname.setText(data.getFood());
//        cookexplain.setText(data.getExp());
//        hour.setText(data.getHourvalue());
//        min.setText(data.getMinvalue());
//        sec.setText(data.getSecvalue());
//        }
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
    //아이템을 하나 하나 보여주는(bind 되는) 메소드
//        itemViewHolder.onBind(mlistData.get(position)); onBind 메소드를 이용해서 코드수를 줄이고 한번에 바인드를 할 수 있다.
        Data item = mlistData.get(position);
        itemViewHolder.foodname.setText(item.getFood());
        itemViewHolder.cookexplain.setText(item.getExp());
        itemViewHolder.hour.setText(item.getHourvalue());
        itemViewHolder.min.setText(item.getMinvalue());
        itemViewHolder.sec.setText(item.getMinvalue());

    }

    //MakingAlarmSpinner의 데이터 총 개수수
    @Override
    public int getItemCount() {
        return mlistData.size();
    }

    //외부에서 item을 추가시킬수 있는 함수
//    void additem(Data data){
//        mlistData.add(data);
//    }
}