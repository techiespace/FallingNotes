package com.techiespace.projects.fallingnotes.course.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techiespace.projects.fallingnotes.Database.AppDatabase;
import com.techiespace.projects.fallingnotes.Database.Skill;
import com.techiespace.projects.fallingnotes.Database.SkillDao;
import com.techiespace.projects.fallingnotes.R;

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
    // Provide a suitable constructor (depends on the kind of dataset)
    UniversitySublevelAdapter(int level_id, Context context) {
        this.context = context;
        this.level_id = level_id;
//        mDataset = myDataset;
        mDb = AppDatabase.getInstance(context);
        skillListByLevel = mDb.skillDao().getSkillsByLevel(level_id);

        //TODO: AppExecutors executes the query after getCount is called causing the app to crash due to NullPointerException
        //Temp fix : AllowMainThreadQueries() added
        /*
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
            }
        });
*/

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