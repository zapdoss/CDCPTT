package com.example.skirmish.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExplorationContinued extends AppCompatActivity {
    private String usr;
    private String patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploration_continued);
        usr = getIntent().getStringExtra("usr");
        patient = getIntent().getStringExtra("patient");
        Button continueButton=(Button) findViewById(R.id.button5);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ExplorationContinued.this, MotivatorRegistration.class);
                i.putExtra("usr", usr);
                i.putExtra("patient", patient);
                startActivity(i);
                //startActivity(new Intent(ExplorationContinued.this, MotivatorRegistration.class));
            }
        });
    }
}
