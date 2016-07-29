package com.sharity.itu.sharity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Madhura on 7/17/2016.
 */
public class DatabaseCreator extends SQLiteOpenHelper {

    public static final String DATABSE_NAME = "SHARITY.db";

    public static final String USER_INFO_TABLE = "USER_INFO";
    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String EMAIL_ID = "EMAIL_ID";
    public static final String CURRENT_PROG = "CURRENT_PROG";
    public static final String INTR_TOPICS = "TOPICS";
    public static final String EXPERTISE = "EXPERTISE";


    public static final String REQUEST_TABLE = "REQUEST_INFO";
    public static final String CATEGORY = "CATEGORY";
    public static final String PRIORITY = "PRIORITY";
    public static final String TITLE = "TITLE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String CREATE_DATE = "CREATE_DT";
    public static final String COMMENTS = "COMMENTS";
    public static final String REPLY_USER_NAME = "USER_NAME_RP";
    public static final String REPLY_DATE = "REPLY_DT";



    public DatabaseCreator(Context context) {
        super(context, DATABSE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_INFO_TABLE + "(" + USER_NAME + " VARCHAR(50) NOT NULL, "
                                                          + PASSWORD + " VARCHAR(15) NOT NULL, "
                                                          + EMAIL_ID + " VARCHAR(50) PRIMARY KEY NOT NULL, "
                                                          + CURRENT_PROG + " VARCHAR(20) NOT NULL, "
                                                          + INTR_TOPICS + " VARCHAR(100) NOT NULL, "
                                                          + EXPERTISE + " VARCHAR(100) NOT NULL ) ");

        db.execSQL("CREATE TABLE " + REQUEST_TABLE + "("     + USER_NAME + " VARCHAR(50) NOT NULL, "
                                                            + EMAIL_ID + " VARCHAR(50) PRIMARY KEY NOT NULL, "
                                                            + CATEGORY + " VARCHAR(20) NOT NULL, "
                                                            + PRIORITY + " VARCHAR(10) NOT NULL, "
                                                            + TITLE + " VARCHAR(15) NOT NULL, "
                                                            + DESCRIPTION + " VARCHAR(300) NOT NULL, "
                                                            + CREATE_DATE + " DATE NOT NULL, "
                                                            + COMMENTS + " VARCHAR(300) NOT NULL, "
                                                            + REPLY_USER_NAME + " VARCHAR(50) NOT NULL, "
                                                            + REPLY_DATE + " DATE NOT NULL ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
