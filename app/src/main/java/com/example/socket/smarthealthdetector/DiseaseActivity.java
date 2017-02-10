package com.example.socket.smarthealthdetector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DiseaseActivity extends Activity implements NetworkResponseListener{

    ArrayList<Issues> issuesArrayList;
    ArrayList<Disease> diseaseList;
    TextView issues;
    String name;
    int id;
    Disease disease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        String disaseString = getIntent().getStringExtra("diseaseList");
        Gson gson = new Gson();
        Type type = new TypeToken<Disease>(){}.getType();
        disease = gson.fromJson(disaseString, type);
        Log.d("Disease is ",""+disease.getName());
        issues= (TextView)findViewById(R.id.issues);
        issuesArrayList = new ArrayList<>();

        FetchData fetchData = new FetchData(DiseaseActivity.this,DiseaseActivity.this);
        fetchData.setType_of_request("GET");
        fetchData.setUrl("https://healthservice.priaid.ch/issues?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InNha2V0X2t1bWFyX2VkdUBwcmlhaWQuY2giLCJyb2xlIjoiVXNlciIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL3NpZCI6IjIxNiIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvdmVyc2lvbiI6Ijk2IiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6Ijk5OTk5OTk5OSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IkJhc2ljIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9sYW5ndWFnZSI6ImVuLWdiIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9leHBpcmF0aW9uIjoiMjA5OS0xMi0zMSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcHN0YXJ0IjoiMjAwMC0wMS0wMSIsImlzcyI6Imh0dHBzOi8vYXV0aHNlcnZpY2UucHJpYWlkLmNoIiwiYXVkIjoiaHR0cHM6Ly9oZWFsdGhzZXJ2aWNlLnByaWFpZC5jaCIsImV4cCI6MTQ4NjczMjY5MCwibmJmIjoxNDg2NzI1NDkwfQ.rJkoHws5GAuIP2zrxa2S9EpesQfivc5eR3xcLlMdHZ8&language=en-gb&format=json");
        fetchData.execute();
    }
    @Override
    public void preRequest() {

    }

    @Override
    public void postRequest(Object object) {
        Toast.makeText(DiseaseActivity.this,"Rececived data",Toast.LENGTH_SHORT).show();
        Log.d("Received data","Received data "+object);
        try {
            String finalString = "DiseaseList- \n\t";
            String s = ""+object;
            JSONArray jsonArray = new JSONArray(s);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Issues issues = new Issues(jsonObject.getString("Name"),jsonObject.getInt("ID"));
                issuesArrayList.add(issues);
            }

            for (int i=0;i<issuesArrayList.size();i++){
                    for(int j=0;j<disease.getHsl().length();j++){
                    Log.d("Matching ","Matching "+(issuesArrayList.get(i).getId())+"    "+(disease.getHsl().get(j)));
                        if((double)((issuesArrayList.get(i).getId())*1.0)==(double)(disease.getHsl().get(j))){
                            Log.d("Matched","Matched"+issuesArrayList.get(i).getId());
                            finalString += issuesArrayList.get(i).getName()+"\n\t";
                        }


                }
            }
            issues.setText(finalString);


            for (int i=0;i<issuesArrayList.size();i++){
                Log.d("Receviced ",issuesArrayList.get(i).getName()+"    "+issuesArrayList.get(i).getId());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
