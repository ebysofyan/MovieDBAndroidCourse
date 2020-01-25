package app.ebysofyan.itec.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.ebysofyan.itec.R
import app.ebysofyan.itec.core.base.ViewPagerAdapter
import app.ebysofyan.itec.ui.fragments.main.MainFragment
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.tabbar.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        setSupportActionBar(_toolbar)
        val actionBar = supportActionBar.apply {
            title = "Movies Database"
        }
        initTabsLayout()
    }

    private fun initTabsLayout() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.add(MainFragment.newInstance() to "Movies")
        main_viewpager.adapter = adapter

        _tabbar_layout.setupWithViewPager(main_viewpager)
    }
}
