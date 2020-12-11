package nh.hackaton.forkids.ui.fragment.home.connect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentConnectBinding
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import org.koin.android.ext.android.inject


class ConnectFragment : BaseFragment<FragmentConnectBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_connect

    override val viewModel: UserViewModel by inject()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}