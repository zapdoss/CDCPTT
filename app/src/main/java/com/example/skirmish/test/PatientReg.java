package com.example.skirmish.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.skirmish.test.R.id.dp;
import static com.example.skirmish.test.R.id.imageView;

public class PatientReg extends AppCompatActivity {

    Health_nav_db hn = new Health_nav_db(this);
    Patient_db pt = new Patient_db(this);
    Food_db foods=new Food_db(this);
    LocalDietSerialized localDiet = new LocalDietSerialized();
    private String usr;
    private String patient;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView displayPic;
    private String currentPicPath;
    boolean picTaken=false;
    private RadioButton r1,r2;
    private int sexSelected=2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_reg);

        Toolbar toolb = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolb);
        ActionBar p = getSupportActionBar();
        p.setDisplayShowTitleEnabled(false);
        TextView o = (TextView)findViewById(R.id.tool_title);

        TextView titlebar = (TextView) findViewById(R.id.tool_hn);
        this.usr = getIntent().getStringExtra("usr");
        this.patient = "";
        //this.localDiet=(LocalDietSerialized)getIntent().getSerializableExtra("localdiet");
        //TextView t = (TextView)findViewById(R.id.textView4);
        final String a = hn.getName(usr);
        titlebar.setText("HN: "+a);
        o.setText(patient);
        displayPic=(ImageView)findViewById(dp);
        r1=(RadioButton)findViewById(R.id.radioButton1);
        r2=(RadioButton)findViewById(R.id.radioButton2);

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r1.isChecked()) r1.setChecked(false);
                r2.setChecked(true);
                sexSelected=1;
            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(r2.isChecked()) r2.setChecked(false);
                r1.setChecked(true);
                sexSelected=0;
            }
        });

    }

    public void takePic(View v){
        if(v.getId()==R.id.b_takePic){
            dispatchTakePictureIntent();
            //setPic();
        }
    }

    public void addPatient(View v){
        if(v.getId()==R.id.b_addPatient){
            if(findViewById(R.id.tv_nm).toString().isEmpty() || findViewById(R.id.tv_age).toString().isEmpty() || sexSelected==2){
                Toast.makeText(this,"Fill the details!",Toast.LENGTH_SHORT).show();
            }
            else {
                if(picTaken && displayPic.getDrawable()!=null){
                    BitmapDrawable drawable = (BitmapDrawable) displayPic.getDrawable();
                    Bitmap image = drawable.getBitmap();
    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();
                    pt.insertx(((EditText)findViewById(R.id.et_nm)).getText().toString(), Integer.parseInt(((EditText)findViewById(R.id.et_age)).getText().toString()), this.usr, sexSelected, imageInByte);
                }
                else
                pt.insertx(((EditText)findViewById(R.id.et_nm)).getText().toString(), Integer.parseInt(((EditText)findViewById(R.id.et_age)).getText().toString()), this.usr, sexSelected, null);
                int q = pt.getLastID();
                foods.insertCat1(q, 1, 1, 1, 1, 1, 1);
                foods.insertCat2(q, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
                foods.insertCat3(q, 1, 1, 1, 1, 1, 1);
                foods.insertCat4(q, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
                foods.insertCat5(q, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
                Intent i = new Intent(this, Search.class);
                i.putExtra("usr", usr);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(this, "new id: " + q, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            this.displayPic.setImageBitmap(imageBitmap);
           setPic();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        this.currentPicPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.d("data",this.currentPicPath);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                Log.d("data",this.currentPicPath);
                //setPic();
            }
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = this.displayPic.getWidth();
        int targetH = this.displayPic.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.currentPicPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        this.picTaken=true;
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(this.currentPicPath, bmOptions);
        this.displayPic.setImageBitmap(bitmap);
    }

}
