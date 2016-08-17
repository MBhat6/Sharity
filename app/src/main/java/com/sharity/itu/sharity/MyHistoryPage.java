package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Created by Madhura on 8/16/2016.
 */
public class MyHistoryPage extends  Activity implements OnClickListener{

    TextView name;
    TextView homeButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);

        name = (TextView) findViewById(R.id.userName);
        name.setText("Priyanka Gopalakrishna"); //Temporary hard coded value

        homeButton = (TextView) findViewById(R.id.homeBtn);
        homeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("clicks","You clicked home button in My Request");
        Intent i = new Intent(MyHistoryPage.this, HomePage.class);
        startActivity(i);

    }
}
