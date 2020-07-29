package com.example.graphicsinnovation.ui

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphicsinnovation.R
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry


class BoxGraph : AppCompatActivity() {
    private lateinit var candleStickChart: CandleStickChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.box_graph)

        candleStickChart = findViewById<CandleStickChart>(R.id.boxChart)
        setData()
    }

    private fun setData(){
        val ceList: MutableList<CandleEntry> = ArrayList()
        ceList.add(CandleEntry(0f, 4.62f, 2.02f, 2.70f, 4.13f))
        ceList.add(CandleEntry(1f, 5.50f, 2.70f, 3.35f, 4.96f))
        ceList.add(CandleEntry(2f, 5.25f, 3.02f, 3.50f, 4.50f))
        ceList.add(CandleEntry(3f, 6f, 3.25f, 4.40f, 5.0f))
        ceList.add(CandleEntry(4f, 5.57f, 2f, 2.80f, 4.5f))


        val cds = CandleDataSet(ceList, "Entries")
        cds.color = Color.rgb(80, 80, 80)
        cds.shadowColor = Color.DKGRAY
        cds.shadowWidth = 0.7f
        cds.decreasingColor = Color.RED
        cds.decreasingPaintStyle = Paint.Style.FILL
        cds.increasingColor = Color.rgb(122, 242, 84)
        cds.increasingPaintStyle = Paint.Style.STROKE
        cds.neutralColor = Color.BLUE
        cds.valueTextColor = Color.RED
        val cd = CandleData(cds)
        candleStickChart.data = cd
        candleStickChart.animateXY(2000, 2000)
        candleStickChart.invalidate()
    }

}