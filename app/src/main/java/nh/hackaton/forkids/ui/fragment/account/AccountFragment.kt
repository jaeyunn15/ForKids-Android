package nh.hackaton.forkids.ui.fragment.account

import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentAccountBinding
import nh.hackaton.forkids.ui.adapter.AccountListAdapter
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_account

    override val viewModel: AccountViewModel by inject()

    private val accountListAdapter = AccountListAdapter()

    override fun initStartView() {
        initSet()
        callData()
        initAdapter()
    }

    fun initSet(){

    }

    fun callData(){
        val finacno_parent = "00820100004940000000000005773"
        val df: DateFormat = SimpleDateFormat("yyyyMMdd")
        val date: String = df.format(Calendar.getInstance().time)

        val regno = pref.getRegno()
        Log.d("호출 ", "$date / $finacno_parent / $regno")
        viewModel.getAccountList(date,"20201212",date,regno!!)
        pref.saveRegno(String.format("%04d",Integer.parseInt(regno)+1))
    }

    fun initAdapter(){
        viewDataBinding.rvAccountSearch.run {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = accountListAdapter
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.accountListLiveData.observe(viewLifecycleOwner){
            accountListAdapter.setRECdata(it)
        }
    }

    override fun initAfterBinding() {
    }

}