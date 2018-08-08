package top.defaults.drawabletoolboxapp.spec

import top.defaults.drawabletoolboxapp.DrawableFactory

class SegmentedControlDrawableSpec(name: String, factory: DrawableFactory) : DrawableSpec(name, factory) {

    companion object {
        const val TYPE_LEFT_MOST = 1
        const val TYPE_MIDDLE = 2
        const val TYPE_RIGHT_MOST = 3
    }

    var itemCount = 3
    var selectedIndex: Int = 0

    fun getType(index: Int): Int {
        return when(index) {
            0 -> TYPE_LEFT_MOST
            itemCount - 1 -> TYPE_RIGHT_MOST
            else -> TYPE_MIDDLE
        }
    }
}