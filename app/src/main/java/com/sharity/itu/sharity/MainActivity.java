/////////////////////////////////////////////////////////////////////////
//                                                                     //
//                                                                     //
//      class name: MainActivity.java                                  //
//                                                                     //
/////////////////////////////////////////////////////////////////////////
//                                                                     //
//      Revision log                                                   //
//     --------------                                                  //
//                                                                     //
//      Created by                      Madhura Bhat                   //
//      Modified for login in function  Priyanka                       //
//      Modified for code review        Madhura Bhat                   //
//      Modified for code review        Priyanka                       //
//      Bug fix                         Priyanka                       //
//      Bug fix                         Priyanka                       //
//      Bug fix                         Madhura                        //
/////////////////////////////////////////////////////////////////////////


package com.sharity.itu.sharity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created ny Madhura on 7/16/2016.
 */
public class MainActivity extends Activity implements OnClickListener
        /*OnTouchListener, onItemClickListener*/{

    private EditText email;
    private EditText password;
    private TextView loginBtn;
    private TextView signUpBtn;
    private TextView forgotBtn;
    private String emailStr;
    private String pwd;

    DatabaseCreator helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        helper = new DatabaseCreator(this);

        helper = new DatabaseCreator(this);

        email = (EditText) findViewById(R.id.email_id) ;

        password = (EditText) findViewById(R.id.pwd_id) ;

        loginBtn = (TextView) findViewById(R.id.login);
        loginBtn.setOnClickListener(this);

        signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        signUpBtn.setPaintFlags(signUpBtn.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        signUpBtn.setOnClickListener(this);

        forgotBtn =(TextView)findViewById(R.id.forgot_pwd);
        forgotBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

       if(v.getId() == loginBtn.getId()){

           if(validateFields()){
               pwd = password.getText().toString();
               emailStr = email.getText().toString().trim();

               Log.i("Email  ", emailStr);
               Log.i("pwd  " , pwd);

               helper = new DatabaseCreator(this);

               ArrayList details = helper.searchPass(emailStr);

               String dbEmail = details.get(0).toString();
               String dbPwd = details.get(1).toString();
               String dbName = details.get(2).toString();

               Log.i("Db email ", dbEmail);
               Log.i("Db password: ", dbPwd);
               Log.i("Db name: ", dbName);

               if(!dbEmail.equals("None")){
                   if (pwd.equals(dbPwd)) {
                       Intent i = new Intent(MainActivity.this, HomePage.class);

                       Bundle detailBundle = new Bundle();

                       detailBundle.putString("EMAIL", dbEmail);
                       detailBundle.putString("NAME", dbName);

                       i.putExtras(detailBundle);
                       startActivity(i);
                   } else {
                       Toast temp = Toast.makeText(MainActivity.this, "Invalid Password. Try again", Toast.LENGTH_SHORT);
                       temp.show();
                   }
               }
           }
       }
       else if(v.getId() == signUpBtn.getId()) {

            Log.i("clicks", "You Clicked sign up button");
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);
        }
        else if(v.getId() ==  forgotBtn.getId()){

           helper = new DatabaseCreator(this);

           final Dialog dialog = new Dialog(MainActivity.this);
           dialog.getWindow();
           dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
           dialog.setContentView(R.layout.forget_search);
           dialog.show();

           final  EditText security =(EditText)dialog.findViewById(R.id.securityhint_edt);
           final  TextView getpass =(TextView)dialog.findViewById(R.id.get_pwd);
           final  TextView email = (TextView)dialog.findViewById(R.id.email_hint) ;

           Button ok =(Button)dialog.findViewById(R.id.replyBtn);
           Button cancel =(Button)dialog.findViewById(R.id.cancel_btn);

           ok.setOnClickListener(new View.OnClickListener() {

               public void onClick(View v) {

                   String userInput = security.getText().toString();
                   String hintEmail = email.getText().toString();

                   if(userInput.equals("") || userInput.length() == 0)
                   {
                       Toast.makeText(getApplicationContext(), "Please enter your Security Hint", Toast.LENGTH_SHORT).show();
                   }
                   else if(hintEmail.equals("") || hintEmail.length() == 0){

                       Toast.makeText(getApplicationContext(), "Please enter your ITU Email ID", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       String storedPassword = helper.getAllTags(userInput, hintEmail);
                       if(storedPassword == null) {
                           Toast.makeText(getApplicationContext(), "Please enter the correct details", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Log.d("GET PASSWORD",storedPassword);
                           getpass.setText(storedPassword);
                       }
                   }
               }
           });
           cancel.setOnClickListener(new OnClickListener() {

               @Override
               public void onClick(View v) {
                   dialog.dismiss();
               }
           });

           dialog.show();
       }
    }

    /**
     * Field validations
     * @return
     */
    public boolean validateFields(){

        boolean validate = true;

        String validemail= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";

        Matcher m = Pattern.compile(validemail).matcher(email.getText().toString());

        if(email.getText().toString().trim().equals("") || email.getText().toString().trim().length() == 0){

            Toast.makeText(this,"Please enter your ITU Email ID", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if(!m.find()){
            Toast.makeText(this,"Your Email ID is invalid", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if(password.getText().toString().equals("") || password.getText().toString().length() == 0){

            Toast.makeText(this,"Please enter your Password", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        else if (email.getText().toString() != null || email.getText().toString() != "") {

            String emailValidate[] = email.getText().toString().split("@");

            String trailerStr = emailValidate[1].toString();
            String compareStr = "students.itu.edu";

            Log.i(" Email trailer : ", trailerStr);

            if (!trailerStr.trim().contentEquals(compareStr.trim())) {

                Toast.makeText(this, "Please enter your ITU student email ID", Toast.LENGTH_SHORT).show();
                validate = false;
            }
        }
        return validate;
    }
}