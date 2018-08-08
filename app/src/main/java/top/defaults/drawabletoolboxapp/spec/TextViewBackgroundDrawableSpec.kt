package top.defaults.drawabletoolboxapp.spec

import top.defaults.drawabletoolboxapp.DrawableFactory

class TextViewBackgroundDrawableSpec(name: String, factory: DrawableFactory) : DrawableSpec(name, factory) {

    var isDarkBackground: Boolean = false
    fun isDarkBackground(boolean: Boolean = true) = apply { isDarkBackground = boolean }
}