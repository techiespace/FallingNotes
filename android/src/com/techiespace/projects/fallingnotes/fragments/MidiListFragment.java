package com.techiespace.projects.fallingnotes.fragments;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.R;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SectionDataModel;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SingleItemModel;

import java.io.IOException;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MidiListFragment extends Fragment {
    private ArrayList<String> allSampleData;
    private ArrayList<String> deviceSampleData;

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

        allSampleData = new ArrayList<>();
        getLocalData();
        // specify an adapter (see also next example)
//        String[][] uniSublevelTitleData = (String[][]) getArguments().getSerializable("uniSublevelTitleData");
        RecyclerView.Adapter mAdapter = new MidiListAdapter(allSampleData, getContext(), false);
        recyclerView.setAdapter(mAdapter);
        return rootView;
    }


    private void getLocalData() {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("Available inappmidi");
        ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();
        AssetManager assetManager = getActivity().getAssets();
        try {
            String[] files = assetManager.list("inappmidi");
            for (String file : files) {
                if (file.contains(".mid"))
                    allSampleData.add(file);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


//        dm.setAllItemInSection(singleItemModels);
//        allSampleData.add(dm);
    }

}
