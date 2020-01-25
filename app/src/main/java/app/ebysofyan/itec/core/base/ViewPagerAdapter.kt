package app.ebysofyan.itec.core.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = mutableListOf<Pair<Fragment, String>>()

    fun getFragments() = fragments

    fun add(item: Pair<Fragment, String>) {
        this.fragments.add(item)
    }

    override fun getItem(position: Int): Fragment = fragments[position].first

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = fragments[position].second
}