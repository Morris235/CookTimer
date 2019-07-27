//package com.example.cooktimer;
//
//import android.app.AppComponentFactory;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class test extends RecyclerView.Adapter<test.ItemViewHolder> {
//    /*onCreate->AdapterName adaptername = new AdapterName(new Clickevent)*/
//    /*recyclerview.setAdapter(adapter)*/
//    /*List<DataModel> datamodel = ArrayList<>();*/
//    /*datamodel.add(new DataModel(data1: data, data2:data))*/
//    /*datamodel.add(new DataModel(data1: data, data2:data))*/
//    /*adapter.setItems(datamodel)*/
//
//    //아이템 클릭했을때 인터페이스 이름
//    interface OnItemClickListener {
//    //콜백 메소드 이름
//        void OnItemClicked(MakingAlarmSpinner model);
//    }
//
//    private AdapterView.OnItemClickListener mListener;
//
//    private List<MakingAlarmSpinner> mItems = new ArrayList<>();
//
//    public test() {}
//
//    public test( AdapterView.OnItemClickListener listener) {
//        mListener = listener;
//    }
//
//    public void setItems(List<MakingAlarmSpinner> items) {
//        this.mItems = items;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout./*ItemLayOut*/, parent, false);
//        final ItemViewHolder viewHolder = new ItemViewHolder(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    final MakingAlarmSpinner item = mItems.get(viewHolder.getAdapterPosition());
//                    mListener.OnItemClicked(item);
//                }
//            }
//        });
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
//        MakingAlarmSpinner item = mItems.get(position);
//        // TODO : 데이터를 뷰홀더에 표시하시오
//        // EX) holder.data1.setText(position.getData());
//        // EX) holder.data2.setText(position.getData());
//    }
//
//    @Override
//    public int getItemCount() {
//        return mItems.size();
//    }
//
//    public static class ItemViewHolder extends RecyclerView.ViewHolder {
//        // TODO : 뷰홀더 완성하시오
//        // EX) TextView data1;
//        // EX) TextView data2;
//        public ItemViewHolder(@NonNull View itemView) {
//            super(itemView);
//            // TODO : 뷰홀더 완성하시오
//            // EX) data1 = itemView.findViewById(R.id.ViewName);
//            // EX) data2 = itemView.findViewById(R.id.ViewName);
//        }
//    }
//}