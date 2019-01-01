/*
package com.techiespace.projects.fallingnotes;

import android.app.Activity;
import android.widget.TextView;

import com.techiespace.projects.fallingnotes.pianoHelpers.PianoKey;
import com.techiespace.projects.fallingnotes.pianoHelpers.PianoView;

import java.util.HashMap;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;

public class AudioProcessor extends Activity {
    AudioDispatcher dispatcher;
    TextView noteText;
    PianoView pianoView;

    String[] input = {"C4", "D4", "E4", "F4", "G4", "A4", "B4"};
    int inputIndex = 0;
    int countNoteOccurance = 0;
    int[] result = new int[100000];
    double pitchFreqArr[] = {4186.01, 3951.07, 3729.31, 3520, 3322.44, 3135.96, 2959.96, 2793.83, 2637.02, 2489.02,
            2349.32, 2217.46, 2093, 1975.53, 1864.66, 1760, 1661.22, 1567.98, 1479.98, 1396.91, 1318.51, 1244.51, 1174.66,
            1108.73, 1046.5, 987.767, 932.328, 880, 830.609, 783.991, 739.989, 698.456, 659.255, 622.254, 587.33, 554.365,
            523.251, 493.883, 466.164, 440, 415.305, 391.995, 369.994, 349.228, 329.628, 311.127, 293.665, 277.183, 261.626,
            246.942, 233.082, 220, 207.652, 195.998, 184.997, 174.614, 164.814, 155.563, 146.832, 138.591, 130.813, 123.471,
            116.541, 110, 103.826, 97.9989, 92.4986, 87.3071, 82.4069, 77.7817, 73.4162, 69.2957, 65.4064, 61.7354, 58.2705,
            55, 51.913, 48.9995, 46.2493, 43.6536, 41.2035, 38.8909, 36.7081, 34.6479, 32.7032, 30.8677, 29.1353, 27.5};
    double limitArr[][] = {{26.6824, 28.3176}, {28.2691, 29.9529}, {29.95, 31.7339}, {31.7309, 33.621},    //A0
            {33.6178, 35.6202}, {35.6167, 37.7382}, {37.7346, 39.9823}, {39.9784, 42.3598}, {42.3558, 44.8786}, {44.8742, 47.5471},    //C#1
            {47.5428, 50.3746}, {50.3695, 53.3697}, {53.3647, 56.5435}, {56.538, 59.9058}, {59.8999, 63.4678}, {63.4618, 67.2419},     //G1
            {67.2355, 71.2403}, {71.2334, 75.4764}, {75.4691, 79.9644}, {79.9568, 84.7195}, {84.7113, 89.7572}, {89.7484, 95.0943},    //C#2
            {95.0854, 100.749}, {100.739, 106.74}, {106.729, 113.087}, {113.076, 119.812}, {119.8, 126.936}, {126.924, 134.484},       //G2
            {134.471, 142.48}, {142.466, 150.952}, {150.938, 159.928}, {159.914, 169.439}, {169.423, 179.514}, {179.496, 190.188},     //C#3
            {190.171, 201.499}, {201.478, 213.479}, {213.459, 226.174}, {226.152, 239.623}, {239.6, 253.872}, {253.848, 268.968},      //G3
            {268.942, 284.962}, {284.934, 301.906}, {301.877, 319.858}, {319.828, 338.878}, {338.845, 359.028}, {358.993, 380.377},    //C#4
            {380.34, 402.995}, {402.957, 426.96}, {426.918, 452.348}, {452.305, 479.246}, {479.199, 507.742}, {507.694, 537.935},      //G4
            {537.882, 569.922}, {569.868, 603.812}, {603.754, 639.716}, {639.655, 677.755}, {677.689, 718.057}, {717.988, 760.755},    //C#5
            {760.682, 805.992}, {805.914, 853.918}, {853.836, 904.695}, {904.609, 958.492}, {958.401, 1015.49}, {1015.39, 1075.87},    //G5
            {1075.76, 1139.84}, {1139.74, 1207.62}, {1207.51, 1279.44}, {1279.31, 1355.51}, {1355.38, 1436.11}, {1435.98, 1521.51},    //C#6
            {1521.36, 1611.98}, {1611.83, 1707.84}, {1707.67, 1809.39}, {1809.23, 1916.99}, {1916.8, 2030.97}, {2030.77, 2151.73},     //G6
            {2151.53, 2279.69}, {2279.47, 2415.25}, {2415.02, 2558.87}, {2558.61, 2711.02}, {2710.77, 2872.24}, {2871.96, 3043.02},    //C#7
            {3042.72, 3223.96}, {3223.66, 3415.68}, {3415.34, 3618.78}, {3618.43, 3833.97}, {3833.6, 4061.95}, {4068.54, 4303.48}};    //G7


    public AudioProcessor(TextView noteText, PianoView pianoView) {

        this.noteText = noteText;
        this.pianoView = pianoView;
        dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 512);

        HashMap<Integer, String> map = createHashMap();
        String input = "C4";
        String[] output = {};
        //String[] input = {"C"};

        PitchDetectionHandler pdh = (res, e) -> {
            final float pitchInHz = res.getPitch();
            runOnUiThread(() -> processPitch(pitchInHz, map));
        };
        be.tarsos.dsp.AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(pitchProcessor);

        Thread audioThread = new Thread(dispatcher, "Audio Thread");
        audioThread.start();
    }

    HashMap<Integer, String> createHashMap() {
        //HashMap
        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "A0");
        map.put(1, "A#0");
        map.put(2, "B0");
        map.put(3, "C1");
        map.put(4, "C#1");
        map.put(5, "D1");
        map.put(6, "D#1");
        map.put(7, "E1");
        map.put(8, "F1");
        map.put(9, "F#1");
        map.put(10, "G1");
        map.put(11, "G#1");
        map.put(12, "A1");
        map.put(13, "A#1");
        map.put(14, "B1");
        map.put(15, "C2");
        map.put(16, "C#2");
        map.put(17, "D2");
        map.put(18, "D#2");
        map.put(19, "E2");
        map.put(20, "F2");
        map.put(21, "F#2");
        map.put(22, "G2");
        map.put(23, "G#2");
        map.put(24, "A2");
        map.put(25, "A#2");
        map.put(26, "B2");
        map.put(27, "C3");
        map.put(28, "C#3");
        map.put(29, "D3");
        map.put(30, "D#3");
        map.put(31, "E3");
        map.put(32, "F3");
        map.put(33, "F#3");
        map.put(34, "G3");
        map.put(35, "G#3");
        map.put(36, "A3");
        map.put(37, "A#3");
        map.put(38, "B3");
        map.put(39, "C4");
        map.put(40, "C#4");
        map.put(41, "D4");
        map.put(42, "D#4");
        map.put(43, "E4");
        map.put(44, "F4");
        map.put(45, "F#4");
        map.put(46, "G4");
        map.put(47, "G#4");
        map.put(48, "A4");
        map.put(49, "A#4");
        map.put(50, "B4");
        map.put(51, "C5");
        map.put(52, "C#5");
        map.put(53, "D5");
        map.put(54, "D#5");
        map.put(55, "E5");
        map.put(56, "F5");
        map.put(57, "F#5");
        map.put(58, "G5");
        map.put(59, "G#5");
        map.put(60, "A5");
        map.put(61, "A#5");
        map.put(62, "B5");
        map.put(63, "C6");
        map.put(64, "C#6");
        map.put(65, "D6");
        map.put(66, "D#6");
        map.put(67, "E6");
        map.put(68, "F6");
        map.put(69, "F#6");
        map.put(70, "G6");
        map.put(71, "G#6");
        map.put(72, "A6");
        map.put(73, "A#6");
        map.put(74, "B6");
        map.put(75, "C7");
        map.put(76, "C#7");
        map.put(77, "D7");
        map.put(78, "D#7");
        map.put(79, "E7");
        map.put(80, "F7");
        map.put(81, "F#7");
        map.put(82, "G7");
        map.put(83, "G#7");
        map.put(84, "A7");
        map.put(85, "A#7");
        map.put(86, "B7");
        map.put(87, "C8");
        return map;
    }

    public void processPitch(float pitchInHz, HashMap<Integer, String> map) {

        PianoKey keyToPlay = null;

        if (map.get(getNote(pitchInHz, 0, 87)) != null) {
            String detectedNote = map.get(getNote(pitchInHz, 0, 87)); //assuming 88 notes


            if (detectedNote == input[inputIndex])
                countNoteOccurance++;
            if (countNoteOccurance >= 2) {
                keyToPlay = findPianoKey(input[inputIndex]);

                if (keyToPlay != null)
                    pianoView.autoScroll(keyToPlay);

                keyToPlay.getKeyDrawable().setState(new int[]{android.R.attr.state_pressed});
                pianoView.invalidate();
//                keyToPlay.getKeyDrawable().setState(new int[]{-android.R.attr.state_pressed});
//                pianoView.invalidate();
                countNoteOccurance = 0;
                if (inputIndex < input.length - 1) {
                    inputIndex++;
                    noteText.setText(input[inputIndex]);
                } else {
                    noteText.setText("Baas");
                }
            }
        }
    }

    private PianoKey findPianoKey(String input) {
        */
/*ArrayList<com.techiespace.projects.fallingnotes.PianoKey[]> pianoKeys;
 *//*
 */
/*if(input.charAt(1)=='#')//The input in black key
        {
            pianoKeys = pianoView.getBlackStaticKeys();
        }
       //The input is a white piano key
        else {
            pianoKeys = pianoView.getWhiteStaticKeys();
        }*//*
 */
/*

        for (com.techiespace.projects.fallingnotes.PianoKey[] pianokey : pianoKeys) {
            for (int i = 0; i < pianokey.length; i++) {
                if (pianokey[i].getLetterName().equals(input)) {
                    return pianokey[i];
                }
            }
        }*//*

        return null;
    }

    public int getNote(float freq, int low, int high) {
        int middle = (low + high) / 2;
        if (freq <= 26.6825 || freq >= 4303.48)
            return -1;
        if (limitArr[middle][1] >= freq && limitArr[middle][0] <= freq)
            return middle;
        if (limitArr[middle][0] > freq)
            return getNote(freq, low, middle);
        if (limitArr[middle][1] < freq)
            return getNote(freq, middle + 1, high);
        return -1;
    }
}
*/
