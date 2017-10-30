package com.massivedisaster.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.massivedisaster.widget.extensions.setAppBarLayout
import kotlinx.android.synthetic.main.activity_arc_toolbar_view.*

class ArcToolbarViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arc_toolbar_view)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        arcToolbar.setAppBarLayout(appbar)
    }
}

