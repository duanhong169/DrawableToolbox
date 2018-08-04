package top.defaults.drawabletoolboxapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import top.defaults.drawabletoolbox.DrawableBuilder
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import top.defaults.view.TextButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<View>(R.id.view)
        val drawable = DrawableBuilder()
                .ring()
//                .useLevelForRing()
                .useGradient()
//                .useLevelForGradient()
                .sweepGradient()
                .gradientRadiusInFraction(0.5f)
                .rotate(0f, 360f)
//                .scale(0.5f)
//                .scaleGravity(Gravity.START or Gravity.TOP)
                .mirror()
                .build()
        val animator = ObjectAnimator.ofInt(drawable, "level", 10000, 0)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.duration = 3000
        animator.interpolator = LinearInterpolator()
        animator.start()
        view.setBackgroundDrawable(drawable)

        val button = findViewById<TextButton>(R.id.javaVersion)
        button.setBackgroundDrawable(DrawableBuilder()
                .rectangle()
                .rounded()
                .hairlineBordered()
                .strokeColor(ContextCompat.getColor(this, android.R.color.holo_purple))
                .strokeColorPressed(ContextCompat.getColor(this, android.R.color.holo_orange_light))
                .strokeColorSelected(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
                .solidColorSelected(ContextCompat.getColor(this, android.R.color.holo_orange_light))
                .build())
        button.setOnClickListener {
            val intent = Intent(this, JavaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
    }
}
