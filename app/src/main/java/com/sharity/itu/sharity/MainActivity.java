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


/////////////////////////This is drop-down loading for Courses/////////////////
//        program = (EditText) findViewById(R.id.program);
//        program.setOnTouchListener(this);

//        courseList = new String[] { "Master of Business Administration", "Master of Science in Computer Engineering",
//                              "Master of Science in Computer Science", "Master of Science in Digital Arts",
//                               "Master of Science in Electrical Engineering", "Master of Science in Engineering Management",
//                                "Master of Science in Software Engineering"};
//        courseListPopup = new ListPopupWindow(this);
//        courseListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_text_custom, courseList));
//        courseListPopup.setAnchorView(program);
//        courseListPopup.setModal(true);
//        courseListPopup.setOnItemClickListener(this)
//////////////END - courses///////////////////////////////////////////////

/////////////////////Drop down population for Category////////////////////////////
//        category = (EditText) findViewById(R.id.categoryId);
//        category.setOnTouchListener(this);
//
//        categoryList = new String[] { "Books", "Stationary", "Homework help"};
//
//        catListPopup = new ListPopupWindow(this);
//        catListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_text_custom, categoryList));
//        catListPopup.setAnchorView(category);
//        catListPopup.setModal(true);
//        catListPopup.setOnItemClickListener(this);
//
//        priority = (EditText) findViewById(R.id.priorityId);
//        priority.setOnTouchListener(this);
//
//        priorityList = new String[] { "High", "Medium", "Low"};
//
//        priorityListPopup = new ListPopupWindow(this);
//        priorityListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_custom_popup, priorityList));
//        priorityListPopup.setAnchorView(priority);
//        priorityListPopup.setModal(true);
//        priorityListPopup.setOnItemClickListener(this);
//
//    }
//
//
//
////    @Override
////   public void onItemClick(AdapterView<?> parent, View view, int position,
////                           long id) {
////        String courseItem = courseList[position];
////        program.setText(courseItem);
////        courseListPopup.dismiss();
////        switch ( view.getId()) {
////            case R.id.list_popUp_1:
////                String CatItem = categoryList[position];
////                category.setText(CatItem);
////                catListPopup.dismiss();
////                break;
////            case R.id.list_popUp_2:
////                String prItem = priorityList[position];
////                priority.setText(prItem);
////                priorityListPopup.dismiss();
////                break;
////        }
////    }
////
////
////    public boolean onTouch(View v, MotionEvent event) {
////        final int DRAWABLE_RIGHT = 2;
////
//////        if (event.getAction() == MotionEvent.ACTION_UP) {
//////            if (event.getX() >= (v.getWidth() - ((EditText) v)
//////                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//////                courseListPopup.show();
//////                return true;
//////            }
//////        }
//////////////////////////Course list///////////////////////////////////////
////
////        if (event.getAction() == MotionEvent.ACTION_UP) {
////            if (event.getX() >= (v.getWidth() - ((EditText) v)
////                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
////
////                if(v.getId() == category.getId()) {
////                    catListPopup.show();
////                }
////                else if(v.getId() == priority.getId()) {
////                    priorityListPopup.show();
////                }
////                return true;
////            }
////        }
////       return false;
////
    }

    @Override
    public void onClick(View v) {
        Log.i("clicks","You Clicked sign up button");
        Intent i = new Intent(MainActivity.this, SignUp.class);
        startActivity(i);
    }
}