package com.example.skirmish.test;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private String[]xData={"Proteins", "Grains", "Vegetables","FATS"};
    String name; //temp
    private String usr;
    private String patient;
    Food_db foods = new Food_db(this);
    String[] nut;
    int[] nut_im;
    ArrayList<Integer> values = new ArrayList<>();
    String[] fats = {"Cheese", "Nuts and Seeds"};
    String[] carbo = {"Milk", "Potato"};
    String[] prot = {"Paneer", "Dal"};
    String[] vit = {"Cheese", "Nuts and Seeds"};


    int[] carboq=new int[carbo.length];
    int[] fatsq=new int[fats.length];
    int[] protq=new int[prot.length];
    int[] vitq=new int[vit.length];

    String[] nutrients={"Carbohydrates","Fats","Proteins","Vitamins"};

    Cursor c;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        usr = getIntent().getStringExtra("usr");
        patient = getIntent().getStringExtra("patient");
        DatabaseAdapter o=new DatabaseAdapter(this);
        Patient_db p= new Patient_db(this);
        o.insertContact("maya", "BLR", "18","m","neelam","20","20","20","20","20");
        o.updateContact("maya","27","10","20","27","10");
        int[] dataString;// = new int[4];
        dataString =p.getArray(Integer.parseInt(patient));
      //  dataString=o.getAllContacts("maya");
        //   Log.d("Current & Ideal Diet", "Cone");
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

        for(int i=0; i<yData.length; i++) {
            yData[i] = sum[i];//(dataString[i]);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_and_ideal_diet);
        PieChart current=(PieChart) findViewById(R.id.idPieChartCurrent);
        //current.setDescription("Current Diet");
        current.setHoleRadius(4);
        //current.setRotationEnabled(true);
        current.setUsePercentValues(true);
        current.setTransparentCircleAlpha(0);
        current.setDragDecelerationEnabled(true);
        current.setDrawSliceText(true);

        addDataSet(current, "Current Diet Chart");



        current.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1=e.toString().indexOf("y: ");
                String amount=e.toString().substring(pos1+2);

                for(int i=0; i<yData2.length; i++){
                    if(yData2[i]==Float.parseFloat(amount)){
                        pos1=i;
                        break;
                    }
                }

                String yPercent[]=calcPercent(yData2);
                String foodGroup=xData[pos1];
                Toast.makeText(CurrentAndIdealDiet.this, foodGroup+": "+yPercent[pos1]+"%", Toast.LENGTH_SHORT).show();
                //Toast.makeText(PresentDiet.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });

        updateYData();

        PieChart ideal=(PieChart) findViewById(R.id.idPieChartIdeal);
        //ideal.setDescription("Ideal Diet");
        ideal.setHoleRadius(4);
        //ideal.setRotationEnabled(true);
        ideal.setUsePercentValues(true);
        ideal.setTransparentCircleAlpha(0);
        ideal.setDragDecelerationEnabled(true);
        ideal.setDrawSliceText(true);

        addDataSet(ideal, "Ideal Diet Chart");

        ideal.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int pos1=e.toString().indexOf("y: ");
                String amount=e.toString().substring(pos1+2);

                for(int i=0; i<yData.length; i++){
                    if(yData[i]==Float.parseFloat(amount)){
                        pos1=i;
                        break;
                    }
                }

                String[]yPercent=calcPercent(yData);
                String foodGroup=xData[pos1];
                Toast.makeText(CurrentAndIdealDiet.this, foodGroup+": "+yPercent[pos1]+"%", Toast.LENGTH_SHORT).show();
                //Toast.makeText(PresentDiet.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
            }
        });

        Button nextButton=(Button) findViewById(R.id.button2);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CurrentAndIdealDiet.this, Exploration.class);
                i.putExtra("usr", usr);
                i.putExtra("patient", patient);
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
