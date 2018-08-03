package top.defaults.drawabletoolboxapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Button
import top.defaults.drawabletoolbox.DrawableBuilder
import android.util.TypedValue
import android.view.animation.BounceInterpolator
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val drawable = DrawableBuilder()
                .ring()
//                .useLevelForRing()
                .useGradient()
//                .useLevelForGradient()
                .sweepGradient()
                .gradientRadiusInFraction(0.5f)
                .rotate(0f, 360f)
                .build()
        val animator = ObjectAnimator.ofInt(imageView, "imageLevel", 10000, 0)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.duration = 3000
        animator.interpolator = BounceInterpolator()
        animator.start()
        imageView.setImageDrawable(drawable)

        val button = findViewById<Button>(R.id.javaVersion)
        val backgroundDrawableBuilder = DrawableBuilder()
                .rectangle()
                .rounded()
                .strokeWidth(dpToPx(0.5f))
                .dashed()
                .strokeColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .strokeColorPressed(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        button.setBackgroundDrawable(backgroundDrawableBuilder.build())
        button.setOnClickListener {
            val intent = Intent(this, JavaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()
    }
}
