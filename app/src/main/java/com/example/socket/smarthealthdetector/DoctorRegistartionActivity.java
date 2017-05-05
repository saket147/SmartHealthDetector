package com.example.socket.smarthealthdetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorRegistartionActivity extends AppCompatActivity {
    EditText name, contact, address, speciality;
    LinearLayout linearLayout;
    Button button;
    DatabaseReference doctorDatabase;
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
        doctorDatabase = FirebaseDatabase.getInstance().getReference("doctors");
    }

    /*public void addDoc(View view) {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        db.addDoctor(new Doctor(name.getText().toString(), contact.getText().toString(), address.getText().toString(), speciality.getText().toString()));

    }*/
    public void addDoc(View view){
        addDoctor();
    }
    public void showForm(View v) {
        button.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    private void addDoctor(){
        String doctor_name = name.getText().toString().trim();
        String doctor_contact = contact.getText().toString().trim();
        String doctor_address = address.getText().toString().trim();
        String doctor_speciality = speciality.getText().toString().trim();
        if (!TextUtils.isEmpty(doctor_name)){
            String id  = doctorDatabase.push().getKey();
            Doctor doctor = new Doctor(doctor_name, doctor_contact, doctor_address, doctor_speciality);
            doctorDatabase.child(id).setValue(doctor);
            Toast.makeText(DoctorRegistartionActivity.this, "Doctor List Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(DoctorRegistartionActivity.this,"Enter one", Toast.LENGTH_SHORT).show();
        }
    }
}
