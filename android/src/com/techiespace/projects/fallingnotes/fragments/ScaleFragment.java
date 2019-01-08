package com.techiespace.projects.fallingnotes.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.RecyclerViewDataAdapter;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SectionDataModel;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SingleItemModel;

import java.util.ArrayList;

//Code taken from - http://khmertechtrain.tk/index.php/2017/10/03/create-a-vertical-scroll-and-horizontal-scroll-app-like-google-play-store/
/**
 * A simple {@link Fragment} subclass.
 */
public class ScaleFragment extends Fragment {

    private ArrayList<SectionDataModel> allSampleData;

    public ScaleFragment() {
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
        dm.setHeaderTitle("Major Scale");
        ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();
        singleItemModels.add(new SingleItemModel("C", "C"));
        singleItemModels.add(new SingleItemModel("C#", "C#"));
        singleItemModels.add(new SingleItemModel("D", "D"));
        singleItemModels.add(new SingleItemModel("D#", "D#"));
        singleItemModels.add(new SingleItemModel("E", "E"));
        singleItemModels.add(new SingleItemModel("F", "F"));
        singleItemModels.add(new SingleItemModel("F#", "F#"));
        singleItemModels.add(new SingleItemModel("G", "G"));
        singleItemModels.add(new SingleItemModel("G#", "G#"));
        singleItemModels.add(new SingleItemModel("A", "A"));
        singleItemModels.add(new SingleItemModel("A#", "A#"));
        singleItemModels.add(new SingleItemModel("B", "B"));

        dm.setAllItemInSection(singleItemModels);
        allSampleData.add(dm);

        dm = new SectionDataModel();
        dm.setHeaderTitle("Minor Scale");
        singleItemModels = new ArrayList<>();
        singleItemModels.add(new SingleItemModel("C", "C"));
        singleItemModels.add(new SingleItemModel("C#", "C#"));
        singleItemModels.add(new SingleItemModel("D", "D"));
        singleItemModels.add(new SingleItemModel("D#", "D#"));
        singleItemModels.add(new SingleItemModel("E", "E"));
        singleItemModels.add(new SingleItemModel("F", "F"));
        singleItemModels.add(new SingleItemModel("F#", "F#"));
        singleItemModels.add(new SingleItemModel("G", "G"));
        singleItemModels.add(new SingleItemModel("G#", "G#"));
        singleItemModels.add(new SingleItemModel("A", "A"));
        singleItemModels.add(new SingleItemModel("A#", "A#"));
        singleItemModels.add(new SingleItemModel("B", "B"));

        dm.setAllItemInSection(singleItemModels);
        allSampleData.add(dm);

        dm = new SectionDataModel();
        dm.setHeaderTitle("Blues Scale");
        singleItemModels = new ArrayList<>();
        singleItemModels.add(new SingleItemModel("C", "C"));
        singleItemModels.add(new SingleItemModel("C#", "C#"));
        singleItemModels.add(new SingleItemModel("D", "D"));
        singleItemModels.add(new SingleItemModel("D#", "D#"));
        singleItemModels.add(new SingleItemModel("E", "E"));
        singleItemModels.add(new SingleItemModel("F", "F"));
        singleItemModels.add(new SingleItemModel("F#", "F#"));
        singleItemModels.add(new SingleItemModel("G", "G"));
        singleItemModels.add(new SingleItemModel("G#", "G#"));
        singleItemModels.add(new SingleItemModel("A", "A"));
        singleItemModels.add(new SingleItemModel("A#", "A#"));
        singleItemModels.add(new SingleItemModel("B", "B"));

        dm.setAllItemInSection(singleItemModels);
        allSampleData.add(dm);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
