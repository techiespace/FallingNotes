package com.techiespace.projects.fallingnotes.fragments.nestedListUi;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techiespace.projects.fallingnotes.PracticeActivity;
import com.techiespace.projects.fallingnotes.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemModels;
    private Context mContext;

    public SectionListDataAdapter(ArrayList<SingleItemModel> itemModels, Context mContext) {
        this.itemModels = itemModels;
        this.mContext = mContext;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_card, null);
        SingleItemRowHolder singleItemRowHolder = new SingleItemRowHolder(v, itemModels);
        return singleItemRowHolder;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int position) {
        SingleItemModel itemModel = itemModels.get(position);
        holder.tvTitle.setText(itemModel.getName());
        holder.setMidiPath(itemModel.getUrl());
        itemModel.setUrl(itemModels.get(position).getUrl());
        holder.itemModels.get(position).getUrl();
    }

    @Override
    public int getItemCount() {
        return (null != itemModels ? itemModels.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;
        protected ImageView itemImage;
        private String midiPath;
        private ArrayList<SingleItemModel> itemModels;

        public SingleItemRowHolder(View itemView, ArrayList<SingleItemModel> itemModels) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.itemImage = itemView.findViewById(R.id.itemImage);
            this.itemModels = itemModels;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, PracticeActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, midiPath);
                    mContext.startActivity(intent);
                    Log.e("testing", "onClick: " + tvTitle.getText());
                }
            });
        }

        public void setMidiPath(String midiPath) {
            this.midiPath = midiPath;
        }
    }

}