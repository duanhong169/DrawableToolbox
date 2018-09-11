package top.defaults.drawabletoolboxapp

import android.content.Context
import android.graphics.Color
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.Gravity
import top.defaults.drawabletoolbox.DrawableBuilder
import top.defaults.drawabletoolbox.LayerDrawableBuilder
import top.defaults.drawabletoolbox.PathShapeDrawableBuilder
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
            TextViewBackgroundDrawableSpec("Layer List: Several Borders", object : DrawableFactory {
                override fun build(): Drawable {
                    val layer1 = DrawableBuilder()
                            .size(200)
                            .rectangle()
                            .rounded()
                            .hairlineBordered()
                            .strokeColor(COLOR_DEFAULT)
                            .strokeColorPressed(COLOR_PRESSED)
                            .build()
                    val layer2 = DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .solidColor(COLOR_DEFAULT)
                            .build()
                    val layer3 = DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .solidColor(Color.WHITE)
                            .ripple()
                            .rippleColor(COLOR_DEFAULT)
                            .build()
                    return LayerDrawableBuilder()
                            .add(layer1)
                            .add(layer2)
                            .inset(10)
                            .add(layer3)
                            .inset(20)
                            .build()
                }
            }),
            ImageViewSourceDrawableSpec("Showcase: Layer List 1", object : DrawableFactory {
                override fun build(): Drawable {
                    val layer1 = DrawableBuilder()
                            .size(180)
                            .rectangle()
                            .build()
                    val layer2 = DrawableBuilder()
                            .oval()
                            .solidColor(COLOR_DEFAULT)
                            .build()
                    val layer3 = DrawableBuilder()
                            .rectangle()
                            .solidColor(COLOR_DEFAULT_DARK)
                            .rotate(45f)
                            .build()
                    val layer4 = DrawableBuilder()
                            .rectangle()
                            .bottomLeftRadius(100)
                            .solidColor(COLOR_DEFAULT_DARK)
                            .build()
                    val layer5 = DrawableBuilder()
                            .oval()
                            .solidColor(COLOR_DEFAULT)
                            .build()
                    val layerDrawable = LayerDrawableBuilder()
                            .add(layer1)
                            .add(layer2)
                            .inset(20, 20, 100, 100)
                            .add(layer3)
                            .inset(100, 20, 20, 100)
                            .add(layer4)
                            .inset(20, 100, 100, 20)
                            .add(layer5)
                            .inset(100, 100, 20, 20)
                            .build()
                    return DrawableBuilder()
                            .baseDrawable(layerDrawable)
                            .rotate(0f, 360f)
                            .build()
                }
            }).animateRestart().initialLevel(8750),
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
            ImageViewSourceDrawableSpec("Star", object : DrawableFactory {
                fun star(): Path {
                    val path = Path()
                    val half = 50
                    path.moveTo(half * 0.5f, half * 0.84f)
                    path.lineTo(half * 1.5f, half * 0.84f)
                    path.lineTo(half * 0.68f, half * 1.45f)
                    path.lineTo(half * 1.0f, half * 0.5f)
                    path.lineTo(half * 1.32f, half * 1.45f)
                    path.lineTo(half * 0.5f, half * 0.84f)
                    path.close()
                    return path
                }

                override fun build(): Drawable {
                    return PathShapeDrawableBuilder()
                            .path(star(), 100f, 100f)
                            .size(200)
                            .build {
                                it.paint.color = COLOR_DEFAULT
                            }
                }
            }),
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
