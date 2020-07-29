package com.example.graphicsinnovation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.graphicsinnovation.R
import com.example.graphicsinnovation.utils.goToActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHistogram.setOnClickListener(this)
        btnDispersion.setOnClickListener(this)
        btnBox.setOnClickListener(this)
        btnImageToText.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnHistogram -> {this.goToActivity<HistogramGraph>()}
            R.id.btnDispersion -> {this.goToActivity<DispersionGraph>()}
            R.id.btnBox -> {this.goToActivity<BoxGraph>()}
            R.id.btnImageToText -> {this.goToActivity<ImageToText>()}
        }
    }
}