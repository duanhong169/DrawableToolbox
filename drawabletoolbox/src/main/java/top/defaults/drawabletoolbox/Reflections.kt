package top.defaults.drawabletoolbox

import android.graphics.drawable.GradientDrawable
import java.lang.reflect.Field
import java.lang.reflect.Method

private val gradientState = resolveGradientState()

private fun resolveGradientState(): Class<*> {
    val classes = GradientDrawable::class.java.declaredClasses
    for (singleClass in classes) {
        if (singleClass.simpleName == "GradientState") return singleClass
    }
    throw RuntimeException("GradientState could not be found in current GradientDrawable implementation")
}

@Throws(SecurityException::class, NoSuchFieldException::class)
private fun resolveField(source: Class<*>, fieldName: String): Field {
    val field = source.getDeclaredField(fieldName)
    field.isAccessible = true
    return field
}

@Throws(SecurityException::class, NoSuchMethodException::class)
private fun resolveMethod(source: Class<*>, methodName: String, vararg parameterTypes: Class<*>): Method {
    val method = source.getDeclaredMethod(methodName, *parameterTypes)
    method.isAccessible = true
    return method
}

fun setInnerRadius(drawable: GradientDrawable, value: Int) {
    try {
        val innerRadius = resolveField(gradientState, "mInnerRadius")
        innerRadius.setInt(drawable.constantState, value)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setInnerRadiusRatio(drawable: GradientDrawable, value: Float) {
    try {
        val innerRadius = resolveField(gradientState, "mInnerRadiusRatio")
        innerRadius.setFloat(drawable.constantState, value)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setThickness(drawable: GradientDrawable, value: Int) {
    try {
        val innerRadius = resolveField(gradientState, "mThickness")
        innerRadius.setInt(drawable.constantState, value)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setThicknessRatio(drawable: GradientDrawable, value: Float) {
    try {
        val innerRadius = resolveField(gradientState, "mThicknessRatio")
        innerRadius.setFloat(drawable.constantState, value)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setUseLevelForShape(drawable: GradientDrawable, value: Boolean) {
    try {
        val useLevelForShape = resolveField(gradientState, "mUseLevelForShape")
        useLevelForShape.setBoolean(drawable.constantState, value)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setOrientation(drawable: GradientDrawable, value: GradientDrawable.Orientation) {
    try {
        val orientation = resolveField(gradientState, "mOrientation")
        orientation.set(drawable.constantState, value)
        val rectIdDirty = resolveField(GradientDrawable::class.java, "mRectIsDirty")
        rectIdDirty.setBoolean(drawable, true)
        drawable.invalidateSelf()
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setColors(drawable: GradientDrawable, value: IntArray) {
    try {
        val colors = resolveField(gradientState, "mColors")
        colors.set(drawable.constantState, value)
        drawable.invalidateSelf()
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setGradientRadiusType(drawable: GradientDrawable, value: Int) {
    try {
        val type = resolveField(gradientState, "mGradientRadiusType")
        type.setInt(drawable.constantState, value)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}

fun setGradientRadius(drawable: GradientDrawable, value: Float) {
    try {
        val gradientRadius = resolveField(gradientState, "mGradientRadius")
        gradientRadius.setFloat(drawable.constantState, value)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}