package com.example.socket.smarthealthdetector;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import static android.R.layout.simple_list_item_1;

public class MainActivity extends Activity implements NetworkResponseListener{
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<Disease> diseaseList;
    ArrayList<Disease>[] IDList;
    ArrayList<String> symptomNameList;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> IdList;
    int pos =-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diseaseList = new ArrayList<>();
        IdList  = new ArrayList<>();
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.mutliSelectTextVIew);
        FetchData fetchData = new FetchData(MainActivity.this,MainActivity.this);
        fetchData.setType_of_request("GET");
        fetchData.setUrl("https://healthservice.priaid.ch/symptoms/0/man?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNha2V0X2t1bWFyX2VkdUBwcmlhaWQuY2giLCJyb2xlIjoiVXNlciIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL3NpZCI6IjIxNiIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvdmVyc2lvbiI6Ijk4IiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6Ijk5OTk5OTk5OSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IkJhc2ljIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9sYW5ndWFnZSI6ImVuLWdiIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9leHBpcmF0aW9uIjoiMjA5OS0xMi0zMSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcHN0YXJ0IjoiMjAwMC0wMS0wMSIsImlzcyI6Imh0dHBzOi8vYXV0aHNlcnZpY2UucHJpYWlkLmNoIiwiYXVkIjoiaHR0cHM6Ly9oZWFsdGhzZXJ2aWNlLnByaWFpZC5jaCIsImV4cCI6MTQ5MTY1NjIwNiwibmJmIjoxNDkxNjQ5MDA2fQ.jR67EyVwFGMSeuWK4-c44imQnObDD5yMC-oBm98YYvY&language=en-gb&format=json");
        fetchData.execute();
        symptomNameList = new ArrayList<>();
        Button check = (Button)findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Position","Posittion is "+pos);
                Log.d("Data",""+autoCompleteTextView.getListSelection());
                Intent intent =new Intent(MainActivity.this,DiseaseActivity.class);
                Gson gson = new Gson();
                String jsonDiseases = gson.toJson(diseaseList.get(pos));
                intent.putExtra("diseaseList", jsonDiseases);
                startActivity(intent);
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                String diseaseName = (String) parent.getAdapter().getItem(position);
                Log.d("DN","DN is "+diseaseName);
                pos = symptomNameList.indexOf(diseaseName);
            }
        });
}

    @Override
    public void preRequest() {

    }

    @Override
    public void postRequest(Object object) {
        Toast.makeText(MainActivity.this,"Rececived data",Toast.LENGTH_SHORT).show();
        Log.d("Received data","Received data "+object);
        try {
            String s = ""+object;
            JSONArray jsonArray = new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Disease disease = new Disease(jsonObject.getString("Name"),jsonObject.getInt("ID"),jsonObject.getJSONArray("HealthSymptomLocationIDs"));
                diseaseList.add(disease);
                symptomNameList.add(jsonObject.getString("Name"));
            }
            adapter= new ArrayAdapter<>(MainActivity.this,simple_list_item_1,symptomNameList);
            autoCompleteTextView.setAdapter(adapter);

            for (int i=0;i<diseaseList.size();i++){
                Log.d("Recevice ",diseaseList.get(i).getName()+"    "+diseaseList.get(i).getId());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
