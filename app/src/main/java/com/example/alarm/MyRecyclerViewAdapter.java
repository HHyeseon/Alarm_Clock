package com.example.alarm;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {    //err(NotFound)

    LayoutInflater layoutInflater;
    ArrayList<Item> arrayList =  new ArrayList<Item>();
    int checkedItemCount = 0;

    class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        TextView textView1;
        CheckBox checkBox;

        public ViewHolder(View view){
            super(view);
            textView1 = view.findViewById(R.id.textView1);
            this.checkBox = view.findViewById(R.id.checkBox);
            this.checkBox.setOnCheckedChangeListener(this);

        }

        public void setData(){
            Item item = arrayList.get(getAdapterPosition());
            textView1.setText(String.valueOf(item.getHour()) + ":" + String.valueOf(item.getMinute()));              //err(NotFound)

        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Item item = arrayList.get(super.getAdapterPosition());
            item.setChecked(isChecked);
            if (isChecked) ++checkedItemCount;
            else --checkedItemCount;
           // if (checkedItemCount <= 1)
            //    ((Activity)textView1.getContext()).invalidateOptionsMenu();
        }


    }



    public MyRecyclerViewAdapter(Context context, ArrayList<Item>arrayList){
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList=arrayList;
    }

    @Override
    public int getItemCount(){
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = layoutInflater.inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index){
        viewHolder.setData();               //err(NotFound)
    }

}
