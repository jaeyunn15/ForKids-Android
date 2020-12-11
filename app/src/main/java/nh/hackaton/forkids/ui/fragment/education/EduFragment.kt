package nh.hackaton.forkids.ui.fragment.education

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentEduBinding
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject

class EduFragment : BaseFragment<FragmentEduBinding, AccountViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.fragment_edu

    override val viewModel: AccountViewModel by inject()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}