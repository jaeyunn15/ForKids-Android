package nh.hackaton.forkids.ui.fragment.home.parent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentHomeParentsAnalysisBinding
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject

class HomeParentsAnalysisFragment : BaseFragment<FragmentHomeParentsAnalysisBinding, AccountViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_home_parents_analysis

    override val viewModel: AccountViewModel by inject()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}