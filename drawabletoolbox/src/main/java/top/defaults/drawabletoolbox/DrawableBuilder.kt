package top.defaults.drawabletoolbox

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.StateSet

class DrawableBuilder {

    private var properties = DrawableProperties()

    fun properties(properties: DrawableProperties) = apply { this.properties = properties.copy() }
    fun shape(shape: Int) = apply { properties.shape = shape }
    fun innerRadius(innerRadius: Int) = apply { properties.innerRadius = innerRadius }
    fun innerRadiusRatio(innerRadiusRatio: Float) = apply { properties.innerRadiusRatio = innerRadiusRatio }
    fun thickness(thickness: Int) = apply { properties.thickness = thickness }
    fun thicknessRatio(thicknessRatio: Float) = apply { properties.thicknessRatio = thicknessRatio }

    fun cornerRadius(cornerRadius: Int) = apply { properties.cornerRadius = cornerRadius }
    fun topLeftRadius(topLeftRadius: Int) = apply { properties.topLeftRadius = topLeftRadius }
    fun topRightRadius(topRightRadius: Int) = apply { properties.topRightRadius = topRightRadius }
    fun bottomRightRadius(bottomRightRadius: Int) = apply { properties.bottomRightRadius = bottomRightRadius }
    fun bottomLeftRadius(bottomLeftRadius: Int) = apply { properties.bottomLeftRadius = bottomLeftRadius }

    fun useGradient(useGradient: Boolean) = apply { properties.useGradient = useGradient }
    fun type(type: Int) = apply { properties.type = type }
    fun angle(angle: Int) = apply { properties.angle = angle }
    fun centerX(centerX: Float) = apply { properties.centerX = centerX }
    fun centerY(centerY: Float) = apply { properties.centerY = centerY }
    fun useCenterColor(useCenterColor: Boolean) = apply { properties.useCenterColor = useCenterColor }
    fun startColor(startColor: Int) = apply { properties.startColor = startColor }
    fun centerColor(centerColor: Int) = apply { properties.centerColor = centerColor }
    fun endColor(endColor: Int) = apply { properties.endColor = endColor }
    fun gradientRadiusType(gradientRadiusType: Int) = apply { properties.gradientRadiusType = gradientRadiusType }
    fun gradientRadius(gradientRadius: Float) = apply { properties.gradientRadius = gradientRadius }

    fun width(width: Int) = apply { properties.width = width }
    fun height(height: Int) = apply { properties.height = height }

    fun solidColor(solidColor: Int) = apply { properties.solidColor = solidColor }
    private var solidColorPressed: Int? = null
    fun solidColorPressed(color: Int?) = apply { solidColorPressed = color }
    private var solidColorDisabled: Int? = null
    fun solidColorDisabled(color: Int?) = apply { solidColorDisabled = color }
    private var solidColorSelected: Int? = null
    fun solidColorSelected(color: Int?) = apply { solidColorSelected = color }

    fun strokeWidth(strokeWidth: Int) = apply { properties.strokeWidth = strokeWidth }
    fun strokeColor(strokeColor: Int) = apply { properties.strokeColor = strokeColor }
    private var strokeColorPressed: Int? = null
    fun strokeColorPressed(color: Int?) = apply { strokeColorPressed = color }
    private var strokeColorDisabled: Int? = null
    fun strokeColorDisabled(color: Int?) = apply { strokeColorDisabled = color }
    private var strokeColorSelected: Int? = null
    fun strokeColorSelected(color: Int?) = apply { strokeColorSelected = color }
    fun dashWidth(dashWidth: Int) = apply { properties.dashWidth = dashWidth }
    fun dashGap(dashGap: Int) = apply { properties.dashGap = dashGap }

    fun build(): Drawable {
        val drawable: Drawable
        if (needStateListDrawable()) {
            drawable = StateListDrawableBuilder()
                    .pressed(buildPressedDrawable())
                    .disabled(buildDisabledDrawable())
                    .selected(buildSelectedDrawable())
                    .normal(buildNormalDrawable())
                    .build()
        } else {
            drawable = GradientDrawable()
            setupGradientDrawable(drawable)
        }
        return drawable
    }

    private fun getSolidColorStateList(): ColorStateList {
        val states = mutableListOf<IntArray>()
        val colors = mutableListOf<Int>()

        solidColorPressed?.let {
            states.add(intArrayOf(android.R.attr.state_pressed))
            colors.add(it)
        }
        solidColorDisabled?.let {
            states.add(intArrayOf(-android.R.attr.state_enabled))
            colors.add(it)
        }
        solidColorSelected?.let {
            states.add(intArrayOf(android.R.attr.state_selected))
            colors.add(it)
        }
        states.add(StateSet.WILD_CARD)
        colors.add(properties.solidColor)

        return ColorStateList(states.toTypedArray(), colors.toIntArray())
    }

    private fun getStrokeColorStateList(): ColorStateList {
        val states = mutableListOf<IntArray>()
        val colors = mutableListOf<Int>()

        strokeColorPressed?.let {
            states.add(intArrayOf(android.R.attr.state_pressed))
            colors.add(it)
        }
        strokeColorDisabled?.let {
            states.add(intArrayOf(-android.R.attr.state_enabled))
            colors.add(it)
        }
        strokeColorSelected?.let {
            states.add(intArrayOf(android.R.attr.state_selected))
            colors.add(it)
        }
        states.add(StateSet.WILD_CARD)
        colors.add(properties.strokeColor)

        return ColorStateList(states.toTypedArray(), colors.toIntArray())
    }

    private fun buildPressedDrawable(): Drawable? {
        if (solidColorPressed == null && strokeColorPressed == null) return null

        val pressedDrawable = GradientDrawable()
        setupGradientDrawable(pressedDrawable)
        solidColorPressed?.let {
            pressedDrawable.setColor(it)
        }
        strokeColorPressed?.let {
            pressedDrawable.setColor(it)
        }
        return pressedDrawable
    }

    private fun buildDisabledDrawable(): Drawable? {
        if (solidColorDisabled == null && strokeColorDisabled == null) return null

        val disabledDrawable = GradientDrawable()
        setupGradientDrawable(disabledDrawable)
        solidColorDisabled?.let {
            disabledDrawable.setColor(it)
        }
        strokeColorDisabled?.let {
            disabledDrawable.setColor(it)
        }
        return disabledDrawable
    }

    private fun buildSelectedDrawable(): Drawable? {
        if (solidColorSelected == null && strokeColorSelected == null) return null

        val selectedDrawable = GradientDrawable()
        setupGradientDrawable(selectedDrawable)
        solidColorSelected?.let {
            selectedDrawable.setColor(it)
        }
        strokeColorSelected?.let {
            selectedDrawable.setColor(it)
        }
        return selectedDrawable
    }

    private fun buildNormalDrawable(): Drawable {
        val pressedDrawable = GradientDrawable()
        setupGradientDrawable(pressedDrawable)
        return pressedDrawable
    }

    private fun setupGradientDrawable(drawable: GradientDrawable) {
        properties.apply {
            drawable.shape = shape
            if (shape == GradientDrawable.RING) {
                setInnerRadius(drawable, innerRadius)
                setInnerRadiusRatio(drawable, innerRadiusRatio)
                setThickness(drawable, thickness)
                setThicknessRatio(drawable, thicknessRatio)
                setUseLevelForShape(drawable, false)
            }
            drawable.cornerRadii = getCornerRadii()
            if (useGradient) {
                drawable.gradientType = type
                drawable.gradientRadius = gradientRadius
                drawable.setGradientCenter(centerX, centerY)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    drawable.orientation = getOrientation()
                    drawable.colors = getColors()
                } else {
                    setOrientation(drawable, getOrientation())
                    setColors(drawable, getColors())
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    drawable.color = getSolidColorStateList()
                } else {
                    drawable.setColor(solidColor)
                }
            }
            drawable.setSize(width + strokeWidth, height + strokeWidth)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable.setStroke(strokeWidth, getStrokeColorStateList(), dashWidth.toFloat(), dashGap.toFloat())
            } else {
                drawable.setStroke(strokeWidth, strokeColor, dashWidth.toFloat(), dashGap.toFloat())
            }
        }
    }

    private fun needStateListDrawable(): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                && (hasStrokeColorStateList() || (!properties.useGradient && hasSolidColorStateList()))
    }

    private fun hasSolidColorStateList(): Boolean {
        return solidColorPressed != null || solidColorDisabled != null || solidColorSelected != null
    }

    private fun hasStrokeColorStateList(): Boolean {
        return strokeColorPressed != null || strokeColorDisabled != null || strokeColorSelected != null
    }
}