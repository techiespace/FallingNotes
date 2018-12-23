package com.techiespace.projects.fallingnotes.Utils;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.util.SparseIntArray;

import com.techiespace.projects.fallingnotes.pianoHelpers.Piano;
import com.techiespace.projects.fallingnotes.pianoHelpers.PianoKey;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioUtils {

    private final static int MAX_STREAM = 11;
    private static AudioUtils instance = null;
    private ExecutorService service = Executors.newCachedThreadPool();
    private Handler handler;
    private AudioManager audioManager;
    private int minSoundId = -1;
    private int maxSoundId = -1;
    private SoundPool pool;
    private Context context;
    private int loadNum;

    private SparseIntArray whiteKeyMusics = new SparseIntArray();
    private SparseIntArray blackKeyMusics = new SparseIntArray();

    private boolean isLoadFinish = false;

    private boolean isLoading = false;


    private AudioUtils(Context context, int maxStream) {
        this.context = context;
        //  handler = new AudioStatusHandler(context.getMainLooper());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pool = new SoundPool.Builder().setMaxStreams(maxStream)
                    .setAudioAttributes(
                            new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build())
                    .build();
        } else {
            pool = new SoundPool(maxStream, AudioManager.STREAM_MUSIC, 1);
        }
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }


    public static AudioUtils getInstance(Context context) {
        return getInstance(context, MAX_STREAM);
    }

    public static AudioUtils getInstance(Context context,
                                         int maxStream) {
        if (instance == null || instance.pool == null) {
            synchronized (AudioUtils.class) {
                if (instance == null || instance.pool == null) {
                    instance = new AudioUtils(context, maxStream);
                }
            }
        }
        return instance;
    }


    public void playMusic(final PianoKey key) {
        if (key != null) {
            if (key.getType() != null) {
                service.execute(() -> {
                    switch (key.getType()) {
                        case BLACK:
                            playBlackKeyMusic(key.getGroup(), key.getPositionOfGroup());
                            break;
                        case WHITE:
                            playWhiteKeyMusic(key.getGroup(), key.getPositionOfGroup());
                            break;
                    }
                });

            }
        }
    }


    private void playWhiteKeyMusic(int group, int positionOfGroup) {
        int position;
        if (group == 0) {
            position = positionOfGroup;
        } else {
            position = (group - 1) * 7 + 2 + positionOfGroup;
        }
        play(whiteKeyMusics.get(position));
    }

    /**
     * 播放黑键音乐
     *
     * @param group           组数，从0开始
     * @param positionOfGroup 组内位置
     */
    private void playBlackKeyMusic(int group, int positionOfGroup) {
        int position;
        if (group == 0) {
            position = positionOfGroup;
        } else {
            position = (group - 1) * 5 + 1 + positionOfGroup;
        }
        play(blackKeyMusics.get(position));
    }

    private void play(int soundId) {
        float volume = 1;
        if (audioManager != null) {
            float actualVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            volume = actualVolume / maxVolume;
        }
        if (volume <= 0) {
            volume = 1f;
        }
        pool.play(soundId, volume, volume, 1, 0, 1f);
    }

    public void loadMusic(final Piano piano) throws Exception {
        if (pool == null) {
            throw new Exception("请初始化SoundPool");
        }
        if (piano != null) {
            if (!isLoading && !isLoadFinish) {
                isLoading = true;
                pool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
                    loadNum++;
                    if (loadNum == Piano.PIANO_NUMS) {
                        isLoadFinish = true;
                        //sendProgressMessage(100);
                        // sendFinishMessage();
                        //静音播放一个音频,防止延时
                        pool.play(whiteKeyMusics.get(0), 0, 0, 1, -1, 2f);
                    } else {
                        //if (System.currentTimeMillis() - currentTime >= SEND_PROGRESS_MESSAGE_BREAK_TIME) {
                        // sendProgressMessage((int) (((float) loadNum / (float) Piano.PIANO_NUMS) * 100f));
                        //  currentTime = System.currentTimeMillis();
                        //  }
                    }
                });
                service.execute(() -> {
                    // sendStartMessage();
                    ArrayList<PianoKey[]> whiteKeys = piano.getWhitePianoKeys();
                    int whiteKeyPos = 0;
                    for (int i = 0; i < whiteKeys.size(); i++) {
                        for (PianoKey key : whiteKeys.get(i)) {
                            try {
                                int soundID = pool.load(context, key.getVoiceId(), 1);
                                whiteKeyMusics.put(whiteKeyPos, soundID);
                                if (minSoundId == -1) {
                                    minSoundId = soundID;
                                }
                                whiteKeyPos++;
                            } catch (Exception e) {
                                isLoading = false;
                                //   sendErrorMessage(e);
                                return;
                            }
                        }
                    }
                    ArrayList<PianoKey[]> blackKeys = piano.getBlackPianoKeys();
                    int blackKeyPos = 0;
                    for (int i = 0; i < blackKeys.size(); i++) {
                        for (PianoKey key : blackKeys.get(i)) {
                            try {
                                int soundID = pool.load(context, key.getVoiceId(), 1);
                                blackKeyMusics.put(blackKeyPos, soundID);
                                blackKeyPos++;
                                if (soundID > maxSoundId) {
                                    maxSoundId = soundID;
                                }
                            } catch (Exception e) {
                                isLoading = false;
                                //   sendErrorMessage(e);
                                return;
                            }
                        }
                    }
                });
            }
        }
    }


}