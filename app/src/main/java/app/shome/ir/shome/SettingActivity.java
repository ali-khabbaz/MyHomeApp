package app.shome.ir.shome;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import app.shome.ir.shome.service.ServiceDelegate;
import app.shome.ir.shome.service.Services;
import app.shome.ir.shome.ui.LoginActivity;

public class SettingActivity extends AppCompatActivity implements ServiceDelegate{
    ImageButton scanHub;
    RelativeLayout layout;
    Button start;
    Handler handler;
    int dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting2);
        scanHub =  (ImageButton) findViewById(R.id.scanHub);
        start= (Button) findViewById(R.id.start);
        layout= (RelativeLayout) findViewById(R.id.layout);
        handler=new Handler(Looper.getMainLooper());
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
         dp = Math.round(200 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        dp= (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 200, displaymetrics );

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(a);
            }
        });
        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.clockwise_rotation);
        scanHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanHub.setBackgroundResource(R.drawable.radar2);
                rotation.setRepeatCount(Animation.INFINITE);
                rotation.setDuration(3000);
                scanHub.startAnimation(rotation);
                scanHub.setClickable(false);
                new Services.DetectLocalServer(0,SettingActivity.this,SHomeConstant.LOCAL_PORT).execute();


            }
        });
    }

    private void addServer(final String ip)
    {
        ImageView v=new ImageView(this);
        RelativeLayout.LayoutParams a=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        Random random=new Random();
        a.topMargin=random.nextInt(dp);
        a.leftMargin=random.nextInt(dp);
        v.setImageResource(R.drawable.seekbar_normal);
        v.setLayoutParams(a);
        layout.addView(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SHomeApplication.LOCAL_IP = ip;
                SHomeApplication.LOCAL_PORT = SHomeConstant.LOCAL_PORT;
                SHomeApplication.save();
            }
        });

    }

    @Override
    public void onPostResult(int requestCode, final String date) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                addServer(date);

            }
        });

    }
}
