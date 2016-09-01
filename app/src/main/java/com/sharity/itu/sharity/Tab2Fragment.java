/////////////////////////////////////////////////////////////////////////
//                                                                     //
//                                                                     //
//       class name: Tab2Fragment.java                                 //
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

import java.util.ArrayList;

/**
 * Created by Madhura on 8/18/2016.
 */
public class Tab2Fragment extends Fragment{

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    ArrayList dataList;

    DatabaseCreator dao;
    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2_design, container, false);

        super.onCreate(savedInstanceState);

        if(getArguments()!=null){
            name = getArguments().getString("Name");
            Log.i("------------Name:Tab2", name);
        }

        expandableListView = (ExpandableListView)view.findViewById(R.id.expandableListView2);

        dataList = new ArrayList();
        dao = new DatabaseCreator(getContext());
        dataList = dao.fetchRowsForMediumPriority();

        expandableListAdapter = new CustomExpandableListTab2(getActivity(), dataList, name);
        expandableListView.setAdapter(expandableListAdapter);

        return view;
    }
}
