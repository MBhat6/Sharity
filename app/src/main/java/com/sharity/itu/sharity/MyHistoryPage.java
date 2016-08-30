package com.sharity.itu.sharity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Madhura on 8/16/2016.
 */
public class MyHistoryPage extends  Activity {

    TextView name;
    String userName;
    String email;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    ArrayList dataList;

    DatabaseCreator dao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_history_page);

        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("NAME");
        email = bundle.getString("EMAIL");

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListHistory);

        dataList = new ArrayList();
        dao = new DatabaseCreator(this);
        dataList = dao.fetchRowsForHistory(userName, email);

        expandableListAdapter = new CustomExpandableListHistory(this, dataList, userName);
        expandableListView.setAdapter(expandableListAdapter);

    }

}
