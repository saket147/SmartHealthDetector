package com.example.socket.smarthealthdetector;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity {
    DatabaseHandler db;
    RecyclerView recyclerView;
    DoctorListAdapter doctorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        db = new DatabaseHandler(this);
        List<Doctor> doctorList = db.getDoctors();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        doctorListAdapter = new DoctorListAdapter(doctorList);
        Toast.makeText(this, "Activity Opened", Toast.LENGTH_SHORT).show();
        Log.d("fatal", doctorList.get(0).getName());

        recyclerView.setAdapter(doctorListAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

    }


}
