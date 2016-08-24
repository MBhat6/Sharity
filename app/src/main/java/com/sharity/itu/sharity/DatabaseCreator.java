package com.sharity.itu.sharity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


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
    public static final String SWITCH = "SWITCH";
    Context contextDb;

    Date curDate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("MM-DD-yy");
    String DateToStr = format.format(curDate);



    public DatabaseCreator(Context context) {

        super(context, DATABSE_NAME, null, 1);
        contextDb = context;
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_INFO_TABLE + "(" + USER_NAME + " VARCHAR(50) NOT NULL, "
                                                          + PASSWORD + " VARCHAR(15) NOT NULL, "
                                                          + EMAIL_ID + " VARCHAR(50) PRIMARY KEY NOT NULL, "
                                                          + CURRENT_PROG + " VARCHAR(20) NOT NULL, "
                                                          + INTR_TOPICS + " VARCHAR(100) NOT NULL, "
                                                          + EXPERTISE + " VARCHAR(100) NOT NULL, "
                                                          + SWITCH + " VARCHAR(5) NOT NULL ) ");

        db.execSQL("CREATE TABLE " + REQUEST_TABLE + "("    + USER_NAME + " VARCHAR(50) NOT NULL, "
                                                            + EMAIL_ID + " VARCHAR(50) NOT NULL, "
                                                            + CATEGORY + " VARCHAR(20) NOT NULL, "
                                                            + PRIORITY + " VARCHAR(10) NOT NULL, "
                                                            + TITLE + " VARCHAR(15) NOT NULL, "
                                                            + DESCRIPTION + " VARCHAR(300) NOT NULL, "
                                                            + CREATE_DATE + " DATE NOT NULL, "
                                                            + COMMENTS + " VARCHAR(300), "
                                                            + REPLY_USER_NAME + " VARCHAR(50), "
                                                            + REPLY_DATE + " DATE ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + REQUEST_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_INFO_TABLE);
        onCreate(db);
    }

    /**
     * Save the request details
     * @param saveDetails
     */
    public void saveRequest(Details saveDetails){

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USER_NAME, saveDetails.getName());
            values.put(EMAIL_ID, saveDetails.getEmail());
            values.put(CATEGORY, saveDetails.getCategory());
            values.put(PRIORITY, saveDetails.getPriority());
            values.put(TITLE, saveDetails.getTitle());
            values.put(DESCRIPTION, saveDetails.getDesc());
            values.put(CREATE_DATE, DateToStr);
            values.put(REPLY_USER_NAME, "");

            long rowInserted = db.insert(REQUEST_TABLE, COMMENTS, values);
            db.close();

            if(rowInserted != -1) {

                Log.i("Row insertion", "New request row ID: " + rowInserted);
                Toast.makeText(contextDb, "New request has been created. ", Toast.LENGTH_SHORT).show();

                Log.i("clicks","Navigating to home page");
                Intent i = new Intent(contextDb, HomePage.class);
                contextDb.startActivity(i);
            }

            else {
                Toast.makeText(contextDb, "Oops!!! Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(contextDb, "An error occurred while creating a new request. Please try again later",Toast.LENGTH_SHORT).show();
        }

    }
}
