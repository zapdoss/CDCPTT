package com.example.skirmish.test;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Exploration extends AppCompatActivity {
    private String usr;
    private String patient;
    private LocalDietSerialized localDiet;
    private Health_nav_db hn=new Health_nav_db(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploration);
        Toolbar toolb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView o = (TextView)findViewById(R.id.tool_title);

        TextView titlebar = (TextView) findViewById(R.id.tool_hn);
        this.usr = getIntent().getStringExtra("usr");
        this.localDiet = (LocalDietSerialized) getIntent().getSerializableExtra("localdiet");
        this.patient = getIntent().getStringExtra("patient");
        //this.localDiet=(LocalDietSerialized)getIntent().getSerializableExtra("localdiet");
        //TextView t = (TextView)findViewById(R.id.textView4);
        final String a = hn.getName(usr);
        titlebar.setText("HN: "+a);
        o.setText("Patient ID: "+patient);

        Button yesButton=(Button) findViewById(R.id.button3);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Exploration.this, ExplorationContinued.class);
                i.putExtra("usr", usr);
                i.putExtra("patient", patient);
                i.putExtra("localdiet",localDiet);
                startActivity(i);
                //startActivity(new Intent(Exploration.this, ExplorationContinued.class));
            }
        });


    }
}
