package com.example.skirmish.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Health_nav_db helper = new Health_nav_db(this);
    Patient_db patients = new Patient_db(this);
    Food_db foods = new Food_db(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView t = (TextView)findViewById(R.id.tool_hn);
        t.setText("");
        TextView o = (TextView)findViewById(R.id.tool_title);
        o.setText("CDCPT");


        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.megan);
// convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.srk);
// convert bitmap to byte
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        image1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
        byte imageInByte1[] = stream1.toByteArray();

        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.aamir);
// convert bitmap to byte
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        image2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
        byte imageInByte2[] = stream2.toByteArray();


        helper.insertx();
        //patients.insertx(15,"aamir",45,"admin",imageInByte2,20,20,30,30);
//        patients.insertx(4,"srk",45,"hn1",imageInByte1,30,30,30,10);
//        foods.insertx(4,1,8,1);
//        foods.insertx(4,6,2,2);
//        foods.insertx(4,2,7,3);
//        foods.insertx(4,1,3,0);
//        patients.insertx(13,"Megan <3",19,"hn1",imageInByte,25,25,25,25);
//        foods.insertx(13,1,1,1);
//        foods.insertx(13,3,2,2);
//        foods.insertx(13,2,6,3);
//        foods.insertx(13,1,1,0);
        /*patients.insertx(6,"naMo",19,"kj",null);
        patients.insertx(7,"aass",19,"a1",null);
        patients.insertx(25,"zinda",19,"admin",imageInByte);
        patients.insertx(9,"chetak",4,"kj",null);
        patients.insertx(10,"big b",70,"kj",null);
        patients.insertx(11,"srk",45,"kj",imageInByte1);
        patients.insertx(12,"surya",11,"kj",null);*/
    }

    public void login(View v){
        if (v.getId()==R.id.b_login){
            EditText u = (EditText)findViewById(R.id.et_user);
            EditText p = (EditText)findViewById(R.id.et_pass);
            String usr = u.getText().toString();
            String pas = p.getText().toString();
            //helper.insert();
            //String n = new String();
            boolean a = helper.validate(usr,pas);
            Intent i = new Intent(this, Search.class);
            i.putExtra("usr",usr);
            if(a) {
                u.getText().clear();
                p.getText().clear();
                startActivity(i);
            }
            else{
                Toast t = Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT);
                t.show();
            }
            //}
        }
    }

    public void signup1(View v){
        if (v.getId()==R.id.b_sign){
            Intent i = new Intent(this, SignUp.class);
            startActivity(i);
            //}
        }
    }
}
