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
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Madhura on 8/16/2016.
 */
public class NewRequestPage extends  Activity implements View.OnTouchListener,
        AdapterView.OnItemClickListener, OnClickListener{

    TextView name;
    TextView homeButton;
    private EditText category;
    private EditText priority;
    private TextView createBtn;
    private EditText title;
    private EditText desc;
    private  EditText header;
    private ListPopupWindow catListPopup;
    private ListPopupWindow priorityListPopup;
    private String[] categoryList;
    private String[] priorityList;
    Details setReqDetails;
    DatabaseCreator myDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_page);

        header = (EditText) findViewById(R.id.reqPageTxt);
        header.setOnTouchListener(this);
        /**
         * Loading Category dropdown
         */
        category = (EditText) findViewById(R.id.categoryId);
        category.setOnTouchListener(this);

        categoryList = new String[] { "Books", "Stationary", "Homework help"};

        catListPopup = new ListPopupWindow(this);
        catListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_text_custom, categoryList));
        catListPopup.setAnchorView(category);
        catListPopup.setModal(true);
        catListPopup.setOnItemClickListener(this);


        /**
         * Loading Priority dropdown
         */

        priority = (EditText) findViewById(R.id.priorityId);
        priority.setOnTouchListener(this);

        priorityList = new String[] { "High", "Medium", "Low"};

        priorityListPopup = new ListPopupWindow(this);
        priorityListPopup.setAdapter(new ArrayAdapter<String>(this,R.layout.list_custom_popup, priorityList));
        priorityListPopup.setAnchorView(priority);
        priorityListPopup.setModal(true);
        priorityListPopup.setOnItemClickListener(this);


        createBtn = (TextView) findViewById(R.id.Create);
        createBtn.setOnClickListener(this);

        title = (EditText) findViewById(R.id.title);

        desc = (EditText) findViewById(R.id.desc);


    }

    @Override
    public void onClick(View v) {
        Log.i("validateFields: ", String.valueOf(validateFields()));
        if(validateFields()){
            setReqDetails = new Details("Madhura", "bhatmadhu493@students.itu.edu", category.getText().toString(),
                    priority.getText().toString(), title.getText().toString(), desc.getText().toString());
            myDb = new DatabaseCreator(this);
            myDb.saveRequest(setReqDetails);
        }


    }

    public boolean validateFields(){
        boolean validate = true;

        if(category.getText().toString().equals("") || category.getText().toString() == null){
            Log.i("category.getText()",category.getText().toString());

            Toast.makeText(this,"Please select a category", Toast.LENGTH_LONG).show();
            validate = false;
        }
        else if(priority.getText().toString().equals("") || priority.getText().toString() == null){

            Log.i("priority.getText()",priority.getText().toString());

            Toast.makeText(this,"Please set a priority for the request", Toast.LENGTH_LONG).show();
            validate = false;
        }
        else if(title.getText().toString().equals("") || title.getText().toString() == null){
            Toast.makeText(this,"Title is required", Toast.LENGTH_LONG).show();
            validate = false;
        }
        else if(desc.getText().toString().equals("") || desc.getText().toString() == null){
            Toast.makeText(this,"Please write a description of the request", Toast.LENGTH_LONG).show();
            validate = false;
        }

        return validate;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch ( view.getId()) {
            case R.id.list_popUp_1:
                String CatItem = categoryList[position];
                category.setText(CatItem);
                catListPopup.dismiss();
                break;
            case R.id.list_popUp_2:
                String prItem = priorityList[position];
                priority.setText(prItem);
                priorityListPopup.dismiss();
                break;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_LEFT = 0;

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getX() >= (v.getWidth() - ((EditText) v)
                    .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {

                if(v.getId() == category.getId()) {
                    catListPopup.show();
                }
                else if(v.getId() == priority.getId()) {
                    priorityListPopup.show();
                }
                return true;
            }
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() <= header.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width()) {
                Log.i("I am here", " inside action");
                if (v.getId() == header.getId()) {
                    Intent i = new Intent(this, HomePage.class);
                    startActivity(i);
                }
            }
            return true;
        }
        return false;
    }
}
