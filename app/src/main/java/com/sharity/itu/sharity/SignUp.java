package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Madhura on 8/15/2016.
 */
public class SignUp extends Activity implements View.OnClickListener   {

    EditText email;
    EditText pass1 ;
    EditText pass2 ;
    EditText hint;

    String emailstr ;
    String pass1str ;
    String pass2str ;
    String hintstr ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);


        TextView signUpBtn = (TextView) findViewById(R.id.create_acc);
        signUpBtn.setOnClickListener(this);


        email =(EditText)findViewById(R.id.signUp_email);
        pass1 =(EditText)findViewById(R.id.SignUp_new_pwd);
        pass2 =(EditText)findViewById(R.id.signUp_re_new_pwd);
        hint =(EditText)findViewById(R.id.hint);
    }

    @Override
    public void onClick(View v) {

        if(validateFields()){

            Intent i = new Intent(SignUp.this, SignUpDetailsPage.class);

            Bundle bundle = new Bundle();

            bundle.putString("email", emailstr.trim());
            bundle.putString("pwd", pass1str);
            bundle.putString("hint", hintstr);

            i.putExtras(bundle);

            startActivity(i);
        }
    }

    /**
     * Field validation
     * @return validate
     */
    public boolean validateFields(){

        boolean validate = true;

        emailstr = email.getText().toString();
        pass1str = pass1.getText().toString();
        pass2str = pass2.getText().toString();
        hintstr = hint.getText().toString();

        String validemail= "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                "\\@" +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" +

                "\\." +

                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                ")+";

        Matcher m = Pattern.compile(validemail).matcher(emailstr);

        if (emailstr.trim().equals("") || emailstr.trim().length() == 0) {

            Toast.makeText(this,"Please enter your ITU email ID", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        else if(pass1str.equals("") || pass1str.length() == 0){

            Toast.makeText(this,"Please enter a Password", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        else if(pass2str.equals("") || pass2str.length() == 0){

            Toast.makeText(this,"Please re-enter the Password", Toast.LENGTH_SHORT).show();
            validate = false;

        }

        else if(hintstr.equals("") || hintstr.length() == 0){

            Toast.makeText(this,"Please enter a Security Hint", Toast.LENGTH_SHORT).show();
            validate = false;

        }

        else if(!m.find()){
            Toast.makeText(this,"Your Email ID is invalid", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        else if(!pass1str.equals(pass2str)) {

            Toast.makeText(this, "Your passwords do not match" ,Toast.LENGTH_SHORT).show();
            validate = false;

        }

        else if (emailstr != null || emailstr != "") {

            String emailValidate[] = emailstr.split("@");

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


