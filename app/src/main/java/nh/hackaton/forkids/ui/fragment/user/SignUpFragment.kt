package nh.hackaton.forkids.ui.fragment.user

import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentSignUpBinding
import nh.hackaton.forkids.ui.activity.SignUpActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import org.koin.android.ext.android.inject


class SignUpFragment : BaseFragment<FragmentSignUpBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up

    override val viewModel: UserViewModel by inject()

    override fun initStartView() {
        val passwordFragment = PasswordFragment()
        viewDataBinding.cvNext.setOnClickListener {
            val signUpActivity = activity as SignUpActivity
            signUpActivity.replaceFragment(passwordFragment)
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }


}