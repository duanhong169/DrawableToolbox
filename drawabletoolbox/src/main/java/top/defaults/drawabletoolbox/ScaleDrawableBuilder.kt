package top.defaults.drawabletoolbox

import android.graphics.drawable.Drawable
import android.graphics.drawable.ScaleDrawable
import android.view.Gravity

class ScaleDrawableBuilder: DrawableWrapperBuilder<ScaleDrawableBuilder>() {

    private var level: Int = 10000
    private var scaleGravity = Gravity.CENTER
    private var scaleWidth: Float = 0f
    private var scaleHeight: Float = 0f

    fun level(level: Int) = apply { this.level = level }
    fun scaleGravity(gravity: Int) = apply { this.scaleGravity = gravity }
    fun scaleWidth(scale: Float) = apply { this.scaleWidth = scale }
    fun scaleHeight(scale: Float) = apply { this.scaleHeight = scale }

    override fun build(): Drawable {
        val scaleDrawable = ScaleDrawable(drawable, scaleGravity, scaleWidth, scaleHeight)
        scaleDrawable.level = level
        return scaleDrawable
    }
}