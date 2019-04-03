package com.techiespace.projects.fallingnotes.Database;

import android.content.Context;

import com.techiespace.projects.fallingnotes.dbInterface;

import java.util.concurrent.CountDownLatch;

public class databaseHandler implements dbInterface {
    private AppDatabase mDb;
    private static databaseHandler dbHandler;
    float score;

    private databaseHandler(Context context)
    {
        mDb = AppDatabase.getInstance(context);

    }

    public static databaseHandler getInstance(Context context)
    {
        if(dbHandler==null)
        {
            dbHandler = new databaseHandler(context);

        }

            return dbHandler;
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
                        new Skill(1, "Getting to know the keyboard", "Note Names", 1, "1_1_C.mid", "• Middle C also called C4 is the note in the center of the keybord.\n• This key will act as a refrence key to identify all keys on the piano."),
                        new Skill(2, "More Notes", "Note Names", 1, "AqcnR-xEYdc", "• A is the key just before B and E is the key to the right of two black notes."),
                        new Skill(3, "Sharps n Flats", "Note Names", 1, "1_3_Sharps.mid", "• The black keys are called sharps and flats.\n• A note to the right of a white key is sharp and a note to left is flat.\n• A note can have two names. e.g. A# = Bb"),
                        new Skill(4, "Finger Position", "Place your hand on the keyboard right", 1, "1_4_Saints Go Marching In.mid", "• Place your thumb on C, your index finder on D and so on... One finger on each note.\n"),
                        new Skill(5, "Finger Position", "Place your hand on the keyboard right", 1, "1_5_ItsyBitsySpider.mid", "• Jump between hand positions as needed."),
                        new Skill(6, "Posture", "Posture", 1, "LE_f3N6gOSg", "• Piano Posture Checklist\n• Sit upright.\n• Your feet should stay flat on the ground\n• Keep forearm parallel to the ground.\n• Curl your finges as if grabbing a ball."),
                        new Skill(7, "Intervals", "Whole step, Half Step", 2, "1_7_Intervals.mid", "• Two notes without any key between them are said to be half step apart\n• Two notes with exactly one key(note) between them are said to be whole step apart."),
                        new Skill(8, "Key Signature", "Key Signature, Accidentals", 2, "7rwd7i6Ls9M", "• Key Signature of a musical piece is the set of notes used to play that piece.\n• Key signature, in musical notation, the arrangement of sharp or flat signs on particular lines and spaces of a musical staff to indicate that the corresponding notes, in every octave, are to be consistently raised (by sharps) or lowered (by flats) from their natural pitches."),
                        new Skill(9, "Major Scale - C", "Forming Major Scales", 2, "2_9_CScale.mid", "• Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(10, "Major Scale - G", "Forming Major Scales", 2, "2_10_GScale.mid", "• Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(11, "Major Scale - F", "Forming Major Scales", 2, "2_11_FScale.mid", "• Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(12, "Major Scale - D", "Forming Major Scales", 2, "2_12_DScale.mid", "• Major Scales are formed by the formula - WWHWWWH"),
                        new Skill(13, "Memorising scales", "Count number or sharps in a scale", 2, "N1lLDGCeh3k", "• Given the number of accidentals, their names can be found using the Mnemonic - Father Christmas Gave Daddy An Electric Blanket. e.g. G Major Scale has one accidental i.e. F, D Major Scale has 2 accidentals i.e. F and C."),
                        new Skill(14, "Minor Scale - A", "Forming Minor Scales", 2, "AZw3dQ53lEY", "• Minor Scales are formed by the formula - WHWWHWW"),
                        new Skill(15, "Minor Scale - E", "Relative Minor", 2, "xbEer87lfdU", "• The Relative Minor (scale) of a Major scale is located 3 half steps below the root note of the Major Scale."),
                        new Skill(16, "Simple Melody for Right hand", "Finger Co-ordination exercise", 3, "3_16_Forest Gump - Main Theme in C.mid", "• Forest Gump melody in C Major"),
                        new Skill(17, "Changing the key", "Keys", 3, "3_17_Forest Gump - Main Theme in D.mid", "• See how to same melody sounds in a different key."),
                        new Skill(18, "Changing the key II", "More Keys", 3, "3_18_Forest Gump - Main Theme in G.mid", "• See how to same melody sounds in a different key"),
                        new Skill(19, "Major Scale - A", "Forming Major Scales", 3, "3_19_AScale.mid", "• Learn the A Major Scale with 3 sharps"),
                        new Skill(20, "Major Scale - E", "Forming Major Scales", 3, "3_20_EScale.mid", "• Learn the E Major Scale with 4 sharps"),
                        new Skill(21, "Major Scale - B", "Forming Major Scales", 3, "3_21_BScale.mid", "• Learn the B Major Scale with 5 sharps."),
                        new Skill(22, "Major Scale - F", "Forming Major Scales", 3, "3_22_FmScale.mid", "• Learn the F Minor Scale with 3 sharps."),
                        new Skill(23, "Major Scale - C", "Forming Major Scales", 3, "3_23_C#mScale.mid", "• Learn the C Minor Scale with 4 sharps."),
                        new Skill(24, "Major Scale - G", "Forming Major Scales", 3, "3_24_G#mScale.mid", "• Learn the G Minor Scale with 5 sharps."),
                        new Skill(25, "Practice I", "Songs", 4, "4_25_MaryHadALittleLamb.mid", "• Mary Had A Little Lamb"),
                        new Skill(26, "Practice II", "Songs", 4, "4_26_HappyBirthdayToYou.mid", "• Happy Birthday to You"),
                        new Skill(27, "Practice III", "Songs", 4, "4_27_JingleBells.mid", "• Jingle Bells"),
                        new Skill(28, "Practice IV", "Songs", 4, "4_28_SilentNight.mid", "• Silent Night"),
                        new Skill(29, "Practice V", "Songs", 4, "4_29_Hedwig Theme - Harry Potter.mid", "• Harry Potter"),
                        new Skill(30, "Practice VI", "Songs", 4, "4_30_Greensleeves.mid", "• Greensleeves"),
                        new Skill(31, "Practice VII", "Songs", 4, "4_31_OdeToJoy.mid", "• Ode to Joy"),
                        new Skill(33, "Theory II", "Note Duration", 5, "LVOjKCztqTs", "• The length of time that a note is played is called its note duration, which is determined by the type of note.\n• The whole note  has the longest note duration in modern music.\n• The half note is half the duration of a whole note.\n• Two half notes occupy the same amount of time as a whole note.\n• The quarter note is a fourth of a whole note.\n• Two quarter notes equal a duration of a half note.\n• 4 Quarter notes occupy the same duration of a whole note.\n• Notes smaller in duration of a quarter note have flags."),
                        new Skill(34, "Theory III", "Note Duration", 5, "5_34_Note_Values_whole_half_quarter.mid", "• The half note is half the duration of a whole note.\n• Two half notes occupy the same amount of time as a whole note.\n• Play at 120bpm."),
                        new Skill(35, "Theory IV", "Modes", 5, "vpc4SwXf6cU", "• The C major scale corresponds to the white keys on a piano keyboard, beginning with the note C.\n• But you can use this same collection of notes to create seven more scales (called modes), simply by starting in a different place.\n• Here are the seven modes that can be derived from the pitches of the C major scale:\n• C Ionian (major) — CDEFGABC\n• D Dorian — DEFGABCD\n• E Phrygian — EFGABCDE\n• F Lydian — FGABCDEF\n• G Mixolydian — GABCDEFG\n• A Aeolian (minor) — ABCDEFGA\n• B Locrian — BCDEFGAB\n• The pitches and intervals are always in the same order, but the different starting positions give each mode a unique musical character.\n• You can think of the modes as being \"rotations\" of each other.")
/*                        new Skill(36, "Theory V", "Ionian Mode", 5, "", "Ionian"),
                        new Skill(37, "Theory VI", "Ionian Mode", 5, "", "Dorian"),
                        new Skill(38, "Theory VII", "Phrygian Mode", 5, "", "Phrygian")*/
                );


                //Adding Scales
                mDb.scaleDao().deleteALL();


                mDb.scaleDao().insertAllScales(
                        new Scale(1, "C", "inappmidi/scales/major/C.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(2, "C#", "inappmidi/scales/major/C#.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(3, "D", "inappmidi/scales/major/D.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(4, "D#", "inappmidi/scales/major/D#.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(5, "E", "inappmidi/scales/major/E.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(6, "F", "inappmidi/scales/major/F.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(7, "F#", "inappmidi/scales/major/F#.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(8, "G", "inappmidi/scales/major/G.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(9, "G#", "inappmidi/scales/major/G#.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(10, "A", "inappmidi/scales/major/A.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(11, "A#", "inappmidi/scales/major/A#.mid", "Major Scales", "1) Scale Instructions", true),
                        new Scale(12, "B", "inappmidi/scales/major/B.mid", "Major Scales", "1) Scale Instructions", true));


                mDb.scaleDao().insertAllScales(
                        new Scale(13, "C", "inappmidi/scales/minor/C.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(14, "C#", "inappmidi/scales/minor/C#.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(15, "D", "inappmidi/scales/minor/D.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(16, "D#", "inappmidi/scales/minor/D#.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(17, "E", "inappmidi/scales/minor/E.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(18, "F", "inappmidi/scales/minor/F.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(19, "F#", "inappmidi/scales/minor/F#.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(20, "G", "inappmidi/scales/minor/G.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(21, "G#", "inappmidi/scales/minor/G#.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(22, "A", "inappmidi/scales/minor/A.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(23, "A#", "inappmidi/scales/minor/A#.mid", "Minor Scales", "1) Scale Instructions", true),
                        new Scale(24, "B", "inappmidi/scales/minor/B.mid", "Minor Scales", "1) Scale Instructions", true));



                mDb.scaleDao().insertAllScales(
                        new Scale(25, "C", "inappmidi/scales/pentatonic/major/PMC.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(26, "C#", "inappmidi/scales/pentatonic/major/PMC#.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(27, "D", "inappmidi/scales/pentatonic/major/PMD.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(28, "D#", "inappmidi/scales/pentatonic/major/PMD#.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(29, "E", "inappmidi/scales/pentatonic/major/PME.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(30, "F", "inappmidi/scales/pentatonic/major/PMF.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(31, "F#", "inappmidi/scales/pentatonic/major/PMF#.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(32, "G", "inappmidi/scales/pentatonic/major/PMG.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(33, "G#", "inappmidi/scales/pentatonic/major/PMG#.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(34, "A", "inappmidi/scales/pentatonic/major/PMA.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(35, "A#", "inappmidi/scales/pentatonic/major/PMA#.mid", "Blues Major Scales", "1) Scale Instructions", true),
                        new Scale(36, "B", "inappmidi/scales/pentatonic/major/PMB.mid", "Blues Major Scales", "1) Scale Instructions", true));

                mDb.scaleDao().insertAllScales(
                        new Scale(37, "C", "inappmidi/scales/pentatonic/minor/PMC.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(38, "C#", "inappmidi/scales/pentatonic/minor/PMC#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(39, "D", "inappmidi/scales/pentatonic/minor/PMD.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(40, "D#", "inappmidi/scales/pentatonic/minor/PMD#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(41, "E", "inappmidi/scales/pentatonic/minor/PME.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(42, "F", "inappmidi/scales/pentatonic/minor/PMF.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(43, "F#", "inappmidi/scales/pentatonic/minor/PMF#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(44, "G", "inappmidi/scales/pentatonic/minor/PMG.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(45, "G#", "inappmidi/scales/pentatonic/minor/PMG#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(46, "A", "inappmidi/scales/pentatonic/minor/PMA.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(47, "A#", "inappmidi/scales/pentatonic/minor/PMA#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(48, "B", "inappmidi/scales/pentatonic/minor/PMB.mid", "Blues Minor Scales", "1) Scale Instructions", true));

                mDb.scaleDao().insertAllScales(
                        new Scale(49, "C", "inappmidi/scales/pentatonic/minor/PMC.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(50, "C#", "inappmidi/scales/pentatonic/minor/PMC#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(51, "D", "inappmidi/scales/pentatonic/minor/PMD.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(52, "D#", "inappmidi/scales/pentatonic/minor/PMD#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(53, "E", "inappmidi/scales/pentatonic/minor/PME.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(54, "F", "inappmidi/scales/pentatonic/minor/PMF.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(55, "F#", "inappmidi/scales/pentatonic/minor/PMF#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(56, "G", "inappmidi/scales/pentatonic/minor/PMG.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(57, "G#", "inappmidi/scales/pentatonic/minor/PMG#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(58, "A", "inappmidi/scales/pentatonic/minor/PMA.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(59, "A#", "inappmidi/scales/pentatonic/minor/PMA#.mid", "Blues Minor Scales", "1) Scale Instructions", true),
                        new Scale(60, "B", "inappmidi/scales/pentatonic/minor/PMB.mid", "Blues Minor Scales", "1) Scale Instructions", true));

                mDb.scaleDao().insertAllScales(
                        new Scale(61, "C", "inappmidi/chords/major/C.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(62, "C#", "inappmidi/chords/major/C#.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(63, "D", "inappmidi/chords/major/D.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(64, "D#", "inappmidi/chords/major/D#.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(65, "E", "inappmidi/chords/major/E.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(66, "F", "inappmidi/chords/major/F.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(67, "F#", "inappmidi/chords/major/F#.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(68, "G", "inappmidi/chords/major/G.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(69, "G#", "inappmidi/chords/major/G#.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(70, "A", "inappmidi/chords/major/A.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(71, "A#", "inappmidi/chords/major/A#.mid", "Major Chords", "1) Chord Instructions", false),
                        new Scale(72, "B", "inappmidi/chords/major/B.mid", "Major Chords", "1) Chord Instructions", false));

                mDb.scaleDao().insertAllScales(
                        new Scale(73, "C", "inappmidi/chords/minor/C.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(74, "C#", "inappmidi/chords/minor/C#.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(75, "D", "inappmidi/chords/minor/D.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(76, "D#", "inappmidi/chords/minor/D#.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(77, "E", "inappmidi/chords/minor/E.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(78, "F", "inappmidi/chords/minor/F.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(79, "F#", "inappmidi/chords/minor/F#.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(80, "G", "inappmidi/chords/minor/G.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(81, "G#", "inappmidi/chords/minor/G#.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(82, "A", "inappmidi/chords/minor/A.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(83, "A#", "inappmidi/chords/minor/A#.mid", "Minor Chords", "1) Chord Instructions", false),
                        new Scale(84, "B", "inappmidi/chords/minor/B.mid", "Minor Chords", "1) Chord Instructions", false));

                mDb.scaleDao().insertAllScales(
                        new Scale(85, "C", "inappmidi/chords/dim/C.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(86, "C#", "inappmidi/chords/dim/C#.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(87, "D", "inappmidi/chords/dim/D.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(88, "D#", "inappmidi/chords/dim/D#.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(89, "E", "inappmidi/chords/dim/E.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(90, "F", "inappmidi/chords/dim/F.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(91, "F#", "inappmidi/chords/dim/F#.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(92, "G", "inappmidi/chords/dim/G.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(93, "G#", "inappmidi/chords/dim/G#.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(94, "A", "inappmidi/chords/dim/A.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(95, "A#", "inappmidi/chords/dim/A#.mid", "Diminished Chords", "1) Chord Instructions", false),
                        new Scale(96, "B", "inappmidi/chords/dim/B.mid", "Diminished Chords", "1) Chord Instructions", false));
            }
        });
    }

    @Override
    public void submitScore(final String midiName, int totalNotes, int rightNotes) {

       final float score = rightNotes*100/totalNotes;

       AppExecutors.getInstance().diskIO().execute(new Runnable() {
           @Override
           public void run() {
               boolean completed = score > 85;
               String[] midiNameArr = midiName.split("/");
               mDb.skillDao().updateSkillInfo(midiNameArr[midiNameArr.length - 1], score, completed);
           }
       });
    }


    @Override
    public float getScoreByMidi(final String midiName) {


        final CountDownLatch latch = new CountDownLatch(1);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                score= mDb.skillDao().getScoreByMidi(midiName);
                latch.countDown();


            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return score;


    }
}
