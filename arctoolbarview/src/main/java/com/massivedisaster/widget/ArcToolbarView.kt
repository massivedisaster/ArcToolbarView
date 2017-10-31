package com.massivedisaster.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.FrameLayout
import com.massivedisaster.widget.arctoolbarview.R

class ArcToolbarView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var arcHeight: Float = 0.0f
    private var extendOverBoundary: Float = 0.0f
    private var scale: Float = 1.0f
    private var arcPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var arcRect: RectF = RectF()

    init {
        arcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        arcPaint.style = Paint.Style.FILL

        parseAttributes(attrs)
    }

    private fun getWindowBackgroundColorOrDefault(defaultColor: Int): Int {
        val a = TypedValue()
        context.theme.resolveAttribute(android.R.attr.windowBackground, a, true)
        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return a.data
        }
        return defaultColor
    }

    private fun parseAttributes(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ArcToolbarView)
            arcHeight = a.getDimension(R.styleable.ArcToolbarView_arcHeight, 60f)
            extendOverBoundary = a.getDimension(R.styleable.ArcToolbarView_arcBoundary, 60f)
            arcPaint.color = a.getColor(R.styleable.ArcToolbarView_arcColor, getWindowBackgroundColorOrDefault(Color.TRANSPARENT))

            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        arcRect.set(-extendOverBoundary,
                height - arcHeight * scale,
                width + extendOverBoundary,
                height + arcHeight * scale)

        canvas.drawOval(arcRect, arcPaint)
    }

    fun setScale(n: Float) {
        var scale = n
        if (n < 0) scale = 0.0f
        this.scale = scale

        invalidate()
    }

    fun setArcColor(color: Int) {
        arcPaint.color = color
        invalidate()
    }
}