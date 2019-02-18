package com.techiespace.projects.fallingnotes.course.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;

public class UniversityFragment extends Fragment {


    public UniversityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_university, container, false);
        CardView helloLevel = rootView.findViewById(R.id.helloLevel);
        helloLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle titleBundle = new Bundle();
                String[][] uniSublevelTitleData = new String[][]{{"Skill Name 1", "Skill List 1"}, {"Skill Name 2", "Skill List 2"}, {"Skill Name 3", "Skill List 3"}, {"Skill Name 4", "Skill List 4"}, {"Skill Name 5", "Skill List 5"}, {"Skill Name 6", "Skill List 6"}, {"Skill Name 7", "Skill List 7"}, {"Skill Name 8", "Skill List 8"}, {"Skill Name 9", "Skill List 9"}, {"Skill Name 10", "Skill List 10"}, {"Skill Name 11", "Skill List 11"}, {"Skill Name 12", "Skill List 12"}};
                titleBundle.putSerializable("uniSublevelTitleData", uniSublevelTitleData);
                Fragment universitySublevelFragment = new UniversitySublevelFragment();
                universitySublevelFragment.setArguments(titleBundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
