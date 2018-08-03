package top.defaults.drawabletoolboxapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import top.defaults.checkerboarddrawable.CheckerboardDrawable;

public class CheckerboardRelativeLayout extends RelativeLayout {

    public CheckerboardRelativeLayout(Context context) {
        this(context, null);
    }

    public CheckerboardRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckerboardRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        CheckerboardDrawable drawable = new CheckerboardDrawable.Builder().size(30).build();
        setBackgroundDrawable(drawable);
    }
}
