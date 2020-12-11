package nh.hackaton.forkids.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import nh.hackaton.forkids.ui.fragment.home.kids.HomeKidsAccountFragment
import nh.hackaton.forkids.ui.fragment.home.kids.HomeKidsAnalysisFragment


class MainViewPagerAdapter(fragmentActivity: FragmentActivity?) : FragmentStateAdapter(fragmentActivity!!) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeKidsAccountFragment()
            else -> HomeKidsAnalysisFragment()
        }
    }
}
