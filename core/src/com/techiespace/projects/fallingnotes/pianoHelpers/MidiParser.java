package com.techiespace.projects.fallingnotes.pianoHelpers;
/**
 * Print MIDI note events to stdout
 * <p>
 * January 2010, Michael Massberg - michael (at) massberg (dot) org
 */

import com.badlogic.gdx.Gdx;
import com.techiespace.projects.fallingnotes.Note;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MidiParser {
    Note[] notes;
    ArrayList<Note> noteArrayList = new ArrayList<Note>();

    public static int readFixedLengthCode(InputStream inputStream, int n) throws IOException {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp = (temp << 8) | inputStream.read();
        }
        return temp;
    }

    public static int readVariableLengthCode(InputStream inputStream) throws IOException {
        int b = 0, temp = 0;
        do {
            b = inputStream.read();
            temp = (temp << 7) | (b & 0x7F);
        } while ((b & 0x80) != 0);
        return temp;
    }

    public static int formatTime(double offset) {
        int millis = (int) (offset * 1000.0 + 0.5);
        return millis;
    }

    public Note[] parse(String file) {

        try {

            int track = -1;
            double bpm = -120.0;
            InputStream inputStream = Gdx.files.internal(file).read();

            int chunkMagic = readFixedLengthCode(inputStream, 4);
            if (chunkMagic != 0x4D546864) {
                throw new IOException("cannot find chunk magic");
            }

            inputStream.skip(4); // Chunk size
            inputStream.skip(2); // Format
            int numTracks = readFixedLengthCode(inputStream, 2);
            int timeDiv = readFixedLengthCode(inputStream, 2);

            double ticksPerFrameOrQuarter = 1.0;
            double framesOrQuartersPerSecond = Math.abs(bpm) / 60.0;

            if ((timeDiv & 0x8000) != 0) {
                int fps = ((timeDiv & 0x7F00) >> 8);
                framesOrQuartersPerSecond = fps;
                if (fps == 29) {
                    framesOrQuartersPerSecond = 29.97;
                }
                ticksPerFrameOrQuarter = (timeDiv & 0xFF);
            } else {
                ticksPerFrameOrQuarter = (timeDiv & 0x7FFF);
            }

            for (int i = 0; i < numTracks; i++) {
                double[] onsets = new double[128];
                int[] velocities = new int[128];
                for (int j = 0; j < 128; j++) {
                    onsets[j] = -1.0;
                }

                double accTime = 0;

                int trackMagic = readFixedLengthCode(inputStream, 4);
                if (trackMagic != 0x4D54726B) {
                    throw new IOException("cannot find track magic");
                }

                inputStream.skip(4); // Track size

                int eventType = 0;
                while (true) {
                    int deltaTime = readVariableLengthCode(inputStream);
                    accTime += deltaTime / (framesOrQuartersPerSecond * ticksPerFrameOrQuarter);

                    int param1 = inputStream.read();

                    if ((param1 & 0x80) != 0) {
                        eventType = param1;

                        if (eventType == 0xFF) {
                            // Meta event
                            int type = inputStream.read(); // Type
                            int len = readVariableLengthCode(inputStream);

                            if (type == 0x2F) {
                                // End of track
                                break;
                            } else if (type == 0x51) {
                                // Tempo
                                int tempo = readFixedLengthCode(inputStream, 3);

                                if (bpm < 0.0) {
                                    framesOrQuartersPerSecond = 10.0e5 / ((double) tempo);
                                }
                            } else if (type == 0x54) {
                                inputStream.skip(len);
                                throw new IOException("SMTPE time offset is not supported");
                            } else {
                                inputStream.skip(len); // Data
                            }
                        } else if (eventType == 0xF0 || eventType == 0xF7) {
                            // Sysex event
                            int len = readVariableLengthCode(inputStream);
                            inputStream.skip(len); // Data
                        } else {
                            // Note/Controler event
                            param1 = inputStream.read();
                        }
                    }

                    int type = (eventType >> 4) & 0xF;
                    int channel = eventType & 0xF;

                    if (type != 0xF) {
                        // Note/Controler event
                        int param2 = 0;
                        if (type != 0xC && type != 0xD) {
                            param2 = inputStream.read();
                        }

                        if (type == 0x9 && param2 != 0) {
                            // Note onset
                            onsets[param1] = accTime;
                            velocities[param1] = param2;
                        } else if (type == 0x9 || type == 0x8) {
                            // Note offset
                            if (onsets[param1] >= 0.0 && (i == track || track < 0)) {
                                //copy to Note object
                                Note note = new Note(param1, formatTime(onsets[param1]), formatTime(accTime), velocities[param1]);
                                noteArrayList.add(note);
                                //TODO: Write to file
                            }
                            onsets[param1] = -1;
                        }
                    }
                }
            }

            inputStream.close();
            notes = noteArrayList.toArray(new Note[noteArrayList.size()]);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
        return notes;
    }
}