package com.techiespace.projects.fallingnotes.course.fragments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techiespace.projects.fallingnotes.Database.AppDatabase;
import com.techiespace.projects.fallingnotes.Database.Skill;
import com.techiespace.projects.fallingnotes.Database.SkillDao;
import com.techiespace.projects.fallingnotes.PracticeActivity;
import com.techiespace.projects.fallingnotes.R;
import com.techiespace.projects.fallingnotes.course.activities.UniversityInstructionsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UniversitySublevelAdapter extends RecyclerView.Adapter<UniversitySublevelAdapter.MyViewHolder> {
    //    private String[][] mDataset;
    int level_id;
    List<Skill> skillListByLevel = new ArrayList<Skill>();
    SkillDao skillDao;
    AppDatabase mDb;
    Context context;
    Runnable runnable;
    private boolean dataLoaded = false;

    // Provide a suitable constructor (depends on the kind of dataset)
    UniversitySublevelAdapter(List<Skill> skillListByLevel, Context context) {
        this.skillListByLevel = skillListByLevel;
        this.context = context;
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
        holder.universitySublevelTitleTextView.setText(skillListByLevel.get(position).getSkill_name());
        holder.universitySublevelDescTextView.setText(skillListByLevel.get(position).getSkill_list());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skillListByLevel.get(position).getMidiPath().length() > 0) {
                    Intent intent = new Intent(context, PracticeActivity.class);
                    intent.putExtra("playMidi", false);
                    intent.putExtra(Intent.EXTRA_TEXT, "inappmidi/CSUni/" + skillListByLevel.get(position).getMidiPath());
                    intent.putExtra("instructions_TEXT",skillListByLevel.get(position).getInstructions());
                   // System.out.println("adapter"+skillListByLevel.get(position).getInstructions());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, UniversityInstructionsActivity.class);
                    intent.putExtra("instructions_TEXT",skillListByLevel.get(position).getInstructions());
//                    System.out.println("Adapter "+skillListByLevel.get(position).getInstructions());
                    context.startActivity(intent);
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return skillListByLevel.size();
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