package com.techiespace.projects.fallingnotes;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.techiespace.projects.fallingnotes.Database.AppDatabase;
import com.techiespace.projects.fallingnotes.Database.AppExecutors;
import com.techiespace.projects.fallingnotes.Database.Level;
import com.techiespace.projects.fallingnotes.course.fragments.UniversityFragment;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    CardView universityCardView;
    CardView chooseMidiCardView;
    ProgressBar progressBar;
    TextView percentagetv;
    private AppDatabase mDb;
    private int totalSkills;
    private int completedSkills;


    public DashboardFragment() {
        // Required empty public constructor

        mDb = AppDatabase.getInstance(getContext());
        final CountDownLatch latch = new CountDownLatch(1);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                totalSkills = mDb.skillDao().getTotalSkillNo();
                completedSkills = mDb.skillDao().getCompletedSkillNo();
                System.out.println("Skills "+completedSkills);
                latch.countDown();

            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        universityCardView = rootView.findViewById(R.id.cardViewCSUni);
        chooseMidiCardView = rootView.findViewById(R.id.cardViewChooseMidi);
        progressBar = rootView.findViewById(R.id.circle_progress_bar);
        percentagetv = rootView.findViewById(R.id.compliance_total_count);
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

        progressBar.setProgress(completedSkills/totalSkills);
        percentagetv.setText((int)(completedSkills*100/totalSkills)+"%");

        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub

        switch (requestCode) {

            case 7:

                if (resultCode == RESULT_OK) {

                    Uri u = data.getData();
                    String PathHolder = getRealPathFromURI(u);
                    Toast.makeText(getContext(), "Hi: " + Utils.getPath(getContext(), u), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(), PracticeActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, PathHolder);
                    startActivity(intent);

                }
                break;

        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContext().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);//MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
