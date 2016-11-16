package app.shome.ir.shome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class SettingActivity extends AppCompatActivity {
    ImageButton scanHub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        scanHub =  (ImageButton) findViewById(R.id.scanHub);
        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.clockwise_rotation);
        scanHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanHub.setBackgroundResource(R.drawable.radar2);
                rotation.setRepeatCount(Animation.INFINITE);
                rotation.setDuration(3000);
                scanHub.startAnimation(rotation);
                scanHub.setClickable(false);


            }
        });
    }
}
