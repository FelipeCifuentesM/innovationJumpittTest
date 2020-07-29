package com.example.graphicsinnovation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphicsinnovation.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class HistogramGraph : AppCompatActivity() {
    private lateinit var barChart: BarChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.histogram_graph)

        barChart = findViewById<BarChart>(R.id.histogramChart)
        setData(10)
    }

    private fun setData(count: Int){
        val yVals: ArrayList<BarEntry> = ArrayList()
        for(index in 0..count){
            val value = Math.random()*100
            yVals.add(BarEntry(index.toFloat(),value.toFloat()))
        }
        val set = BarDataSet(yVals,"Data set")

        val labels = ArrayList<String>()
        labels.add("18-Jul")
        labels.add("19-Jul")
        labels.add("20-Jul")
        labels.add("21-Jul")
        labels.add("22-Jul")
        labels.add("23-Jul")
        labels.add("24-Jul")
        labels.add("25-Jul")
        labels.add("26-Jul")
        labels.add("27-Jul")
        labels.add("28-Jul")
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        set.color = R.color.colorPrimary
        set.setDrawValues(true)

        val barData = BarData(set)
        barChart.data = barData
        barChart.invalidate()
        barChart.animateY(1000)
    }

}