package com.example.socket.smarthealthdetector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChooserActivity extends AppCompatActivity implements View.OnClickListener {
    Button mPatient, mDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        mPatient = (Button) findViewById(R.id.patient);
        mDoctor = (Button) findViewById(R.id.doctor);
        mPatient.setOnClickListener(this);
        mDoctor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.patient: {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }
            case R.id.doctor: {
                Intent i = new Intent(this, DoctorRegistartionActivity.class);
                startActivity(i);
                break;
            }
            default:
                Toast.makeText(this, "Something wrong..", Toast.LENGTH_SHORT).show();
        }
    }
}
