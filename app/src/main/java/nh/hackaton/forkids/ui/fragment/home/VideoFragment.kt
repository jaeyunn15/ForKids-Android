package nh.hackaton.forkids.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.lifecycle.ViewModelProvider
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentRemitBottomBinding
import nh.hackaton.forkids.databinding.FragmentVideoBinding
import nh.hackaton.forkids.ui.base.BaseBottomSheetFragment
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import nh.hackaton.forkids.ui.viewmodel.EduViewModel
import nh.hackaton.forkids.ui.viewmodel.SharedViewModel
import org.koin.android.ext.android.inject


class VideoFragment  : BaseBottomSheetFragment<FragmentVideoBinding, EduViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_video

    override val viewModel: EduViewModel by inject()

    val sharedViewModel : SharedViewModel by inject()

    override fun initStartView() {
        sharedViewModel.keyLiveData.observe(viewLifecycleOwner){
            val param = "https://www.youtube.com/watch?v=${it}"
            viewDataBinding.webview.let{
                it.settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                }
                it.isHorizontalScrollBarEnabled = true
                it.isVerticalScrollBarEnabled = true
                it.webChromeClient = WebChromeClient()
                it.loadUrl(param)
            }
        }




    }
}