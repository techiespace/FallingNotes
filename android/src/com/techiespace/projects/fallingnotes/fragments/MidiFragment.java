package com.techiespace.projects.fallingnotes.fragments;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.RecyclerViewDataAdapter;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SectionDataModel;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SingleItemModel;

import java.io.IOException;
import java.util.ArrayList;

public class MidiFragment  extends  Fragment{
    private ArrayList<SectionDataModel> allSampleData;

        public MidiFragment() {
            // Required empty public constructor
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            final View rootView = inflater.inflate(R.layout.fragment_scale, container, false); //the YT tutorial passed 2nd arg as null and no third arg.

            allSampleData = new ArrayList<>();

            createDummyData();

            RecyclerView recyclerView = rootView.findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);
            RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData, getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        /*
        Button startActivityButton = rootView.findViewById(R.id.start_activity_button);

        startActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start your activity here
                Intent i = new Intent(rootView.getContext(), PracticeActivity.class);
                startActivity(i);
            }
        });*/
            return rootView;
        }

        private void createDummyData() {

            SectionDataModel dm = new SectionDataModel();
            dm.setHeaderTitle("Available midi");
            ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();
            AssetManager assetManager = getActivity().getAssets();
            try {
                Log.e("ndskjfds","hsjkdv");
                String[] files = assetManager.list("midi");

                for(int i=0; i<files.length; i++){
                    singleItemModels.add(new SingleItemModel(files[i], files[i]));

                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


            dm.setAllItemInSection(singleItemModels);
            allSampleData.add(dm);

        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }




}
