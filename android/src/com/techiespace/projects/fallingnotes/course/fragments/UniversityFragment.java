package com.techiespace.projects.fallingnotes.course.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        CardView basicsLevel = rootView.findViewById(R.id.basicsLevel);
        CardView melodyLevel = rootView.findViewById(R.id.melodyLevel);
        CardView practiceLevel = rootView.findViewById(R.id.practiceLevel);
        CardView theoryLevel = rootView.findViewById(R.id.theoryLevel);
        CardView melodyIILevel = rootView.findViewById(R.id.melodyIILevel);
        CardView melodyIIILevel = rootView.findViewById(R.id.melodyIIILevel);
        CardView techniqueLevel = rootView.findViewById(R.id.techniqueLevel);
        CardView arpeggioLevel = rootView.findViewById(R.id.arpeggioLevel);
        CardView runsLevel = rootView.findViewById(R.id.runsLevel);
        CardView improvLevel = rootView.findViewById(R.id.improvLevel);
        CardView proLevel = rootView.findViewById(R.id.proLevel);


        //
        helloLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 1);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        basicsLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 2);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        melodyLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 3);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        practiceLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 4);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        theoryLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 5);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        melodyIILevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 6);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        melodyIIILevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 7);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        techniqueLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 8);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        arpeggioLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 9);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        runsLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 10);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        improvLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 11);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        proLevel.setOnClickListener(view -> {
            Bundle levelBundle = new Bundle();
            levelBundle.putInt("level_id", 12);
            Fragment universitySublevelFragment = new UniversitySublevelFragment();
            universitySublevelFragment.setArguments(levelBundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.screen_area, universitySublevelFragment); // give your fragment container id in first parameter
            transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
            transaction.commit();
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
