package top.defaults.drawabletoolboxapp.spec

import android.animation.ValueAnimator
import top.defaults.drawabletoolboxapp.DrawableFactory

class ImageViewSourceDrawableSpec(name: String, factory: DrawableFactory) : DrawableSpec(name, factory) {

    var initialLevel: Int = 10000
    var animationRepeatMode: Int = -1
    var animationEnabled: Boolean = false

    fun initialLevel(level: Int) = apply { initialLevel = level }
    fun animateRestart() = apply { animationRepeatMode = ValueAnimator.RESTART }
    fun animateReverse() = apply { animationRepeatMode = ValueAnimator.REVERSE }
    fun shouldAnimate(): Boolean { return animationEnabled && animationRepeatMode > 0 }
    fun canAnimate(): Boolean { return animationRepeatMode > 0 }
}