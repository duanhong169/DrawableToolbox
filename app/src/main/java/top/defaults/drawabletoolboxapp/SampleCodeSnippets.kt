package top.defaults.drawabletoolboxapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.Gravity
import top.defaults.drawabletoolbox.DrawableBuilder
import top.defaults.drawabletoolbox.StateListDrawableBuilder
import top.defaults.drawabletoolboxapp.spec.DrawableSpec
import top.defaults.drawabletoolboxapp.spec.ImageViewSourceDrawableSpec
import top.defaults.drawabletoolboxapp.spec.SegmentedControlDrawableSpec
import top.defaults.drawabletoolboxapp.spec.TextViewBackgroundDrawableSpec

const val COLOR_DEFAULT = 0xFFBA68C8.toInt()
const val COLOR_DEFAULT_DARK = 0xFF9C27B0.toInt()
const val COLOR_PRESSED = 0xFFF44336.toInt()

fun samples(context: Context): List<DrawableSpec> {
    return listOf(
            TextViewBackgroundDrawableSpec("Bordered with Ripple", object : DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .hairlineBordered()
                            .strokeColor(COLOR_DEFAULT)
                            .strokeColorPressed(COLOR_PRESSED)
                            .ripple()
                            .build()
                }
            }),
            TextViewBackgroundDrawableSpec("Medium-dashed, Bordered with Ripple", object : DrawableFactory {
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
            }),
            TextViewBackgroundDrawableSpec("Filled with States", object : DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .solidColor(COLOR_DEFAULT)
                            .solidColorPressed(COLOR_PRESSED)
                            .build()
                }
            }).isDarkBackground(),
            TextViewBackgroundDrawableSpec("Rounded, Filled with States", object : DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .solidColor(COLOR_DEFAULT)
                            .solidColorPressed(COLOR_PRESSED)
                            .build()
                }
            }).isDarkBackground(),
            TextViewBackgroundDrawableSpec("Rounded, Bordered with Ripple", object : DrawableFactory {
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
            }),
            TextViewBackgroundDrawableSpec("Rounded, Long-dashed, Bordered with Ripple", object : DrawableFactory {
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
            }),
            TextViewBackgroundDrawableSpec("Rounded, Filled with Ripple", object : DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .solidColor(COLOR_DEFAULT)
                            .ripple()
                            .rippleColor(COLOR_PRESSED)
                            .build()
                }
            }).isDarkBackground(),
            TextViewBackgroundDrawableSpec("Rounded, Gradient with Ripple", object : DrawableFactory {
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
            }).isDarkBackground(),
            TextViewBackgroundDrawableSpec("Rounded, Gradient with States", object : DrawableFactory {
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
            }).isDarkBackground(),
            SegmentedControlDrawableSpec("Segmented Control", object : DrawableFactory {

                override fun build(): Drawable = throw RuntimeException("Unsupported build with no index")

                override fun build(drawableSpec: DrawableSpec, index: Int): Drawable {
                    if (drawableSpec is SegmentedControlDrawableSpec) {
                        val type = drawableSpec.getType(index)

                        val baseBuilder = DrawableBuilder()
                                .rectangle()
                                .rounded()
                                .hairlineBordered()
                                .strokeColor(COLOR_DEFAULT)
                                .solidColorSelected(COLOR_DEFAULT)
                                .ripple()

                        return when(type) {
                            SegmentedControlDrawableSpec.TYPE_LEFT_MOST -> {
                                baseBuilder.topRightRadius(0)
                                        .bottomRightRadius(0)
                                        .build()
                            }
                            SegmentedControlDrawableSpec.TYPE_RIGHT_MOST -> {
                                baseBuilder.topLeftRadius(0)
                                        .bottomLeftRadius(0)
                                        .build()
                            }
                            else -> {
                                baseBuilder.cornerRadius(0).build()
                            }
                        }
                    } else {
                        throw RuntimeException("Unsupported drawableSpec")
                    }
                }

            }),
            ImageViewSourceDrawableSpec("Rotate & Leveled the Ring", object : DrawableFactory {
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
            ImageViewSourceDrawableSpec("Rotate & Sweep the Ring", object : DrawableFactory {
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
            ImageViewSourceDrawableSpec("Rotate, Sweep & Flip the Ring", object : DrawableFactory {
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
            ImageViewSourceDrawableSpec("Rotate, Sweep & Vertical Flip the Ring", object : DrawableFactory {
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
            ImageViewSourceDrawableSpec("Rotate, Sweep & Scale the Oval with States", object : DrawableFactory {
                override fun build(): Drawable {
                    val baseBuilder = DrawableBuilder()
                            .size(400)
                            .oval()
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
            ImageViewSourceDrawableSpec("Oval with States", object : DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .oval()
                            .solidColor(COLOR_DEFAULT)
                            .solidColorPressed(COLOR_PRESSED)
                            .build()
                }
            }),
            ImageViewSourceDrawableSpec("Oval with Radial", object : DrawableFactory {
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
