package app.shome.ir.shome.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;

/**
 * Created by Mahdi on 11/01/2016.
 */
public class LoginActivity extends SHomeActivity{

    EditText user_id,user_pass;
    Button login,exit;
    ImageView user,user_shape;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user_shape=(ImageView)findViewById(R.id.user_shape);
        user_id=(EditText)findViewById(R.id.user_id);
        user_pass=(EditText)findViewById(R.id.user_pass);
        login=(Button)findViewById(R.id.login_btn);
        exit=(Button)findViewById(R.id.exit_btn);

        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.clockwise_rotation);
        rotation.setRepeatCount(Animation.INFINITE);
        user_shape.startAnimation(rotation);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_shape.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
//                        finish();
                    }
                }, 750);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

}
