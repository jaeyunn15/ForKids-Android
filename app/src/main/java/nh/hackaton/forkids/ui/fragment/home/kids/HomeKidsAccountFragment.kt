package nh.hackaton.forkids.ui.fragment.home.kids

import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentHomeKidsAccountBinding
import nh.hackaton.forkids.ui.activity.MainActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.fragment.education.EduFragment
import nh.hackaton.forkids.ui.fragment.home.RemitBottomFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject

class HomeKidsAccountFragment : BaseFragment<FragmentHomeKidsAccountBinding, AccountViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.fragment_home_kids_account

    override val viewModel: AccountViewModel by inject()

    override fun initStartView() {
        viewDataBinding.tvRecommendEdu.setOnClickListener {
            val mainActivity = activity as MainActivity
            val eduFragment = EduFragment()
            mainActivity.replaceFragment(eduFragment)
        }
        viewDataBinding.tvHomeKidsRemit.setOnClickListener {
            val remitDialog = RemitBottomFragment()
            remitDialog.show(requireFragmentManager(),"custom")
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}