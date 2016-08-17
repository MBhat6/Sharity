package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;


/**
 * Created by Madhura on 8/16/2016.
 */
public class NewRequestPage extends  Activity implements View.OnTouchListener,
        AdapterView.OnItemClickListener, OnClickListener{

    TextView name;
    TextView homeButton;
    private EditText category;
    private EditText priority;
    private ListPopupWindow catListPopup;
    private ListPopupWindow priorityListPopup;
    private String[] categoryList;
    private String[] priorityList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);

        name = (TextView) findViewById(R.id.userName);
        name.setText("Madhura Bhat"); //Temporary hard coded value

        homeButton = (TextView) findViewById(R.id.homeBtn);
        homeButton.setOnClickListener(this);

        /**
         * Loading Category dropdown
         */
        category = (EditText) findViewById(R.id.categoryId);
        category.setOnTouchListener(this);

        categoryList = new String[] { "Books", "Stationary", "Homework help"};

        catListPopup = new ListPopupWindow(this);
        catListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_text_custom, categoryList));
        catListPopup.setAnchorView(category);
        catListPopup.setModal(true);
        catListPopup.setOnItemClickListener(this);


        /**
         * Loading Priority dropdown
         */

        priority = (EditText) findViewById(R.id.priorityId);
        priority.setOnTouchListener(this);

        priorityList = new String[] { "High", "Medium", "Low"};

        priorityListPopup = new ListPopupWindow(this);
        priorityListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_custom_popup, priorityList));
        priorityListPopup.setAnchorView(priority);
        priorityListPopup.setModal(true);
        priorityListPopup.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Log.i("clicks","You clicked home button in New Request");
        Intent i = new Intent(NewRequestPage.this, HomePage.class);
        startActivity(i);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch ( view.getId()) {
            case R.id.list_popUp_1:
                String CatItem = categoryList[position];
                category.setText(CatItem);
                catListPopup.dismiss();
                break;
            case R.id.list_popUp_2:
                String prItem = priorityList[position];
                priority.setText(prItem);
                priorityListPopup.dismiss();
                break;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() >= (v.getWidth() - ((EditText) v)
                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                if(v.getId() == category.getId()) {
                    catListPopup.show();
                }
                else if(v.getId() == priority.getId()) {
                    priorityListPopup.show();
                }
                return true;
            }
        }
        return false;
    }
}