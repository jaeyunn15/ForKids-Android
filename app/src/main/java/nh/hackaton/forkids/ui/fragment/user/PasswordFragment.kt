package nh.hackaton.forkids.ui.fragment.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentPasswordBinding
import nh.hackaton.forkids.ui.activity.MainActivity
import nh.hackaton.forkids.ui.activity.SignUpActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import org.koin.android.ext.android.inject


class PasswordFragment : BaseFragment<FragmentPasswordBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_password

    override val viewModel: UserViewModel by inject()

    override fun initStartView() {
        viewDataBinding.cvPasswordNext.setOnClickListener {
            val signupActivity = activity as SignUpActivity
            signupActivity.intentActivity()
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }


}