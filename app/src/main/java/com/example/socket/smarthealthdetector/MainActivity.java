package com.example.socket.smarthealthdetector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import static android.R.layout.simple_list_item_checked;

public class MainActivity extends Activity implements NetworkResponseListener{
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<Disease> diseaseList;
    ArrayList<Disease>[] IDList;
    ArrayList<String> symptomNameList;
    ArrayAdapter<String> adapter;
    ArrayList<Integer> IdList;
    int pos =-1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        diseaseList = new ArrayList<>();
        IdList  = new ArrayList<>();
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.mutliSelectTextVIew);
        autoCompleteTextView.setDropDownBackgroundResource(R.color.colorAccent);
        //autoCompleteTextView.setDropDownBackgroundResource();
        FetchData fetchData = new FetchData(MainActivity.this,MainActivity.this);
        //isNetworkAvailable();
        fetchData.setType_of_request("GET");
        fetchData.setUrl("https://api.myjson.com/bins/jdz13");
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
        isNetworkAvailable();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(context, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
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
            adapter= new ArrayAdapter<>(MainActivity.this,simple_list_item_checked,symptomNameList);
            autoCompleteTextView.setAdapter(adapter);

            for (int i=0;i<diseaseList.size();i++){
                Log.d("Recevice ",diseaseList.get(i).getName()+"    "+diseaseList.get(i).getId());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
