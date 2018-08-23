package top.defaults.drawabletoolbox;

import android.graphics.Path
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.PathShape

class PathShapeDrawableBuilder {

    private var path: Path? = null
    private var pathWidth: Int = 100
    private var pathHeight: Int = 100
    private var width: Int = -1
    private var height: Int = -1

    fun path(path: Path?) = apply { this.path = path }
    fun width(width: Int) = apply { this.width = width }
    fun height(height: Int) = apply { this.height = height }
    fun size(size: Int) = apply { width(size).height(size) }
    fun star(size: Int) = apply { star(size, size) }
    fun star(width: Int, height: Int) = apply {
        val path = Path()
        val half = 50
        path.moveTo(half * 0.5f, half * 0.84f)
        path.lineTo(half * 1.5f, half * 0.84f)
        path.lineTo(half * 0.68f, half * 1.45f)
        path.lineTo(half * 1.0f, half * 0.5f)
        path.lineTo(half * 1.32f, half * 1.45f)
        path.lineTo(half * 0.5f, half * 0.84f)
        path.close()
        this.path = path
        this.pathWidth = width
        this.pathHeight = height
    }

    fun build(): ShapeDrawable {
        val shapeDrawable = ShapeDrawable()
        if (path == null || width <= 0 || height <= 0) {
            return shapeDrawable
        }
        val pathShape = PathShape(path, pathWidth.toFloat(), pathHeight.toFloat())

        shapeDrawable.shape = pathShape
        shapeDrawable.intrinsicWidth = width
        shapeDrawable.intrinsicHeight = height
        return shapeDrawable
    }
}
