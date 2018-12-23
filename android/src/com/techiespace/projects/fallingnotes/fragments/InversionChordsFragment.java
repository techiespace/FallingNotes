package com.techiespace.projects.fallingnotes.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InversionChordsFragment extends Fragment {


    public InversionChordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inversion_chords, container, false);
    }

}
