package com.techiespace.projects.fallingnotes.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.techiespace.projects.fallingnotes.PracticeActivity;
import com.techiespace.projects.fallingnotes.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScaleFragment extends Fragment {


    public ScaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_scale, container, false); //the YT tutorial passed 2nd arg as null and no third arg.
        Button startActivityButton = rootView.findViewById(R.id.start_activity_button);

        startActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your activity here
                Intent i = new Intent(rootView.getContext(), PracticeActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
