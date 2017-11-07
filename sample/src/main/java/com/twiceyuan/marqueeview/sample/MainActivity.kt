package com.twiceyuan.marqueeview.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_marquee.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val marqueeViews = ArrayList<View>()

        val images = arrayOf(
                R.drawable.ic_android_black_24dp,
                R.drawable.ic_local_printshop_black_24dp,
                R.drawable.ic_beach_access_black_24dp
        )
        val texts = arrayOf(
                "这是 Android",
                "这是打印机",
                "这是小红伞"
        )

        val inflater = LayoutInflater.from(this)

        for (i in 0..2) {
            val marqueeItem = inflater.inflate(R.layout.item_marquee, marquee, false)
            marqueeItem.image.setImageResource(images[i])
            marqueeItem.text.text = texts[i]
            marqueeViews.add(marqueeItem)
        }

        marquee.setViews(marqueeViews)

        marquee.setInterval(1000)
        marquee.setAnimDuration(1000)

        marquee.setOnItemClickListener({
            position, _ ->
            Toast.makeText(this, texts[position], Toast.LENGTH_SHORT).show()
        })
    }
}
