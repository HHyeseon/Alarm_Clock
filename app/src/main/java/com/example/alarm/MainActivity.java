package com.example.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.EditText;


import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.ArrayList;
import java.lang.String;

import android.content.Intent;


public class MainActivity extends AppCompatActivity{

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pendingIntent;


    MyRecyclerViewAdapter myRecyclerViewAdapter;
    ArrayList<Item> arrayList = new ArrayList<Item>();
  //  ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, arrayList);
        this.context = this;

        // 알람매니저 설정
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // 타임피커 설정
        alarm_timepicker = findViewById(R.id.time_picker);

        // Calendar 객체 생성
        final Calendar calendar = Calendar.getInstance();

        // 알람리시버 intent 생성
        final Intent my_intent = new Intent(this.context, Alarm_Reciver.class);

        // 알람 시작 버튼
        Button alarm_on = findViewById(R.id.btn_start);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                // calendar에 시간 셋팅
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                // 시간 가져옴
                int hour = alarm_timepicker.getHour();
             //   int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = alarm_timepicker.getMinute();
           //     int minute = calendar.get(Calendar.MINUTE);
                Toast.makeText(MainActivity.this,"Alarm 예정 " + hour + "시 " + minute + "분",Toast.LENGTH_SHORT).show();


                // reveiver에 string 값 넘겨주기
                my_intent.putExtra("state","alarm on");

                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, my_intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                // 알람셋팅
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                //데이터를 목록에 추가

       //         Intent intent = new Intent(getBaseContext(),AlarmList.class);
              //  Intent intent = new Intent(context,AlarmList.class);

             /*
                Intent intent = new Intent(MainActivity.this,AlarmList.class);
                intent.putExtra("hour", hour);
                intent.putExtra("minute", minute);
                startActivity(intent);
                */

             Bundle b = new Bundle();
             b.putInt("hour", hour);
             b.putInt("minute", minute);
             Intent intent = new Intent(MainActivity.this,AlarmList.class);
             intent.putExtra("hour", hour);
             intent.putExtra("minute", minute);
             startActivity(intent);
          //      arrayList.add(new Item(hour, minute));
           //     myRecyclerViewAdapter.notifyDataSetChanged();                   //err(NullPointer)
             //   adapter.notifyDataSetChanged();                                   //err(Runtime)


            }
        });

        // 알람 정지 버튼
        Button alarm_off = findViewById(R.id.btn_finish);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Alarm 종료",Toast.LENGTH_SHORT).show();
                // 알람매니저 취소
                alarm_manager.cancel(pendingIntent);

                my_intent.putExtra("state","alarm off");

                // 알람취소
                sendBroadcast(my_intent);


            }
        });

        Button list =  findViewById(R.id.btn_list);
        list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), AlarmList.class);
                startActivity(intent);
            }
        });
    }

}