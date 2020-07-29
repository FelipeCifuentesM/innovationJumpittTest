package com.example.graphicsinnovation.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphicsinnovation.R
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class DispersionGraph : AppCompatActivity() {
    private lateinit var scatterChart: ScatterChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dispersion_graph)

        scatterChart = findViewById<ScatterChart>(R.id.dispersionChart)
        setData()
    }

    private fun setData(){
        val scatterEntries:ArrayList<Entry> = ArrayList()
        scatterEntries.add(Entry(0f, 30f))
        scatterEntries.add(Entry(1f, 80f))
        scatterEntries.add(Entry(2f, 60f))
        scatterEntries.add(Entry(3f, 50f))
        scatterEntries.add(Entry(4f, 70f))
        scatterEntries.add(Entry(5f, 60f))
        scatterEntries.add(Entry(4.5f, 20f))

        var scatterDataSet = ScatterDataSet(scatterEntries, "Inducesmile")
        scatterDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        var scatterData = ScatterData(scatterDataSet)
        val xAxis = scatterChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        val months =
            arrayOf("Ene", "Feb", "Mar", "Apr", "May", "Jun")
        val formatter = IndexAxisValueFormatter(months)
        xAxis.granularity = 1f
        xAxis.valueFormatter = formatter
        scatterChart.data = scatterData
        scatterChart.animateXY(5000, 5000)
        scatterChart.invalidate()

//        scatterDataSet = ScatterDataSet(scatterEntries, "")
//        scatterData = ScatterData(scatterDataSet)
//        scatterChart.setData(scatterData)
//        scatterDataSet.setColors(ColorTemplate.JOYFUL_COLORS)
//        scatterDataSet.setValueTextColor(Color.BLACK)
//        scatterDataSet.setValueTextSize(18f)
    }

}