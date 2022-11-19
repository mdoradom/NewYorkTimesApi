package com.example.activitynewyorktimesapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.activitynewyorktimesapi.databinding.ActivityMainBinding;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String URL1 = "https://api.nytimes.com/svc/movies/v2/reviews/search.json?query=";
    // searchWord
    private static final String URL2 = "&api-key=";
    private static final String KEY = "dqhiCt9glJJSeWNGrKdtby8nPERZnW8T";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(binding.searchText.getText().toString());
            }
        });
    }

    public void search(String text) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL1 + text + URL2 + KEY,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String status = response.optString("status");
                        String num_results = response.optString("num_results");

                        binding.statusResult.setText(status);
                        binding.numRecordsResult.setText(num_results);

                        if (status.equals("OK")) {
                            JSONArray jsonArray = response.optJSONArray("results");
                            List<Result> results = Arrays.asList(new GsonBuilder().create().fromJson(jsonArray.toString(), Result[].class));

                            MyAdapter myAdapter = new MyAdapter(results, getApplicationContext());
                            binding.recyclerView.setAdapter(myAdapter);
                            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag", "onErrorResponse: " + error.getMessage());
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }
}