package com.techiespace.projects.fallingnotes.course.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.Database.AppDatabase;
import com.techiespace.projects.fallingnotes.Database.AppExecutors;
import com.techiespace.projects.fallingnotes.Database.Skill;
import com.techiespace.projects.fallingnotes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UniversitySublevelFragment extends Fragment {

    List<Skill> skillListByLevel = new ArrayList<Skill>();
    AppDatabase mDb;

    public UniversitySublevelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_university_sublevel, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.sublevelRecyclerView);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        int level_id = getArguments().getInt("level_id");
        final CountDownLatch latch = new CountDownLatch(1);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb = AppDatabase.getInstance(getContext());
                skillListByLevel = mDb.skillDao().getSkillsByLevel(level_id);
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RecyclerView.Adapter mAdapter = new UniversitySublevelAdapter(skillListByLevel, getContext());
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
