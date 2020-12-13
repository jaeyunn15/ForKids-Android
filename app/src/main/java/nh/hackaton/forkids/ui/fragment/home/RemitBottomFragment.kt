package nh.hackaton.forkids.ui.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentRemitBottomBinding
import nh.hackaton.forkids.ui.base.BaseBottomSheetFragment
import nh.hackaton.forkids.ui.fragment.home.kids.HomeKidsAccountFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import nh.hackaton.forkids.util.AppPreference
import org.koin.android.ext.android.inject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class RemitBottomFragment : BaseBottomSheetFragment<FragmentRemitBottomBinding, AccountViewModel>() {

    val pref: AppPreference by inject()
    val finacno_parent = "00820100004940000000000005773"
    val df: DateFormat = SimpleDateFormat("yyyyMMdd")
    val date: String = df.format(Calendar.getInstance().time)

    fun sendMoney(money:String, content:String){
        val regno = pref.getRegno()
        Log.d("호출 ", "$date / $finacno_parent / $regno")
        viewModel.getWithdraw(date, "3020000002979", money, content, regno!!)
        pref.saveRegno(String.format("%04d",Integer.parseInt(regno)+1))
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_remit_bottom

    override val viewModel: AccountViewModel by inject()

    override fun initStartView() {
        viewDataBinding.cvRemit.setOnClickListener {
            Log.d("보낼 데이터 ", "${viewDataBinding.etRemitMoney.text.toString()} / ${viewDataBinding.etRemitMessage.text}")
            if (!viewDataBinding.etRemitMoney.text.isNullOrBlank()){
                if (!viewDataBinding.etRemitMessage.text.isNullOrBlank()){
                    sendMoney(viewDataBinding.etRemitMoney.text.toString(), viewDataBinding.etRemitMessage.text.toString())
                    val homeFragment = HomeKidsAccountFragment()
                    homeFragment.fetchNowMoney()
                    dismiss()
                }
            }
        }
    }

}