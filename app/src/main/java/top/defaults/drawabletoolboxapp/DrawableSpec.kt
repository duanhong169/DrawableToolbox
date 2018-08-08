package top.defaults.drawabletoolboxapp

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable

class DrawableSpec(var name: String, private val factory: DrawableFactory, var type: Int = TYPE_IMAGE_VIEW_SOURCE) {

    var isDarkBackground: Boolean = false
    var initialLevel: Int = 10000
    var animationRepeatMode: Int = -1
    var animationEnabled: Boolean = false

    companion object {
        const val TYPE_IMAGE_VIEW_SOURCE = 0
        const val TYPE_TEXT_VIEW_BACKGROUND = 1
    }

    fun build(): Drawable {
        return factory.build()
    }

    fun forTextView() = apply { type = TYPE_TEXT_VIEW_BACKGROUND }
    fun isDarkBackground(boolean: Boolean = true) = apply { isDarkBackground = boolean }
    fun initialLevel(level: Int) = apply { initialLevel = level }
    fun animateRestart() = apply { animationRepeatMode = ValueAnimator.RESTART }
    fun animateReverse() = apply { animationRepeatMode = ValueAnimator.REVERSE }
    fun shouldAnimate(): Boolean { return animationEnabled && animationRepeatMode > 0 }
    fun canAnimate(): Boolean { return animationRepeatMode > 0 }
}