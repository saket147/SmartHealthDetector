package com.example.socket.smarthealthdetector;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DoctorListActivity extends AppCompatActivity {
    DatabaseHandler db;
    RecyclerView recyclerView;
    DoctorListAdapter doctorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        /*db = new DatabaseHandler(this);
        List<Doctor> doctorList = db.getDoctors();*/
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //doctorListAdapter = new DoctorListAdapter(doctorList);
        Toast.makeText(this, "Activity Opened", Toast.LENGTH_SHORT).show();
        //Log.d("fatal", doctorList.get(0).getName());

        new GetDataFromFirebase().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("doctors");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                /*ArrayList<Doctor> values = (ArrayList<Doctor>) dataSnapshot.getValue();
                recyclerView.setAdapter(new DoctorListAdapter(values));*/
                /*GenericTypeIndicator<List<Doctor>> t = new GenericTypeIndicator<List<Doctor>>() {};
                List<Doctor> value = dataSnapshot.getValue(t);*/
                List<Doctor> values = new ArrayList<Doctor>();
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    values.add(child.getValue(Doctor.class));
                }
                recyclerView.setAdapter(new DoctorListAdapter(values));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView.setAdapter(doctorListAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

    }
    private class GetDataFromFirebase extends AsyncTask<Void,Void,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }


}
