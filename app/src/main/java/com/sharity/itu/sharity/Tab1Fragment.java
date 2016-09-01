/////////////////////////////////////////////////////////////////////////
//                                                                     //
//                                                                     //
//       class name: Tab1Fragment.java                                 //
//                                                                     //
/////////////////////////////////////////////////////////////////////////
//                                                                     //
//      Revision log                                                   //
//     --------------                                                  //
//                                                                     //
//      Created by                              Madhura Bhat           //
//      Modified for tab function               Madhura                //
//      Modified for code review                Priyanka               //
/////////////////////////////////////////////////////////////////////////

package com.sharity.itu.sharity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Madhura on 8/18/2016.
 */

public class Tab1Fragment extends Fragment {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    ArrayList dataList;

    DatabaseCreator dao;
    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_design, container, false);

        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            name = getArguments().getString("Name");
            Log.i("------------Name:Tab1", name);
        }

        expandableListView = (ExpandableListView)view.findViewById(R.id.expandableListView1);

        dataList = new ArrayList();
        dao = new DatabaseCreator(getContext());
        dataList = dao.fetchRowsForHighPriority();

        expandableListAdapter = new CustomExpandableListAdapter(getActivity(), dataList, name);
        expandableListView.setAdapter(expandableListAdapter);

        return view;

    }
}