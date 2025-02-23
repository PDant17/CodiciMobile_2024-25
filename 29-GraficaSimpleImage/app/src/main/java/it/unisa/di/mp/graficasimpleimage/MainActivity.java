package it.unisa.di.mp.graficasimpleimage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        RelativeLayout mainframe = findViewById(R.id.mainframe);

        ImageView globoImageView = new ImageView(getApplicationContext());

        globoImageView.setImageDrawable(getResources().getDrawable(R.drawable.earth));

        //int width  = (int) getResources().getDimension(R.dimen.image_width);
        //int height = (int) getResources().getDimension(R.dimen.image_height);
        int width  = 300;
        int height = 300;

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,height);
        lp.addRule(RelativeLayout.CENTER_VERTICAL);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        globoImageView.setLayoutParams(lp);

        globoImageView.setBackgroundColor(Color.GRAY);

        mainframe.addView(globoImageView);

    }
}
