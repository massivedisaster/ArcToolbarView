package com.massivedisaster.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout

class ArcToolbarView(context: Context, set: AttributeSet?) : FrameLayout(context, set) {

    companion object {

        fun convertDpToPixel(n: Float, context: Context): Float =
                n * (context.resources.displayMetrics.densityDpi / 160.0f)
    }

    private var arcSize: Float = 150f
    private var extendOverBoundary: Float = 0.toFloat()
    private var scale: Float = 1.0f
    private var ovalPaint: Paint = Paint(1)
    private var rect: RectF = RectF()

    init {
        this.init()
    }

    private fun init() {

        ovalPaint.style = Paint.Style.FILL

        val a = TypedValue()
        context.theme.resolveAttribute(android.R.attr.windowBackground, a, true)
        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            ovalPaint.color = a.data
        }

        extendOverBoundary = convertDpToPixel(30f, context)
        arcSize = convertDpToPixel(30.0f, context)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        rect.set(-extendOverBoundary, height - arcSize * scale,
                width + extendOverBoundary,
                height + arcSize * scale)

        canvas.drawOval(
                rect,
                ovalPaint)
    }

    fun setScale(n: Float) {
        var scale = n
        if (n < 0) scale = 0.0f
        this.scale = scale

        invalidate()
    }

    fun setArcColor(color: Int) {
        ovalPaint.color = color
        invalidate()
    }
}