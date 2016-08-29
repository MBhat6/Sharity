package com.sharity.itu.sharity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Madhura on 7/17/2016.
 */
public class DatabaseCreator extends SQLiteOpenHelper {

    public static final int OLD_VERSION = 4;
    public static final int NEW_VERSION = 5;

    public static final String DATABSE_NAME = "SHARITY.db";

    public static final String REQUEST_ID = "REQUEST_ID";
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
    public static final String HINT = "HINT";
    Context contextDb;

    Date curDate = new Date();
    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    String DateToStr = format.format(curDate);


    public DatabaseCreator(Context context) {

        super(context, DATABSE_NAME, null, 1);
        contextDb = context;
        SQLiteDatabase db = this.getWritableDatabase();

        //onUpgrade(db, OLD_VERSION, NEW_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE " + USER_INFO_TABLE + "(" + USER_NAME + " VARCHAR(60) NOT NULL, "
                                                                + PASSWORD + " VARCHAR(50) NOT NULL, "
                                                                + EMAIL_ID + " VARCHAR(100) PRIMARY KEY NOT NULL, "
                                                                + CURRENT_PROG + " VARCHAR(100) NOT NULL, "
                                                                + INTR_TOPICS + " VARCHAR(200) NOT NULL, "
                                                                + EXPERTISE + " VARCHAR(200) NOT NULL, "
                                                                + HINT + " VARCHAR(22) NOT NULL ) ");

            db.execSQL("CREATE TABLE " + REQUEST_TABLE + "(" + REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                            + USER_NAME + " VARCHAR(60) NOT NULL, "
                                                            + EMAIL_ID + " VARCHAR(100) NOT NULL, "
                                                            + CATEGORY + " VARCHAR(50) NOT NULL, "
                                                            + PRIORITY + " VARCHAR(10) NOT NULL, "
                                                            + TITLE + " VARCHAR(100) NOT NULL, "
                                                            + DESCRIPTION + " VARCHAR(1500) NOT NULL, "
                                                            + CREATE_DATE + " DATE NOT NULL, "
                                                            + COMMENTS + " VARCHAR(5000), "
                                                            + REPLY_USER_NAME + " VARCHAR(100), "
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
     *
     * @param saveDetails
     */
    public void saveRequest(Details saveDetails) {

        try {
            Log.i("Date value:", DateToStr);
            Log.i("User name ", saveDetails.getName());
            Log.i("User email ", saveDetails.getEmail());
            //  Log.i("User Password", saveDetails.getPassword());

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(USER_NAME, saveDetails.getName());
            values.put(EMAIL_ID, saveDetails.getEmail());
            values.put(CATEGORY, saveDetails.getCategory());
            values.put(PRIORITY, saveDetails.getPriority());
            values.put(TITLE, saveDetails.getTitle());
            values.put(DESCRIPTION, saveDetails.getDesc());
            //  values.put(PASSWORD, saveDetails.getPassword());
            values.put(CREATE_DATE, DateToStr);
            values.put(REPLY_USER_NAME, "");

            long rowInserted = db.insert(REQUEST_TABLE, COMMENTS, values);
            db.close();

            if (rowInserted != -1) {

                Log.i("Row insertion", "New request row ID: " + rowInserted);
                Toast.makeText(contextDb, "New request has been created. ", Toast.LENGTH_SHORT).show();

                Log.i("clicks", "Navigating to home page");
                Intent i = new Intent(contextDb, HomePage.class);

                Bundle detailBundle = new Bundle();

                detailBundle.putString("EMAIL", saveDetails.getEmail());
                detailBundle.putString("NAME", saveDetails.getName());
                //  detailBundle.putString("PASSWORD",saveDetails.getPassword());

                i.putExtras(detailBundle);

                contextDb.startActivity(i);
            } else {
                Toast.makeText(contextDb, "Oops!!! Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(contextDb, "An error occurred while creating a new request. Please try again later", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * To sign up a new user
     *
     * @param details
     */
    public void insertContact(Details details) {

        try {

            Log.i("The details password: ", details.getPassword());
            Log.i("The details hint: ", details.getHint());

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            String query = "SELECT * FROM  " + USER_INFO_TABLE + " WHERE EMAIL_ID =  '" + details.getEmail() + "'";
            Log.i("query: ", query);
            Cursor cursor = db.rawQuery(query, null);
            int count = cursor.getCount();

            if (count == 0) {

                Log.i("values : ", details.getEmail());

                values.put(USER_NAME, details.getName());
                values.put(PASSWORD, details.getPassword());
                values.put(EMAIL_ID, details.getEmail());
                values.put(CURRENT_PROG, details.getCourse());
                values.put(INTR_TOPICS, details.getInterest());
                values.put(EXPERTISE, details.getExpertise());
                values.put(HINT, details.getHint());

                long rowId = db.insert(USER_INFO_TABLE, null, values);

                db.close();

                if (rowId != -1) {

                    Log.i("Sign up row insertion", "New sign up row ID: " + rowId);
                    Toast.makeText(contextDb, "You are successfully registered. ", Toast.LENGTH_SHORT).show();

                    Log.i("clicks", "Navigating to login page");
                    Intent i = new Intent(contextDb, MainActivity.class);
                    contextDb.startActivity(i);
                } else {
                    Toast.makeText(contextDb, "Sign up was not successful. Please try again", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(contextDb, "You are already a member. Please login to the app", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(contextDb, "An error occurred while signing in up for the app. Please try again", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * User login authentication
     *
     * @param email
     * @return
     */
    public ArrayList searchPass(String email) {

        String dbEmail, dbPwd, dbName;

        dbEmail = "None";
        dbPwd = "not found";
        dbName = "None";

        ArrayList detailsList = new ArrayList();

        detailsList.add(dbEmail);
        detailsList.add(dbPwd);
        detailsList.add(dbName);

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String query = "SELECT EMAIL_ID, PASSWORD, USER_NAME FROM  " + USER_INFO_TABLE + " WHERE EMAIL_ID = '" + email + "'";

            Log.i("Sign in query: ", query);

            Cursor cursor = db.rawQuery(query, null);
            int check = cursor.getCount();

            Log.i(" counter ", String.valueOf(check));

            if (cursor.moveToFirst()) {
                do {

                    detailsList.clear();
                    dbEmail = cursor.getString(0);

                    detailsList.add(cursor.getString(0));

                    if (dbEmail.equals(email)) {
                        dbPwd = cursor.getString(1);
                        dbName = cursor.getString(2);

                        detailsList.add(cursor.getString(1));
                        detailsList.add(cursor.getString(2));

                        break;
                    }
                }
                while (cursor.moveToNext());
            }
            if (check == 0) {
                Toast.makeText(contextDb, "You are not a member yet. Please Sign Up", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(contextDb, "An error occurred while logging into for the app. Please try again", Toast.LENGTH_SHORT).show();
        }
        return detailsList;
    }


    /**
     * Forgot password - fetch hint
     *
     * @param hint
     * @return
     */
    public String getAllTags(String hint, String email) {

        String str = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + USER_INFO_TABLE + " WHERE HINT = '" + hint + "' AND EMAIL_ID = '" + email + "'", null);

        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("PASSWORD"));
            } while (c.moveToNext());
        }
        return str;
    }

    /**
     * Get profile details
     *
     * @param name
     * @return
     */
    public ArrayList getProfileDetails(String name, String email) {
        ArrayList list = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor row = db.rawQuery("SELECT  CURRENT_PROG,  " + INTR_TOPICS + ",  EXPERTISE FROM " + USER_INFO_TABLE + " " +
                "WHERE USER_NAME  = '" + name + "' AND EMAIL_ID = '" + email + "'", null);

        if (row.moveToFirst()) {
            do {
                list.add(row.getString(0));
                list.add(row.getString(1));
                list.add(row.getString(2));
            } while (row.moveToNext());
        }
        return list;
    }

    /**
     * Saving the profile details
     * @param saveObj
     */
    public void change(Details saveObj) {

        SQLiteDatabase db = this.getReadableDatabase();

        Log.i("----Profile: NewPw -Old", saveObj.getPassword());
        Log.i("---Profile: Hint -Old", saveObj.getPassword());

        try {
            if(saveObj.getPassword().length() == 0 || saveObj.getPassword() == "") {

                Cursor c = db.rawQuery("SELECT " + PASSWORD + "  FROM  " + USER_INFO_TABLE + " WHERE EMAIL_ID  = '" + saveObj.getEmail() + "'", null);

                if (c.moveToFirst()) {
                    do {
                        saveObj.setPassword(c.getString(c.getColumnIndex("PASSWORD")));
                        Log.i("--------NEWPw------", saveObj.getPassword());
                    } while (c.moveToNext());
                }
            }
            Cursor hintCursor = db.rawQuery("SELECT " + HINT + "  FROM  " + USER_INFO_TABLE + " WHERE EMAIL_ID  = '" + saveObj.getEmail() + "'", null);

            if(hintCursor.moveToFirst()) {
                do {
                    saveObj.setHint(hintCursor.getString(hintCursor.getColumnIndex("HINT")));
                    Log.i("--------Hint------", saveObj.getPassword());

                } while (hintCursor.moveToNext());
            }
            ContentValues values = new ContentValues();
            values.put(USER_NAME, saveObj.getName());
            values.put(EMAIL_ID, saveObj.getEmail());
            values.put(PASSWORD, saveObj.getPassword());
            values.put(CURRENT_PROG, saveObj.getCourse());
            values.put(EXPERTISE, saveObj.getExpertise());
            values.put(INTR_TOPICS, saveObj.getInterest());
            values.put(HINT, saveObj.getHint());

            long rowInserted = db.update(USER_INFO_TABLE, values, " EMAIL_ID =  '"+ saveObj.getEmail() +"' ", null);

            db.close();

            if(rowInserted != -1){
                Log.i("clicks", "Success in updating Profile");

                Toast.makeText(contextDb,"Saved successfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(contextDb, HomePage.class);

                Bundle detailBundle = new Bundle();

                detailBundle.putString("EMAIL", saveObj.getEmail());
                detailBundle.putString("NAME", saveObj.getName());

                i.putExtras(detailBundle);

                contextDb.startActivity(i);
            }
            else {
                Log.i("clicks", "Error in updating the Profile");
                Toast.makeText(contextDb,"Profile update was not successful", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(contextDb, "Error saving your Profile settings. Please try again", Toast.LENGTH_SHORT);

        }
    }

    public List fetchRows(){

        SQLiteDatabase db = this.getReadableDatabase();
        List highList = null;

        Cursor row = db.rawQuery("SELECT  REQUEST_ID, TITLE, USER_NAME,  CATEGORY,  DESCRIPTION, " + CREATE_DATE + " FROM  " + REQUEST_TABLE + " WHERE  PRIORITY = 'High' ", null);

        if (row.moveToFirst()) {
            do {





            } while (row.moveToNext());
        }
        return highList;
    }
}