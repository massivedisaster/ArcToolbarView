# ArcToolbarView

A Arc view for the android Toolbar.

### Download

### Usage

Declare your Toolbar with ArcToolbarView.

```xml
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="com.massivedisaster.sample.ArcToolbarViewActivity">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="180dp">

        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/toolbar_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            app:layout_scrollFlags="scroll|exitUntilCollapsed"
            app:expandedTitleTextAppearance="@style/TextAppearance.AppCompat.Headline"
            app:expandedTitleMarginBottom="50dp"
            app:toolbarId="@+id/toolbar">

            <com.massivedisaster.widget.ArcToolbarView
                android:id="@+id/arcToolbar"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="@android:color/transparent" />

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin" >
            </android.support.v7.widget.Toolbar>

        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>

    <include layout="@layout/content_arc_toolbar_view" />    

</android.support.design.widget.CoordinatorLayout>
```

Set the AppBarLayout to ArcToolbarView.

```java
class ArcToolbarViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arc_toolbar_view)
        setSupportActionBar(toolbar)
        arcToolbar.setAppBarLayout(appbar)
    }
}
```

### License
[MIT LICENSE](LICENSE.md)