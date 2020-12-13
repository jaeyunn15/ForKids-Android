package nh.hackaton.forkids.ui.fragment.home.kids

import android.content.Intent
import android.net.Uri
import android.util.Log
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentHomeKidsAccountBinding
import nh.hackaton.forkids.ui.activity.MainActivity
import nh.hackaton.forkids.ui.activity.VideoActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.fragment.account.AccountFragment
import nh.hackaton.forkids.ui.fragment.education.EduFragment
import nh.hackaton.forkids.ui.fragment.home.RemitBottomFragment
import nh.hackaton.forkids.ui.fragment.home.VideoFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import nh.hackaton.forkids.ui.viewmodel.EduViewModel
import nh.hackaton.forkids.util.SetComma
import org.koin.android.ext.android.inject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class HomeKidsAccountFragment : BaseFragment<FragmentHomeKidsAccountBinding, AccountViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.fragment_home_kids_account

    override val viewModel: AccountViewModel by inject()
    val eduViewModel : EduViewModel by inject()

    override fun initStartView() {
        val mainActivity = activity as MainActivity
        //val finacno = "00820100004940000000000004873"

        val finacno_parent = "00820100004940000000000005773"
        val df: DateFormat = SimpleDateFormat("yyyyMMdd")
        val date: String = df.format(Calendar.getInstance().time)
        val regno = pref.getRegno()
        Log.d("호출 ", "$date / $finacno_parent / $regno")
        viewModel.getAccount(date, finacno_parent, regno!!)
        pref.saveRegno(String.format("%04d",Integer.parseInt(regno)+1))


        viewDataBinding.tvRecommendEdu.setOnClickListener {
            val eduFragment = VideoFragment()
            eduViewModel.getRecommendEdu()
            eduViewModel.recommendEduItemLiveData.observe(viewLifecycleOwner){
                //eduFragment.show(requireFragmentManager(), "custom")
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW
                    ).setData(Uri.parse("https://www.youtube.com/watch?v=${it[0].KEY}"))
                        .setPackage("com.google.android.youtube")
                )
            }

        }

        viewDataBinding.tvHomeKidsRemit.setOnClickListener {
            val remitDialog = RemitBottomFragment()
            remitDialog.show(requireFragmentManager(), "custom")
        }

        viewDataBinding.tvHomeAccountKidsAccountSearch.setOnClickListener {
            val accountFragment = AccountFragment()
            mainActivity.replaceFragment(accountFragment)
        }
        viewDataBinding.tvMission.setOnClickListener {
            val eduFragment = EduFragment()
            mainActivity.replaceFragment(eduFragment)
        }
    }

    override fun initDataBinding() {
        viewModel.accountValueLiveData.observe(viewLifecycleOwner){
            viewDataBinding.tvHomeAccountMoney.text = SetComma(it) +"원"
        }
    }

    override fun initAfterBinding() {
    }

    fun fetchNowMoney(){
        val finacno_parent = "00820100004940000000000005773"
        val df: DateFormat = SimpleDateFormat("yyyyMMdd")
        val date: String = df.format(Calendar.getInstance().time)
        val regno = pref.getRegno()
        Log.d("호출 ", "$date / $finacno_parent / $regno")
        viewModel.getAccount(date, finacno_parent, regno!!)
        pref.saveRegno(String.format("%04d",Integer.parseInt(regno)+1))
    }
}