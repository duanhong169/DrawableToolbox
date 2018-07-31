package top.defaults.drawabletoolboxapp;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import top.defaults.drawabletoolbox.DrawableBuilder;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        TextView textView = findViewById(R.id.textView);
        Drawable background = new DrawableBuilder().shape(GradientDrawable.RING).build();
        textView.setBackgroundDrawable(background);
    }
}
