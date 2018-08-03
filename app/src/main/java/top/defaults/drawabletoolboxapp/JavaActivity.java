package top.defaults.drawabletoolboxapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import top.defaults.drawabletoolbox.DrawableBuilder;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        ImageView imageView = findViewById(R.id.imageView);
        Drawable drawable = new DrawableBuilder()
                .ring()
                .useLevelForRing()
                .useGradient()
                .useLevelForGradient()
                .sweepGradient()
                .gradientRadiusInFraction(0.5f)
                .rotate(-90)
                .build();
        ObjectAnimator animator = ObjectAnimator.ofInt(imageView, "imageLevel", 10000, 0);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
        imageView.setImageDrawable(drawable);
    }
}
