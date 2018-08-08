package top.defaults.drawabletoolboxapp

import android.graphics.drawable.Drawable
import top.defaults.drawabletoolboxapp.spec.DrawableSpec

interface DrawableFactory {

    fun build(drawableSpec: DrawableSpec, index: Int = 0): Drawable = build()

    fun build(): Drawable
}