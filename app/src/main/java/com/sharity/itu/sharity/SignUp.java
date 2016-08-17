package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Madhura on 8/15/2016.
 */
public class SignUp extends Activity implements View.OnClickListener   {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);


        TextView signUpBtn = (TextView) findViewById(R.id.create_acc);
        signUpBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Log.i("clicks","You are enrolled : signning up");
        Intent i = new Intent(SignUp.this, SignUpDetailsPage.class);
        startActivity(i);

    }
}


