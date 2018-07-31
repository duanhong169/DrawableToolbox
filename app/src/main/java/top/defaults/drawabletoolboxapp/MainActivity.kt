package top.defaults.drawabletoolboxapp

import android.content.Intent
import android.graphics.Color
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
        val backgroundDrawableBuilder = DrawableBuilder()
                .shape(GradientDrawable.RECTANGLE)
                .cornerRadius(20)
                .strokeWidth(4)
                .strokeColor(Color.MAGENTA)
                .strokeColorPressed(Color.BLACK)
        textView.setBackgroundDrawable(backgroundDrawableBuilder.build())
        textView.isClickable = true

        val button = findViewById<Button>(R.id.javaVersion)
        button.setBackgroundDrawable(backgroundDrawableBuilder.build())
        button.setOnClickListener {
            val intent = Intent(this, JavaActivity::class.java)
            startActivity(intent)
        }
    }
}
