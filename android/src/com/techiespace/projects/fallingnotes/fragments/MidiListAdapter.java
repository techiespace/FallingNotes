package com.techiespace.projects.fallingnotes.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.techiespace.projects.fallingnotes.PracticeActivity;
import com.techiespace.projects.fallingnotes.R;

import java.util.ArrayList;

public class MidiListAdapter extends RecyclerView.Adapter<com.techiespace.projects.fallingnotes.fragments.MidiListAdapter.MyViewHolder> {
    //    private String[][] mDataset;
    private Context mContext;
    private ArrayList<String> dataList;

    // Provide a suitable constructor (depends on the kind of dataset)
    MidiListAdapter(ArrayList<String> dataList, Context mContext) {
//        mDataset = myDataset;
        this.dataList = dataList;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public com.techiespace.projects.fallingnotes.fragments.MidiListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_midi_item, parent, false);

        com.techiespace.projects.fallingnotes.fragments.MidiListAdapter.MyViewHolder vh = new com.techiespace.projects.fallingnotes.fragments.MidiListAdapter.MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(com.techiespace.projects.fallingnotes.fragments.MidiListAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        int splitLen = dataList.get(position).split("/").length;
        holder.midiTextView.setText(dataList.get(position).split("/")[splitLen - 1]);
        holder.midiTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, PracticeActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, dataList.get(position));
                mContext.startActivity(intent);
                Toast.makeText(view.getContext(), dataList.get(position), Toast.LENGTH_SHORT).show();

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cardView;
        TextView midiTextView;

        MyViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.list_midi_item_card);
            midiTextView = v.findViewById(R.id.midiTextView);
        }
    }
}