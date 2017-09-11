package com.example.skirmish.test;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class CurrentAndIdealDiet extends AppCompatActivity {

    private float[]yData={123f, 67f, 34f, 97f};//new float[5];//{123f, 67f, 34f, 97f, 42f};
    private float[]yData2=new float[yData.length];
    //String []yPercent=new String[yData.length];
    private String[]xData={"Category 1", "Category 2", "Category 3","Category 4","Category 4"};
    String name; //temp
    private String usr;
    private String patient;
    private LocalDietSerialized localDiet;
    private Health_nav_db hn=new Health_nav_db(this);
    String[] nut;
    int[] nut_im;
    ArrayList<Integer> values = new ArrayList<>();
//    String[] fats = {"Cheese", "Nuts and Seeds"};
//    String[] carbo = {"Milk", "Potato"};
//    String[] prot = {"Paneer", "Dal"};
//    String[] vit = {"Cheese", "Nuts and Seeds"};
//
//
//    int[] carboq=new int[carbo.length];
//    int[] fatsq=new int[fats.length];
//    int[] protq=new int[prot.length];
//    int[] vitq=new int[vit.length];

   // String[] nutrients={"Carbohydrates","Fats","Proteins","Vitamins"};

    Cursor c;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_and_ideal_diet);
        usr = getIntent().getStringExtra("usr");
        patient = getIntent().getStringExtra("patient");
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
        int[] dataString;// = new int[4];
        //dataString =p.getArray(Integer.parseInt(patient));
      //  dataString=o.getAllContacts("maya");
        //   Log.d("Current & Ideal Diet", "Cone");
//        carboq=foods.getData(Integer.parseInt(this.patient),"0");
////        for(i=0;i<carbo.length;i++){
////            carboq[i]=(Integer.parseInt(c.getString(i+1)));
////        }
//        fatsq=foods.getData(Integer.parseInt(this.patient),"1");
////        for(i=0;i<fats.length;i++){
////            fatsq[i]=(Integer.parseInt(c.getString(i+1)));
////        }
//        protq=foods.getData(Integer.parseInt(this.patient),"2");
////        for(i=0;i<prot.length;i++){
////            protq[i]=(Integer.parseInt(c.getString(i+1)));
////        }
//        vitq=foods.getData(Integer.parseInt(this.patient),"3");
////        for(i=0;i<vit.length;i++){
////            vitq[i]=(Integer.parseInt(c.getString(i+1)));
////        }

//        int sum[]={0,0,0,0};
//        final String[] data2={"carbo","fat","prot","vitamin",};
//        for(i=0;i<carboq.length;i++){
//            sum[0]=sum[0]+carboq[i];
//        }
//        for(i=0;i<fatsq.length;i++){
//            sum[1]=sum[1]+fatsq[i];
//        }
//        for(i=0;i<protq.length;i++){
//            sum[2]=sum[2]+protq[i];
//        }
//        for(i=0;i<vitq.length;i++){
//            sum[3]=sum[3]+vitq[i];
//        }
//
//        for(int i=0; i<yData.length; i++) {
//            yData[i] = sum[i];//(dataString[i]);
//        }

        yData=localDiet.getPercentages();
        final int[] data1=localDiet.getTotalArray();
        final int[] data3=localDiet.getIdealDiet();
        final String[] data2={"Category 1","Category 2","Category 3","Category 4","Category 5"};

        int i;

        List<PieEntry> list = new ArrayList<>();
        List<PieEntry> list1 = new ArrayList<>();

        for(i=0;i<data1.length;i++) {
            list.add(new PieEntry(data1[i], data2[i]));
           // list1.add(new PieEntry(data3[i],data2[i]));

        }

        for(i=0;i<data3.length;i++){
            list1.add(new PieEntry(data3[i],data2[i]));
        }

        PieChart pieChart = (PieChart)findViewById(R.id.idPieChartIdeal);
        PieChart pieChart1 = (PieChart)findViewById(R.id.idPieChartCurrent);
        pieChart.setHoleRadius(0);
        pieChart.setTransparentCircleAlpha(0);
        pieChart1.setHoleRadius(0);
        pieChart1.setTransparentCircleAlpha(0);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.argb(150,0,0,255));
        colors.add(Color.argb(150,0,255,0));
        colors.add(Color.argb(150,255,0,0));
        colors.add(Color.argb(150,255,255,0));
        colors.add(Color.argb(150,0,0,0));

        PieDataSet swt = new PieDataSet(list,"data");
        PieDataSet swt1 = new PieDataSet(list1,"data");
        swt.setColors(colors);
        swt1.setColors(colors);

        PieData dat = new PieData(swt);
        dat.setDrawValues(false);

        pieChart.setData(dat);
        //pieChart.highlightValue(selected, 0, false);
        pieChart.isUsePercentValuesEnabled();
        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawSliceText(false);
        pieChart.setUsePercentValues(true);
        pieChart.setClickable(false);
        pieChart.setTouchEnabled(false);
        pieChart.invalidate();

        PieData dat1 = new PieData(swt1);
        dat1.setDrawValues(false);

        pieChart1.setData(dat1);
        //pieChart.highlightValue(selected, 0, false);
        pieChart1.isUsePercentValuesEnabled();
        pieChart1.getLegend().setEnabled(false);
        pieChart1.getDescription().setEnabled(false);
        pieChart1.setDrawSliceText(false);
        pieChart1.setUsePercentValues(true);
        pieChart1.setClickable(false);
        pieChart1.setTouchEnabled(false);
        pieChart1.invalidate();



//        PieChart current=(PieChart) findViewById(R.id.idPieChartCurrent);
//        //current.setDescription("Current Diet");
//        current.setHoleRadius(4);
//        //current.setRotationEnabled(true);
//        current.setUsePercentValues(true);
//        current.setTransparentCircleAlpha(0);
//        current.setDragDecelerationEnabled(true);
//        current.setDrawSliceText(true);
//
//        addDataSet(current, "Current Diet Chart");



//        current.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                int pos1=e.toString().indexOf("y: ");
//                String amount=e.toString().substring(pos1+2);
//
//                for(int i=0; i<yData2.length; i++){
//                    if(yData2[i]==Float.parseFloat(amount)){
//                        pos1=i;
//                        break;
//                    }
//                }
//
//                String yPercent[]=calcPercent(yData2);
//                String foodGroup=xData[pos1];
//                Toast.makeText(CurrentAndIdealDiet.this, foodGroup+": "+yPercent[pos1]+"%", Toast.LENGTH_SHORT).show();
//                //Toast.makeText(PresentDiet.this, e.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected() {
//            }
//        });
//
//        updateYData();

//        PieChart ideal=(PieChart) findViewById(R.id.idPieChartIdeal);
//        //ideal.setDescription("Ideal Diet");
//        ideal.setHoleRadius(4);
//        //ideal.setRotationEnabled(true);
//        ideal.setUsePercentValues(true);
//        ideal.setTransparentCircleAlpha(0);
//        ideal.setDragDecelerationEnabled(true);
//        ideal.setDrawSliceText(true);
//
//        addDataSet(ideal, "Ideal Diet Chart");
//
//        ideal.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                int pos1=e.toString().indexOf("y: ");
//                String amount=e.toString().substring(pos1+2);
//
//                for(int i=0; i<yData.length; i++){
//                    if(yData[i]==Float.parseFloat(amount)){
//                        pos1=i;
//                        break;
//                    }
//                }
//
//                String[]yPercent=calcPercent(yData);
//                String foodGroup=xData[pos1];
//                Toast.makeText(CurrentAndIdealDiet.this, foodGroup+": "+yPercent[pos1]+"%", Toast.LENGTH_SHORT).show();
//                //Toast.makeText(PresentDiet.this, e.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected() {
//            }
//        });

        Button nextButton=(Button) findViewById(R.id.button2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CurrentAndIdealDiet.this, FoodDetails.class);
                i.putExtra("usr", usr);
                i.putExtra("patient", patient);
                i.putExtra("localdiet",localDiet);
                startActivity(i);
                //startActivity(new Intent(CurrentAndIdealDiet.this, Exploration.class));
            }
        });

    }

    private void updateYData() {
        int  par[]={10,3,4,2};
        for(int i=0; i<yData.length; i++) {
            yData2[i]=yData[i];
            yData[i] = par[i];//Math.random();
        }
    }

    private void addDataSet(PieChart pc, String diet) {
        ArrayList<PieEntry> yEntrys=new ArrayList<>();
        ArrayList<String> xEntrys=new ArrayList<>();

        for(int i=0; i<yData.length; i++) yEntrys.add(new PieEntry(yData[i], i));
        for(int i=0; i<xData.length; i++) xEntrys.add(xData[i]);

        PieDataSet pieDataSet=new PieDataSet(yEntrys, diet);
        pieDataSet.setSliceSpace(10);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
        pieDataSet.setColors(colors);

        Legend legend=pc.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pc.setData(pieData);
        pc.invalidate();
    }

    private String[] calcPercent(float[]foodData){
        String yPercent[]=new String[foodData.length];
        float[]yPerc=new float[yPercent.length];
        float sum=0;
        for(int i=0; i<foodData.length; i++) sum+=foodData[i];
        for(int i=0; i<yPerc.length; i++){
            yPerc[i]=(foodData[i]*100.0f)/sum;
            yPercent[i]= Float.toString(yPerc[i]);
            int dp=yPercent[i].indexOf('.');
            yPercent[i]=yPercent[i].substring(0, dp+3);
        }
        return yPercent;
    }
}
