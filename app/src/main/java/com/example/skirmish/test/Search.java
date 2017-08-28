package com.example.skirmish.test;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.GridView;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pt = new Patient_db(this);
    private CustomCursorAdapter customAdapter;
    String usr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Toolbar toolb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView o = (TextView)findViewById(R.id.tool_title);
        o.setText("Patients");

        TextView titlebar = (TextView) findViewById(R.id.tool_hn);
        this.usr = getIntent().getStringExtra("usr");
        //TextView t = (TextView)findViewById(R.id.textView4);
        final String a = hn.getName(usr);
        titlebar.setText("HN: "+a);

        final GridView grid = (GridView)findViewById(R.id.grid1);
        //Cursor c = hn.getAllData();

        customAdapter = new CustomCursorAdapter(Search.this, pt.getAllData(usr));
        grid.setAdapter(customAdapter);

       /* new Handler().post(new Runnable() {
            @Override
            public void run() {
                customAdapter = new CustomCursorAdapter(Search.this, hn.getAllData());
                grid.setAdapter(customAdapter);
            }
        });*/

        EditText search = (EditText)findViewById(R.id.e_search);

        customAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return pt.getCursor(constraint.toString(),usr);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if(s.toString().equals("a")) Log.d("asd", "asd");
                customAdapter.getFilter().filter(s.toString());
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        grid.setOnItemClickListener(myOnItemClickListener);



    }

    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Cursor c = (Cursor) parent.getItemAtPosition(position);
            Intent i = new Intent(Search.this,Presentdiet.class);
            i.putExtra("usr",usr);
            i.putExtra("patient",c.getString(0));
            startActivity(i);

        }};


}
