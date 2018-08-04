package top.defaults.drawabletoolbox

import android.graphics.drawable.Drawable

class MirrorDrawableBuilder: DrawableWrapperBuilder<MirrorDrawableBuilder>() {

    override fun build(): Drawable {
        return MirrorDrawable(drawable!!)
    }
}