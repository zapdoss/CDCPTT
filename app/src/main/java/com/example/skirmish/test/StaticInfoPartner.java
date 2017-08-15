package com.example.skirmish.test;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StaticInfoPartner extends AppCompatActivity {

    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pt = new Patient_db(this);
    private String usr;
    private String patient;
    int selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_partner_info);

        Toolbar toolb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView o = (TextView)findViewById(R.id.tool_title);

        TextView titlebar = (TextView) findViewById(R.id.tool_hn);
        this.usr = getIntent().getStringExtra("usr");
        this.patient = getIntent().getStringExtra("patient");
        //TextView t = (TextView)findViewById(R.id.textView4);
        final String a = hn.getName(usr);
        titlebar.setText("HN: "+a);
        o.setText("Patient ID: "+patient);
        this.selected=Integer.parseInt(getIntent().getStringExtra("category"));
    }

    public void next(View v){
        if(v.getId()==R.id.b_next2){
            Intent i = new Intent(this,Food_change.class);
            i.putExtra("category",Integer.toString(this.selected));
            i.putExtra("usr",this.usr);
            i.putExtra("patient",this.patient);
            startActivity(i);
        }
    }
}
