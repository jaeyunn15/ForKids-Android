package nh.hackaton.forkids.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentMyPageBinding
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import org.koin.android.ext.android.inject

class MyPageFragment : BaseFragment<FragmentMyPageBinding, UserViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page

    override val viewModel: UserViewModel by inject()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}