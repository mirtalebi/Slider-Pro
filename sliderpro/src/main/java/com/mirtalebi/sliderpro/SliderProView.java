package com.mirtalebi.sliderpro;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.bluetooth.BluetoothHidDeviceAppQosSettings.MAX;

public class SliderProView extends RelativeLayout {

    private SliderFragmentPagerAdapter sliderFragmentPagerAdapter;
    private SliderProIndicatorView indicatorView;
    private boolean isInited = false;
    private boolean isInitedIndicator = false;
    private long duration = 5000;
    private ViewPager viewPager;
    private int position = 0;
    private int realPosition = 0;
    private CountDownTimer countDownTimer;
    private int defaultPosition = 0;

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return duration;
    }

    public SliderProView(Context context) {
        super(context);
        initialize(context);
    }

    public SliderProView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public SliderProView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SliderProView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    private void loadNext(){
        countDownTimer = new CountDownTimer(duration, 1000){
            public void onTick(long millisUntilFinished){
//                counter++;
            }
            public  void onFinish(){
                if (sliderFragmentPagerAdapter.getSlideModels().size() == 0 || sliderFragmentPagerAdapter.getSlideModels().size() == 1){
                    return;
                }
                if (position == sliderFragmentPagerAdapter.getSlideModels().size()-1){
                    position = -1;
                }
                position += 1;
                realPosition += 1;
                viewPager.setCurrentItem(10 * sliderFragmentPagerAdapter.getSlideModels().size() + position , true);
                loadNext();
            }
        }.start();
    }

    private void initialize(Context context){
        inflate(context, R.layout.pro_slider_view, this);
    }

    public void init(FragmentManager fragmentManager){
        sliderFragmentPagerAdapter = new SliderFragmentPagerAdapter(fragmentManager);
        viewPager = findViewById(R.id.slider_pro_pager);
        isInited = true;
    }

    private void init(){
        viewPager.setAdapter(sliderFragmentPagerAdapter);
        loadNext();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                countDownTimer.cancel();
                int t = viewPager.getCurrentItem()%sliderFragmentPagerAdapter.getSlideModels().size() - position;
                realPosition += t;
                position = viewPager.getCurrentItem()%sliderFragmentPagerAdapter.getSlideModels().size();
                if (isInitedIndicator){
                    for (int i = 0; i < indicatorView.getSelectionCallBacks().size(); i++){
                        indicatorView.getSelectionCallBacks().get(i).deselect();
                    }
                    indicatorView.getSelectionCallBacks().get(position).select();
                }
                loadNext();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                countDownTimer.cancel();
                int t = viewPager.getCurrentItem()%sliderFragmentPagerAdapter.getSlideModels().size() - position;
//                realPosition += t;
                position = viewPager.getCurrentItem()%sliderFragmentPagerAdapter.getSlideModels().size();
                if (isInitedIndicator){
                    for (int i = 0; i < indicatorView.getSelectionCallBacks().size(); i++){
                        indicatorView.getSelectionCallBacks().get(i).deselect();
                    }
                    indicatorView.getSelectionCallBacks().get(position).select();
                }
                loadNext();
            }
        });
    }

    public void addSlide(View view) throws Exception {
        if (!isInited){
            throw new Exception("You must Init SliderPro: init(FragmentManager fragmentManager)");
        }

//        if (sliderFragmentPagerAdapter.getCount() == 0){
//            SlideModel slideModel = new SlideModel();
//            slideModel.setView(view);
//            sliderFragmentPagerAdapter.addSlider(slideModel);

            SlideModel slideModel3 = new SlideModel();
            slideModel3.setView(view);
            sliderFragmentPagerAdapter.addSlider(slideModel3);

            if (sliderFragmentPagerAdapter.getSlideModels().size() == 1){
                init();
            }

//            SlideModel slideModel2 = new SlideModel();
//            slideModel2.setView(view);
//            sliderFragmentPagerAdapter.addSlider(slideModel2);
//        } else {
//            sliderFragmentPagerAdapter.removeSlide(0);
//            SlideModel slideModel2 = new SlideModel();
//            slideModel2.setView(view);
//            sliderFragmentPagerAdapter.addSliderAt(slideModel2 , 0);
//
//            SlideModel slideModel3 = new SlideModel();
//            slideModel3.setView(view);
//            sliderFragmentPagerAdapter.addSlider(slideModel3);

//            sliderFragmentPagerAdapter.removeSlide(sliderFragmentPagerAdapter.getCount() - 1);
//            sliderFragmentPagerAdapter.addSlider(sliderFragmentPagerAdapter.getSlideModels().get(sliderFragmentPagerAdapter.getSlideModels().size() - 1));

//        }
    }

    public void setUpSliderIndicator(SliderProIndicatorView sliderIndicator){
        isInitedIndicator = true;
        sliderIndicator.initIndicator(sliderFragmentPagerAdapter.getSlideModels());
        this.indicatorView = sliderIndicator;
        viewPager.setCurrentItem(10 * sliderFragmentPagerAdapter.getSlideModels().size() + defaultPosition , false);
//        realPosition = 10 * sliderFragmentPagerAdapter.getSlideModels().size() + defaultPosition;
        position = defaultPosition;
        if (isInitedIndicator){
            for (int i = 0; i < indicatorView.getSelectionCallBacks().size(); i++){
                indicatorView.getSelectionCallBacks().get(i).deselect();
            }
            indicatorView.getSelectionCallBacks().get(defaultPosition).select();
        }
    }

    public List<SlideModel> getSlides() throws Exception {
        if (!isInited){
            throw new Exception("You must Init SliderPro: init(FragmentManager fragmentManager)");
        }
        return sliderFragmentPagerAdapter.getSlideModels();
    }

    public List<View> getSlidesView() throws Exception {
        if (!isInited){
            throw new Exception("You must Init SliderPro: init(FragmentManager fragmentManager)");
        }
        List<View> sliders = new ArrayList<>();
        for (SlideModel slideModel : sliderFragmentPagerAdapter.getSlideModels()) {
            sliders.add(slideModel.getView());
        }
        return sliders;
    }

    public void setDefaultPosition(int defaultPosition) {
        this.defaultPosition = defaultPosition;
    }

    public int getDefaultPosition() {
        return defaultPosition;
    }
}
