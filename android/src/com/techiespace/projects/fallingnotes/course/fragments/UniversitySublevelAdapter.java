package com.techiespace.projects.fallingnotes.course.fragments;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techiespace.projects.fallingnotes.R;

public class UniversitySublevelAdapter extends RecyclerView.Adapter<UniversitySublevelAdapter.MyViewHolder> {
    private String[][] mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    UniversitySublevelAdapter(String[][] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UniversitySublevelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_university_sublevel_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.universitySublevelTitleTextView.setText(mDataset[position][0]);
        holder.universitySublevelDescTextView.setText(mDataset[position][1]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cardView;
        TextView universitySublevelTitleTextView;
        TextView universitySublevelDescTextView;

        MyViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.list_uni_sublevel_item_card);
            universitySublevelTitleTextView = v.findViewById(R.id.testUniSublevelTitle);
            universitySublevelDescTextView = v.findViewById(R.id.testUniSublevelDesc);
        }
    }
}