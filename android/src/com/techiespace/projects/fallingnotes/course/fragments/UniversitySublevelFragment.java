package com.techiespace.projects.fallingnotes.course.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;

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
        String[][] uniSublevelTitleData = (String[][]) getArguments().getSerializable("uniSublevelTitleData");
        RecyclerView.Adapter mAdapter = new UniversitySublevelAdapter(uniSublevelTitleData);
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
