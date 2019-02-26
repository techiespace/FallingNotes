package com.techiespace.projects.fallingnotes.Database;

import android.content.Context;

public class databaseHandler {
    private AppDatabase mDb;

    public databaseHandler(Context context)
    {
        mDb = AppDatabase.getInstance(context);

    }


    public void loadDatabase()
    {

        final Level[] level = new Level[12];
        level[0] = new Level(0,"Hello");
        level[1] = new Level(1,"Basics");
        level[2] = new Level(2,"Melody");
        level[3] = new Level(3,"Practice");
        level[4] = new Level(4,"Theory");
        level[5] = new Level(5,"Melody 2");
        level[6] = new Level(6,"Melody 3");
        level[7] = new Level(7,"Techniques");
        level[8] = new Level(8,"Arpeggio");
        level[9] = new Level(9,"Runs");
        level[10] = new Level(10,"Improve");
        level[11] = new Level(11,"pro");
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // COMPLETED (6) Move the logic into the run method and
                // Extract the list of tasks to a final variable
                mDb.levelDao().deleteALL();

                for(int i=0;i<level.length;i++)
                mDb.levelDao().insertLevel(level[i]);

            }
        });
    }
}
