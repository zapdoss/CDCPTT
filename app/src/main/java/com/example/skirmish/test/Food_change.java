package com.example.skirmish.test;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Food_change extends AppCompatActivity {

    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pt = new Patient_db(this);
    Food_db food=new Food_db(this);
    LocalDietSerialized localDiet = new LocalDietSerialized();
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
    //Food_db foods = new Food_db(this);
    String[] nut;
    int[] nut_im;
    ArrayList<Integer> values=new ArrayList<>();
    RadioButton rb = null;

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
        this.localDiet = (LocalDietSerialized) getIntent().getSerializableExtra("localdiet");
        //TextView t = (TextView)findViewById(R.id.textView4);
        final String a = hn.getName(usr);
        titlebar.setText("HN: "+a);
        o.setText("Patient ID: "+patient);
        this.selected=Integer.parseInt(getIntent().getStringExtra("category"));

        final int[] data1= localDiet.getTotalArray();
        final String[] data2={"Category 1","Category 2","Category 3","Category 4","Category 5"};

        int i;

        List<PieEntry> list = new ArrayList<>();

        for(i=0;i<data1.length;i++) {
            list.add(new PieEntry(data1[i], data2[i]));
        }

        PieChart pieChart = (PieChart)findViewById(R.id.pi_2);
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleAlpha(0);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.argb(150,0,0,255));
        colors.add(Color.argb(150,0,255,0));
        colors.add(Color.argb(150,255,0,0));
        colors.add(Color.argb(150,255,255,0));
        colors.add(Color.argb(150,0,0,0));

        PieDataSet swt = new PieDataSet(list,"data");
        swt.setColors(colors);

        PieData dat = new PieData(swt);
        pieChart.setData(dat);
        //pieChart.setCenterText("BMI:"+df.format(bmi));
        pieChart.highlightValue(selected, 0, false);
        pieChart.isUsePercentValuesEnabled();
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setClickable(false);
        pieChart.setTouchEnabled(false);
        pieChart.invalidate();


        g1=(GridView)findViewById(R.id.gridview3);
        setAdap(Integer.toString(this.selected));
        int qua[];

        if (selected==0){
            nut=carbo;
            nut_im=carbo_im;
            qua=localDiet.getCategory1();
        }
        else if (selected==1){
            nut=fats;
            nut_im=fats_im;
            qua=localDiet.getCategory2();
        }
        else if (selected==2){
            nut=prot;
            nut_im=prot_im;
            qua=localDiet.getCategory3();
        }
        else if (selected==3){
            nut=vit;
            nut_im=vit_im;
            qua=localDiet.getCategory4();
        }
        else {
            nut=carbo;
            nut_im=carbo_im;
            qua=localDiet.getCategory5();
        }

        //Cursor c = foods.getData(Integer.parseInt(patient),selected);
        //int i;

        for(i=0;i<qua.length;i++){
            //values.add(Integer.parseInt(c.getString(i+1)));
            values.add(qua[i]);
        }



        final ImageView iv = (ImageView)findViewById(R.id.imageView3);
        final TextView tv1 = (TextView)findViewById(R.id.textView12);
        final TextView tv2 = (TextView)findViewById(R.id.textView13);

        g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(FoodDetails.this,""+position,Toast.LENGTH_SHORT).show();
                index=position;
                LinearLayout item_view = (LinearLayout) view;
                final RadioButton itemcheck = (RadioButton) item_view
                        .findViewById(R.id.rb_adapter2);

//                if (itemcheck.isChecked()) {
//                    itemcheck.setChecked(false);
//                } else {
                if(rb != null)
                {
                    rb.setChecked(false);
                }
                itemcheck.setChecked(true);
                //}
                rb=itemcheck;
                //itemcheck.setChecked(true);
                if (selected==0){
                    tv2.setText(carbo[position]);
                    iv.setImageResource(carbo_im[position]);
                    tv1.setText("Info about "+carbo[position]);
                    //carbo[position]="yy";
                    //setAdap(Integer.toString(selected));
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
            localDiet.setCategory(a,selected);
            i.putExtra("localdiet",this.localDiet);
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
            //Cursor c = foods.getData(Integer.parseInt(patient),selected);
            Toast.makeText(this,Integer.toString(values.get(index)),Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialoguebox1);
                dialog.setTitle("Change Quantity");

                // set the custom dialog components - text, image and button
                final TextView text = (TextView) dialog.findViewById(R.id.textView14);
                final TextView textf = (TextView) dialog.findViewById(R.id.textView16);
                textf.setText(Integer.toString(values.get(index)));
                text.setText(Integer.toString(values.get(index)));

                Button add = (Button) dialog.findViewById(R.id.b_add);
                Button sub = (Button) dialog.findViewById(R.id.b_sub);
                Button addf = (Button) dialog.findViewById(R.id.b_addf);
                Button subf = (Button) dialog.findViewById(R.id.b_subf);
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

                subf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textf.setText(Integer.toString(Integer.parseInt(textf.getText().toString())-1));
                    }
                });

                addf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textf.setText(Integer.toString(Integer.parseInt(textf.getText().toString())+1));
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
