package top.defaults.drawabletoolbox

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.Gravity
import java.util.ArrayList

class LayerDrawableBuilder {

    private val layers = ArrayList<Layer>()

    fun add(layer: Drawable) = apply { layers.add(Layer(layer)) }
    @RequiresApi(Build.VERSION_CODES.M)
    fun gravity(gravity: Int) = apply { layers.last().gravity = gravity }

    fun build(): LayerDrawable {
        val layerDrawable = LayerDrawable(layers.map { it -> it.drawable }.toTypedArray())
        for (i in 0 until layers.size) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                layerDrawable.setLayerGravity(i, layers[i].gravity)
            }
        }
    }

    internal class Layer(var drawable: Drawable) {
        var gravity: Int = Gravity.NO_GRAVITY
    }
}