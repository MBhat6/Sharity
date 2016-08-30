package com.sharity.itu.sharity;

/**
 * Created by Madhura on 8/28/2016.
 */
import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter implements OnClickListener{

    private Context context;
    private ArrayList expandableList;
    TextView comment;
    String userName;
    //String createName;
    String reqId;
    DatabaseCreator dao;
    private ArrayAdapter<String> adapter;


    public CustomExpandableListAdapter(Context context, ArrayList expandableList, String name) {
        this.context = context;
        this.expandableList = expandableList;
        this.userName = name;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        String childItems = expandableList.get(listPosition).toString();
        Log.i("Child items: " , childItems);

        String childStr[] = childItems.split(",,");
        Log.i("Title : " , childStr[1]);

        return childStr[1].toString();
    }

    public Object getChildCustom(int listPosition) {

        String childItems = expandableList.get(listPosition).toString();
        Log.i("Child items: " , childItems);

        String childStr[] = childItems.split(",,");
        Log.i("Title : " , childStr[1]);

        return childStr[1].toString();
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChildCustom(listPosition);

        Log.i("The string values-----", expandedListText);
        String test[] = expandedListText.split(",");

        reqId = test[0].toString();

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(test[1].toString());

        TextView dateView = (TextView) convertView
                .findViewById(R.id.dateItem);
        dateView.setText(test[4].toString());

        TextView categoryView = (TextView) convertView
                .findViewById(R.id.categoryItem);
        categoryView.setText(test[2].toString());

        TextView expandedListDescView = (TextView) convertView
                .findViewById(R.id.expandedListDesc);
        expandedListDescView.setText(test[3].toString());

        TextView commentBtn = (TextView) convertView.findViewById(R.id.button);
        commentBtn.setOnClickListener(this);

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {

        Log.i("Group items size " , String.valueOf(expandableList.size()));

        String title = expandableList.get(listPosition).toString();
        Log.i("Group items: " , title);

        String titleStr[] = title.split(",,");
        Log.i("Title : " , titleStr[0].toString());

        return titleStr[0].toString();
    }

    @Override
    public int getGroupCount() {
        return this.expandableList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        //listTitleTextView.setTextColor(Color.parseColor("#ff4444"));
        listTitleTextView.setAllCaps(true);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {

        dao = new DatabaseCreator(context);
        final Dialog dialog = new Dialog(context);
        dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.comment_dialog);
        dialog.show();

        Button reply =(Button)dialog.findViewById(R.id.replyBtn);
        Button cancel =(Button)dialog.findViewById(R.id.replyCancel_btn);

        comment = (TextView)dialog.findViewById(R.id.reply_box) ;

        final ListView list = (ListView)dialog.findViewById(R.id.commentList);
        ArrayList arrayList = new ArrayList();

        arrayList = dao.getComment(reqId);

        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);

        reply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Req Id : ------", reqId);

                if(comment.getText().toString().trim().length() != 0){
                    Log.i("user name: " , userName);

                    long val = dao.insertComment(reqId, userName, comment.getText().toString().trim());
                    if (val != -1) {

                        comment.setText("");
                        ArrayList items = dao.getComment(reqId);

                        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items);
                        list.setAdapter(adapter);
                        Toast.makeText(context,"Thank you for your comments", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context,"Cannot comment this moment. Try again later", Toast.LENGTH_SHORT);
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