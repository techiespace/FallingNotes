package com.techiespace.projects.fallingnotes;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.techiespace.projects.fallingnotes.course.fragments.UniversityFragment;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    CardView universityCardView;
    CardView chooseMidiCardView;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        universityCardView = rootView.findViewById(R.id.cardViewCSUni);
        chooseMidiCardView = rootView.findViewById(R.id.cardViewChooseMidi);
        universityCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment universityFragment = new UniversityFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.screen_area, universityFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        chooseMidiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/midi");
                startActivityForResult(intent, 7);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (requestCode) {

            case 7:

                if (resultCode == RESULT_OK) {

                    String PathHolder = data.getData().getPath();
                    Toast.makeText(getContext(), PathHolder, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), PracticeActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, PathHolder);
                    startActivity(intent);

                }
                break;

        }
    }

}
