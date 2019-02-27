package com.techiespace.projects.fallingnotes.course.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UniversitySublevelFragment extends Fragment {

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
        RecyclerView.Adapter mAdapter = new UniversitySublevelAdapter(level_id, getContext());
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
