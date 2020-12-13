package nh.hackaton.forkids.ui.fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentSelectLoginTypeBinding
import nh.hackaton.forkids.ui.activity.SignUpActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


class SelectLoginTypeFragment : BaseFragment<FragmentSelectLoginTypeBinding, UserViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_select_login_type

    override val viewModel: UserViewModel by inject()

    override fun initStartView() {
        val signUpFragment = SignUpFragment()
        val loginFragment = LoginFragment()
        val signupActivity = activity as SignUpActivity

        viewDataBinding.btnGotoLogin.setOnClickListener {
            signupActivity.replaceFragment(loginFragment)
        }
        viewDataBinding.btnGotoSignup.setOnClickListener {
            signupActivity.replaceFragment(signUpFragment)
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}