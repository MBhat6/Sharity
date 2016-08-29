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
import android.widget.Toast;

/**
 * Created by Madhura on 8/15/2016.
 */
public class SignUpDetailsPage  extends Activity implements OnTouchListener,
        OnItemClickListener,  OnClickListener{

    private EditText program;
    private EditText name;
    private EditText topic;
    private EditText expertise;
    private String[] courseList;
    private String strEmail;
    private String strPwd;
    private String strHint;
    private ListPopupWindow courseListPopup;
    DatabaseCreator dao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_compl_page);


        TextView signUpBtn = (TextView) findViewById(R.id.save_profile);
        signUpBtn.setOnClickListener(this);

        name = (EditText) findViewById(R.id.first_name);
        topic = (EditText) findViewById(R.id.SignUp_interest);
        expertise = (EditText) findViewById(R.id.SignUp_expertise);


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

        if(validateFields()){

            Bundle bundle = getIntent().getExtras();

            strEmail = bundle.getString("email");
            strPwd = bundle.getString("pwd");
            strHint = bundle.getString("hint");

            Log.i("clicks","Enrolling : signing up");

            Details details = new Details(name.getText().toString().trim(), strEmail, program.getText().toString().trim(), expertise.getText().toString().trim(),
                                                topic.getText().toString().trim(), strHint, strPwd);

            dao = new DatabaseCreator(this);
            dao.insertContact(details);
        }

    }

    /**
     * Field validation
     * @return
     */
    public boolean validateFields(){
        boolean validate = true;

        if(name.getText().toString().trim().equals("") || name.getText().toString().trim().length() == 0){

            Toast.makeText(this,"Name is required", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if(topic.getText().toString().trim().equals("") || topic.getText().toString().trim().length() == 0){

            Toast.makeText(this,"Please mention your Topic of Interest", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if(expertise.getText().toString().trim().equals("") || expertise.getText().toString().trim().length() == 0){

            Toast.makeText(this,"Please mention your Expertise", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if(program.getText().toString().trim().equals("") || program.getText().toString().trim().length() == 0){

            Toast.makeText(this,"Please select your Current Program", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        return validate;
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
