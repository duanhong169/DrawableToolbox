package top.defaults.drawabletoolboxapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.Gravity
import top.defaults.drawabletoolbox.DrawableBuilder
import top.defaults.drawabletoolbox.StateListDrawableBuilder

const val COLOR_DEFAULT = 0xFFBA68C8.toInt()
const val COLOR_DEFAULT_DARK = 0xFF9C27B0.toInt()
const val COLOR_PRESSED = 0xFFF44336.toInt()

fun samples(context: Context): List<DrawableSpec> {
    return listOf(
            DrawableSpec("Bordered with Ripple", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .hairlineBordered()
                            .strokeColor(COLOR_DEFAULT)
                            .strokeColorPressed(COLOR_PRESSED)
                            .ripple()
                            .build()
                }
            }).forTextView(),
            DrawableSpec("Medium-dashed, Bordered with Ripple", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .hairlineBordered()
                            .mediumDashed()
                            .strokeColor(COLOR_DEFAULT)
                            .strokeColorPressed(COLOR_PRESSED)
                            .ripple()
                            .build()
                }
            }).forTextView(),
            DrawableSpec("Filled with States", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .solidColor(COLOR_DEFAULT)
                            .solidColorPressed(COLOR_PRESSED)
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rounded, Filled with States", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .solidColor(COLOR_DEFAULT)
                            .solidColorPressed(COLOR_PRESSED)
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rounded, Bordered with Ripple", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .hairlineBordered()
                            .rounded()
                            .strokeColor(COLOR_DEFAULT)
                            .strokeColorPressed(COLOR_PRESSED)
                            .ripple()
                            .build()
                }
            }).forTextView(),
            DrawableSpec("Rounded, Long-dashed, Bordered with Ripple", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .hairlineBordered()
                            .longDashed()
                            .rounded()
                            .strokeColor(COLOR_DEFAULT)
                            .strokeColorPressed(COLOR_PRESSED)
                            .ripple()
                            .build()
                }
            }).forTextView(),
            DrawableSpec("Rounded, Filled with Ripple", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .solidColor(COLOR_DEFAULT)
                            .ripple()
                            .rippleColor(COLOR_PRESSED)
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rounded, Gradient with Ripple", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .gradient()
                            .linearGradient()
                            .angle(90)
                            .startColor(COLOR_DEFAULT)
                            .endColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                            .ripple()
                            .rippleColor(COLOR_PRESSED)
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rounded, Gradient with States", object: DrawableFactory {
                override fun build(): Drawable {
                    val baseBuilder = DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .gradient()
                            .gradientType(GradientDrawable.LINEAR_GRADIENT)
                            .angle(90)
                    val normalState = baseBuilder
                            .startColor(COLOR_DEFAULT)
                            .endColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                            .build()
                    val pressedState = baseBuilder
                            .startColor(COLOR_PRESSED)
                            .endColor(ContextCompat.getColor(context, R.color.colorAccentDark))
                            .build()
                    return StateListDrawableBuilder()
                            .normal(normalState)
                            .pressed(pressedState)
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rotate & Leveled the Ring", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .ring()
                            .useLevelForRing()
                            .solidColor(COLOR_DEFAULT)
                            .innerRadiusRatio(3f)
                            .thicknessRatio(10f)
                            .rotate(0f, 720f)
                            .build()
                }
            }).animateReverse().initialLevel(5000),
            DrawableSpec("Rotate & Sweep the Ring", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .ring()
                            .innerRadiusRatio(3f)
                            .thicknessRatio(10f)
                            .gradient()
                            .sweepGradient()
                            .rotate(0f, 360f)
                            .build()
                }
            }).animateRestart(),
            DrawableSpec("Rotate, Sweep & Flip the Ring", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .ring()
                            .innerRadiusRatio(3f)
                            .thicknessRatio(10f)
                            .gradient()
                            .sweepGradient()
                            .rotate(0f, 360f)
                            .flip()
                            .build()
                }
            }).animateRestart(),
            DrawableSpec("Rotate, Sweep & Vertical Flip the Ring", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .ring()
                            .innerRadiusRatio(3f)
                            .thicknessRatio(10f)
                            .gradient()
                            .sweepGradient()
                            .rotate(0f, 360f)
                            .flipVertical()
                            .build()
                }
            }).animateRestart(),
            DrawableSpec("Rotate, Sweep & Scale the Ring", object: DrawableFactory {
                override fun build(): Drawable {
                    val baseBuilder =  DrawableBuilder()
                            .size(400)
                            .ring()
                            .gradient()
                            .sweepGradient()
                            .rotate(0f, 360f)
                            .scale(0.5f)
                            .scaleGravity(Gravity.START or Gravity.TOP)
                    val normalState = baseBuilder.build()
                    val pressedState = baseBuilder
                            .startColor(COLOR_PRESSED)
                            .endColor(0x7FFFFFFF)
                            .build()
                    return StateListDrawableBuilder()
                            .normal(normalState)
                            .pressed(pressedState)
                            .build()
                }
            }).animateReverse(),
            DrawableSpec("Oval with States", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .oval()
                            .solidColor(COLOR_DEFAULT)
                            .solidColorPressed(COLOR_PRESSED)
                            .build()
                }
            }),
            DrawableSpec("Oval with Radial", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .oval()
                            .gradient()
                            .radialGradient()
                            .build()
                }
            })
    )
}
