package top.defaults.drawabletoolboxapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import top.defaults.drawabletoolbox.DrawableBuilder
import android.util.TypedValue
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val drawable = DrawableBuilder()
                .ring()
                .useLevelForRing()
                .useGradient()
                .useLevelForGradient()
                .sweepGradient()
                .gradientRadiusInFraction(0.5f)
                .build()
        val animator = ObjectAnimator.ofInt(imageView, "imageLevel", 10000, 0)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()
        imageView.setImageDrawable(drawable)

        val button = findViewById<Button>(R.id.javaVersion)
        val backgroundDrawableBuilder = DrawableBuilder()
                .oval()
                .strokeWidth(dpToPx(1))
                .dashed()
                .strokeColor(Color.MAGENTA)
                .strokeColorPressed(Color.BLACK)
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
