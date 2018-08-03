package top.defaults.drawabletoolbox

import android.graphics.drawable.Drawable

open class DrawableWrapperBuilder<T: DrawableWrapperBuilder<T>> {

    protected var drawable: Drawable? = null

    @Suppress("UNCHECKED_CAST")
    fun drawable(drawable: Drawable): T = apply { this.drawable = drawable } as T

}