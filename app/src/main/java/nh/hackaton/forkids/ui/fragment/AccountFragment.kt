package nh.hackaton.forkids.ui.fragment

import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentAccountBinding
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_account

    override val viewModel: AccountViewModel by inject()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}