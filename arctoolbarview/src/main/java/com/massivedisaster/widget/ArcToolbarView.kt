/*
 * ArcToolbarView - An Arc view for the android Toolbar or anywhere.
 *
 * Copyright (c) 2018 ArcToolbarView
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

package com.massivedisaster.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import com.massivedisaster.widget.arctoolbarview.R

class ArcToolbarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    private companion object {
        const val DEFAULT_HEIGHT = 60F
    }

    private var arcHeight: Float = 0F
    private var extendOverBoundary: Float = 0F
    private var scale: Float = 1F
    private var arcPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var arcRect: RectF = RectF()

    init {
        arcPaint.style = Paint.Style.FILL

        attrs?.let {
            parseAttributes(it)
        }
    }

    private fun getWindowBackgroundColorOrDefault(@ColorInt defaultColor: Int): Int {
        val windowBackground = TypedValue()

        context.theme.resolveAttribute(android.R.attr.windowBackground, windowBackground, true)
        return if (windowBackground.type >= TypedValue.TYPE_FIRST_COLOR_INT
                && windowBackground.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            windowBackground.data
        } else {
            defaultColor
        }
    }

    private fun parseAttributes(attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ArcToolbarView)

        arcHeight = a.getDimension(R.styleable.ArcToolbarView_arcHeight, DEFAULT_HEIGHT)
        extendOverBoundary = a.getDimension(R.styleable.ArcToolbarView_arcBoundary, DEFAULT_HEIGHT)
        arcPaint.color = a.getColor(R.styleable.ArcToolbarView_arcColor, getWindowBackgroundColorOrDefault(Color.TRANSPARENT))

        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        arcRect.set(-extendOverBoundary,
                height - arcHeight * scale,
                width + extendOverBoundary,
                height + arcHeight * scale)

        canvas.drawOval(arcRect, arcPaint)
    }

    fun setScale(scale: Float) {
        this.scale = if (scale < 0F) 0F else scale

        invalidate()
    }

    fun setArcColor(@ColorInt color: Int) {
        arcPaint.color = color
        invalidate()
    }
}