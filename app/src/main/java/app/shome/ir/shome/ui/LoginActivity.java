package app.shome.ir.shome.ui;

import android.content.Context;
import android.content.Intent;
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
    ImageView user,user_shape,user_shape2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        user_shape=(ImageView)findViewById(R.id.user_shape);
        user_shape2=(ImageView)findViewById(R.id.user_shape2);
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
                user_shape.setVisibility(View.INVISIBLE);
                user_shape2.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                }, 250);
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
