package com.example.activitynewyorktimesapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.activitynewyorktimesapi.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        if (intent.getStringExtra("resultImage") != null) {
            Picasso.get().load(intent.getStringExtra("resultImage"))
                    .fit()
                    .centerCrop()
                    .into(binding.resultImage);
        }

        binding.resultTitle.setText(intent.getStringExtra("resultTitle"));
        binding.resultAuthor.setText(intent.getStringExtra("resultAuthor"));
        binding.resultHeadLine.setText(intent.getStringExtra("resultHeadLine"));
        binding.resultDate.setText(intent.getStringExtra("resultDate"));
        binding.summaryResult.setText(intent.getStringExtra("resultSummary"));

        binding.urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = intent.getStringExtra("resultUrl");
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}