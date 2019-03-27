package com.techiespace.projects.fallingnotes.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiespace.projects.fallingnotes.Database.AppDatabase;
import com.techiespace.projects.fallingnotes.Database.AppExecutors;
import com.techiespace.projects.fallingnotes.Database.Level;
import com.techiespace.projects.fallingnotes.Database.Scale;
import com.techiespace.projects.fallingnotes.R;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.RecyclerViewDataAdapter;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SectionDataModel;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SingleItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScaleFragment extends Fragment {

    private ArrayList<SectionDataModel> allSampleData;
    private AppDatabase mDb;
    List<Scale> scales  = new ArrayList<>();

    public ScaleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_scale, container, false); //the YT tutorial passed 2nd arg as null and no third arg.

        allSampleData = new ArrayList<>();
        mDb = AppDatabase.getInstance(getContext());

        createDummyData();

        RecyclerView recyclerView = rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(allSampleData, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
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

        final CountDownLatch latch = new CountDownLatch(1);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                scales = mDb.scaleDao().getScalesByType("Major Scale");
                latch.countDown();


            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("Major Scale");
        ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();


        for(Scale scale : scales) {
            singleItemModels.add(new SingleItemModel(scale.getScale_name(), scale.getMidi_name(), scale.getInstructions()));
            System.out.println(scale.getScale_name());
        }

        dm.setAllItemInSection(singleItemModels);
        allSampleData.add(dm);

        dm = new SectionDataModel();
        dm.setHeaderTitle("Minor Scale");


        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                scales = mDb.scaleDao().getScalesByType("Minor Scale");
                latch.countDown();


            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }




        singleItemModels = new ArrayList<>();
        for(Scale scale : scales) {
            singleItemModels.add(new SingleItemModel(scale.getScale_name(), scale.getMidi_name(), scale.getInstructions()));
            System.out.println(scale.getScale_name());
        }

        dm.setAllItemInSection(singleItemModels);
        allSampleData.add(dm);

        dm = new SectionDataModel();
        dm.setHeaderTitle("Blues Scale");

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                scales = mDb.scaleDao().getScalesByType("Blues Scale");
                latch.countDown();


            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        singleItemModels = new ArrayList<>();
        for(Scale scale : scales) {
            singleItemModels.add(new SingleItemModel(scale.getScale_name(), scale.getMidi_name(), scale.getInstructions()));
            System.out.println(scale.getScale_name());
        }

        dm.setAllItemInSection(singleItemModels);
        allSampleData.add(dm);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
