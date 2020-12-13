package nh.hackaton.forkids.ui.fragment.user

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentLoginBinding
import nh.hackaton.forkids.ui.activity.SignUpActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import nh.hackaton.forkids.util.AccountUtil.USER_ACCOUNT
import org.koin.android.ext.android.inject
import kotlin.random.Random


class LoginFragment : BaseFragment<FragmentLoginBinding, UserViewModel>() {

    private lateinit var flipper: ViewFlipper
    private var currentText : StringBuilder = StringBuilder()
    private lateinit var imm: InputMethodManager


    override val layoutResourceId: Int
        get() = R.layout.fragment_login

    override val viewModel: UserViewModel by inject()

    var num = "00121110000" //계좌번호 고정. 나중에 로컬에서 불러올.

    override fun initStartView() {
        sharedViewModel.userAccountLiveData.observe(viewLifecycleOwner){
            num = it
        }
        setCustomKeyboard()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    private fun setCustomKeyboard(){
        val btnWidth: Int = getBtnWidth()
        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        flipper = viewDataBinding.viewFlipper.apply {
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

        viewDataBinding.etLoginPw.apply {
            setOnClickListener { v: View ->
                imm.hideSoftInputFromWindow(viewDataBinding.etLoginPw.windowToken, 0)
                if (flipper.currentView.id != R.id.login_firstViewFlipper) {
                    reOrderKeyboard(btnWidth)
                    flipper.visibility = View.VISIBLE
                    flipper.displayedChild = 1
                }
            }
        }

        imm.hideSoftInputFromWindow(viewDataBinding.etLoginPw.windowToken, 0)

        viewDataBinding.etLoginPw.requestFocus()

        viewDataBinding.keyLoginOk.apply {
            setOnClickListener{
                if (flipper.currentView.id == R.id.login_firstViewFlipper) {
                    flipper.displayedChild = 0
                    val showtxt = StringBuilder(viewDataBinding.tvInivisibleTest.text)
                    showtxt.append(currentText)

                    Log.d("로그인 시도  ", "$num / $showtxt")
                    viewModel.getUserLogin(num, showtxt.toString())

                    currentText.clear()
                    viewDataBinding.etLoginPw.text.clear()
                    showtxt.append("")
                    viewDataBinding.tvInivisibleTest.text = ""
                    observeData()
                }
            }
        }


        viewDataBinding.keyLoginCancel.apply {
            setOnClickListener{ v : View ->
                val curIndex = viewDataBinding.etLoginPw.selectionStart
                var passwordStr = viewDataBinding.etLoginPw.text.toString()
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
                viewDataBinding.etLoginPw.setText("")
                for( i in 1 .. passWordLength){
                    viewDataBinding.etLoginPw.append("*") }
                viewDataBinding.etLoginPw.setSelection(curIndex-1);
            }
        }
        reOrderKeyboard(btnWidth)
    }

    fun observeData(){
        viewModel.loginStatusLiveData.observe(viewLifecycleOwner){
            if (it){
                val signupActivity = activity as SignUpActivity
                signupActivity.intentActivity()
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
        for (i in 0 until viewDataBinding.loginTableLayout.childCount) {
            tr = viewDataBinding.loginTableLayout.getChildAt(i) as TableRow
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
                        val curIndex = viewDataBinding.etLoginPw.selectionStart
                        var passwordStr = viewDataBinding.etLoginPw.text.toString()
                        val passWordLength = passwordStr.length
                        passwordStr.apply {
                            substring(0, curIndex) + keyTxt + substring(curIndex,passWordLength)
                        }
                        currentText.append(keyTxt)
                        viewDataBinding.etLoginPw.setText("")
                        for (i in 0 until curIndex) {
                            viewDataBinding.etLoginPw.append("*") }
                        viewDataBinding.etLoginPw.append(keyTxt)
                        for (i in curIndex + 1 until passWordLength + 1) {
                            viewDataBinding.etLoginPw.append("*")
                        }
                        viewDataBinding.etLoginPw.setSelection(curIndex + 1)
                        mHandler.sendEmptyMessageDelayed(0,1000)
                    }
                }
            }
        }
    }

    private var mHandler = Handler(Looper.getMainLooper()){ msg: Message? ->
        val curIndex = viewDataBinding.etLoginPw.selectionStart
        viewDataBinding.etLoginPw.setText("")
        for (i in 0 until curIndex) {
            viewDataBinding.etLoginPw.append("*") }
        viewDataBinding.etLoginPw.setSelection(curIndex)
        false
    }

    private fun getBtnWidth(): Int {
        val displaymetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displaymetrics)
        return displaymetrics.widthPixels
    }

}