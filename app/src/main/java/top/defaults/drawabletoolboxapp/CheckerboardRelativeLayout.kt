package top.defaults.drawabletoolboxapp

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

import top.defaults.checkerboarddrawable.CheckerboardDrawable

class CheckerboardRelativeLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    init {
        val drawable = CheckerboardDrawable.Builder().size(30).build()
        setBackgroundDrawable(drawable)
    }
}
