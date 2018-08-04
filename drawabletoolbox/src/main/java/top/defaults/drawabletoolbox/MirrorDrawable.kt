package top.defaults.drawabletoolbox

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Rect
import android.graphics.drawable.Drawable

class MirrorDrawable(private var drawable: Drawable) : Drawable() {

    override fun draw(canvas: Canvas) {
        val saveCount = canvas.save()
        canvas.scale(-1f, 1f, (canvas.width / 2).toFloat(), (canvas.height / 2).toFloat())
        drawable.bounds = Rect(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        canvas.restoreToCount(saveCount)
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