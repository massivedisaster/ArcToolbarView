/*
 * ArcToolbarView - A set of Android libraries to help speed up Android development.
 *
 * Copyright (c) 2017 ArcToolbarView
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
