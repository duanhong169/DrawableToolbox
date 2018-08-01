package top.defaults.drawabletoolboxapp

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import top.defaults.drawabletoolbox.DrawableBuilder
import android.util.TypedValue



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val backgroundDrawableBuilder = DrawableBuilder()
                .oval()
                .strokeWidth(dpToPx(1))
                .dashed()
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

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
    }
}
