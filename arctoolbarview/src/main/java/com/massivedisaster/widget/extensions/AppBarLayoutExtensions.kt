package com.massivedisaster.widget.extensions

import android.support.design.widget.AppBarLayout
import com.massivedisaster.widget.ArcToolbarView

fun ArcToolbarView.setAppBarLayout(appbar: AppBarLayout) {
    appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
        internal var scrollRange = -1f

        override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
            if (scrollRange == -1f) {
                scrollRange = appBarLayout.totalScrollRange.toFloat()
            }

            val scale = 1 + verticalOffset / scrollRange

            this@setAppBarLayout.setScale(scale)
        }
    })
}

