package nh.hackaton.forkids.ui.fragment.education

import androidx.recyclerview.widget.LinearLayoutManager
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentEduBinding
import nh.hackaton.forkids.ui.adapter.AccountListAdapter
import nh.hackaton.forkids.ui.adapter.EduListAdapter
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.EduViewModel
import org.koin.android.ext.android.inject

class EduFragment : BaseFragment<FragmentEduBinding, EduViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.fragment_edu

    override val viewModel: EduViewModel by inject()

    private val eduListAdapter = EduListAdapter()

    override fun initStartView() {
        viewModel.getAllVideo()
        initAdapter()
    }

    fun initAdapter(){
        viewDataBinding.rvEduVideo.run {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = eduListAdapter
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.eduItemListLiveData.observe(viewLifecycleOwner){
            eduListAdapter.setdata(it)
        }
    }

    override fun initAfterBinding() {
    }

}