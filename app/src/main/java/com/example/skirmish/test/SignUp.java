package com.example.skirmish.test;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    Health_nav_db dbhelp = new Health_nav_db(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView t = (TextView)findViewById(R.id.tool_hn);
        t.setText("");
        TextView o = (TextView)findViewById(R.id.tool_title);
        o.setText("Sign Up");
    }

    public void signup2(View v){
        if (v.getId()==R.id.b_signup){
            EditText n = (EditText)findViewById(R.id.edit_name);
            EditText u = (EditText)findViewById(R.id.edit_user);
            EditText p1 = (EditText)findViewById(R.id.edit_pass1);
            EditText p2 = (EditText)findViewById(R.id.edit_pass2);
            String nm = n.getText().toString();
            String usr = u.getText().toString();
            String psw1 = p1.getText().toString();
            String psw2 = p2.getText().toString();
            if(nm.isEmpty() || usr.isEmpty() || psw1.isEmpty() || psw2.isEmpty()){
                Toast j = Toast.makeText(SignUp.this,"No field can remain empty!",Toast.LENGTH_SHORT);
                j.show();}
            else {
                if (!psw1.equals(psw2)) {
                    Toast t = Toast.makeText(SignUp.this, "Passwords do not match!", Toast.LENGTH_SHORT);
                    t.show();
                } else {
                    if (dbhelp.checkusername(usr)) {
                        health_nav hn = new health_nav();
                        hn.setName(nm);
                        hn.setUsername(usr);
                        hn.setPassword(psw1);
                        dbhelp.insert(hn);
                        Toast l = Toast.makeText(SignUp.this, "You're Signed up! Login to continue.", Toast.LENGTH_SHORT);
                        l.show();
                        finish();
                    } else {
                        Toast l = Toast.makeText(SignUp.this, "Choose a different username!", Toast.LENGTH_SHORT);
                        l.show();
                    }
                }
            }
        }
    }
}
