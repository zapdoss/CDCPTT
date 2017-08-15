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
    private String usr;
    private String patient;
    GridView g1;
    String[] fats = {"Cheese", "Nuts and Seeds"};
    int[] fats_im = {R.drawable.cheeze, R.drawable.nuts};
    String[] carbo = {"Milk", "Potato"};
    int[] carbo_im = {R.drawable.milk, R.drawable.potato};
    String[] prot = {"Paneer", "Dal"};
    int[] prot_im = {R.drawable.paneer, R.drawable.dal};
    String[] vit = {"Cheese", "Nuts and Seeds"};
    int[] vit_im = {R.drawable.cheeze, R.drawable.nuts};
    int selected;
    int index = -1;
    Food_db foods = new Food_db(this);
    String[] nut;
    int[] nut_im;
    ArrayList<Integer> values = new ArrayList<>();

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
        this.values = getIntent().getIntegerArrayListExtra("values");

        int[] carboq=new int[carbo.length];
        int[] fatsq=new int[fats.length];
        int[] protq=new int[prot.length];
        int[] vitq=new int[vit.length];

        String[] nutrients={"Carbohydrates","Fats","Proteins","Vitamins"};
        TextView tv=(TextView)findViewById(R.id.textView16);
        tv.setText("Review for "+nutrients[this.selected]);

        Cursor c;
        int i;
        c=foods.getData(Integer.parseInt(this.patient),0);
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
        }

        int sum[]={0,0,0,0};
        final String[] data2={"carbo","fat","prot","vitamin",};
        for(i=0;i<carboq.length;i++){
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
        }

        int temp=0;
        for(i=0;i<values.size();i++){
            temp=temp+values.get(i);
        }

        sum[this.selected]=temp;

        List<PieEntry> list = new ArrayList<>();

        for(i=0;i<data2.length;i++) {
            list.add(new PieEntry(sum[i], data2[i]));
        }

        PieChart pieChart = (PieChart)findViewById(R.id.pi_2);
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleAlpha(0);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);

        PieDataSet swt = new PieDataSet(list,"data");
        swt.setColors(colors);

        PieData dat = new PieData(swt);
        pieChart.setData(dat);
        pieChart.invalidate();

        if (selected==1) {
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
        }

        g1=(GridView)findViewById(R.id.grid3);
        setAdap(Integer.toString(this.selected));

    }

    void setAdap(String s){
        //int c= Integer.parseInt(s)
        if(s.equals("1")) g1.setAdapter(new CustomGridAdapter(this,fats,fats_im));
        else if(s.equals("2")) g1.setAdapter(new CustomGridAdapter(this,prot,prot_im));
        else if(s.equals("3")) g1.setAdapter(new CustomGridAdapter(this,vit,vit_im));
        else if(s.equals("0")) g1.setAdapter(new CustomGridAdapter(this,carbo,carbo_im));
    }

    public void confirm2(View v){
        if(v.getId()==R.id.confirm2){
            foods.update(Integer.parseInt(this.patient),this.selected,values);
            Intent intent = new Intent(getApplicationContext(), DietCalendar.class);
            intent.putExtra("usr",usr);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
