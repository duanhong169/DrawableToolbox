package top.defaults.drawabletoolboxapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.drawableSpecList)
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        val adapter = DrawableSpecAdapter(samples(this))
        list.adapter = adapter
    }
}
