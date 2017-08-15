package com.example.skirmish.test;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Food_change extends AppCompatActivity {

    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pt = new Patient_db(this);
    private String usr;
    private String patient;
    GridView g1;
    String[] fats = {"Cheese", "Nuts and Seeds"};
    int[] fats_im = {R.drawable.cheeze,R.drawable.nuts};
    String[] carbo = {"Milk", "Potato"};
    int[] carbo_im = {R.drawable.milk,R.drawable.potato};
    String[] prot = {"Paneer", "Dal"};
    int[] prot_im = {R.drawable.paneer,R.drawable.dal};
    String[] vit = {"Cheese", "Nuts and Seeds"};
    int[] vit_im = {R.drawable.cheeze,R.drawable.nuts};
    int selected;
    int index=-1;
    Food_db foods = new Food_db(this);
    String[] nut;
    int[] nut_im;
    ArrayList<Integer> values=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_change);

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

        g1=(GridView)findViewById(R.id.gridview3);
        setAdap(Integer.toString(this.selected));

        if (selected==0){
            nut=carbo;
            nut_im=carbo_im;
        }
        else if (selected==1){
            nut=fats;
            nut_im=fats_im;
        }
        else if (selected==2){
            nut=prot;
            nut_im=prot_im;
        }
        else if (selected==3){
            nut=vit;
            nut_im=vit_im;
        }

        Cursor c = foods.getData(Integer.parseInt(patient),selected);
        int i;
        for(i=0;i<nut.length;i++){
            values.add(Integer.parseInt(c.getString(i+1)));
        }



        final ImageView iv = (ImageView)findViewById(R.id.imageView3);
        final TextView tv1 = (TextView)findViewById(R.id.textView12);
        final TextView tv2 = (TextView)findViewById(R.id.textView13);

        g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(FoodDetails.this,""+position,Toast.LENGTH_SHORT).show();
                index=position;
                if (selected==0){
                    tv2.setText(carbo[position]);
                    iv.setImageResource(carbo_im[position]);
                    tv1.setText("Info about "+carbo[position]);
                }
                else if (selected==1){
                    tv2.setText(fats[position]);
                    iv.setImageResource(fats_im[position]);
                    tv1.setText("Info about "+fats[position]);
                }
                else if (selected==2){
                    tv2.setText(prot[position]);
                    iv.setImageResource(prot_im[position]);
                    tv1.setText("Info about "+prot[position]);
                }
                else if (selected==3){
                    tv2.setText(vit[position]);
                    iv.setImageResource(vit_im[position]);
                    tv1.setText("Info about "+vit[position]);
                }

            }
        });

    }

    public void next(View v){
        if(v.getId()==R.id.b_next3){
            Intent i = new Intent(this,Confirmation.class);
            i.putExtra("category",Integer.toString(this.selected));
            i.putExtra("usr",this.usr);
            i.putExtra("patient",this.patient);
            int a[]=new int[values.size()];
            int j;
            for(j=0;j<values.size();j++){
                a[j]=values.get(j);
            }
            Bundle b = new Bundle();
            b.putIntegerArrayList("values",values);
            i.putExtras(b);
            startActivity(i);
        }
    }

    public void confirm(View v){
        if(v.getId()==R.id.b_confirm){
            if(index<0) Toast.makeText(this,"Select",Toast.LENGTH_SHORT).show();
            else{
            Cursor c = foods.getData(Integer.parseInt(patient),selected);
            Toast.makeText(this,Integer.toString(values.get(index)),Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialoguebox1);
                dialog.setTitle("Change Quantity");

                // set the custom dialog components - text, image and button
                final TextView text = (TextView) dialog.findViewById(R.id.textView14);
                text.setText(Integer.toString(values.get(index)));
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(nut_im[index]);

                Button add = (Button) dialog.findViewById(R.id.b_add);
                Button sub = (Button) dialog.findViewById(R.id.b_sub);
                Button cancel = (Button) dialog.findViewById(R.id.button2);
                Button okay = (Button) dialog.findViewById(R.id.button3);

                // if button is clicked, close the custom dialog
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        text.setText(Integer.toString(Integer.parseInt(text.getText().toString())+1));
                    }
                });

                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        text.setText(Integer.toString(Integer.parseInt(text.getText().toString())-1));
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        values.set(index,Integer.parseInt(text.getText().toString()));
                        dialog.dismiss();
                    }
                });


                dialog.show();


            }
        }
    }

    void setAdap(String s){
        //int c= Integer.parseInt(s)
        if(s.equals("1")) g1.setAdapter(new CustomGridAdapter(this,fats,fats_im));
        else if(s.equals("2")) g1.setAdapter(new CustomGridAdapter(this,prot,prot_im));
        else if(s.equals("3")) g1.setAdapter(new CustomGridAdapter(this,vit,vit_im));
        else if(s.equals("0")) g1.setAdapter(new CustomGridAdapter(this,carbo,carbo_im));
    }
}
