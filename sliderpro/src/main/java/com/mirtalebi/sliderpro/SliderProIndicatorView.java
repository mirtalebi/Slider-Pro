package com.mirtalebi.sliderpro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

public class SliderProIndicatorView extends RelativeLayout {

    private static int IMAGE_TYPE_BITMAP = 1;
    private static int IMAGE_TYPE_DRAWABLE = 2;

    private LinearLayout linearLayout;
    private List<SliderSelectionCallBack> selectionCallBacks;
    private Drawable enableImageDrawable = getResources().getDrawable(R.drawable.indicator_dot_enable);
    private Drawable disableImageDrawable = getResources().getDrawable(R.drawable.indicator_dot_disable);
    private Bitmap enableImageBitmap;
    private Bitmap disableImageBitmap;
    private int enableImageType = IMAGE_TYPE_DRAWABLE;
    private int disableImageType = IMAGE_TYPE_DRAWABLE;


    public SliderProIndicatorView(Context context) {
        super(context);
        initialize(context);
    }

    public SliderProIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public SliderProIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SliderProIndicatorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context);
    }

    public void setEnabledImage(Bitmap bitmap){
        enableImageBitmap = bitmap;
        enableImageType = IMAGE_TYPE_BITMAP;
    }

    public void setEnabledImage(Drawable drawable){
        enableImageDrawable = drawable;
        enableImageType = IMAGE_TYPE_DRAWABLE;
    }

    public void setDisabledImage(Bitmap bitmap){
        disableImageBitmap= bitmap;
        disableImageType = IMAGE_TYPE_BITMAP;
    }

    public void setDisabledImage(Drawable drawable){
        disableImageDrawable = drawable;
        disableImageType = IMAGE_TYPE_DRAWABLE;
    }

    private void initialize(Context context) {
        inflate(context, R.layout.pro_slider_indicator, this);
        linearLayout = findViewById(R.id.indicator_list_layout);
    }

    public void initIndicator(List<SlideModel> slideModelList) {
        selectionCallBacks = new ArrayList<>();
        linearLayout.removeAllViews();
        for (SlideModel slideModel : slideModelList) {
//            final View v = findViewById(R.id.dot);
            final View view = LayoutInflater.from(getContext()).inflate(R.layout.row_dots_slider_pro, null, false);
//            if(view.getParent() != null) {
//                ((ViewGroup) view.getParent()).removeView(view); // <- fix
//            }
            linearLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            selectionCallBacks.add(new SliderSelectionCallBack() {
                @Override
                public void select() {
                    try{
                        if (enableImageType == IMAGE_TYPE_BITMAP){
                            ((ImageView) view.findViewById(R.id.dot_image)).setImageBitmap(enableImageBitmap);
                        } else {
                            ((ImageView) view.findViewById(R.id.dot_image)).setImageDrawable(enableImageDrawable);
                        }
                    } catch (Exception e){
                        ((ImageView) view.findViewById(R.id.dot_image)).setImageDrawable(getResources().getDrawable(R.drawable.indicator_dot_enable));
                    }
                }

                @Override
                public void deselect() {
                    try {
                        if (disableImageType == IMAGE_TYPE_BITMAP){
                            ((ImageView) view.findViewById(R.id.dot_image)).setImageBitmap(disableImageBitmap);
                        } else {
                            ((ImageView) view.findViewById(R.id.dot_image)).setImageDrawable(disableImageDrawable);
                        }
                    } catch (Exception e){
                        ((ImageView) view.findViewById(R.id.dot_image)).setImageDrawable(getResources().getDrawable(R.drawable.indicator_dot_disable));
                    }
                }
            });

        }
    }

    public List<SliderSelectionCallBack> getSelectionCallBacks() {
        return selectionCallBacks;
    }
}

