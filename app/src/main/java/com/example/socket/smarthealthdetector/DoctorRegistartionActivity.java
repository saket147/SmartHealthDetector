package com.example.socket.smarthealthdetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class DoctorRegistartionActivity extends AppCompatActivity {
    EditText name, contact, address, speciality;
    LinearLayout linearLayout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registartion);
        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contactno);
        address = (EditText) findViewById(R.id.address);
        speciality = (EditText) findViewById(R.id.speciality);
        linearLayout= (LinearLayout) findViewById(R.id.doctor_registartion);
        button= (Button) findViewById(R.id.register);
    }

    public void addDoc(View view) {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        db.addDoctor(new Doctor(name.getText().toString(), contact.getText().toString(), address.getText().toString(), speciality.getText().toString()));

    }
    public void showForm(View v) {
        button.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }
}
