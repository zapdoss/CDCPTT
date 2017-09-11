package com.example.skirmish.test;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class FoodDetails extends AppCompatActivity {

    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pt = new Patient_db(this);
    Food_db food=new Food_db(this);
    LocalDietSerialized localDiet = new LocalDietSerialized();
    private String usr;
    private String patient;
    int selected=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi_details);

        Toolbar toolb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView o = (TextView)findViewById(R.id.tool_title);

        TextView titlebar = (TextView) findViewById(R.id.tool_hn);
        this.usr = getIntent().getStringExtra("usr");
        this.patient = getIntent().getStringExtra("patient");
        this.localDiet=(LocalDietSerialized)getIntent().getSerializableExtra("localdiet");
        //TextView t = (TextView)findViewById(R.id.textView4);
        final String a = hn.getName(usr);
        titlebar.setText("HN: "+a);
        o.setText("Patient ID: "+patient);

        //g1=(GridView)findViewById(R.id.gridview2);
       // g1.setAdapter(new CustomGridAdapter(this,fats,fats_im));

        //final int[] data1= food.getArray(Integer.parseInt(this.patient));
        final int[] data1=localDiet.getTotalArray();
        final String[] data2={"Category 1","Category 2","Category 3","Category 4","Category 5"};

        int i;

        List<PieEntry> list = new ArrayList<>();

        for(i=0;i<data1.length;i++) {
            list.add(new PieEntry(data1[i], data2[i]));
        }

        PieChart pieChart = (PieChart)findViewById(R.id.pi_1);
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
        dat.setDrawValues(false);

        pieChart.setData(dat);
        pieChart.isUsePercentValuesEnabled();
        pieChart.setDrawSliceText(false);
        pieChart.setUsePercentValues(true);
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);
        //pieChart.setTransparentCircleAlpha(2);
        pieChart.invalidate();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1 = h.toString().indexOf("x: ");
                String s = h.toString().substring(pos1+3,pos1+4);

                /*for(int i=0;i<data1.length;i++){
                    if(data1[i]==Float.parseFloat(s)){
                         pos1=i;
                         break;
                    }
                }*/

                Toast.makeText(FoodDetails.this,s,Toast.LENGTH_SHORT).show();

                setAdap(s);
            }

            @Override
            public void onNothingSelected() {
                setAdap("-1");

            }
        });

//        final ImageView iv = (ImageView)findViewById(R.id.imageView2);
//        final TextView tv1 = (TextView)findViewById(R.id.textView11);
//        final TextView tv2 = (TextView)findViewById(R.id.textView10);
//
//        g1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(FoodDetails.this,""+position,Toast.LENGTH_SHORT).show();
//                if (selected==0){
//                    tv2.setText(carbo[position]);
//                    iv.setImageResource(carbo_im[position]);
//                    tv1.setText("Info about "+carbo[position]);
//                }
//                else if (selected==1){
//                    tv2.setText(fats[position]);
//                    iv.setImageResource(fats_im[position]);
//                    tv1.setText("Info about "+fats[position]);
//                }
//                else if (selected==2){
//                    tv2.setText(prot[position]);
//                    iv.setImageResource(prot_im[position]);
//                    tv1.setText("Info about "+prot[position]);
//                }
//                else if (selected==3){
//                    tv2.setText(vit[position]);
//                    iv.setImageResource(vit_im[position]);
//                    tv1.setText("Info about "+vit[position]);
//                }
//
//            }
//        });

    }

    public void next(View v){
        if(v.getId()==R.id.b_next1){
            if(selected==-1){
                Toast.makeText(this,"Select a category from Pie Chart",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i = new Intent(this,Food_change.class);//StaticInfoPartner.class);
                i.putExtra("category",Integer.toString(this.selected));
                i.putExtra("usr",this.usr);
                i.putExtra("patient",this.patient);
                i.putExtra("localdiet",this.localDiet);
                startActivity(i);
            }
        }
    }

    void setAdap(String s){
//        //int c= Integer.parseInt(s);
        this.selected= Integer.parseInt(s);
//        if(s.equals("1")) g1.setAdapter(new CustomGridAdapter(this,fats,fats_im));
//        else if(s.equals("2")) g1.setAdapter(new CustomGridAdapter(this,prot,prot_im));
//        else if(s.equals("3")) g1.setAdapter(new CustomGridAdapter(this,vit,vit_im));
//        else if(s.equals("0")) g1.setAdapter(new CustomGridAdapter(this,carbo,carbo_im));
//        else if(s.equals("-1")) g1.setAdapter(new CustomGridAdapter(this,empty,empty_im));
    }
}
