package com.example.a1605417.privatestorage;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayInputStream;

/**
 * Created by Tuhin on 25/10/2017.
 */

public class LoginCheck {
    static void logincheckk(Cursor cursor, Context context, Intent intent) {

        if (cursor.getCount() == 1) {
           Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
            String name = null;
            String emaill = null;
            String passwordd = null;
            String contact = null;
            String gender = null;
            String dob =null;
            String city = null;
            byte[] image1=null;

            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(MyDbHelper.col1));
                emaill = cursor.getString(cursor.getColumnIndex(MyDbHelper.col2));
                passwordd = cursor.getString(cursor.getColumnIndex(MyDbHelper.col3));
                contact = cursor.getString(cursor.getColumnIndex(MyDbHelper.col4));
                gender = cursor.getString(cursor.getColumnIndex(MyDbHelper.col5));
                dob = cursor.getString(cursor.getColumnIndex(MyDbHelper.col6));
                city = cursor.getString(cursor.getColumnIndex(MyDbHelper.col7));
               image1=cursor.getBlob(cursor.getColumnIndex(MyDbHelper.col8));
            }
            intent.putExtra("n", name);
            intent.putExtra("e", emaill);
            intent.putExtra("p", passwordd);
            intent.putExtra("c", contact);
            intent.putExtra("g", gender);
            intent.putExtra("d", dob);
            intent.putExtra("ci", city);
            intent.putExtra("l",image1);

            context.startActivity(intent);

        } else {
                      Toast.makeText(context, "Login unSuccessful", Toast.LENGTH_SHORT).show();
        }

    }
}
