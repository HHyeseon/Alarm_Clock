package com.example.alarm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
   // String title;
   // Date createTime;
    int hour;
    int minute;
    boolean checked;

    static SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public Item(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
/*
    public String getTitle() {
        return title;
    }
*/

 /*
    public Date getCreateTime() {
        return createTime;
    }
    */

    public int getHour(){
        return hour;
    }

    public int getMinute(){
        return minute;
    }


    public String getCreateTimeFormatted(){
        return format.format(hour);
    }

/*
    public String getCreateTimeFormatted() {
        return format.format(createTime);
    }
*/
    public boolean isChecked() { return checked; }
    public void setChecked(boolean checked) { this.checked = checked; }
}