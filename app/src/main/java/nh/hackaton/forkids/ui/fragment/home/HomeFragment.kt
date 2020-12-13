package nh.hackaton.forkids.ui.fragment.home

import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentHomeBinding
import nh.hackaton.forkids.ui.adapter.MainViewPagerAdapter
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, AccountViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override val viewModel: AccountViewModel by inject()

    override fun initStartView() {
        viewModel.getDataRank()
    }

    override fun initDataBinding() {
        viewModel.nickNameLiveData.observe(viewLifecycleOwner){
            viewDataBinding.tvHomeNaming2.text = it
        }

    }

    override fun initAfterBinding() {
        val vpAdapter = MainViewPagerAdapter(requireActivity())
        val pageMargin = requireContext().resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
        val pageOffset = requireContext().resources.getDimensionPixelOffset(R.dimen.offset).toFloat()
        viewDataBinding.vpHome.apply {
            adapter = vpAdapter
            offscreenPageLimit = 2
            setPageTransformer(ViewPager2.PageTransformer { page, position ->
                val myOffset = position * -(2 * pageMargin + pageOffset)
                if (ViewCompat.getLayoutDirection(viewDataBinding.vpHome) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                    page.translationX = -myOffset
                } else {
                    page.translationX = myOffset
                }
            })
        }
    }


}