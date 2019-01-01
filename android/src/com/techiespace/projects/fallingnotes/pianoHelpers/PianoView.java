/*
package com.techiespace.projects.fallingnotes.pianoHelpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.techiespace.projects.fallingnotes.R;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class PianoView extends View {


    private final static String TAG = "PianoView";
    private static ArrayList<PianoKey[]> whiteStaticKeys;
    private static ArrayList<PianoKey[]> blackStaticKeys;
    private Piano piano = null;
    private ArrayList<PianoKey[]> whitePianoKeys;
    private ArrayList<PianoKey[]> blackPianoKeys;

    //Thread Safe array list of the keys that are pressed
    private CopyOnWriteArrayList<PianoKey> pressedKeys = new CopyOnWriteArrayList<>();


    //Paint for images<!--<com.example.library.view.PianoView-->

    private Paint paint;

    //Square to identify the sound name
    private RectF square;

    //Colors
    private String pianoColors[] = {
            "#C0C0C0", "#A52A2A", "#FF8C00", "#FFFF00", "#00FA9A", "#00CED1", "#4169E1", "#FFB6C1",
            "#FFEBCD"
    };

    private Context context;


    private int layoutWidth = 0;

    private float scale = 1;


    //  private OnPianoListener pianoListener;


    private int progress = 0;
    private boolean canPress = true;

    private boolean isInitFinish = false;
    private int minRange = 0;
    private int maxRange = 0;
    //
    private int maxStream;


    public PianoView(Context context) {
        this(context, null);
    }

    public PianoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PianoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        paint = new Paint();
        paint.setAntiAlias(true);
        //initialize the square
        paint.setStyle(Paint.Style.FILL);
        //Initialize the square
        square = new RectF();

        //This is used becuase it is dicovered that

        if (piano == null)
            piano = new Piano(context, scale);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.e(TAG, "onMeasure");
        Drawable whiteKeyDrawable = ContextCompat.getDrawable(context, R.drawable.white_piano_key);
        //Minimum Hieght
        int whiteKeyHeight = whiteKeyDrawable.getIntrinsicHeight();
        //Get the Height and Width of the layout and its mode
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //Set Height
        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = Math.min(height, whiteKeyHeight);
                break;
            case MeasureSpec.UNSPECIFIED:
                height = whiteKeyHeight;
                break;
            default:
                break;
        }
        //Set the zoom ratio
        scale = (float) (height - getPaddingTop() - getPaddingBottom()) / (float) (whiteKeyHeight);
        layoutWidth = width - getPaddingLeft() - getPaddingRight();
        //Set layout height and width
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //Initialize the piano
        if (piano == null) {

            //  Log.v("Initializing com.techiespace.projects.fallingnotes.Piano","onDraw");
            minRange = 0;
            maxRange = layoutWidth;
            piano = new Piano(context, scale);
            whiteStaticKeys = piano.getWhitePianoKeys();    //not null
            blackStaticKeys = piano.getBlackPianoKeys();

            //Get white keys
            whitePianoKeys = piano.getWhitePianoKeys();
            //Get Black Keys
            blackPianoKeys = piano.getBlackPianoKeys();


        }

        if (whitePianoKeys != null) {
            // Log.v("jdbkfhsd  ","whitepianokeynot null");
            for (int i = 0; i < whitePianoKeys.size(); i++) {
                for (PianoKey key : whitePianoKeys.get(i)) {
                    paint.setColor(Color.parseColor(pianoColors[i]));
                    key.getKeyDrawable().draw(canvas);
                    //Initialize the sound name area
                    Rect r = key.getKeyDrawable().getBounds();
                    int sideLength = (r.right - r.left) / 2;
                    int left = r.left + sideLength / 2;
                    int top = r.bottom - sideLength - sideLength / 3;
                    int right = r.right - sideLength / 2;
                    int bottom = r.bottom - sideLength / 3;
                    square.set(left, top, right, bottom);
                    canvas.drawRoundRect(square, 6f, 6f, paint);
                    paint.setColor(Color.BLACK);
                    paint.setTextSize(sideLength / 1.8f);
                    Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
                    int baseline =
                            (int) ((square.bottom + square.top - fontMetrics.bottom - fontMetrics.top) / 2);
                    paint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(key.getLetterName(), square.centerX(), baseline, paint);
                }
            }
        }

        if (blackPianoKeys != null) {
            for (int i = 0; i < blackPianoKeys.size(); i++) {
                for (PianoKey key : blackPianoKeys.get(i)) {
                    key.getKeyDrawable().draw(canvas);
                }
            }
        }

        if (!isInitFinish && piano != null) {
            isInitFinish = true;

        }

    }

    public int getPianoWidth() {
        if (piano != null) {
            return piano.getPianoWith();
        }
        return 0;
    }


    public int getLayoutWidth() {
        return layoutWidth;
    }


    public void setPianoColors(String[] pianoColors) {
        if (pianoColors.length == 9) {
            this.pianoColors = pianoColors;
        }
    }


    public void scroll(int progress) {
        int x;
        switch (progress) {
            case 0:
                x = 0;
                break;
            case 100:
                x = getPianoWidth() - getLayoutWidth();
                break;
            default:
                x = (int) (((float) progress / 100f) * (float) (getPianoWidth() - getLayoutWidth()));
                break;
        }
        minRange = x;
        maxRange = x + getLayoutWidth();
        this.scrollTo(x, 0);

        Log.v("x cordinate", "abc " + x);
        this.progress = progress;
    }


    public void autoScroll(PianoKey key) {

        if (key != null) {
            Rect[] areas = key.getAreaOfKey();
            if (areas != null && areas.length > 0 && areas[0] != null) {
                int left = areas[0].left, right = key.getAreaOfKey()[0].right;
                for (int i = 1; i < areas.length; i++) {
                    if (areas[i] != null) {
                        if (areas[i].left < left) {
                            left = areas[i].left;
                        }
                        if (areas[i].right > right) {
                            right = areas[i].right;
                        }
                    }
                }
                if (left < minRange || right > maxRange) {
                    int progress = (int) ((float) left * 100 / (float) getPianoWidth());
                    scroll(progress);
                }
            }
        }

    }

//    public ArrayList<com.techiespace.projects.fallingnotes.PianoKey[]> getWhiteStaticKeys()
//    {
//        if(whiteStaticKeys == null)
//            Log.v("Pianoview  ","statkey is null");
//        return whiteStaticKeys;
//    }
//    public ArrayList<com.techiespace.projects.fallingnotes.PianoKey[]> getBlackStaticKeys()
//    {
//        if(blackStaticKeys == null)
//            Log.v("Pianoview  ","statkey is null");
//        return blackStaticKeys;
//    }


    //Hnadle updown clicks
    public void PerformClickDown(String note) {
        blackPianoKeys = piano.getBlackPianoKeys();
        whitePianoKeys = piano.getWhitePianoKeys();

        if (note.charAt(1) == '#') {
            for (PianoKey[] pianokey : blackPianoKeys) {
                for (int i = 0; i < pianokey.length; i++) {
                    if (pianokey[i].getLetterName().equals(note)) {

                        this.performButtonDownClick(pianokey[i]);

                    }
                }
            }
        } else {
            for (PianoKey[] pianokey : whitePianoKeys) {
                for (int i = 0; i < pianokey.length; i++) {
                    if (pianokey[i].getLetterName().equals(note)) {
                        this.performButtonDownClick(pianokey[i]);

                    }
                }
            }

        }

    }

    public void PerformClickUp(String note) {
        blackPianoKeys = piano.getBlackPianoKeys();
        whitePianoKeys = piano.getWhitePianoKeys();

        if (note.charAt(1) == '#') {
            for (PianoKey[] pianokey : blackPianoKeys) {
                for (int i = 0; i < pianokey.length; i++) {
                    if (pianokey[i].getLetterName().equals(note)) {

                        this.performButtonUpClick(pianokey[i]);

                    }
                }
            }
        } else {
            for (PianoKey[] pianokey : whitePianoKeys) {
                for (int i = 0; i < pianokey.length; i++) {
                    if (pianokey[i].getLetterName().equals(note)) {
                        this.performButtonUpClick(pianokey[i]);

                    }
                }
            }

        }

    }

    public void performButtonDownClick(PianoKey key) {
        autoScroll(key);
        key.getKeyDrawable().setState(new int[]{android.R.attr.state_pressed});
        this.invalidate();
    }

    public void performButtonUpClick(PianoKey key) {
        autoScroll(key);
        key.getKeyDrawable().setState(new int[]{-android.R.attr.state_pressed});
        this.invalidate();

    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        postDelayed(() -> scroll(progress), 200);
    }
}







*/
