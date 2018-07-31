package top.defaults.drawabletoolbox

import android.graphics.drawable.GradientDrawable
import android.os.Build

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

    fun strokeWidth(strokeWidth: Int) = apply { properties.strokeWidth = strokeWidth }
    fun strokeColor(strokeColor: Int) = apply { properties.strokeColor = strokeColor }
    fun dashWidth(dashWidth: Int) = apply { properties.dashWidth = dashWidth }
    fun dashGap(dashGap: Int) = apply { properties.dashGap = dashGap }

    fun build() : GradientDrawable {
        val drawable = GradientDrawable()

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
                drawable.setColor(solidColor)
            }
            drawable.setSize(width + strokeWidth, height + strokeWidth)
            drawable.setStroke(strokeWidth, strokeColor, dashWidth.toFloat(), dashGap.toFloat())
        }
        return drawable
    }
}