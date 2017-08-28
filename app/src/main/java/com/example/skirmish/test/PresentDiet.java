package com.example.skirmish.test;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class PresentDiet extends AppCompatActivity {

    private float[]yData={123f, 67f, 34f, 97f};
    String []yPercent=new String[yData.length];
    private String[]xData={"Carbohydrates", "Proteins", "Fats", "Vitamins"};
    PieChart pieChart;
    Button nextButton;
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


    private void calcPercent(){
        float[]yPerc=new float[yPercent.length];
        float sum=0;
        for(int i=0; i<yData.length; i++) sum+=yData[i];
        for(int i=0; i<yPerc.length; i++){
            yPerc[i]=(yData[i]*100.0f)/sum;
            yPercent[i]= Float.toString(yPerc[i]);
            int dp=yPercent[i].indexOf('.');
            yPercent[i]=yPercent[i].substring(0, dp+2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_diet);
        usr = getIntent().getStringExtra("usr");
        patient = getIntent().getStringExtra("patient");


//        c=foods.getData(Integer.parseInt(this.patient),0);
//        for(i=0;i<carbo.length;i++){
//            carboq[i]=(Integer.parseInt(c.getString(i+1)));
//        }
//        c=foods.getData(Integer.parseInt(this.patient),1);
//        for(i=0;i<fats.length;i++){
//            fatsq[i]=(Integer.parseInt(c.getString(i+1)));
//        }
//        c=foods.getData(Integer.parseInt(this.patient),2);
//        for(i=0;i<prot.length;i++){
//            protq[i]=(Integer.parseInt(c.getString(i+1)));
//        }
//        c=foods.getData(Integer.parseInt(this.patient),3);
//        for(i=0;i<vit.length;i++){
//            vitq[i]=(Integer.parseInt(c.getString(i+1)));
//        }
        carboq=foods.getData(Integer.parseInt(this.patient),"0");
//        for(i=0;i<carbo.length;i++){
//            carboq[i]=(Integer.parseInt(c.getString(i+1)));
//        }
        fatsq=foods.getData(Integer.parseInt(this.patient),"1");
//        for(i=0;i<fats.length;i++){
//            fatsq[i]=(Integer.parseInt(c.getString(i+1)));
//        }
        protq=foods.getData(Integer.parseInt(this.patient),"2");
//        for(i=0;i<prot.length;i++){
//            protq[i]=(Integer.parseInt(c.getString(i+1)));
//        }
        vitq=foods.getData(Integer.parseInt(this.patient),"3");
//        for(i=0;i<vit.length;i++){
//            vitq[i]=(Integer.parseInt(c.getString(i+1)));
//        }

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


        nextButton=(Button) findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PresentDiet.this, DietExploration.class);
                i.putExtra("usr", usr);
                i.putExtra("patient", patient);
                startActivity(i);
                //startActivity(new Intent(PresentDiet.this, DietExploration.class));
            }
        });
        int[] dataString;// = new int[4];
        Patient_db p= new Patient_db(this);

        dataString =p.getArray(Integer.parseInt(patient));
        //  dataString=o.getAllContacts("maya");
        //   Log.d("Current & Ideal Diet", "Cone");
        for(int i=0; i<yData.length; i++) {
            yData[i] = sum[i];//(dataString[i]);
        }
        pieChart=(PieChart) findViewById(R.id.idPieChart);
        //pieChart.setDescription("आपकी Present Diet");
        pieChart.setHoleRadius(4);
        //pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDragDecelerationEnabled(true);
        pieChart.setDrawSliceText(true);

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
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

                calcPercent();
                String foodGroup=xData[pos1];
                Toast.makeText(PresentDiet.this, foodGroup+": "+yPercent[pos1]+"%", Toast.LENGTH_SHORT).show();
                //Toast.makeText(PresentDiet.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys=new ArrayList<>();
        ArrayList<String> xEntrys=new ArrayList<>();

        for(int i=0; i<yData.length; i++) yEntrys.add(new PieEntry(yData[i], i));
        for(int i=0; i<xData.length; i++) xEntrys.add(xData[i]);

        PieDataSet pieDataSet=new PieDataSet(yEntrys, "Present Diet Chart");
        pieDataSet.setSliceSpace(10);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);
        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
