package top.defaults.drawabletoolbox

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Parcel
import android.os.Parcelable

class DrawableProperties (
        var shape: Int = GradientDrawable.RECTANGLE,
        var innerRadius: Int = -1,
        var innerRadiusRatio: Float = 9f,
        var thickness: Int = -1,
        var thicknessRatio: Float = 3f,

        cornerRadius: Int = 0,
        var topLeftRadius: Int = 0,
        var topRightRadius: Int = 0,
        var bottomRightRadius: Int = 0,
        var bottomLeftRadius: Int = 0,

        var useGradient: Boolean = false,
        var type: Int = GradientDrawable.RADIAL_GRADIENT,
        var angle: Int = 0,
        var centerX: Float = 0.5f,
        var centerY: Float = 0.5f,
        var useCenterColor: Boolean = true,
        var startColor: Int = DEFAULT_COLOR,
        var centerColor: Int = 0xFFFFFFFF.toInt(),
        var endColor: Int = 0x7FFFFFFF,
        var gradientRadiusType: Int = RADIUS_TYPE_FRACTION,
        var gradientRadius: Float = 0.5f,

        var width: Int = -1,
        var height: Int = -1,

        var solidColor: Int = Color.TRANSPARENT,
        var solidColorStateList: ColorStateList? = null,

        var strokeWidth: Int = 0,
        var strokeColor: Int = Color.DKGRAY,
        var strokeColorStateList: ColorStateList? = null,
        var dashWidth: Int = 0,
        var dashGap: Int = 0
) : Parcelable {

    companion object {
        const val RADIUS_TYPE_PIXELS = 0
        const val RADIUS_TYPE_FRACTION = 1

        internal const val DEFAULT_COLOR = 0xFF2DCFCA.toInt()

        @JvmField val CREATOR = object : Parcelable.Creator<DrawableProperties> {
            override fun createFromParcel(parcel: Parcel): DrawableProperties {
                return DrawableProperties(parcel)
            }

            override fun newArray(size: Int): Array<DrawableProperties?> {
                return arrayOfNulls(size)
            }
        }
    }

    var cornerRadius: Int = cornerRadius
        set(value) {
            field = value
            topLeftRadius = value
            topRightRadius = value
            bottomRightRadius = value
            bottomLeftRadius = value
        }

    init {
        this.cornerRadius = cornerRadius
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readFloat(),
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
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readFloat(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(ColorStateList::class.java.classLoader),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(ColorStateList::class.java.classLoader),
            parcel.readInt(),
            parcel.readInt())

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
        return if (useCenterColor) {
            intArrayOf(startColor, centerColor, endColor)
        } else intArrayOf(startColor, endColor)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(shape)
        parcel.writeInt(innerRadius)
        parcel.writeFloat(innerRadiusRatio)
        parcel.writeInt(thickness)
        parcel.writeFloat(thicknessRatio)
        parcel.writeInt(cornerRadius)
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
        parcel.writeInt(centerColor)
        parcel.writeInt(endColor)
        parcel.writeInt(gradientRadiusType)
        parcel.writeFloat(gradientRadius)
        parcel.writeInt(width)
        parcel.writeInt(height)
        parcel.writeInt(solidColor)
        parcel.writeParcelable(solidColorStateList, flags)
        parcel.writeInt(strokeWidth)
        parcel.writeInt(strokeColor)
        parcel.writeParcelable(strokeColorStateList, flags)
        parcel.writeInt(dashWidth)
        parcel.writeInt(dashGap)
    }

    override fun describeContents(): Int {
        return 0
    }
}