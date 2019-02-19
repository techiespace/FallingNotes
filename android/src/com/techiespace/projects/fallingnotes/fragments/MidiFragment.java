package com.techiespace.projects.fallingnotes.fragments;

import android.content.ContentResolver;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.techiespace.projects.fallingnotes.R;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.RecyclerViewDataAdapter;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SectionDataModel;
import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SingleItemModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MidiFragment extends Fragment {
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

        return rootView;
    }

    private void createDummyData() {

        //
        //
        //
        // Code to access local files
        //
        //
        ///
        //
        HashMap<String, String> pdfFiles = new HashMap<String, String>();

        ContentResolver cr = getContext().getContentResolver();
        Uri uri = MediaStore.Files.getContentUri("external");

// every column, although that is huge waste, you probably need
// BaseColumns.DATA (the path) only.
        String[] projection = null;

// exclude media files, they would be here also.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_NONE;
        String[] selectionArgs = null; // there is no ? in selection so null here

        String sortOrder = null; // unordered
        Cursor allNonMediaFiles = cr.query(uri, projection, selection, selectionArgs, sortOrder);

        String selectionMimeType = MediaStore.Files.FileColumns.MIME_TYPE + "=?";
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension("mid");
        String[] selectionArgsPdf = new String[]{mimeType};
        Cursor allPdfFiles = cr.query(uri, projection, selectionMimeType, selectionArgsPdf, sortOrder);


        if (allPdfFiles != null) {
            // move cursor to first row
            if (allPdfFiles.moveToFirst()) {
                do {
                    // Get version from Cursor
                    String Path = allPdfFiles.getString(allPdfFiles.getColumnIndex(MediaStore.Files.FileColumns.DATA));
                    //  System.out.println(Arrays.toString(allPdfFiles.getColumnNames()));
                    String fileName = allPdfFiles.getString(allPdfFiles.getColumnIndex(MediaStore.Files.FileColumns.TITLE));
                    // add the bookName into the bookTitles ArrayList
                    pdfFiles.put(fileName, Path);
                    // move to next row
                    System.out.println(fileName);
                } while (allPdfFiles.moveToNext());
            }
        }
        allPdfFiles.close();


        //
        //
        //
        // Ends here
        //
        //
        //
        //


        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle("Available inappmidi");
        ArrayList<SingleItemModel> singleItemModels = new ArrayList<>();
        AssetManager assetManager = getActivity().getAssets();
        try {
            String[] files = assetManager.list("inappmidi");

            for (int i = 0; i < files.length; i++) {
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
