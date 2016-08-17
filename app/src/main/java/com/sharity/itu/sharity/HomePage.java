package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

/**
 * Created by Madhura on 8/16/2016.
 */
public class HomePage extends Activity implements OnClickListener {

    TextView name;
    Button nBtn;
    Button ProfBtn;
    Button ReqBtn;
    Button HistBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        name = (TextView) findViewById(R.id.userName);
        name.setText("Madhura Bhat"); //Temporary hard coded value

        nBtn = (Button) findViewById(R.id.newsBtn);
        nBtn.setOnClickListener(this);

        ProfBtn = (Button) findViewById(R.id.profileBtn);
        ProfBtn.setOnClickListener(this);

        ReqBtn = (Button) findViewById(R.id.requestBtn);
        ReqBtn.setOnClickListener(this);

        HistBtn = (Button) findViewById(R.id.historyBtn);
        HistBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        Log.i("The value of view ID: ", String.valueOf(v.getId()));

        if( v.getId() == nBtn.getId() ) {
            Log.i("clicks","You clicked news button");
            Intent i = new Intent(HomePage.this, NewsPage.class);
            startActivity(i);
        }

        else if( v.getId() == ProfBtn.getId() ) {
            Log.i("clicks","You clicked profile button");
            Intent i = new Intent(HomePage.this, ProfilePage.class);
            startActivity(i);
        }

        else if( v.getId() == ReqBtn.getId() ) {
            Log.i("clicks","You clicked request button");
            Intent i = new Intent(HomePage.this, NewRequestPage.class);
            startActivity(i);
        }

        else if( v.getId() == HistBtn.getId() ) {
            Log.i("clicks","You clicked my request button");
            Intent i = new Intent(HomePage.this, MyHistoryPage.class);
            startActivity(i);
        }

    }
}
