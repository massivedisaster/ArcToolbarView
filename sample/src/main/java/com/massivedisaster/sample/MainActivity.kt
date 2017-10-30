package com.massivedisaster.sample

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.animation.AccelerateInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var animationDuration = 700L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var arcHeight = resources.getDimensionPixelSize(R.dimen.arc_height)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var halfScreenHeight = (displayMetrics.heightPixels / 2) - arcHeight

        val valueAnimator = ValueAnimator.ofInt(halfScreenHeight, arcHeight)

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Int
            val topArcParams = topArc.layoutParams
            val bottomArcParams = bottomArc.layoutParams

            topArcParams.height = value
            bottomArcParams.height = value

            topArc.layoutParams = topArcParams
            bottomArc.layoutParams = bottomArcParams
        }

        valueAnimator.interpolator = AccelerateInterpolator(0.5f)
        valueAnimator.duration = animationDuration

        valueAnimator.start()
    }
}
