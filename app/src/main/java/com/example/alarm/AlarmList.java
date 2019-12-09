package com.example.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import android.widget.TimePicker;



public class AlarmList extends AppCompatActivity {

    MyRecyclerViewAdapter myRecyclerViewAdapter;
    ArrayList<Item> arrayList;
    final Calendar calendar = Calendar.getInstance();
   // int hour = calendar.get(Calendar.HOUR_OF_DAY);
   // int minute = calendar.get(Calendar.MINUTE);
   TimePicker alarm_timepicker;





 //   ArrayAdapter<Item> adapter;
  //  Intent intent = getIntent();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);

       //     int hour = intent.getExtras().getInt("hour");     //err
       //     int minute = intent.getExtras().getInt("minute");   //err
        /*
        int hour;
        int minute;
        try{
            Bundle extras = getIntent().getExtras();
            hour = extras.getInt("hour");
            minute = extras.getInt("minute");
        } catch(Exception e) {
            hour = 0;
            minute = 0;
        }
*/


        int hour;
        int minute;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            hour = bundle.getInt(String.valueOf("hour"));
            minute = bundle.getInt(String.valueOf("minute"));
        }
        else{
            hour = -1;
            minute = -1;

        }



       /*   Bundle extras = getIntent().getExtras();
        int hour = extras.getInt("hour");
        int minute = extras.getInt("minute");
        */




        arrayList = new ArrayList<Item>();
        arrayList.add(new Item(hour, minute));

  //      myRecyclerViewAdapter.notifyDataSetChanged();

      //  adapter=new ArrayAdapter<Item>(AlarmList.this, android.R.layout.activity_list_item, arrayList);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, arrayList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myRecyclerViewAdapter);


        //List에서 삭제
        Button b = (Button)findViewById(R.id.btn_delete);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                for (int i = arrayList.size() - 1; i >= 0; --i)
                    if (arrayList.get(i).isChecked())
                        arrayList.remove(i);
                myRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

}

