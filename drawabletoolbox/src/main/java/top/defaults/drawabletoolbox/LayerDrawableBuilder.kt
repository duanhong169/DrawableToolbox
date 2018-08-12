package top.defaults.drawabletoolbox

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.Gravity
import java.util.ArrayList

class LayerDrawableBuilder {

    companion object {
        const val INSET_UNDEFINED = Int.MIN_VALUE
        const val SIZE_UNDEFINED = Int.MIN_VALUE
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private var paddingMode = LayerDrawable.PADDING_MODE_NEST
    private var paddingLeft = 0
    private var paddingTop = 0
    private var paddingRight = 0
    private var paddingBottom = 0
    private var paddingStart = 0
    private var paddingEnd = 0
    private val layers = ArrayList<Layer>()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun paddingMode(mode: Int) = apply { paddingMode = mode }
    fun paddingLeft(padding: Int) = apply { paddingLeft = padding }
    fun paddingTop(padding: Int) = apply { paddingTop = padding }
    fun paddingRight(padding: Int) = apply { paddingRight = padding }
    fun paddingBottom(padding: Int) = apply { paddingBottom = padding }
    fun paddingStart(padding: Int) = apply { paddingStart = padding }
    fun paddingEnd(padding: Int) = apply { paddingEnd = padding }
    fun add(drawable: Drawable) = apply { layers.add(Layer(drawable)) }
    fun width(width: Int) = apply { layers.last().width = width }
    fun height(height: Int) = apply { layers.last().height = height }
    fun insetLeft(inset: Int) = apply { layers.last().insetLeft = inset }
    fun insetTop(inset: Int) = apply { layers.last().insetTop = inset }
    fun insetRight(inset: Int) = apply { layers.last().insetRight = inset }
    fun insetBottom(inset: Int) = apply { layers.last().insetBottom = inset }
    @RequiresApi(Build.VERSION_CODES.M)
    fun gravity(gravity: Int) = apply { layers.last().gravity = gravity }
    @RequiresApi(Build.VERSION_CODES.M)
    fun insetStart(inset: Int) = apply { layers.last().insetStart = inset }
    @RequiresApi(Build.VERSION_CODES.M)
    fun insetEnd(inset: Int) = apply { layers.last().insetEnd = inset }

    fun modify(index: Int, drawable: Drawable,
               width: Int = SIZE_UNDEFINED,
               height: Int = SIZE_UNDEFINED,
               insetLeft: Int = INSET_UNDEFINED,
               insetTop: Int = INSET_UNDEFINED,
               insetRight: Int = INSET_UNDEFINED,
               insetBottom: Int = INSET_UNDEFINED,
               insetStart: Int = INSET_UNDEFINED,
               insetEnd: Int = INSET_UNDEFINED) =
            apply {
                val layer = layers[index]
                layer.drawable = drawable
                if (width != SIZE_UNDEFINED) layer.width = width
                if (height != SIZE_UNDEFINED) layer.height = height
                if (insetLeft != INSET_UNDEFINED) layer.insetLeft = insetLeft
                if (insetTop != INSET_UNDEFINED) layer.insetTop = insetTop
                if (insetRight != INSET_UNDEFINED) layer.insetRight = insetRight
                if (insetBottom != INSET_UNDEFINED) layer.insetBottom = insetBottom
                if (insetStart != INSET_UNDEFINED) layer.insetStart = insetStart
                if (insetEnd != INSET_UNDEFINED) layer.insetEnd = insetEnd
            }

    fun build(): LayerDrawable {
        val layerDrawable = LayerDrawable(layers.map { it -> it.drawable }.toTypedArray())
        for (i in 0 until layers.size) {
            val layer = layers[i]
            layerDrawable.setLayerInset(i, layer.insetLeft, layer.insetTop, layer.insetRight, layer.insetBottom)
            layerDrawable.setId(i, i)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                layerDrawable.setLayerGravity(i, layer.gravity)
                layerDrawable.setLayerInsetStart(i, layer.insetStart)
                layerDrawable.setLayerInsetEnd(i, layer.insetEnd)
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            layerDrawable.paddingMode = paddingMode
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layerDrawable.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
            if (paddingStart != 0 || paddingEnd != 0) {
                layerDrawable.setPaddingRelative(paddingStart, paddingTop, paddingEnd, paddingBottom)
            }
        }

        return layerDrawable
    }

    internal class Layer(var drawable: Drawable) {
        var gravity: Int = Gravity.NO_GRAVITY
        var width: Int = -1
        var height: Int = -1
        var insetLeft: Int = 0
        var insetTop: Int = 0
        var insetRight: Int = 0
        var insetBottom: Int = 0
        var insetStart: Int = INSET_UNDEFINED
        var insetEnd: Int = INSET_UNDEFINED
    }
}