package nh.hackaton.forkids.ui.fragment.home.kids

import android.graphics.Color
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import nh.hackaton.forkids.R
import nh.hackaton.forkids.databinding.FragmentHomeKidsAnalysisBinding
import nh.hackaton.forkids.ui.activity.MainActivity
import nh.hackaton.forkids.ui.base.BaseFragment
import nh.hackaton.forkids.ui.fragment.analysis.AnalysisFragment
import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import org.koin.android.ext.android.inject

class HomeKidsAnalysisFragment : BaseFragment<FragmentHomeKidsAnalysisBinding, AccountViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_home_kids_analysis

    override val viewModel: AccountViewModel by inject()

    val entries = ArrayList<PieEntry>()

    override fun initStartView() {
        viewModel.getDataRank()
        viewDataBinding.piechart.setUsePercentValues(true)

        addData()

    }

    fun addData(){
        viewModel.rankDataLiveData.observe(viewLifecycleOwner){
            it.forEach {data ->
                entries.add(PieEntry(data.COUNT.toFloat(), data.COMMENT))
            }
            pieDataDraw()
        }
    }

    fun pieDataDraw(){
        val colorItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorItems.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colorItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorItems.add(c)
        colorItems.add(ColorTemplate.getHoloBlue())

        //val tf = Typeface.createFromAsset(requireContext().assets, "font/notosans_regular.ttf")
        val pieDataSet = PieDataSet(entries, "")
        pieDataSet.apply {
            colors = colorItems
            valueTextColor = Color.BLACK
            valueTextSize = 13f
            //valueTypeface = tf
        }


        val pieData = PieData(pieDataSet)
        viewDataBinding.piechart.apply {
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
        viewDataBinding.tvAnalysisShow.setOnClickListener {
            val mainActivity = activity as MainActivity
            val analysisFragment = AnalysisFragment()
            mainActivity.replaceFragment(analysisFragment)
        }
    }

    override fun initAfterBinding() {
    }

}