package com.mirtalebi.sliderpro;

import android.transition.Slide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class SliderFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<SlideModel> slideModels;

    SliderFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        slideModels = new ArrayList<>();
    }

    @Override
    public int getCount() {
//        if (slideModels.size() == 1){
//            return 1;
//        }
        return slideModels.size();
    }

    void addSlider(SlideModel model){
        slideModels.add(model);
        notifyDataSetChanged();
    }

    void addSliderAt(SlideModel model , int position){
        slideModels.add(position , model);
        notifyDataSetChanged();
    }

//    public void removeSlide(int position){
//        slideModels.remove(position);
//        notifyDataSetChanged();
//    }

    public List<SlideModel> getSlideModels() {
        return slideModels;
    }

    @Override
    public Fragment getItem(int position) {
        if (slideModels.size() == 1){
            SlideFragment.newInstance(slideModels.get(0));
        }
        return SlideFragment.newInstance(slideModels.get(position%slideModels.size()));
    }
}