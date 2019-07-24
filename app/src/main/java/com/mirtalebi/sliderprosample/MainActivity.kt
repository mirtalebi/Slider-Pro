package com.mirtalebi.sliderprosample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.mirtalebi.sliderpro.SliderProView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sliderProView = findViewById<SliderProView>(R.id.slider)
        sliderProView.init(supportFragmentManager)

        val v = LayoutInflater.from(applicationContext).inflate(R.layout.slider1 , null)
        val v2 = LayoutInflater.from(applicationContext).inflate(R.layout.slider2 , null)
        val v3 = LayoutInflater.from(applicationContext).inflate(R.layout.slider3, null)
        val v4 = LayoutInflater.from(applicationContext).inflate(R.layout.slider4 , null)
        val v5 = LayoutInflater.from(applicationContext).inflate(R.layout.slider5 , null)
//        findViewById<RelativeLayout>(R.id.test).addView(v)
        sliderProView.addSlide(v);
//        sliderProView.addSlide(v2);
//        sliderProView.addSlide(v3);
//        sliderProView.addSlide(v4);
//        sliderProView.addSlide(v5);
        sliderProView.setUpSliderIndicator(slider_indicator)
//        sliderProView.addSlide(v);
//        sliderProView.addSlide(v);
//        sliderProView.addSlide(v);
//        sliderProView.addSlide(v);

    }
}
