package app.shome.ir.shome.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;
import app.shome.ir.shome.service.ServiceDelegate;
import app.shome.ir.shome.service.Services;

public class Test1{}
//        extends SHomeActivity
//        implements NavigationView.OnNavigationItemSelectedListener, ServiceDelegate {
//    ProgressDialog progressDialog;
//    ListView listView;
//    MyAdapter adapter = new MyAdapter();
//    Device[] devices;
//    LayoutInflater layoutInflater;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);
////        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                progressDialog.show();
////                Services.GetStatusService service = new Services.GetStatusService(Test1.this);
////                service.execute();
////            }
////        });
////        listView = (ListView) findViewById(R.id.device_list);
//        listView.setAdapter(adapter);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Connecting....");
////        progressDialog.setCancelable(false);
//        progressDialog.show();
//        Services.GetStatusService service = new Services.GetStatusService(this);
//        service.execute();
//
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_node_anagement) {
//            Intent a = new Intent(this, NodeManagmenActivity.class);
//            startActivity(a);
//        } else if (id == R.id.nav_gallery) {
//            Intent a = new Intent(this, Main3Activity.class);
//            startActivity(a);
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public void onPostResult(String data) {
//        progressDialog.dismiss();
//        boolean ok = false;
//        if (data != null) {
//            try {
//                JSONObject jsonObject = new JSONObject(data);
//                String cmdT = jsonObject.getString("CmdT");
//                if (cmdT.equals("3")) {
//                    JSONArray zoneD = jsonObject.getJSONArray("ZoneD");
//                    devices = new Device[zoneD.length()];
//
//                    for (int i = 0; i < zoneD.length(); i++) {
//                        devices[i] = new Device();
//                        JSONObject jsonObject1 = zoneD.getJSONObject(i);
//                        devices[i].zone = jsonObject1.getString("Zone");
//                        devices[i].type = jsonObject1.getString("DTYPE");
//                        devices[i].generationid = jsonObject1.getString("DGID");
//                        devices[i].state = jsonObject1.getString("ST");
//                        devices[i].stateBool = (devices[i].state != null && !devices[i].state.equals("null") && !devices[i].state.equals("LOW ")) ? true : false;
//
//                        devices[i].id = i;
//
//                    }
//                    adapter.notifyDataSetChanged();
//                    ok = true;
//
//
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        } else
//
//        {
//            devices = new Device[1];
//
//
//            devices[0] = new Device();
//
//            devices[0].zone = "";
//            devices[0].type = "";
//            devices[0].generationid = "";
//            devices[0].state = "";
//            devices[0].stateBool = false;
//            devices[0].id = 1;
//
//
//            adapter.notifyDataSetChanged();
//
//        }
//        if (!ok) {
//            Toast.makeText(this, "Server error", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    public static class Device {
//        public long id;
//        public String zone;
//        public String type;
//        public String generationid;
//        public boolean stateBool;
//        public String state;
//    }
//
//    class MyAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//
//            return (devices == null) ? 0 : devices.length;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return devices[position];
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return devices[position].id;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            View inflate = layoutInflater.inflate(R.layout.activity_device_detail, null);
//            ToggleButton toggleButton = (ToggleButton) inflate.findViewById(R.id.toggleButton);
//            toggleButton.setChecked(devices[position].stateBool);
//            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    devices[position].stateBool = isChecked;
//                    new Services.ChangeDeviceState().execute(devices[position]);
//                }
//            });
//
//            return inflate;
//        }
//    }
//}
