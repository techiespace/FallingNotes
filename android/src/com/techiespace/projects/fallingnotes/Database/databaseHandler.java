package com.techiespace.projects.fallingnotes.Database;

import android.content.Context;

import com.techiespace.projects.fallingnotes.fragments.nestedListUi.SingleItemModel;

import java.util.ArrayList;

public class databaseHandler {
    private AppDatabase mDb;

    public databaseHandler(Context context)
    {
        mDb = AppDatabase.getInstance(context);

    }


    public void loadDatabase()
    {

        final Level[] level = new Level[12];
        level[0] = new Level(1, "Hello");
        level[1] = new Level(2, "Basics");
        level[2] = new Level(3, "Melody");
        level[3] = new Level(4, "Practice");
        level[4] = new Level(5, "Theory");
        level[5] = new Level(6, "Melody 2");
        level[6] = new Level(7, "Melody 3");
        level[7] = new Level(8, "Techniques");
        level[8] = new Level(9, "Arpeggio");
        level[9] = new Level(10, "Runs");
        level[10] = new Level(11, "Improve");
        level[11] = new Level(12, "Pro");
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // COMPLETED (6) Move the logic into the run method and
                // Extract the list of tasks to a final variable
                mDb.levelDao().deleteALL();

                for(int i=0;i<level.length;i++)
                    mDb.levelDao().insertLevel(level[i]);

                mDb.skillDao().insertAllSkills(
                        new Skill(1, "Getting to know the keyboard", "Note Names", 1, "1_1_C.mid", "1. Middle C also called C4 is the note in the center of the keybord. This key will act as a refrence key to identify all keys on the piano"),
                        new Skill(2, "More Notes", "Note Names", 1, "", "1. A is the key just before B and E is the key to the right of two black notes"),
                        new Skill(3, "Sharps n Flats", "Note Names", 1, "1_3_Sharps.mid", "1. The black keys are called sharps and flats. 2. A note to the right of a white key is sharp and a note to left is flat. 3. A note can have two names. e.g. A# = Bb "),
                        new Skill(4, "Finger Position", "Place your hand on the keyboard right", 1, "", "1. One finger on each finger"),
                        new Skill(5, "Finger Position", "Place your hand on the keyboard right", 1, "1_5_ItsyBitsySpider.mid", "1. Jump between hand positions as needed."),
                        new Skill(6, "Posture", "Posture", 1, "", "1. Sit upright. 2. Your feet should stay flat on the ground 3. Keep forearm parallel to the ground. 4. Curl your finges as if grabbing a ball."),
                        new Skill(7, "Intervals", "Whole step, Half Step", 2, "1_7_Intervals.mid", "1. Two notes without any key between them are said to be half step apart 2. Two notes with exactly one key(note) between them are said to be whole step apart."),
                        new Skill(8, "", "Thumb under, Cross over", 2, "", ""),
                        new Skill(9, "Major Scale - C", "Forming Major Scales", 2, "2_9_CScale.mid", "Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(10, "Major Scale - G", "Forming Major Scales", 2, "2_10_GScale.mid", "Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(11, "Major Scale - F", "Forming Major Scales", 2, "2_11_FScale.mid", "Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(12, "Major Scale - D", "Forming Major Scales", 2, "2_12_DScale.mid", "Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(13, "Memorising scales", "Count number or sharps in a scale", 2, "", "Given the number of accidentals, their names can be found using the Mnemonic - Father Christmas Gave Daddy An Electric Blanket. e.g. G Major Scale has one accidental i.e. F, D Major Scale has 2 accidentals i.e. F and C."),
                        new Skill(14, "Minor Scale - A", "Forming Minor Scales", 2, "", "Minor Scales are formed by the formula - WHWWHWW"),
                        new Skill(15, "Minor Scale - E", "Relative Minor", 2, "", "The Relative Minor (scale) of a Major scale is located 3 half steps below the root note of the Major Scale"),
                        new Skill(16, "Simple Melody for Right hand", "Finger Co-ordination exercise", 3, "", "Forest Gump melody in C Major"),
                        new Skill(17, "Changing the key", "Keys", 3, "", "See how to same melody sounds in a different key"),
                        new Skill(18, "Changing the key II", "More Keys", 3, "", "See how to same melody sounds in a different key"),
                        new Skill(19, "Major Scale - A", "Forming Major Scales", 3, "3_19_AScale.mid", "Learn the A Major Scale with 3 sharps"),
                        new Skill(20, "Major Scale - E", "Forming Major Scales", 3, "3_20_EScale.mid", "Learn the E Major Scale with 4 sharps"),
                        new Skill(21, "Major Scale - B", "Forming Major Scales", 3, "", "Learn the A Major Scale with 5 sharps"),
                        new Skill(22, "Major Scale - F", "Forming Major Scales", 3, "", "Learn the F Minor Scale with 3 sharps"),
                        new Skill(23, "Major Scale - C", "Forming Major Scales", 3, "", "Learn the A Minor Scale with 4 sharps"),
                        new Skill(24, "Major Scale - G", "Forming Major Scales", 3, "", "Learn the A Minor Scale with 5 sharps"),
                        new Skill(25, "Practice I", "Songs", 4, "4_25_MaryHadALittleLamb.mid", "Mary Had A Little Lamb"),
                        new Skill(26, "Practice II", "Songs", 4, "4_26_HappyBirthdayToYou.mid", "Happy Birthday to You"),
                        new Skill(27, "Practice III", "Songs", 4, "4_27_JingleBells.mid", "Jingle Bells"),
                        new Skill(28, "Practice IV", "Songs", 4, "4_28_SilentNight.mid", "Silent Night"),
                        new Skill(29, "Practice V", "Songs", 4, "", "Harry Potter"),
                        new Skill(30, "Practice VI", "Songs", 4, "4_30_Greensleeves.mid", "Greensleeves"),
                        new Skill(31, "Practice VII", "Songs", 4, "4_31_OdeToJoy.mid", "Ode to Joy"),
                        new Skill(33, "Theory II", "Note Duration", 5, "", "The length of time that a note is played is called its note duration , which is determined by the type of note. The whole note  has the longest note duration in modern music."),
                        new Skill(34, "Theory III", "Note Duration", 5, "", "The half note is half the duration of a whole note. Two half notes occupy the same amount of time as a whole note"),
                        new Skill(35, "Theory IV", "Ionian Mode", 5, "", "The quarter note is a fourth of a whole note. Two quarter notes equal a duration of a half note. 4 Quarter notes occupy the same duration of a whole note. Notes smaller in duration of a quarter note have flags."),
                        new Skill(36, "Theory V", "Dorian Mode", 5, "", "Ionian"),
                        new Skill(37, "Theory VI", "Phrygian Mode", 5, "", "Dorian"),
                        new Skill(38, "Theory VII", "Placeholder", 5, "", "Phrygian"),
                        new Skill(39, "Theory VIII", "Placeholder", 5, "", "Instructions Placeholder"),
                        new Skill(40, "Melody", "Placeholder", 5, "", "Instructions Placeholder")
                );


                //Adding Scales
                mDb.scaleDao().deleteALL();


                mDb.scaleDao().insertAllScales(
                new Scale(1,"C", "inappmidi/C.mid","Major Scale","1) Scale Instructions"),
                new Scale(2,"C#", "inappmidi/scales/major/C#.mid","Major Scale","1) Scale Instructions"),
                new Scale(3,"D", "inappmidi/scales/major/D.mid","Major Scale","1) Scale Instructions"),
                new Scale(4,"D#", "inappmidi/scales/major/D#.mid","Major Scale","1) Scale Instructions"),
                new Scale(5,"E", "inappmidi/scales/major/E.mid","Major Scale","1) Scale Instructions"),
                new Scale(6,"F", "inappmidi/scales/major/F.mid","Major Scale","1) Scale Instructions"),
                new Scale(7,"F#", "inappmidi/scales/major/F#.mid","Major Scale","1) Scale Instructions"),
                new Scale(8,"G", "inappmidi/scales/major/G.mid","Major Scale","1) Scale Instructions"),
                new Scale(9,"G#", "inappmidi/scales/major/G#.mid","Major Scale","1) Scale Instructions"),
                new Scale(10,"A", "inappmidi/scales/major/A.mid","Major Scale","1) Scale Instructions"),
                new Scale(11,"A#", "inappmidi/scales/major/A#.mid","Major Scale","1) Scale Instructions"),
                new Scale(12,"B", "inappmidi/scales/major/B.mid","Major Scale","1) Scale Instructions"));



                mDb.scaleDao().insertAllScales(
                        new Scale(13,"C", "C","Minor Scale","1) Scale Instructions"),
                        new Scale(14,"C#", "C#","Minor Scale","1) Scale Instructions"),
                        new Scale(15,"D", "D","Minor Scale","1) Scale Instructions"),
                        new Scale(16,"D#", "D#","Minor Scale","1) Scale Instructions"),
                        new Scale(17,"E", "E","Minor Scale","1) Scale Instructions"),
                        new Scale(18,"F", "F","Minor Scale","1) Scale Instructions"),
                        new Scale(19,"F#", "F#","Minor Scale","1) Scale Instructions"),
                        new Scale(20,"G", "G","Minor Scale","1) Scale Instructions"),
                        new Scale(21,"G#", "G#","Minor Scale","1) Scale Instructions"),
                        new Scale(22,"A", "A","Minor Scale","1) Scale Instructions"),
                        new Scale(23,"A#", "A#","Minor Scale","1) Scale Instructions"),
                        new Scale(24,"B", "B","Minor Scale","1) Scale Instructions"));



                mDb.scaleDao().insertAllScales(
                        new Scale(25,"C", "C","Blues Scale","1) Scale Instructions"),
                        new Scale(26,"C#", "C#","Blues Scale","1) Scale Instructions"),
                        new Scale(27,"D", "D","Blues Scale","1) Scale Instructions"),
                        new Scale(28,"D#", "D#","Blues Scale","1) Scale Instructions"),
                        new Scale(29,"E", "E","Blues Scale","1) Scale Instructions"),
                        new Scale(30,"F", "F","Blues Scale","1) Scale Instructions"),
                        new Scale(31,"F#", "F#","Blues Scale","1) Scale Instructions"),
                        new Scale(32,"G", "G","Blues Scale","1) Scale Instructions"),
                        new Scale(33,"G#", "G#","Blues Scale","1) Scale Instructions"),
                        new Scale(34,"A", "A","Blues Scale","1) Scale Instructions"),
                        new Scale(35,"A#", "A#","Blues Scale","1) Scale Instructions"),
                        new Scale(36,"B", "B","Blues Scale","1) Scale Instructions"));


            }
        });
    }
}
