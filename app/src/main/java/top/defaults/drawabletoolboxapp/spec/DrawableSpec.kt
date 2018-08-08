package top.defaults.drawabletoolboxapp.spec

import android.graphics.drawable.Drawable
import top.defaults.drawabletoolboxapp.DrawableFactory

open class DrawableSpec(var name: String, private val factory: DrawableFactory) {

    open fun build(index: Int = 0): Drawable = factory.build(this, index)
}