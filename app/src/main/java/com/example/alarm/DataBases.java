package com.example.alarm;
import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final int hour = 0;
        public static final int minute = 0;
        //    public static final String _TABLENAME0 = "usertable";           //테이블 생성
        public static final int _TABLENAME0 = 1;           //테이블 생성

        /*
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +hour+"text not null,"
                +minute+"text not null";
    }
    */

        public static final int _CREATE0 = _TABLENAME0
                + hour
                + minute;
    }
}
