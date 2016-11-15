package app.shome.ir.shome.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeApplication;
import app.shome.ir.shome.SettingActivity;

public class IntroActivity extends AppCompatActivity {

    int layouts[]=new int[]{R.layout.intro_lay1,R.layout.intro_lay2,R.layout.intro_lay3,R.layout.intro_lay4};
    RadioButton radioButton[]=new RadioButton[4];
    ViewPager viewPager ;
    Button skip,next;
    int currentindex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        if(SHomeApplication.isInitialization)
        {
            Intent a=new Intent(IntroActivity.this, LoginActivity.class);
            startActivity(a);
            finish();
        }
        radioButton[0]= (RadioButton) findViewById(R.id.radioButton1);
        radioButton[1]= (RadioButton) findViewById(R.id.radioButton2);
        radioButton[2]= (RadioButton) findViewById(R.id.radioButton3);
        radioButton[3]= (RadioButton) findViewById(R.id.radioButton4);
        next= (Button) findViewById(R.id.next);
        skip= (Button) findViewById(R.id.skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(a);
                finish();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(++currentindex);
            }
        });

        viewPager=(ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position>=radioButton.length)
                {
                    next.setText("Finish");
                }else
                {
                    next.setText("next");
                }
                radioButton[position].setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public class CustomPagerAdapter extends PagerAdapter {

        private Context mContext;

        public CustomPagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewGroup layout = (ViewGroup) inflater.inflate(layouts[position], collection, false);
            collection.addView(layout);
            return layout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return layouts.length;
        }


    }
}
