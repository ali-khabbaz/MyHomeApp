package app.shome.ir.shome.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;
import app.shome.ir.shome.ui.fragment.NodeManagmentFragment;

public class NodeManagmenActivity {}
//extends SHomeActivity {
//    String tabTile[];
//    NodeManagmentFragment[] tabFragment;
//    NodeManagmentFragment zoneManagmentFragment;
//    NodeManagmentFragment deviceManagmentFragment;
//    NodeManagmentFragment catManagmentFragment;
//
//
//    private SectionsPagerAdapter mSectionsPagerAdapter;
//
//
//    private ViewPager mViewPager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_node_managmen);
//        tabTile = new String[]{getString(R.string.zone_tab_title), getString(R.string.device_tab_title), getString(R.string.category_tab_title)};
//        zoneManagmentFragment = new NodeManagmentFragment();
//        zoneManagmentFragment.setType(ZONE_TYPE);
//        deviceManagmentFragment = new NodeManagmentFragment();
//        deviceManagmentFragment.setType(DEVICE_TYPE);
//        catManagmentFragment = new NodeManagmentFragment();
//        catManagmentFragment.setType(CATEGORY_TYPE);
//        tabFragment = new NodeManagmentFragment[]{zoneManagmentFragment, deviceManagmentFragment, catManagmentFragment};
//
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//
//
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.menu_node_managmen, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//
//
//
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return tabFragment[position];
//        }
//
//        @Override
//        public int getCount() {
//            return tabTile.length;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tabTile[position];
//
//        }
//    }
//}
