package com.techiespace.projects.fallingnotes.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MidiListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public MidiListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_midi_list, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.midiListRecyclerView);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
//        String[][] uniSublevelTitleData = (String[][]) getArguments().getSerializable("uniSublevelTitleData");
//        RecyclerView.Adapter mAdapter = new UniversitySublevelAdapter(uniSublevelTitleData);
//        recyclerView.setAdapter(mAdapter);
        return rootView;
    }

}
