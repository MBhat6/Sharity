package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * Created by Madhura on 8/15/2016.
 */
public class SignUpDetailsPage  extends Activity implements OnTouchListener,
        OnItemClickListener,  OnClickListener{

    private EditText program;
    private String[] courseList;
    private ListPopupWindow courseListPopup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_compl_page);


        TextView signUpBtn = (TextView) findViewById(R.id.save_profile);
        signUpBtn.setOnClickListener(this);


        program = (EditText) findViewById(R.id.signUp_course);
        program.setOnTouchListener(this);

        courseList = new String[] { "Master of Business Administration", "Master of Science in Computer Engineering",
                              "Master of Science in Computer Science", "Master of Science in Digital Arts",
                               "Master of Science in Electrical Engineering", "Master of Science in Engineering Management",
                                "Master of Science in Software Engineering"};
        courseListPopup = new ListPopupWindow(this);
        courseListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_text_custom, courseList));
        courseListPopup.setAnchorView(program);
        courseListPopup.setModal(true);
        courseListPopup.setOnItemClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Log.i("clicks","You are enrolled : signing up");
        Intent i = new Intent(SignUpDetailsPage.this, HomePage.class);
        startActivity(i);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int DRAWABLE_RIGHT = 2;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() >= (v.getWidth() - ((EditText) v)
                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                courseListPopup.show();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String courseItem = courseList[position];
        program.setText(courseItem);
        courseListPopup.dismiss();

    }

}
