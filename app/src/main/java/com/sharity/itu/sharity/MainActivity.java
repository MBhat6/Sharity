package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener
        /*OnTouchListener, onItemClickListener*/{

    DatabaseCreator myDb;
    private EditText program;
    private EditText category;
    private EditText priority;
    private ListPopupWindow courseListPopup;
    private ListPopupWindow catListPopup;
    private ListPopupWindow priorityListPopup;
    private String[] courseList;
    private String[] categoryList;
    private String[] priorityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        myDb = new DatabaseCreator(this);



        TextView signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        signUpBtn.setPaintFlags(signUpBtn.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        signUpBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.i("clicks","You Clicked sign up button");
        Intent i = new Intent(MainActivity.this, SignUp.class);
        startActivity(i);
    }
}