package com.massivedisaster.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.massivedisaster.widget.extensions.setAppBarLayout
import kotlinx.android.synthetic.main.activity_arc_toolbar_view.*

class ArcToolbarViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arc_toolbar_view)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { _ ->
            startActivity(Intent(this, MainActivity::class.java))
        }

        arcToolbar.setAppBarLayout(appbar)
    }
}

