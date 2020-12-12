package nh.hackaton.forkids.ui.fragment.home.kids

import android.util.Log
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentHomeKidsAccountBinding
import nh.hackaton.forkids.ui.activity.MainActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.fragment.education.EduFragment
import nh.hackaton.forkids.ui.fragment.home.RemitBottomFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class HomeKidsAccountFragment : BaseFragment<FragmentHomeKidsAccountBinding, AccountViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.fragment_home_kids_account

    override val viewModel: AccountViewModel by inject()

    override fun initStartView() {
        val finacno = "00820100004940000000000004873"
        val df: DateFormat = SimpleDateFormat("yyyyMMdd")
        val date: String = df.format(Calendar.getInstance().time)

        val regno = pref.getRegno()
        Log.d("호출 ", "$date / $finacno / $regno")
        viewModel.getAccount(date, finacno, regno!!)
        pref.saveRegno(String.format("%04d",Integer.parseInt(regno)+1))

        viewDataBinding.tvRecommendEdu.setOnClickListener {
            val mainActivity = activity as MainActivity
            val eduFragment = EduFragment()
            mainActivity.replaceFragment(eduFragment)
        }
        viewDataBinding.tvHomeKidsRemit.setOnClickListener {
            val remitDialog = RemitBottomFragment()
            remitDialog.show(requireFragmentManager(), "custom")
            val regno = pref.getRegno()
            Log.d("호출 ", "$date / $finacno / $regno")
            viewModel.getWithdraw(date, "3020000002979", "3500", "문방구", regno!!)
            pref.saveRegno(String.format("%04d",Integer.parseInt(regno)+1))

        }

        viewDataBinding.tvHomeAccountKidsAccountSearch.setOnClickListener {
            val regno = pref.getRegno()
            Log.d("호출 ", "$date / $finacno / $regno")
            viewModel.getAccountList(date,"20201212","20201213",regno!!)
            pref.saveRegno(String.format("%04d",Integer.parseInt(regno)+1))
        }
    }

    override fun initDataBinding() {
        viewModel.accountValueLiveData.observe(viewLifecycleOwner){
            viewDataBinding.tvHomeAccountMoney.text = it
        }
    }

    override fun initAfterBinding() {
    }

}