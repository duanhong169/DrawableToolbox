package top.defaults.drawabletoolbox

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Parcel
import android.os.Parcelable
import android.view.Gravity

data class DrawableProperties (

        // <shape>
        @JvmField var shape: Int = GradientDrawable.RECTANGLE,
        @JvmField var innerRadius: Int = -1,
        @JvmField var innerRadiusRatio: Float = 9f,
        @JvmField var thickness: Int = -1,
        @JvmField var thicknessRatio: Float = 3f,
        @JvmField var useLevelForRing: Boolean = false,

        // <corner>
        private var _cornerRadius: Int = 0,
        @JvmField var topLeftRadius: Int = 0,
        @JvmField var topRightRadius: Int = 0,
        @JvmField var bottomRightRadius: Int = 0,
        @JvmField var bottomLeftRadius: Int = 0,

        // <gradient>
        @JvmField var useGradient: Boolean = false,
        @JvmField var type: Int = GradientDrawable.RADIAL_GRADIENT,
        @JvmField var angle: Int = 0,
        @JvmField var centerX: Float = 0.5f,
        @JvmField var centerY: Float = 0.5f,
        @JvmField var useCenterColor: Boolean = false,
        @JvmField var startColor: Int = Constants.DEFAULT_COLOR,
        @JvmField var centerColor: Int? = null,
        @JvmField var endColor: Int = 0x7FFFFFFF,
        @JvmField var gradientRadiusType: Int = RADIUS_TYPE_FRACTION,
        @JvmField var gradientRadius: Float = 0.5f,
        @JvmField var useLevelForGradient: Boolean = false,

        // <size>
        @JvmField var width: Int = -1,
        @JvmField var height: Int = -1,

        // <solid>
        @JvmField var solidColor: Int = Color.TRANSPARENT,
        @JvmField var solidColorStateList: ColorStateList? = null,

        // <stroke>
        @JvmField var strokeWidth: Int = 0,
        @JvmField var strokeColor: Int = Color.DKGRAY,
        @JvmField var strokeColorStateList: ColorStateList? = null,
        @JvmField var dashWidth: Int = 0,
        @JvmField var dashGap: Int = 0,

        // <rotate>
        @JvmField var useRotate: Boolean = false,
        @JvmField var pivotX: Float = 0.5f,
        @JvmField var pivotY: Float = 0.5f,
        @JvmField var fromDegrees: Float = 0f,
        @JvmField var toDegrees: Float = 0f,

        // <scale>
        @JvmField var useScale: Boolean = false,
        @JvmField var scaleLevel: Int = 10000,
        @JvmField var scaleGravity: Int = Gravity.CENTER,
        @JvmField var scaleWidth: Float = 0f,
        @JvmField var scaleHeight: Float = 0f,

        // flip
        @JvmField var useFlip: Boolean = false,
        @JvmField var orientation: Int = FlipDrawable.ORIENTATION_HORIZONTAL,

        // ripple
        @JvmField var useRipple: Boolean = false,
        @JvmField var rippleColor: Int = Constants.DEFAULT_COLOR,
        @JvmField var rippleColorStateList: ColorStateList? = null,
        @JvmField var rippleRadius: Int = -1
) : Parcelable {

    companion object {
        const val RADIUS_TYPE_PIXELS = 0
        const val RADIUS_TYPE_FRACTION = 1

        @JvmField val CREATOR = object : Parcelable.Creator<DrawableProperties> {
            override fun createFromParcel(parcel: Parcel): DrawableProperties {
                return DrawableProperties(parcel)
            }

            override fun newArray(size: Int): Array<DrawableProperties?> {
                return arrayOfNulls(size)
            }
        }
    }

    var cornerRadius: Int = _cornerRadius
        set(value) {
            _cornerRadius = value
            topLeftRadius = value
            topRightRadius = value
            bottomRightRadius = value
            bottomLeftRadius = value
        }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(ColorStateList::class.java.classLoader),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(ColorStateList::class.java.classLoader),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readParcelable(ColorStateList::class.java.classLoader),
            parcel.readInt()) {
    }

    fun copy(): DrawableProperties {
        val parcel = Parcel.obtain()
        writeToParcel(parcel, 0)
        parcel.setDataPosition(0)
        val properties = DrawableProperties.CREATOR.createFromParcel(parcel)
        parcel.recycle()
        return properties
    }

    fun getCornerRadii(): FloatArray {
        return floatArrayOf(topLeftRadius.toFloat(), topLeftRadius.toFloat(),
                topRightRadius.toFloat(), topRightRadius.toFloat(),
                bottomRightRadius.toFloat(), bottomRightRadius.toFloat(),
                bottomLeftRadius.toFloat(), bottomLeftRadius.toFloat())
    }

    fun getOrientation(): GradientDrawable.Orientation {
        val angle = this.angle % 360
        val orientation: GradientDrawable.Orientation
        orientation = when (angle) {
            0 -> GradientDrawable.Orientation.LEFT_RIGHT
            45 -> GradientDrawable.Orientation.BL_TR
            90 -> GradientDrawable.Orientation.BOTTOM_TOP
            135 -> GradientDrawable.Orientation.BR_TL
            180 -> GradientDrawable.Orientation.RIGHT_LEFT
            225 -> GradientDrawable.Orientation.TR_BL
            270 -> GradientDrawable.Orientation.TOP_BOTTOM
            315 -> GradientDrawable.Orientation.TL_BR
            else -> throw IllegalArgumentException("Unsupported angle: $angle")
        }
        return orientation
    }

    fun getColors(): IntArray {
        return if (useCenterColor && centerColor != null) {
            intArrayOf(startColor, centerColor!!, endColor)
        } else intArrayOf(startColor, endColor)
    }

    fun materialization(): Drawable = DrawableBuilder().batch(this).build()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(shape)
        parcel.writeInt(innerRadius)
        parcel.writeFloat(innerRadiusRatio)
        parcel.writeInt(thickness)
        parcel.writeFloat(thicknessRatio)
        parcel.writeByte(if (useLevelForRing) 1 else 0)
        parcel.writeInt(_cornerRadius)
        parcel.writeInt(topLeftRadius)
        parcel.writeInt(topRightRadius)
        parcel.writeInt(bottomRightRadius)
        parcel.writeInt(bottomLeftRadius)
        parcel.writeByte(if (useGradient) 1 else 0)
        parcel.writeInt(type)
        parcel.writeInt(angle)
        parcel.writeFloat(centerX)
        parcel.writeFloat(centerY)
        parcel.writeByte(if (useCenterColor) 1 else 0)
        parcel.writeInt(startColor)
        parcel.writeValue(centerColor)
        parcel.writeInt(endColor)
        parcel.writeInt(gradientRadiusType)
        parcel.writeFloat(gradientRadius)
        parcel.writeByte(if (useLevelForGradient) 1 else 0)
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeInt(solidColor)
        parcel.writeParcelable(solidColorStateList, flags)
        parcel.writeInt(strokeWidth)
        parcel.writeInt(strokeColor)
        parcel.writeParcelable(strokeColorStateList, flags)
        parcel.writeInt(dashWidth)
        parcel.writeInt(dashGap)
        parcel.writeByte(if (useRotate) 1 else 0)
        parcel.writeFloat(pivotX)
        parcel.writeFloat(pivotY)
        parcel.writeFloat(fromDegrees)
        parcel.writeFloat(toDegrees)
        parcel.writeByte(if (useScale) 1 else 0)
        parcel.writeInt(scaleLevel)
        parcel.writeInt(scaleGravity)
        parcel.writeFloat(scaleWidth)
        parcel.writeFloat(scaleHeight)
        parcel.writeByte(if (useFlip) 1 else 0)
        parcel.writeInt(orientation)
        parcel.writeByte(if (useRipple) 1 else 0)
        parcel.writeInt(rippleColor)
        parcel.writeParcelable(rippleColorStateList, flags)
        parcel.writeInt(rippleRadius)
    }

    override fun describeContents(): Int {
        return 0
    }
}