package com.example.a1605417.privatestorage;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import android.net.Uri;

public class Profile extends AppCompatActivity {
    TextView textView2;
    TextView textView3;
    ImageView imageView ;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView textView1 = (TextView) findViewById(R.id.textView10);
        textView2 = (TextView) findViewById(R.id.textView11);
        textView3 = (TextView) findViewById(R.id.textView12);
        TextView textView4 = (TextView) findViewById(R.id.textView13);
        TextView textView5 = (TextView) findViewById(R.id.textView14);
        TextView textView6 = (TextView) findViewById(R.id.textView15);
        TextView textView7 = (TextView) findViewById(R.id.textView16);
        imageView=(ImageView)findViewById(R.id.imageView2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("n");
        String email = intent.getStringExtra("e");
        String password = intent.getStringExtra("p");
        String contact = intent.getStringExtra("c");
        String gender = intent.getStringExtra("g");
        String dob = intent.getStringExtra("d");
        String city = intent.getStringExtra("ci");
        byte[] image=intent.getByteArrayExtra("l");
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(image);
        Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);
       imageView.setImageBitmap(bitmap);

        textView1.setText(name);
        textView2.setText(email);
        textView3.setText(password);
        textView4.setText(contact);
        textView5.setText(gender);
        textView6.setText(dob);
        textView7.setText(city);

    }

    public void deluser(View view) {
        String email = textView2.getText().toString();
        MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext());
        int aa = myDbHelper.delData(email);

        if (aa > 0) {
            Toast.makeText(this, "User Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "User is not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(View view) {
        MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext());
        Cursor cursor = myDbHelper.login(textView2.getText().toString(), textView3.getText().toString());
        Intent intent = new Intent(this, Update.class);
        LoginCheck.logincheckk(cursor, getApplicationContext(), intent);
    }

}
