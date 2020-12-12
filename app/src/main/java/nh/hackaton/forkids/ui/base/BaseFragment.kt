package nh.hackaton.forkids.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import nh.hackaton.forkids.ui.activity.MainActivity
import nh.hackaton.forkids.ui.activity.SignUpActivity
import nh.hackaton.forkids.ui.viewmodel.SharedViewModel
import nh.hackaton.forkids.util.AppPreference
import org.koin.android.ext.android.inject

abstract class BaseFragment <T:ViewDataBinding, R: BaseViewModel>():Fragment(){
    lateinit var viewDataBinding: T

    abstract val layoutResourceId: Int

    abstract val viewModel: R

    val pref: AppPreference by inject()

    lateinit var sharedViewModel : SharedViewModel

    abstract fun initStartView()

    abstract fun initDataBinding()

    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater,layoutResourceId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStartView()
        initDataBinding()
        initAfterBinding()
    }



}