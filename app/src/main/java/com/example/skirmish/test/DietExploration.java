package com.example.skirmish.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DietExploration extends AppCompatActivity {
    private String usr;
    private String patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usr = getIntent().getStringExtra("usr");
        patient = getIntent().getStringExtra("patient");
        setContentView(R.layout.activity_diet_exploration);
        Button nextButton=(Button) findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DietExploration.this, CurrentAndIdealDiet.class);
                i.putExtra("usr", usr);
                i.putExtra("patient", patient);
                startActivity(i);
                //startActivity(new Intent(DietExploration.this, CurrentAndIdealDiet.class));
            }
        });
    }
}
