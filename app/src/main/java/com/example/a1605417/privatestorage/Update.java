package com.example.a1605417.privatestorage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        String name = intent.getStringExtra("n");
        email = intent.getStringExtra("e");
        String password = intent.getStringExtra("p");
        String contact = intent.getStringExtra("c");
        String gender = intent.getStringExtra("g");
        String dob = intent.getStringExtra("d");
        String city = intent.getStringExtra("ci");


        final EditText editText1 = (EditText) findViewById(R.id.et1);
        final EditText editText2 = (EditText) findViewById(R.id.et2);
        final EditText editText3 = (EditText) findViewById(R.id.et3);
        final EditText editText4 = (EditText) findViewById(R.id.et4);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg);
        final Spinner spinner = (Spinner) findViewById(R.id.sp1);
        final TextView textView = (TextView) findViewById(R.id.d_tv);

        editText1.setText(name);
        editText2.setText(email);
        editText3.setText(password);
        editText4.setText(contact);

        if (gender.equals("Male")) {
            radioGroup.check(R.id.rb1);
        } else {
            radioGroup.check(R.id.rb2);
        }
        textView.setText(dob);
        String[] cityArray = getResources().getStringArray(R.array.City);
        for (int i = 0; i < cityArray.length; i++) {
            if (cityArray[i].equals(city)) {
                spinner.setSelection(i);
            }
        }
        Button button = (Button) findViewById(R.id.b_update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //values

                int rid = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(rid);

                String name = editText1.getText().toString();
                String email1 = editText2.getText().toString();
                String password = editText3.getText().toString();
                String contact = editText4.getText().toString();
                String gender = radioButton.getText().toString();
                String dob = textView.getText().toString();
                String city = (String) spinner.getSelectedItem();

                MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext());
                int a = myDbHelper.updateData(email, name, email1, password, contact, gender, dob, city);
                if (a > 0) {
                    Toast.makeText(Update.this, "Update Successful", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(Update.this, "Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void pickDOB(View view) {

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.dob_popup, null);
        final PopupWindow popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        final DatePicker datePicker = view1.findViewById(R.id.dp);
        Button button = view1.findViewById(R.id.b1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
                TextView textView = (TextView) findViewById(R.id.d_tv);
                textView.setText(date);
                popupWindow.dismiss();
            }
        });

    }
}
