package com.example.skirmish.test;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Confirmation extends AppCompatActivity {

    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pt = new Patient_db(this);
    LocalDietSerialized localDiet = new LocalDietSerialized();
    private String usr;
    private String patient;
    GridView g1;
//    String[] fats = {"Cheese", "Nuts and Seeds"};
//    int[] fats_im = {R.drawable.cheeze, R.drawable.nuts};
//    String[] carbo = {"Milk", "Potato"};
//    int[] carbo_im = {R.drawable.milk, R.drawable.potato};
//    String[] prot = {"Paneer", "Dal"};
//    int[] prot_im = {R.drawable.paneer, R.drawable.dal};
//    String[] vit = {"Cheese", "Nuts and Seeds"};
//    int[] vit_im = {R.drawable.cheeze, R.drawable.nuts};
    String[] Category1 = localDiet.getCategory1Names();
    int[] Category1Im = localDiet.getCategory1Im();
    String[] Category2 = localDiet.getCategory2Names();
    int[] Category2Im = localDiet.getCategory2Im();
    String[] Category3 = localDiet.getCategory3Names();
    int[] Category3Im = localDiet.getCategory3Im();
    String[] Category4 = localDiet.getCategory4Names();
    int[] Category4Im = localDiet.getCategory4Im();
    String[] Category5 = localDiet.getCategory5Names();
    int[] Category5Im = localDiet.getCategory5Im();
    int selected;
    int index = -1;
    Food_db foods = new Food_db(this);
    String[] nut;
    int[] nut_im;
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<Integer> values1 = new ArrayList<>();
    ArrayList<Integer> change_im = new ArrayList<>();
    ArrayList<Integer> values2 = new ArrayList<>();
    ArrayList<Integer> values3 = new ArrayList<>();
    ArrayList<Integer> values4 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Toolbar toolb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView o = (TextView) findViewById(R.id.tool_title);

        TextView titlebar = (TextView) findViewById(R.id.tool_hn);
        this.usr = getIntent().getStringExtra("usr");
        this.patient = getIntent().getStringExtra("patient");
        //TextView t = (TextView)findViewById(R.id.textView4);
        final String a = hn.getName(usr);
        titlebar.setText("HN: " + a);
        o.setText("Patient ID: " + patient);
        this.selected = Integer.parseInt(getIntent().getStringExtra("category"));
        //this.values = getIntent().getIntegerArrayListExtra("values");
        this.localDiet=(LocalDietSerialized)getIntent().getSerializableExtra("localdiet");

//        int[] carboq=new int[carbo.length];
//        int[] fatsq=new int[fats.length];
//        int[] protq=new int[prot.length];
//        int[] vitq=new int[vit.length];

        String[] nutrients={"Category 1","Category 2","Category 3","Category 4","Category 5"};
        //TextView tv=(TextView)findViewById(R.id.textView16);
        //tv.setText("Review");

        //Retrieving details from the database
        Cursor c;
        int i;
        /*c=foods.getData(Integer.parseInt(this.patient),0);
        for(i=0;i<carbo.length;i++){
            carboq[i]=(Integer.parseInt(c.getString(i+1)));
        }
        c=foods.getData(Integer.parseInt(this.patient),1);
        for(i=0;i<fats.length;i++){
            fatsq[i]=(Integer.parseInt(c.getString(i+1)));
        }
        c=foods.getData(Integer.parseInt(this.patient),2);
        for(i=0;i<prot.length;i++){
            protq[i]=(Integer.parseInt(c.getString(i+1)));
        }
        c=foods.getData(Integer.parseInt(this.patient),3);
        for(i=0;i<vit.length;i++){
            vitq[i]=(Integer.parseInt(c.getString(i+1)));
        }*/

        //Retrieving local details
        int[] sum=localDiet.getTotalArray();

        //Generating pie chart from local details
        final String[] data2={"Category 1","Category 2","Category 3","Category 4","Category 5"};
        /*for(i=0;i<carboq.length;i++){
            sum[0]=sum[0]+carboq[i];
        }
        for(i=0;i<fatsq.length;i++){
            sum[1]=sum[1]+fatsq[i];
        }
        for(i=0;i<protq.length;i++){
            sum[2]=sum[2]+protq[i];
        }
        for(i=0;i<vitq.length;i++){
            sum[3]=sum[3]+vitq[i];
        }*/

        /*int temp=0;
        for(i=0;i<values.size();i++){
            temp=temp+values.get(i);
        }

        sum[this.selected]=temp;*/

        List<PieEntry> list = new ArrayList<>();

        for(i=0;i<data2.length;i++) {
            list.add(new PieEntry(sum[i], data2[i]));
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
        dat.setDrawValues(false);

        pieChart.setData(dat);
        pieChart.isUsePercentValuesEnabled();
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawSliceText(false);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setClickable(false);
        pieChart.setTouchEnabled(false);
        pieChart.invalidate();

        //Find changes by comparing
        int[] Original1q = foods.getData(Integer.parseInt(this.patient),"1q");
        int[] Original1f = foods.getData(Integer.parseInt(this.patient),"1f");
        for(i=0;i<Category1.length;i++){
            if(Original1q[i]!=localDiet.getCategory1()[i] || Original1f[i]!=localDiet.getCategory1f()[i]){
                values.add(localDiet.getCategory1()[i]);
                values1.add(localDiet.getCategory1f()[i]);
                change_im.add(Category1Im[i]);
            }
        }
        int[] Original2q = foods.getData(Integer.parseInt(this.patient),"2q");
        int[] Original2f = foods.getData(Integer.parseInt(this.patient),"2f");
        for(i=0;i<Category2.length;i++){
            if(Original2q[i]!=localDiet.getCategory2()[i] || Original2f[i]!=localDiet.getCategory2f()[i]){
                values.add(localDiet.getCategory2()[i]);
                values1.add(localDiet.getCategory2f()[i]);
                change_im.add(Category2Im[i]);
            }
        }
        int[] Original3q = foods.getData(Integer.parseInt(this.patient),"3q");
        int[] Original3f = foods.getData(Integer.parseInt(this.patient),"3f");
        for(i=0;i<Category3.length;i++){
            if(Original3q[i]!=localDiet.getCategory3()[i] || Original3f[i]!=localDiet.getCategory3f()[i]){
                values.add(localDiet.getCategory3()[i]);
                values1.add(localDiet.getCategory3f()[i]);
                change_im.add(Category3Im[i]);
            }
        }
        int[] Original4q = foods.getData(Integer.parseInt(this.patient),"4q");
        int[] Original4f = foods.getData(Integer.parseInt(this.patient),"4f");
        for(i=0;i<Category4.length;i++){
            if(Original4q[i]!=localDiet.getCategory4()[i] || Original4f[i]!=localDiet.getCategory4f()[i]){
                values.add(localDiet.getCategory4()[i]);
                values1.add(localDiet.getCategory4f()[i]);
                change_im.add(Category4Im[i]);
            }
        }
        int[] Original5q = foods.getData(Integer.parseInt(this.patient),"5q");
        int[] Original5f = foods.getData(Integer.parseInt(this.patient),"5f");
        for(i=0;i<Category5.length;i++){
            if(Original5q[i]!=localDiet.getCategory5()[i] || Original5f[i]!=localDiet.getCategory5f()[i]){
                values.add(localDiet.getCategory5()[i]);
                values1.add(localDiet.getCategory5f()[i]);
                change_im.add(Category5Im[i]);
            }
        }

        //

        /*if (selected==1) {
            for (i = 0; i < fats.length; i++) {
                fats[i] = fats[i] + ": " + fatsq[i]+"-> "+values.get(i);
            }
        }
        else if(selected==0) {
            for (i = 0; i < carbo.length; i++) {
                carbo[i] = carbo[i] + ": " + carboq[i]+"-> "+values.get(i);
            }
        }
        else if(selected==2) {
            for (i = 0; i < prot.length; i++) {
                prot[i] = prot[i] + ": " + protq[i]+"-> "+values.get(i);
            }
        }
        else if(selected==3) {
            for (i = 0; i < vit.length; i++) {
                vit[i] = vit[i] + ": " + vitq[i]+"-> "+values.get(i);
            }
        }*/

        g1=(GridView)findViewById(R.id.grid3);
        setAdap(Integer.toString(this.selected));

    }

    void setAdap(String s){
        //int c= Integer.parseInt(s)
        g1.setAdapter(new ConfirmationGridAdapter(this,values,values1,change_im));
        /*if(s.equals("1")) g1.setAdapter(new ConfirmationGridAdapter(this,values,values1,fats_im));
        else if(s.equals("2")) g1.setAdapter(new ConfirmationGridAdapter(this,values,values1,prot_im));
        else if(s.equals("3")) g1.setAdapter(new ConfirmationGridAdapter(this,values,values1,vit_im));
        else if(s.equals("0")) g1.setAdapter(new ConfirmationGridAdapter(this,values,values1,carbo_im));
        */
    }

    public void confirm2(View v){
        if(v.getId()==R.id.confirm2){
            //foods.update(Integer.parseInt(this.patient),this.selected,values);
            updateFoodDB(Integer.parseInt(this.patient));
            Intent intent = new Intent(getApplicationContext(), DietCalendar.class);
            intent.putExtra("usr",usr);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void newchange(View v){
        if(v.getId()==R.id.newchange){
            //foods.update(Integer.parseInt(this.patient),this.selected,values);
            Intent i = new Intent(getApplicationContext(), FoodDetails.class);
            i.putExtra("localdiet",this.localDiet);
            i.putExtra("usr",this.usr);
            i.putExtra("patient",this.patient);
            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    public void updateFoodDB(int s){
        int i;
        for(i=0;i<5;i++){
            foods.update(s,i,localDiet.getCategory(i),localDiet.getCategoryf(i));
        }
    }
}
