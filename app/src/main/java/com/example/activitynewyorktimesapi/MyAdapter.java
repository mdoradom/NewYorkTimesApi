package com.example.activitynewyorktimesapi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Result> results;
    private Context context;

    public MyAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String imageUrl = null;
        if (results.get(position).multimedia != null) {
            Picasso.get().load(results.get(position).multimedia.src)
                    .fit()
                    .centerCrop()
                    .into(holder.rowImage);
            imageUrl = results.get(position).multimedia.src;
        } else {
            holder.rowImage.setImageResource(R.drawable.nytlogo1);
        }
        holder.resultTitle.setText(results.get(position).display_title);
        holder.resultAuthor.setText(results.get(position).byline);
        holder.resultHeadLine.setText(results.get(position).headline);
        holder.resultDate.setText(results.get(position).publication_date);

        String finalImageUrl = imageUrl;
        holder.rowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("resultImage", finalImageUrl);
                    intent.putExtra("resultTitle", results.get(position).display_title);
                    intent.putExtra("resultAuthor", results.get(position).byline);
                    intent.putExtra("resultHeadLine", results.get(position).headline);
                    intent.putExtra("resultDate", results.get(position).publication_date);
                    intent.putExtra("resultSummary", results.get(position).summary_short);
                    intent.putExtra("resultUrl", results.get(position).link.url);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView rowImage;
        private TextView resultTitle;
        private TextView resultAuthor;
        private TextView resultHeadLine;
        private TextView resultDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rowImage = itemView.findViewById(R.id.resultImage);
            resultTitle = itemView.findViewById(R.id.resultTitle);
            resultAuthor = itemView.findViewById(R.id.resultAuthor);
            resultHeadLine = itemView.findViewById(R.id.resultHeadLine);
            resultDate = itemView.findViewById(R.id.resultDate);
        }
    }
}
