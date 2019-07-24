package com.mirtalebi.sliderpro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlideFragment extends Fragment {

    private SlideModel slideModel;

    public SlideFragment() {

    }

    static SlideFragment newInstance(SlideModel slideModel) {
        SlideFragment fragment = new SlideFragment();
        fragment.slideModel = slideModel;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slide , container , false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        try {
            if (slideModel.getView().getParent() != null) {
                ((ViewGroup) slideModel.getView().getParent()).removeView(slideModel.getView()); // <- fix
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        ((RelativeLayout) v.findViewById(R.id.main_view)).addView(slideModel.getView() , params);

        return v;
    }
}
