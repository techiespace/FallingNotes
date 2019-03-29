package com.techiespace.projects.fallingnotes;


import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    int FILE_SELECT_CODE = 7;
    int FILE_SIZE_LIMIT = 20000000;
    String filename;
    int filesize;



    public DashboardFragment() {
        // Required empty public constructor

        mDb = AppDatabase.getInstance(getContext());
        final CountDownLatch latch = new CountDownLatch(1);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                totalSkills = mDb.skillDao().getTotalSkillNo();
                completedSkills = mDb.skillDao().getCompletedSkillNo();
                System.out.println("Skills "+totalSkills);
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
                    intent.setType("*/*");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    //intent.putExtra("browseCoa", itemToBrowse);
                    //Intent chooser = Intent.createChooser(intent, "Select a File to Upload");
                    try {
                        //startActivityForResult(chooser, FILE_SELECT_CODE);
                        startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"),FILE_SELECT_CODE);
                    } catch (Exception ex) {
                        System.out.println("browseClick :"+ex);//android.content.ActivityNotFoundException ex
                    }



                }
        });

        progressBar.setProgress(completedSkills/totalSkills);
        percentagetv.setText((int)(completedSkills*100/totalSkills)+"%");

        return rootView;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri uri = data.getData();

                    if (filesize >= FILE_SIZE_LIMIT) {
                        Toast.makeText(getContext(),"The selected file is too large. Selet a new file with size less than 2mb",Toast.LENGTH_LONG).show();
                    } else {
                        String mimeType = getContext().getContentResolver().getType(uri);
                        if (mimeType == null) {
                            String path = getPath(getContext(), uri);
                            if (path == null) {
                                filename = FilenameUtils.getName(uri.toString());
                            } else {
                                File file = new File(path);
                                filename = file.getName();
                            }
                        } else {
                            Uri returnUri = data.getData();
                            Cursor returnCursor = getContext().getContentResolver().query(returnUri, null, null, null, null);
                            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                            int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                            returnCursor.moveToFirst();
                            filename = returnCursor.getString(nameIndex);
                            String size = Long.toString(returnCursor.getLong(sizeIndex));
                        }
                        File fileSave = getContext().getExternalFilesDir(null);
                        String sourcePath = getContext().getExternalFilesDir(null).toString();
                        try {
                            copyFileStream(new File(sourcePath + "/" + filename), uri,getContext());
                           // Toast.makeText(getContext(),"src"+sourcePath , Toast.LENGTH_SHORT).show();
                         //   System.out.println("src"+sourcePath);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void copyFileStream(File dest, Uri uri, Context context)
            throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = context.getContentResolver().openInputStream(uri);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
            os.close();
        }
    }
    public static String getPath(Context context, Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else
            if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = { column };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

}
