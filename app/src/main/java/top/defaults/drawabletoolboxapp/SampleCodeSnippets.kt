package top.defaults.drawabletoolboxapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.view.Gravity
import top.defaults.drawabletoolbox.DrawableBuilder
import top.defaults.drawabletoolbox.StateListDrawableBuilder

fun samples(context: Context): List<DrawableSpec> {
    return listOf(
            DrawableSpec("Rounded Bordered", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .hairlineBordered()
                            .rounded()
                            .strokeColor(ContextCompat.getColor(context, R.color.colorPrimary))
                            .strokeColorPressed(ContextCompat.getColor(context, R.color.colorAccent))
                            .build()
                }
            }).forTextView(),
            DrawableSpec("Rounded Filled", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .solidColor(ContextCompat.getColor(context, R.color.colorPrimary))
                            .solidColorPressed(ContextCompat.getColor(context, R.color.colorAccent))
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rounded Gradient", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .useGradient()
                            .gradientType(GradientDrawable.LINEAR_GRADIENT)
                            .angle(90)
                            .startColor(ContextCompat.getColor(context, R.color.colorPrimary))
                            .endColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rounded Gradient State", object: DrawableFactory {
                override fun build(): Drawable {
                    val baseBuilder = DrawableBuilder()
                            .rectangle()
                            .rounded()
                            .useGradient()
                            .gradientType(GradientDrawable.LINEAR_GRADIENT)
                            .angle(90)
                    val normalState = baseBuilder
                            .startColor(ContextCompat.getColor(context, R.color.colorPrimary))
                            .endColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                            .build()
                    val pressedState = baseBuilder
                            .startColor(ContextCompat.getColor(context, R.color.colorAccent))
                            .endColor(ContextCompat.getColor(context, R.color.colorAccentDark))
                            .build()
                    return StateListDrawableBuilder()
                            .normal(normalState)
                            .pressed(pressedState)
                            .build()
                }
            }).forTextView().isDarkBackground(),
            DrawableSpec("Rotate & Scale the Ring", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(400)
                            .ring()
                            .useGradient()
                            .sweepGradient()
                            .rotate(0f, 360f)
                            .scale(0.5f)
                            .scaleGravity(Gravity.START or Gravity.TOP)
                            .build()
                }
            }),
            DrawableSpec("Rotate & Flip the Ring", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .ring()
                            .useGradient()
                            .sweepGradient()
                            .rotate(0f, 360f)
                            .flipVertical()
                            .build()
                }
            }),
            DrawableSpec("Oval", object: DrawableFactory {
                override fun build(): Drawable {
                    return DrawableBuilder()
                            .size(200)
                            .oval()
                            .solidColor(ContextCompat.getColor(context, R.color.colorPrimary))
                            .build()
                }
            })
    )
}
