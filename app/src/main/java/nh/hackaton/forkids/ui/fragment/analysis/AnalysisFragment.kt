package nh.hackaton.forkids.ui.fragment.analysis

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentAnalysisBinding
import nh.hackaton.forkids.ui.adapter.AccountAnalysisAdapter
import nh.hackaton.forkids.ui.adapter.AccountListAdapter
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import nh.hackaton.forkids.ui.viewmodel.EduViewModel
import org.koin.android.ext.android.inject

class AnalysisFragment : BaseFragment<FragmentAnalysisBinding, AccountViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_analysis

    override val viewModel: AccountViewModel by inject()

    val eduViewModel : EduViewModel by inject()

    private val analysisAdapter = AccountAnalysisAdapter()

    val entries = ArrayList<PieEntry>()

    override fun initStartView() {
        initAdapter()
        viewModel.getDetailRank()
        eduViewModel.getRecommendEdu()
        addData()
    }

    fun initAdapter(){
        viewDataBinding.rvAnalysisDetail.run {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = analysisAdapter
            setHasFixedSize(true)
        }
    }

    private fun addData(){
        viewModel.detailRankDataLiveData.observe(viewLifecycleOwner){
            analysisAdapter.setdata(it)
            it.forEach {data ->
                entries.add(PieEntry(data.EXPENSE.toFloat(), data.COMMENT))
            }
            drawChart()
        }
        eduViewModel.recommendEduItemLiveData.observe(viewLifecycleOwner){
            viewDataBinding.data = it[0]
        }
    }


    private fun drawChart(){
        val colorItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorItems.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colorItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorItems.add(c)
        colorItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            colors = colorItems
            valueTextColor = Color.BLACK
            valueTextSize = 13f
        }

        val pieData = PieData(pieDataSet)
        viewDataBinding.piechart2.apply {
            data = pieData
            description.isEnabled = false
            isRotationEnabled = false
            centerText = "지출 내역"
            setEntryLabelColor(Color.BLACK)
            animateXY(2500,2500)
            animate()
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}