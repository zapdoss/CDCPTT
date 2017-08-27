package com.example.skirmish.test;
import com.example.skirmish.test.PiRotate_helper.PiRotateListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DynamicPi extends AppCompatActivity {


    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pat=new Patient_db(this);
    String usr;
    String patient;
    Singleton_helper m_Inst = Singleton_helper.getInstance();
    LocalDietSerialized localDiet = new LocalDietSerialized();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi);

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
        o.setText("Diet for patient ID: "+patient);

        //Toast t = Toast.makeText(DynamicPi.this,patient,Toast.LENGTH_SHORT);
        //t.show();


        //Code to access database and put in local diet.
        int c1[] = {1,1};
        int c2[] = {1,1};
        int c3[] = {1,1};
        int c4[] = {1,1};
        int c5[] = {1,1};
        localDiet.setCategory1(c1);
        localDiet.setCategory2(c2);
        localDiet.setCategory3(c3);
        localDiet.setCategory4(c4);
        localDiet.setCategory5(c5);
        int[] arr = localDiet.getPercentagesInInt();
        //


        //int[] arr = pat.getArray(Integer.parseInt(this.patient));
        /*int total=0;
        int  par[]={1,1,1,6,1};
        for(int i=0;i<5;i++){
            total+=par[i];
        }
        int p1=(par[0]*100)/total;
        int  p2=(par[1]*100)/total;
        int  p3=(par[2]*100)/total;
        int  p4=(par[3]*100)/total;
        int p5=100-p1-p2-p3-p4;*/

        int p1=arr[0];
        int p2=arr[1];
        int p3=arr[2];
        int p4=arr[3];
        int p5=100-p1-p2-p3-p4;


        m_Inst.InitGUIFrame(this);

        RelativeLayout panel = (RelativeLayout) findViewById(R.id.rl_pi);
        //setContentView(panel);

        final TextView tv1 = (TextView) findViewById(R.id.tv_p1);
        // tv2.setId(id1);

        final TextView tv2 = (TextView) findViewById(R.id.tv_p2);
        //  tv3.setId(id2);

        final TextView tv3 = (TextView) findViewById(R.id.tv_p3);
        //tv4.setId(id3);

        final TextView tv4 = (TextView) findViewById(R.id.tv_p4);
        final TextView tv5 = (TextView) findViewById(R.id.tv_p5);

        tv1.setText(""+p1);
       // tv2.setId(id1);

        tv2.setText(p2+"");
      //  tv3.setId(id2);

        tv3.setText(p3+"");
        //tv4.setId(id3);

        tv4.setText(p4+"");

        tv5.setText(p5+"");
        //tv5.setId(id4);

        final ImageView i = new ImageView(this);
        Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.stator1);
        float scaleWidth = ((float) m_Inst.Scale(250)) / image.getWidth();
        float scaleHeight = ((float) m_Inst.Scale(250)) / image.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap bmpRotorOn = Bitmap.createBitmap(
                image, 0, 0,
                image.getWidth(),image.getHeight() , matrix , true);

        i.setImageBitmap(bmpRotorOn);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(i, lp);

        final ImageView image1 = new ImageView(this);
        panel.addView(image1,lp);
        image1.setImageBitmap(createBitMap((float)3.6*p5,(float)3.6*p1,(float)3.6*p2,(float)3.6*p3,(float)3.6*p4));



//        final PiRotate_helper rv = new PiRotate_helper(this, 1, R.drawable.rotoron1,
//                m_Inst.Scale(250), m_Inst.Scale(250),false);
//        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
//        panel.addView(rv, lp);
//       // final float p;
//        rv.setRotorPercentage(0);

        final PiRotate_helper rv1 = new PiRotate_helper(this, p1, R.drawable.rotoron1,
                m_Inst.Scale(250), m_Inst.Scale(250),true);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv1, lp);
        //float p1;
        rv1.setRotorPercentage(p1);

        final PiRotate_helper rv2 = new PiRotate_helper(this, p1+p2, R.drawable.rotoron1,
                m_Inst.Scale(250), m_Inst.Scale(250),true);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv2, lp);
        //float p2;
        rv2.setRotorPercentage(p1+p2);

        final PiRotate_helper rv3 = new PiRotate_helper(this, p1+p2+p3, R.drawable.rotoron1,
                m_Inst.Scale(250), m_Inst.Scale(250),true);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv3, lp);
        //float p3;
        rv3.setRotorPercentage(p1+p2+p3);

        final PiRotate_helper rv4 = new PiRotate_helper(this, p1+p2+p3+p4, R.drawable.rotoron1,
                m_Inst.Scale(250), m_Inst.Scale(250),true);
        lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        panel.addView(rv4, lp);
        //float p3;
        rv4.setRotorPercentage(p1+p2+p3+p4);

        rv1.setMin(-180);
        rv1.setMax(rv2.getRotorPosAngle());
        rv2.setMin(rv1.getRotorPosAngle());
        rv2.setMax(rv3.getRotorPosAngle());
        rv3.setMin(rv2.getRotorPosAngle());
        rv3.setMax(rv4.getRotorPosAngle());
        rv4.setMin(rv3.getRotorPosAngle());
        rv4.setMax(180);

        /*rv.SetListener(new PiRotateListener() {
			public void onStateChange(boolean newstate) {

			}

			public void onRotate(final int percentage) {
				final int yes = percentage + 100 - rv3.getP();

				tv2.post(new Runnable() {
					public void run() {
						tv2.setText("" + yes);
					}
				});
				image1.setImageBitmap(createBitMap((float)(yes*3.6),(float)((rv1.getP()-percentage)*3.6),(float)(Integer.parseInt(tv4.getText().toString())*3.6),(float)(Integer.parseInt(tv5.getText().toString())*3.6)));

				tv3.post(new Runnable() {
					public void run() {
						int a = rv1.getP()-percentage;
						//int b=a+2;
						//int c = a-percentage;
						tv3.setText( ""+a  );
					}
				});

			}
		});*/

        rv1.SetListener(new PiRotateListener() {
            public void onStateChange(boolean newstate) {

            }

            public void onRotate(final int percentage) {
                final int yes = percentage;
                tv1.post(new Runnable() {
                    public void run() {
                        tv1.setText("" + yes);
                    }
                });
                image1.setImageBitmap(createBitMap((float)(Integer.parseInt(tv5.getText().toString())*3.6),(float)(yes*3.6),(float)((rv2.getP()-percentage)*3.6),(float)(Integer.parseInt(tv3.getText().toString())*3.6),(float)(Integer.parseInt(tv4.getText().toString())*3.6)));

                tv2.post(new Runnable() {
                    public void run() {
                        int a = rv2.getP()-percentage;
                        //int b=a+2;
                        //int c = a-percentage;
                        tv2.setText( ""+a);
                    }
                });

                rv1.setMin(-180);
                rv1.setMax(rv2.getRotorPosAngle());
                rv2.setMin(rv1.getRotorPosAngle());
                rv2.setMax(rv3.getRotorPosAngle());
                rv3.setMin(rv2.getRotorPosAngle());
                rv3.setMax(rv4.getRotorPosAngle());
                rv4.setMin(rv3.getRotorPosAngle());
                rv4.setMax(180);

            }
        });

        rv2.SetListener(new PiRotateListener() {
            public void onStateChange(boolean newstate) {

            }

            public void onRotate(final int percentage) {
                final int yes = percentage - rv1.getP();
                tv2.post(new Runnable() {
                    public void run() {
                        tv2.setText("" + yes);
                    }
                });
                image1.setImageBitmap(createBitMap((float)(Integer.parseInt(tv5.getText().toString())*3.6),(float)(Integer.parseInt(tv1.getText().toString())*3.6),(float)(yes*3.6),(float)((rv3.getP()-percentage)*3.6),(float)(Integer.parseInt(tv4.getText().toString())*3.6)));


                tv3.post(new Runnable() {
                    public void run() {
                        int a = rv3.getP()-percentage;
                        //int b=a+2;
                        //int c = a-percentage;
                        tv3.setText( ""+a  );
                    }
                });

                rv1.setMin(-180);
                rv1.setMax(rv2.getRotorPosAngle());
                rv2.setMin(rv1.getRotorPosAngle());
                rv2.setMax(rv3.getRotorPosAngle());
                rv3.setMin(rv2.getRotorPosAngle());
                rv3.setMax(rv4.getRotorPosAngle());
                rv4.setMin(rv3.getRotorPosAngle());
                rv4.setMax(180);
            }
        });

        rv3.SetListener(new PiRotateListener() {
            public void onStateChange(boolean newstate) {

            }

            public void onRotate(final int percentage) {
                final int yes = percentage - rv2.getP();
                tv3.post(new Runnable() {
                    public void run() {
                        tv3.setText("" + yes);
                    }
                });
                image1.setImageBitmap(createBitMap((float)(Integer.parseInt(tv5.getText().toString())*3.6),(float)(Integer.parseInt(tv1.getText().toString())*3.6),(float)(Integer.parseInt(tv2.getText().toString())*3.6),(float)(yes*3.6),(float)((rv4.getP()-percentage)*3.6)));


                tv4.post(new Runnable() {
                    public void run() {
                        int a = rv4.getP()-percentage;
                        //int b=a+2;
                        //int c = a-percentage;
                        tv4.setText( ""+a  );
                    }
                });

                rv1.setMin(-180);
                rv1.setMax(rv2.getRotorPosAngle());
                rv2.setMin(rv1.getRotorPosAngle());
                rv2.setMax(rv3.getRotorPosAngle());
                rv3.setMin(rv2.getRotorPosAngle());
                rv3.setMax(rv4.getRotorPosAngle());
                rv4.setMin(rv3.getRotorPosAngle());
                rv4.setMax(180);
            }
        });

        rv4.SetListener(new PiRotateListener() {
            public void onStateChange(boolean newstate) {

            }

            public void onRotate(final int percentage) {
                final int yes = percentage - rv3.getP();
                tv4.post(new Runnable() {
                    public void run() {
                        tv4.setText("" + yes);
                    }
                });
                //image1.setImageBitmap(createBitMap((float)(Integer.parseInt(tv2.getText().toString())*3.6),(float)(Integer.parseInt(tv3.getText().toString())*3.6),(float)(Integer.parseInt(tv4.getText().toString())*3.6),(float)(Integer.parseInt(tv5.getText().toString())*3.6)));


                tv5.post(new Runnable() {
                    public void run() {
                        int a = 100-percentage;
                        //int b=a+2;
                        //int c = a-percentage;
                        tv5.setText( ""+a);
                    }
                });
                image1.setImageBitmap(createBitMap((float)(Integer.parseInt(tv5.getText().toString())*3.6),(float)(Integer.parseInt(tv1.getText().toString())*3.6),(float)(Integer.parseInt(tv2.getText().toString())*3.6),(float)(Integer.parseInt(tv3.getText().toString())*3.6),(float)(Integer.parseInt(tv4.getText().toString())*3.6)));
                rv1.setMin(-180);
                rv1.setMax(rv2.getRotorPosAngle());
                rv2.setMin(rv1.getRotorPosAngle());
                rv2.setMax(rv3.getRotorPosAngle());
                rv3.setMin(rv2.getRotorPosAngle());
                rv3.setMax(rv4.getRotorPosAngle());
                rv4.setMin(rv3.getRotorPosAngle());
                rv4.setMax(360);

            }
        });


    }

    private Bitmap createBitMap(float a,float b,float c,float d,float f) {
        Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);  //creates bmp
        bitMap = bitMap.copy(bitMap.getConfig(), true);     //lets bmp to be mutable
        Canvas canvas = new Canvas(bitMap);//draw a canvas in defined bmp
        Paint paint = new Paint();
        float size = Math.min(canvas.getWidth(), canvas.getHeight());
        paint.setStrokeWidth(size / 2);
        paint.setStyle(Paint.Style.STROKE);
        final RectF oval = new RectF(0, 0, size, size);
        oval.inset(size / 4, size / 4);


        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setAlpha(0x80);
        Path greenPath = new Path();
        greenPath.arcTo(oval, 90, b, true);
        canvas.drawPath(greenPath, paint);



        paint.setColor(Color.BLUE);
        paint.setAlpha(0x80);
        Path bluePath = new Path();
        bluePath.arcTo(oval, 90 + b, c, true);
        canvas.drawPath(bluePath, paint);

        paint.setColor(Color.YELLOW);
        paint.setAlpha(0x80);
        paint.setAntiAlias(true);
        Path yellowPath = new Path();
        yellowPath.arcTo(oval,90 + b + c, d, true);
        canvas.drawPath(yellowPath, paint);

        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setAlpha(0x80);
        Path redPath = new Path();
        redPath.arcTo(oval, 90+b+c+d,f, true);
        canvas.drawPath(redPath, paint);

        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setAlpha(0x80);
        Path blackPath = new Path();
        blackPath.arcTo(oval, 90+b+c+d+f,a, true);
        canvas.drawPath(blackPath, paint);

        return bitMap;
        //canvas.drawCircle(50,50,30,paint);

        //image1.setImageBitmap(bitMap);
    }

    public void next(View v){
        if (v.getId()==R.id.b_next){
            final TextView tv1 = (TextView) findViewById(R.id.tv_p1);
            // tv2.setId(id1);
            final TextView tv2 = (TextView) findViewById(R.id.tv_p2);
            //  tv3.setId(id2);
            final TextView tv3 = (TextView) findViewById(R.id.tv_p3);
            //tv4.setId(id3);
            final TextView tv4 = (TextView) findViewById(R.id.tv_p4);
            final TextView tv5 = (TextView) findViewById(R.id.tv_p5);
           int[] a={Integer.parseInt(tv1.getText().toString()),Integer.parseInt(tv2.getText().toString()),Integer.parseInt(tv3.getText().toString()),Integer.parseInt(tv4.getText().toString(),Integer.parseInt(tv5.getText().toString())) };
            this.localDiet.setIdealDiet(a);
            Intent i = new Intent(this, FoodDetails.class);
            i.putExtra("localdiet",this.localDiet);
            i.putExtra("usr",this.usr);
            i.putExtra("patient",this.patient);
            startActivity(i);
            //}
        }
    }
}
