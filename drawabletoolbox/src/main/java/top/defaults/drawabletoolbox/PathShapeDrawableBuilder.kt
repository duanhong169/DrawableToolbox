package top.defaults.drawabletoolbox

import android.graphics.Path
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.PathShape

class PathShapeDrawableBuilder {

    private var path: Path? = null
    private var pathStandardWidth: Float = 100f
    private var pathStandardHeight: Float = 100f
    private var width: Int = -1
    private var height: Int = -1

    fun path(path: Path, pathStandardWidth: Float, pathStandardHeight: Float) = apply {
        this.path = path
        this.pathStandardWidth = pathStandardWidth
        this.pathStandardHeight = pathStandardHeight
    }

    fun width(width: Int) = apply { this.width = width }
    fun height(height: Int) = apply { this.height = height }
    fun size(size: Int) = apply { width(size).height(size) }

    fun build(custom: ((shapeDrawable: ShapeDrawable) -> Unit)? = null): ShapeDrawable {
        val shapeDrawable = ShapeDrawable()
        if (path == null || width <= 0 || height <= 0) {
            return shapeDrawable
        }
        val pathShape = PathShape(path, pathStandardWidth, pathStandardHeight)

        shapeDrawable.shape = pathShape
        shapeDrawable.intrinsicWidth = width
        shapeDrawable.intrinsicHeight = height
        if (custom != null) {
            custom(shapeDrawable)
        }
        return shapeDrawable
    }
}
