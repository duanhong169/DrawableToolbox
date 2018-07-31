package top.defaults.drawabletoolboxapp

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import top.defaults.drawabletoolbox.DrawableBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val background = DrawableBuilder().shape(GradientDrawable.OVAL).build()
        textView.setBackgroundDrawable(background)

        findViewById<Button>(R.id.javaVersion).setOnClickListener {
            val intent = Intent(this, JavaActivity::class.java)
            startActivity(intent)
        }
    }
}
