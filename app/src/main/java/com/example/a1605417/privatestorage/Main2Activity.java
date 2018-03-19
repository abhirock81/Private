package com.example.a1605417.privatestorage;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {
    public final static String APP_PATH_SD_CARD = "/DesiredSubfolderName/";
     String name1;
    String email1;
    String password1;
    String contact1;
     String gender1;
     String dob1;
     String city1;
    String  fullPath;
    @InjectView(R.id.floatingActionButton2)
    FloatingActionButton fab;
    @InjectView(R.id.button4)
    Button b4;
    @InjectView(R.id.textView)
    TextView textView1;
    public ImageView imageView;
    FragmentTransaction fragmentTransaction;
    Bitmap bitmap1;
    Bitmap bitmap2;
    byte[] byteArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.inject(this);
        imageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
         name1 = intent.getStringExtra("n");
        email1 = intent.getStringExtra("e");
        password1 = intent.getStringExtra("p");
         contact1 = intent.getStringExtra("c");
        gender1 = intent.getStringExtra("g");
         dob1 = intent.getStringExtra("d");
        city1 = intent.getStringExtra("ci");

    }

    @OnClick(R.id.floatingActionButton2)
    public void click(View view) {
        Snackbar.make(findViewById(R.id.fragment), "Clicked FAB.", Snackbar.LENGTH_LONG)
                //.setAction("Action", this)
                .show();

        FragmentManager fm = getFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        PickerFragment pickerFragment = new PickerFragment();
        pickerFragment.show(getFragmentManager(), "dialog");
        fragmentTransaction.commit();

    }
    public void createPalette1(Bitmap bitmap) {
        bitmap1=bitmap;
        imageView.setImageBitmap(bitmap1);
    }
    public void createPalette(Uri imageUri) {

        Picasso.with(this).load(imageUri).into(imageView);

        // Do this async on activity
        InputStream imageStream = null;
        try {
            imageStream = getContentResolver().openInputStream(imageUri);
            bitmap1 = BitmapFactory.decodeStream(imageStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }




    @OnClick(R.id.button4)
    public void click2(View view){

         fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_PATH_SD_CARD ;
        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            OutputStream fOut = null;
            File file = new File(fullPath, "desiredFilename.png");
            file.createNewFile();
            fOut = new FileOutputStream(file);

// 100 means no compression, the lower you go, the stronger the compression
            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();

            MediaStore.Images.Media.insertImage(this.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());



        } catch (Exception e) {
            Log.e("saveToExternalStorage()", e.getMessage());
        }
        MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext());
        long a=myDbHelper.insertData(name1,email1,password1,contact1,gender1,dob1,city1,fullPath);
        if (a > 0) {
            Toast.makeText(getApplicationContext(), "Sign Up Successful", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, Login.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Sign Up unSuccessful", Toast.LENGTH_SHORT).show();
        }

    }


}


