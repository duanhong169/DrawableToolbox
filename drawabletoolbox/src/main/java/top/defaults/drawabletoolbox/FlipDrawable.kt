package top.defaults.drawabletoolbox

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Rect
import android.graphics.drawable.Drawable

class FlipDrawable(private var drawable: Drawable, private var orientation: Int = ORIENTATION_HORIZONTAL) : Drawable() {

    companion object {
        const val ORIENTATION_HORIZONTAL = 0
        const val ORIENTATION_VERTICAL = 1
    }

    override fun draw(canvas: Canvas) {
        val saveCount = canvas.save()
        if (orientation == ORIENTATION_VERTICAL) {
            canvas.scale(1f, -1f, (canvas.width / 2).toFloat(), (canvas.height / 2).toFloat())
        } else {
            canvas.scale(-1f, 1f, (canvas.width / 2).toFloat(), (canvas.height / 2).toFloat())
        }
        drawable.bounds = Rect(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        canvas.restoreToCount(saveCount)
    }

    override fun onLevelChange(level: Int): Boolean {
        drawable.level = level
        invalidateSelf()
        return true
    }

    override fun getIntrinsicWidth(): Int {
        return drawable.intrinsicWidth
    }

    override fun getIntrinsicHeight(): Int {
        return drawable.intrinsicHeight
    }

    override fun setAlpha(alpha: Int) {
        drawable.alpha = alpha
    }

    override fun getOpacity(): Int {
        return drawable.opacity
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        drawable.colorFilter = colorFilter
    }
}