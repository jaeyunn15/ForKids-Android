package nh.hackaton.forkids.ui.activity

import android.content.Intent
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.ActivitySignUpBinding
import nh.hackaton.forkids.ui.base.BaseActivity
import nh.hackaton.forkids.ui.fragment.user.SignUpFragment
import nh.hackaton.forkids.util.replace

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {


    override val layoutResourceId: Int
        get() = R.layout.activity_sign_up

    override fun initStartView() {
        val signUpFragment = SignUpFragment()
        replaceFragment(signUpFragment)
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    fun replaceFragment(fragment: androidx.fragment.app.Fragment){
        replace(R.id.container, fragment)
    }

    fun intentActivity(){
        startActivity(
            Intent(baseContext, MainActivity::class.java)
        )
    }
}