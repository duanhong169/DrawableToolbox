package top.defaults.drawabletoolbox

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.*
import android.os.Build
import android.util.StateSet

class RippleDrawableBuilder: DrawableWrapperBuilder<RippleDrawableBuilder>() {

    private var color: Int = Constants.DEFAULT_COLOR
    private var colorStateList: ColorStateList? = null
    private var radius: Int = -1

    fun color(color: Int) = apply { this.color = color }
    fun colorStateList(colorStateList: ColorStateList?) = apply { this.colorStateList = colorStateList }
    fun radius(radius: Int) = apply { this.radius = radius }

    override fun build(): Drawable {
        var drawable = this.drawable!!
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val colorStateList = this.colorStateList ?: ColorStateList(
                    arrayOf(StateSet.WILD_CARD),
                    intArrayOf(color)
            )

            var mask = if (drawable is DrawableContainer) drawable.getCurrent() else drawable
            if (mask is ShapeDrawable) {
                val state = mask.getConstantState()
                if (state != null) {
                    val temp = state.newDrawable().mutate() as ShapeDrawable
                    temp.paint.color = Color.BLACK
                    mask = temp
                }
            } else if (mask is GradientDrawable) {
                val state = mask.getConstantState()
                if (state != null) {
                    val temp = state.newDrawable().mutate() as GradientDrawable
                    temp.setColor(Color.BLACK)
                    mask = temp
                }
            } else {
                mask = ColorDrawable(Color.BLACK)
            }

            val rippleDrawable = RippleDrawable(colorStateList, drawable, mask)
            setRadius(rippleDrawable, radius)
            rippleDrawable.invalidateSelf()
            drawable = rippleDrawable
        }
        return drawable
    }
}