package nh.hackaton.forkids.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.ItemEduVideoBinding
import nh.hackaton.forkids.databinding.ViewAccountSearchBinding
import nh.hackaton.forkids.model.response.ResVideoEduItemDto
import nh.hackaton.forkids.util.AddPlusMinus
import nh.hackaton.forkids.util.ChangeState
import nh.hackaton.forkids.util.ReplaceDate
import nh.hackaton.forkids.util.SetComma

class EduListAdapter : RecyclerView.Adapter<EduListAdapter.EdulistViewHolder>() {

    private var eduList : ArrayList<ResVideoEduItemDto> = ArrayList()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): EduListAdapter.EdulistViewHolder {
        val binding: ItemEduVideoBinding
                = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_edu_video, parent, false)
        return EdulistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EduListAdapter.EdulistViewHolder, position: Int) {
        holder.onBind(eduList[position], position)
    }

    fun setdata(data : List<ResVideoEduItemDto>){
        this.eduList = data as ArrayList<ResVideoEduItemDto>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = eduList.size

    inner class EdulistViewHolder(private val binding: ItemEduVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ResVideoEduItemDto, position: Int) {
            binding.apply {
                binding.data = item
            }
        }
    }
}