package com.techiespace.projects.fallingnotes.fragments;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.techiespace.projects.fallingnotes.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocalDeviceMidiFragment extends Fragment {

    private ArrayList<String> allSampleData;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public LocalDeviceMidiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_device_midi_list, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.deviceMidiListRecyclerView);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        allSampleData = new ArrayList<>();
        getDeviceData();
        RecyclerView.Adapter mAdapter = new MidiListAdapter(allSampleData, getContext(), true); //true indicates local device
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void getDeviceData() {
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
                    allSampleData.add(Path);
                    System.out.println("Path is: " + Path + " " + fileName);
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

    }
}
