package top.defaults.drawabletoolboxapp

import android.graphics.drawable.Drawable

interface DrawableFactory {
    fun build(): Drawable
}