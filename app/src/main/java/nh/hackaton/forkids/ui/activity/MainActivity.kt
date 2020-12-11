package nh.hackaton.forkids.ui.activity

import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.ActivityMainBinding
import nh.hackaton.forkids.ui.base.BaseActivity
import nh.hackaton.forkids.ui.fragment.home.HomeFragment
import nh.hackaton.forkids.util.replace

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun initStartView() {
        val homeFragment = HomeFragment()
        replaceFragment(homeFragment)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
    }

    fun replaceFragment(fragment: androidx.fragment.app.Fragment){
        replace(R.id.main_container, fragment)
    }

}