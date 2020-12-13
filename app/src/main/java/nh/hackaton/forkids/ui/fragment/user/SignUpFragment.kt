package nh.hackaton.forkids.ui.fragment.user

import android.view.View
import android.widget.Toast
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentSignUpBinding
import nh.hackaton.forkids.ui.activity.SignUpActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import org.koin.android.ext.android.inject
import kotlin.properties.Delegates


class SignUpFragment : BaseFragment<FragmentSignUpBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up

    override val viewModel: UserViewModel by inject()

    lateinit var name : String
    lateinit var reg_no : String
    lateinit var phone_no : String
    var type by Delegates.notNull<Int>()

    override fun initStartView() {
        val passwordFragment = PasswordFragment()
        val signUpActivity = activity as SignUpActivity

        viewDataBinding.tvSignupTypeKids.setOnClickListener {
            type = 0
            sharedViewModel.type.postValue(type)
        }
        viewDataBinding.tvSignupTypeParent.setOnClickListener {
            type = 1
            sharedViewModel.type.postValue(type)
        }
        viewDataBinding.cvNext.setOnClickListener {
            checkDataIsNull()
            signUpActivity.replaceFragment(passwordFragment)
        }
        viewDataBinding.tvSignupTypeParent.setOnClickListener {
            viewDataBinding.ivTypeCheckParent.visibility = View.VISIBLE
            viewDataBinding.ivTypeCheckKids.visibility = View.GONE
        }
        viewDataBinding.tvSignupTypeKids.setOnClickListener {
            viewDataBinding.ivTypeCheckParent.visibility = View.GONE
            viewDataBinding.ivTypeCheckKids.visibility = View.VISIBLE
        }
    }

    private fun checkDataIsNull(){
        if (viewDataBinding.etName.text.isNullOrBlank()){
            Toast.makeText(requireContext(), "이름 미입력", Toast.LENGTH_SHORT).show()
        }else{
            name = viewDataBinding.etName.text.toString()
            sharedViewModel.name.postValue(name)
        }

        if (viewDataBinding.etBirthday.text.isNullOrBlank()){
            Toast.makeText(requireContext(),"주민등록번호 미입력", Toast.LENGTH_SHORT).show()
        }else{
            reg_no = viewDataBinding.etBirthday.text.toString()
            sharedViewModel.reg.postValue(reg_no)
        }

        if (viewDataBinding.etPhoneNumber.text.isNullOrBlank()){
            Toast.makeText(requireContext(), "핸드폰번호 미입력", Toast.LENGTH_SHORT).show()
        }else{
            phone_no = viewDataBinding.etPhoneNumber.text.toString()
            sharedViewModel.phone.postValue(phone_no)
        }
    }

    override fun initDataBinding() {}

    override fun initAfterBinding() {}


}