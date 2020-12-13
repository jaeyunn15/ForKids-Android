package nh.hackaton.forkids.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.ViewAccountSearchBinding
import nh.hackaton.forkids.databinding.ViewAnalysisDetailBinding
import nh.hackaton.forkids.model.response.REC
import nh.hackaton.forkids.model.response.ResDetailRankItemDto
import nh.hackaton.forkids.util.AddPlusMinus
import nh.hackaton.forkids.util.ChangeState
import nh.hackaton.forkids.util.ReplaceDate
import nh.hackaton.forkids.util.SetComma

class AccountAnalysisAdapter : RecyclerView.Adapter<AccountAnalysisAdapter.AccountAnalysislistViewHolder>() {

    private var detailDataList : ArrayList<ResDetailRankItemDto> = ArrayList()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): AccountAnalysisAdapter.AccountAnalysislistViewHolder {
        val binding: ViewAnalysisDetailBinding
                = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.view_analysis_detail, parent, false)
        return AccountAnalysislistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountAnalysisAdapter.AccountAnalysislistViewHolder, position: Int) {
        holder.onBind(detailDataList[position], position)
    }

    fun setdata(data : List<ResDetailRankItemDto>){
        this.detailDataList = data as ArrayList<ResDetailRankItemDto>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = detailDataList.size

    inner class AccountAnalysislistViewHolder(private val binding: ViewAnalysisDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResDetailRankItemDto, position: Int) {
            binding.apply {
                binding.data = item
                binding.tvAnalysisDetailMoney.text = SetComma(item.EXPENSE.toInt().toString()) +"원"
            }
        }
    }
}