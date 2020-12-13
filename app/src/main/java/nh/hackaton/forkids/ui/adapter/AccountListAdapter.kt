package nh.hackaton.forkids.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.ViewAccountSearchBinding
import nh.hackaton.forkids.model.response.REC
import nh.hackaton.forkids.util.AddPlusMinus
import nh.hackaton.forkids.util.ChangeState
import nh.hackaton.forkids.util.ReplaceDate
import nh.hackaton.forkids.util.SetComma

class AccountListAdapter : RecyclerView.Adapter<AccountListAdapter.AccountlistViewHolder>() {

    private var accountList : ArrayList<REC> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccountListAdapter.AccountlistViewHolder {
        val binding: ViewAccountSearchBinding
                = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_account_search, parent, false)
        return AccountlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountListAdapter.AccountlistViewHolder, position: Int) {
        holder.onBind(accountList[position], position)
    }

    fun setRECdata(data : List<REC>){
        this.accountList = data as ArrayList<REC>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = accountList.size

    inner class AccountlistViewHolder(private val binding: ViewAccountSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: REC, position: Int) {
            binding.apply {
                binding.data = item
                binding.tvViewAccountSearchDay.text = ReplaceDate(item.Trdd)
                binding.tvViewAccountSearchType.text = ChangeState(item.MnrcDrotDsnc)
                binding.tvViewAccountSearchMoney.text = AddPlusMinus(SetComma(item.Tram),item.MnrcDrotDsnc)
                binding.tvViewAccountSearchPresentValue.text = SetComma(item.AftrBlnc)+"원"
                binding.tvViewAccountSearchTime.text = item.Txtm
            }
        }
    }
}