package com.techiespace.projects.fallingnotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class SliderActivity extends AppCompatActivity {
    private Button btnSkip, btnNext;
    private ViewPager sViewPager;
    private LinearLayout dotsLayout;
    private TextView dots[];

    SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main_slider1);

        // find view id
        initView();

        Log.e("Slider Activity","Slider Activity");

        // create Adapter object
        sliderAdapter = new SliderAdapter(this);
        // set adapter in ViewPager
        sViewPager.setAdapter(sliderAdapter);
        // set PageChangeListener
        sViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        // adding bottom dots -> addBottomDots(0);
//      addDotIndicator(0);
        addBottomDots(0);

    }


    // btnSkipClick
    public  void btnSkipClick(View v) {

        Utils.saveSharedSetting(this, PracticeActivity.PREF_USER_FIRST_TIME_GAME, "false");
        finish();
       // launchHomeScreen();
    }

    //btnNextClick
    public  void btnNextClick(View v) {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(1);
//        if (current < layouts.length) {
        if (current < sliderAdapter.image_slide.length) {
            // move to next screen
            sViewPager.setCurrentItem(current);
        } else {
            Utils.saveSharedSetting(this, PracticeActivity.PREF_USER_FIRST_TIME_GAME, "false");
            finish();
           // launchHomeScreen();
        }
    }


    private int getItem(int i) {
        return sViewPager.getCurrentItem() + i;
    }

    private void initView() {
        sViewPager = (ViewPager) findViewById(R.id.sViewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);
    }


    // viewPagerPage ChangeListener according to Dots-Points
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == sliderAdapter.image_slide.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };



    // add dot indicator
    public void addDotIndicator(){
        dots = new TextView[4];
        for (int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8266;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.white));

            dotsLayout.addView(dots[i]);
        }
    }

    // set of Dots points
    private void addBottomDots(int currentPage) {
//        dots = new TextView[layouts.length];
        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.white));  // dot_inactive
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.blue)); // dot_active
    }


}
