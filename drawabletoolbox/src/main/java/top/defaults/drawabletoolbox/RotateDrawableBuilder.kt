package top.defaults.drawabletoolbox

import android.graphics.drawable.Drawable
import android.graphics.drawable.RotateDrawable

class RotateDrawableBuilder: DrawableWrapperBuilder<RotateDrawableBuilder>() {

    private var pivotX: Float = 0.5f
    private var pivotY: Float = 0.5f
    private var fromDegrees: Float = 0f
    private var toDegrees: Float = 360f

    fun pivotX(x: Float) = apply { pivotX = x }
    fun pivotY(y: Float) = apply { pivotY = y }
    fun fromDegrees(degree: Float) = apply { fromDegrees = degree }
    fun toDegrees(degree: Float) = apply { toDegrees = degree }

    override fun build(): Drawable {
        val rotateDrawable = RotateDrawable()
        drawable?.let {
            setDrawable(rotateDrawable, it)
            apply {
                setPivotX(rotateDrawable, pivotX)
                setPivotY(rotateDrawable, pivotY)
                setFromDegrees(rotateDrawable, fromDegrees)
                setToDegrees(rotateDrawable, toDegrees)
            }
        }
        return rotateDrawable
    }
}