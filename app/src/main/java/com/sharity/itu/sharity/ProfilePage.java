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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Madhura on 8/16/2016.
 */
public class ProfilePage extends  Activity implements View.OnTouchListener,
        AdapterView.OnItemClickListener, OnClickListener{

    private EditText userName;
    private EditText interest;
    private EditText expertise;
    private EditText program;
    private EditText newPwdEdit;
    private EditText cnfrmEdit;

    private ListPopupWindow courseListPopup;
    DatabaseCreator dao;

    private String[] courseList;
    private String newPwd;
    private String cnfrmPwd;
    private String userNameEdit;

    private Button submitBtn;

    private String dbEmail;
    private String dbName;
    private String progEdit;
    private String expertiseEdit;
    private String interestEdit;
    private String hint;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);

        dao = new DatabaseCreator(this);

        userName = (EditText) findViewById(R.id.name);

        interest = (EditText) findViewById(R.id.interest);

        expertise = (EditText) findViewById(R.id.expertise);


        program = (EditText) findViewById(R.id.course);
        program.setOnTouchListener(this);

        newPwdEdit = (EditText)findViewById(R.id.re_new_pwd);

        cnfrmEdit  = (EditText)findViewById(R.id.new_pwd);

        submitBtn =  (Button)findViewById(R.id.save_profile);
        submitBtn.setOnClickListener(this);


        courseList = new String[] { "Master of Business Administration", "Master of Science in Computer Engineering",
                "Master of Science in Computer Science", "Master of Science in Digital Arts",
                "Master of Science in Electrical Engineering", "Master of Science in Engineering Management",
                "Master of Science in Software Engineering"};
        courseListPopup = new ListPopupWindow(this);
        courseListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_text_custom, courseList));
        courseListPopup.setAnchorView(program);
        courseListPopup.setModal(true);
        courseListPopup.setOnItemClickListener(this);


        Bundle bundle = getIntent().getExtras();
        dbName = bundle.getString("NAME");
        dbEmail = bundle.getString("EMAIL");

        ArrayList details = dao.getProfileDetails(dbName, dbEmail);

        if(details != null) {
            userName.setText(dbName);
            program.setText(details.get(0).toString());
            interest.setText(details.get(1).toString());
            expertise.setText(details.get(2).toString());
        }
    }

    @Override
    public void onClick(View v) {
        hint = null;
        Log.i("validateFields: ", String.valueOf(saveButtton()));
        if(saveButtton()){
            Details saveObj = new Details(userNameEdit, dbEmail, progEdit, expertiseEdit, interestEdit, hint, newPwd);
            dao.change(saveObj);
        }
    }
    /**
     * Field validation
     * @return boolean value
     */
    public boolean saveButtton(){
        boolean validate = true;

        userNameEdit = userName.getText().toString().trim();
        progEdit = program.getText().toString().trim();
        expertiseEdit = expertise.getText().toString().trim();
        interestEdit = interest.getText().toString().trim();
        newPwd = newPwdEdit.getText().toString();
        cnfrmPwd=cnfrmEdit.getText().toString();

        if(userName.getText().toString().trim() == null || userName.getText().toString().trim().length() == 0){
            Toast.makeText(this,"Username is required", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        else if(program.getText().toString().trim()== null || program.getText().toString().trim().length() == 0 ){
            Toast.makeText(this,"Please select the program", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        else if((expertise.getText().toString().trim() == null)  || (expertise.getText().toString().trim().length() == 0 )){
            Toast.makeText(this,"Please enter expertise", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        else if(interest.getText().toString().trim() == null || interest.getText().toString().trim().length() == 0){
            Toast.makeText(this,"Please enter your interest", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        else if((newPwd == null || newPwd == "") && (!(cnfrmPwd == null || cnfrmPwd == ""))){
            Toast.makeText(this,"Please enter a password", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if((cnfrmPwd == null || cnfrmPwd == "") && (!(newPwd == null || newPwd == ""))){
            Toast.makeText(this,"Please confirm the password", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if(!newPwd.equalsIgnoreCase(cnfrmPwd)){
            Toast.makeText(this," Passwords does not match", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        return validate;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String courseItem = courseList[position];
        program.setText(courseItem);
        courseListPopup.dismiss();
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
}