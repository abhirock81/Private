package com.example.a1605417.privatestorage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         editText1 = (EditText) findViewById(R.id.et1);
         editText2 = (EditText) findViewById(R.id.et2);
    }

    public void nxtPage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        String email = editText1.getText().toString();
        String password = editText2.getText().toString();

        MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext());
        Cursor cursor = myDbHelper.login(email, password);
        Intent intent = new Intent(getApplicationContext(), Profile.class);
        LoginCheck.logincheckk(cursor, getApplicationContext(), intent);
    }

    @Override
    public void onBackPressed() {

    }
}
