package nh.hackaton.forkids.ui.fragment.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentPasswordBinding
import nh.hackaton.forkids.ui.activity.MainActivity
import nh.hackaton.forkids.ui.activity.SignUpActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import nh.hackaton.forkids.util.AccountUtil.USER_ACCOUNT
import org.koin.android.ext.android.inject
import kotlin.random.Random


class PasswordFragment : BaseFragment<FragmentPasswordBinding, UserViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_password

    override val viewModel: UserViewModel by inject()

    override fun initStartView() {
        observeData()
        viewDataBinding.cvPasswordNext.setOnClickListener {
            val signupActivity = activity as SignUpActivity
            signupActivity.intentActivity()
        }
        setCustomKeyboard()
    }

    fun observeData(){
        sharedViewModel.name_livedata.observe(viewLifecycleOwner){
            name_data = it
            Log.d("패스워드 화면 ",it)
        }
        sharedViewModel.type_livedata.observe(viewLifecycleOwner){
            type_data = it.toString()
            Log.d("패스워드 화면 ",it.toString())
        }
        sharedViewModel.phone_livedata.observe(viewLifecycleOwner){
            phone_data = it
            Log.d("패스워드 화면 ",it)
        }
        sharedViewModel.reg_livedata.observe(viewLifecycleOwner){
            reg_data = it
            Log.d("패스워드 화면 ",it)
        }
    }

    fun isCreateUser(){
        viewModel.getUserCreate(reg_data, name_data, input_password, phone_data, type_data)
    }

    private fun setCustomKeyboard(){
        val btnWidth: Int = getBtnWidth()
        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        flipper = viewDataBinding.pwFragViewFlipper.apply {
            visibility = View.VISIBLE
            val inAni = AnimationUtils.loadAnimation(requireContext(), R.anim.in_animation)
            inAni.interpolator = AccelerateInterpolator()
            inAnimation = inAni
            val outAni = AnimationUtils.loadAnimation(requireContext(), R.anim.out_animation)
            outAni.interpolator = DecelerateInterpolator()
            outAnimation = outAni
        }

        val textView = ""
        val emptyView = TextView(requireContext())
        flipper.addView(emptyView, 0)

        viewDataBinding.etPwFragPassword.apply {
            setOnClickListener { v: View ->
                imm.hideSoftInputFromWindow(viewDataBinding.etPwFragPassword.windowToken, 0)
                if (flipper.currentView.id != R.id.login_firstViewFlipper) {
                    reOrderKeyboard(btnWidth)
                    flipper.visibility = View.VISIBLE
                    flipper.displayedChild = 1
                }
            }
        }

        imm.hideSoftInputFromWindow(viewDataBinding.etPwFragPassword.windowToken, 0)

        viewDataBinding.etPwFragPassword.requestFocus()

        viewDataBinding.keyPwFragOk.apply {
            setOnClickListener{
                if (flipper.currentView.id == R.id.pw_frag_firstViewFlipper) {
                    flipper.displayedChild = 0
                    val showtxt = StringBuilder(viewDataBinding.tvPwFragPassword.text)
                    showtxt.append(currentText)

                    viewDataBinding.tvPwFragPassword.text = showtxt.toString()
                    input_password = showtxt.toString()

                    isCreateUser()

                    showtxt.append("")
                    currentText.clear()
                    viewDataBinding.etPwFragPassword.text.clear()
                    showtxt.clear()
                    viewDataBinding.tvPwFragPassword.text = ""
                }
                observeCreateStatus()
            }
        }


        viewDataBinding.keyPwFragCancel.apply {
            setOnClickListener{ v : View ->
                val curIndex = viewDataBinding.etPwFragPassword.selectionStart
                var passwordStr = viewDataBinding.etPwFragPassword.text.toString()
                var passWordLength = passwordStr.length
                if (curIndex == 0 || passWordLength == 0) {
                    return@setOnClickListener
                }
                passwordStr.apply {
                    passwordStr = substring(0,curIndex-1)+substring(curIndex,passWordLength)
                }
                currentText.apply{
                    currentText = StringBuilder(toString().substring(0,curIndex-1)+toString().substring(curIndex,passWordLength))
                }
                passWordLength = passwordStr.length
                viewDataBinding.etPwFragPassword.setText("")
                for( i in 1 .. passWordLength){
                    viewDataBinding.etPwFragPassword.append("*") }
                viewDataBinding.etPwFragPassword.setSelection(curIndex-1);
            }
        }
        reOrderKeyboard(btnWidth)
    }

    private fun observeCreateStatus(){
        val signUpActivity = activity as SignUpActivity
        val loginFragment = LoginFragment()
        viewModel.createStatusLiveData.observe(viewLifecycleOwner){
            if (it){
                viewModel.createAccountLiveData.observe(viewLifecycleOwner){data ->
                    sharedViewModel.userAccount.postValue(data)
                    signUpActivity.replaceFragment(loginFragment)
                }
            }
        }
    }

    private fun reOrderKeyboard(btnWidth: Int) {
        val keyNumberArr = ArrayList<String>()
        for (i in 0..9) {
            keyNumberArr.add(i.toString())
        }
        var tr: TableRow? = null
        var btn: Button? = null
        var randIndx: Int = 0;
        var random = Random
        for (i in 0 until viewDataBinding.lpwFragTableLayout.childCount) {
            tr = viewDataBinding.lpwFragTableLayout.getChildAt(i) as TableRow
            for (i in 0 until tr.childCount) {
                btn = tr.getChildAt(i) as Button
                if (btn.id == -1) {
                    randIndx = random.nextInt(keyNumberArr.size)
                    btn.text = keyNumberArr[randIndx]
                    btn.width = btnWidth/3
                    val keyTxt = btn.text
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        keyNumberArr.removeIf { x ->
                            x == keyNumberArr[randIndx]
                        }
                    }
                    btn.setOnClickListener { v: View ->
                        val curIndex = viewDataBinding.etPwFragPassword.selectionStart
                        var passwordStr = viewDataBinding.etPwFragPassword.text.toString()
                        val passWordLength = passwordStr.length
                        passwordStr.apply {
                            substring(0, curIndex) + keyTxt + substring(curIndex,passWordLength)
                        }
                        currentText.append(keyTxt)
                        viewDataBinding.etPwFragPassword.setText("")
                        for (i in 0 until curIndex) {
                            viewDataBinding.etPwFragPassword.append("*") }
                        viewDataBinding.etPwFragPassword.append(keyTxt)
                        for (i in curIndex + 1 until passWordLength + 1) {
                            viewDataBinding.etPwFragPassword.append("*")
                        }
                        viewDataBinding.etPwFragPassword.setSelection(curIndex + 1)
                        mHandler.sendEmptyMessageDelayed(0,1000)
                    }
                }
            }
        }
    }

    private var mHandler = Handler(Looper.getMainLooper()){ msg: Message? ->
        val curIndex = viewDataBinding.etPwFragPassword.selectionStart
        viewDataBinding.etPwFragPassword.setText("")
        for (i in 0 until curIndex) {
            viewDataBinding.etPwFragPassword.append("*") }
        viewDataBinding.etPwFragPassword.setSelection(curIndex)
        false
    }

    private fun getBtnWidth(): Int {
        val displaymetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymetrics)
        return displaymetrics.widthPixels
    }

    override fun initDataBinding() {}

    override fun initAfterBinding() {}

    private lateinit var flipper: ViewFlipper
    private var currentText : StringBuilder = StringBuilder()
    private lateinit var imm: InputMethodManager
    private var input_password = ""
    private var name_data = ""
    private var type_data = ""
    private var phone_data = ""
    private var reg_data = ""
}