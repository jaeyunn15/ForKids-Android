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

    override fun initStartView() {
        viewDataBinding.piechart.setUsePercentValues(true)

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(508f, "카페"))
        entries.add(PieEntry(600f, "문방구"))
        entries.add(PieEntry(300f, "음식점"))
        entries.add(PieEntry(670f, "도서"))
        entries.add(PieEntry(270f, "편의점"))

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