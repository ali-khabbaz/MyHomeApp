package app.shome.ir.shome.ui;

import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ToggleButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;
import app.shome.ir.shome.SHomeApplication;
import app.shome.ir.shome.SHomeConstant;
import app.shome.ir.shome.SettingActivity;
import app.shome.ir.shome.Utils;
import app.shome.ir.shome.db.MySqliteOpenHelper;
import app.shome.ir.shome.db.model.Device;
import app.shome.ir.shome.db.model.Zone;
import app.shome.ir.shome.service.ServiceDelegate;
import app.shome.ir.shome.service.Services;

public class MainActivity extends SHomeActivity implements ServiceDelegate, SHomeConstant, OnClickListener {
    //    ProgressDialog progressDialog;
//    AlertDialog tryAgainDialog;
    LinearLayout progress;
    TextView progressTextView;
    //    ViewPager viewPager;
    LinearLayout zoneTabLayout;
    LinearLayout dashboard_layer;
    //    DashboardFragment dashboard;
//    Vector<ZoneFragment> zoneFragments = new Vector<>();
//    Vector<View> zoneTabs = new Vector<>();
//    FragmentAdapter adapter;
    ImageButton dashboardTab;
    ToggleButton settingbtn;
    LayoutInflater inflater;
    LinearLayout edit_device;
    LinearLayout edit_zone;
    LinearLayout edit_senario;
    LinearLayout setting_layer;
    LinearLayout edit_user;
    LinearLayout rules;
    LinearLayout about_me;
    LinearLayout exit;
    Vector<Device> data;
    RecyclerView recyclerView;
    MyAdapter adapter;

    Comparator<Device> dashboardComprator = new Comparator<Device>() {
        @Override
        public int compare(Device lhs, Device rhs) {
            return lhs.dashindex.compareTo(rhs.dashindex);
        }
    };
    Comparator<Device> zoneComprator = new Comparator<Device>() {
        @Override
        public int compare(Device lhs, Device rhs) {
            return lhs.index.compareTo(rhs.index);
        }
    };

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();

            }
        });
        final ToggleButton settingbtn = (ToggleButton) findViewById(R.id.setting);
        final Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        final Animation alpha_out = AnimationUtils.loadAnimation(this, R.anim.alpha_out);
        final Animation rotation = AnimationUtils.loadAnimation(this, R.anim.clockwise_rotation);
        final Animation rotation_out = AnimationUtils.loadAnimation(this, R.anim.unclockwise_rotation);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setting_layer = (LinearLayout) findViewById(R.id.setting_layer);
        dashboard_layer = (LinearLayout) findViewById(R.id.dashboardLayer);
        edit_device = (LinearLayout) findViewById(R.id.edit_device);
        edit_zone = (LinearLayout) findViewById(R.id.edit_zone);
        edit_senario = (LinearLayout) findViewById(R.id.edit_senario);
        edit_user = (LinearLayout) findViewById(R.id.edit_user);
        rules = (LinearLayout) findViewById(R.id.rules);
        about_me = (LinearLayout) findViewById(R.id.about_me);
        exit = (LinearLayout) findViewById(R.id.exit);


        final float orgPos1X = setting_layer.getX();

        progress = (LinearLayout) findViewById(R.id.progressLayout);
//        viewPager= (ViewPager) findViewById(R.id.devincefragmentcontainer);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        zoneTabLayout = (LinearLayout) findViewById(R.id.zoneLayout);

//        progressDialog = new ProgressDialog(this);
//        tryAgainDialog = getTryAgainDialog();
        if (!SHomeApplication.isInitialization) {
//            progressDialog.setMessage(getString(R.string.server_connection));
//            progressDialog.show();
//            new Services.DetectLocalServer(SERVER_DETECT_REQUEST, this,6666).execute();
            new Services.GetStatusService(GET_DEVICE_STATUS, this, SHomeApplication.LOCAL_IP, SHomeApplication.LOCAL_PORT).execute();
        } else {
            progress.setVisibility(View.GONE);
            init();
        }
        final int screenWidth = Utils.getScreenWidth(MainActivity.this);
        final int sw = screenWidth;
        final int dtime = 1500;
        setting_layer.setX(orgPos1X - screenWidth);
        assert settingbtn != null;
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settingbtn.isChecked()) {
                    settingbtn.setClickable(false);
//                    setting_layer.setX(orgPos1X - screenWidth );

                    rotation.setRepeatCount(Animation.INFINITE);
                    rotation.setRepeatCount(0);
                    settingbtn.startAnimation(rotation_out);
//                    settingbtn.setHighlightColor(0xff33b5e5);
                    setting_layer.animate().translationX(setting_layer.getX() + sw).setDuration(dtime);
                    dashboard_layer.animate().alpha((float) 0.3).setDuration(dtime);

//                    recyclerView.animate().translationX(screenWidth).setDuration(dtime);
//                    setting_layer.setVisibility(View.VISIBLE);
//                    recyclerView.setVisibility(View.GONE);
                    final Handler handler = new Handler();
                    setting_layer.setEnabled(false);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            settingbtn.setClickable(true);
                        }
                    }, dtime);


                } else {
                    settingbtn.setClickable(false);
                    settingbtn.startAnimation(rotation);
                    setting_layer.animate().translationX(orgPos1X - screenWidth).setDuration(dtime);
//                    recyclerView.animate().translationX(orgPos1X).setDuration(dtime);
                    dashboard_layer.animate().alpha(1).setDuration(dtime);
//                    setting_layer.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.VISIBLE);
                    final Handler handler = new Handler();
                    setting_layer.setEnabled(false);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            settingbtn.setClickable(true);
                        }
                    }, dtime);


                }


            }
        });

        edit_device.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, DeviceActivity.class);
                startActivity(a);
            }
        });
        edit_zone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, ZoneActivity.class);
                startActivity(a);

            }
        });
        edit_senario.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(a);
            }
        });
        edit_user.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, UserActivity.class);
                startActivity(a);
            }
        });
        about_me.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_about_me);

            }
        });
        rules.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_rules);

            }
        });

    }

    private void refreshContent() {
        new Services.GetStatusService(GET_DEVICE_STATUS, this, SHomeApplication.LOCAL_IP, SHomeApplication.LOCAL_PORT).execute();

    }

    private void initZoneTab() {
        zoneTabLayout.removeAllViews();

        Collection<Zone> values = MySqliteOpenHelper.getInstance().allZones.values();
        for (Zone z : values) {
//            ZoneFragment zoneFragment = new ZoneFragment();
//            zoneFragment.setZone(z);
//            zoneFragments.add(zoneFragment);
            View inflate = inflater.inflate(R.layout.activity_zone_tab, null);

            TextView zonetitle = (TextView) inflate.findViewById(R.id.zonetitle);
            zonetitle.setText(z.name);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 86, getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 62, getResources().getDisplayMetrics()));
            params.leftMargin = 2;
            params.rightMargin = 2;
            params.topMargin = 4;
            params.bottomMargin = 4;

            inflate.setLayoutParams(params);
            inflate.setTag(z);
//            zoneTabs.add(inflate);
            zoneTabLayout.addView(inflate);
            inflate.setOnClickListener(this);

        }
    }


    private void init() {
        progress.setVisibility(View.GONE);

//        dashboard=new DashboardFragment();
        initZoneTab();
//        adapter = new FragmentAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);
        dashboardTab = (ImageButton) findViewById(R.id.dashboardtab);
        dashboardTab.setOnClickListener(this);
        MySqliteOpenHelper.getInstance().loadData(false);
        data = MySqliteOpenHelper.getInstance().dashboarDevice;
        Collections.sort(data, dashboardComprator);
        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(DeviecHolder item) {
                item.progressBar.setVisibility(View.VISIBLE);
                new Services.ChangeDeviceState(CHANGE_DEVICE_STATUS, item, item.device, SHomeApplication.LOCAL_IP, SHomeApplication.LOCAL_PORT).execute();


            }
        };
        adapter = new MyAdapter(itemClickListener);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 1;
                else

                    return adapter.getItemViewType(position);

            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
//        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
//            @Override
//            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
//                Object tag = v.getTag();
//                if(tag!=null && tag instanceof Device) {
//                    Device device = (Device) tag;
//                    new Services.ChangeDeviceState().execute(device);
//                }
//                // do it
//            }
//        });


// Extend the Callback class
        ItemTouchHelper.Callback _ithCallback = new ItemTouchHelper.Callback() {
            //and in your imlpementaion of
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                Collections.swap(data, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                MySqliteOpenHelper.getInstance().updateIndex(data);


                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //TODO
            }

            //defines the enabled move directions in each state (idle, swiping, dragging).
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);
            }
        };
        ItemTouchHelper ith = new ItemTouchHelper(_ithCallback);
        ith.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onPostResult(int requestCOde, String data) {
        if (requestCOde == SERVER_DETECT_REQUEST) {
            progressTextView.setText("Connection Server");

            new Services.GetStatusService(GET_DEVICE_STATUS, this, SHomeApplication.LOCAL_IP, SHomeApplication.LOCAL_PORT).execute();

        } else if (requestCOde == GET_DEVICE_STATUS) {

            boolean ok = false;
            Device[] devices;
            int zone_id = 1;
            HashMap<String, Zone> zone = new HashMap<>();
            if (data != null) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String cmdT = jsonObject.getString("CmdT");
                    if (cmdT.equals("3")) {
                        JSONArray zoneD = jsonObject.getJSONArray("ZoneD");

                        devices = new Device[zoneD.length()];
                        for (int i = 0; i < zoneD.length(); i++) {
                            devices[i] = new Device();
                            JSONObject jsonObject1 = zoneD.getJSONObject(i);
                            devices[i].generationId = jsonObject1.getString("DGID");
                            devices[i].status = jsonObject1.getString("ST");
                            Device device = MySqliteOpenHelper.getInstance().allDevice.get(devices[i].generationId);
                            if (device != null) {
                                device.status = devices[i].status;
                                MySqliteOpenHelper.getInstance().allDevice.put(device.generationId, device);

//                            MySqliteOpenHelper.getInstance().allDevice.put(devices[i].generationId,)
                                continue;
                            }
                            String zone1 = jsonObject1.getString("Zone");
                            if (zone.containsKey(zone1)) {
                                devices[i].defaulZone = zone.get(zone1);
                                devices[i].index = i;
                                devices[i].dashindex = i;
                            } else {
                                Zone a = new Zone();
                                a.name_fa = zone1;
                                a.name = zone1;
                                a = MySqliteOpenHelper.getInstance().addZone(zone1);
//                            zone.put(zone1, a);
                                devices[i].defaulZone = a;
                                zone.put(a.name_fa, a);
                            }
                            devices[i].defaulCategory = MySqliteOpenHelper.getInstance().allCategories.get(jsonObject1.getLong("DTYPE"));

                            if (devices[i].status.toUpperCase().equals("HIGH") || devices[i].status.toUpperCase().equals("LOW"))
                                devices[i].type = ON_OFF_DEVICE_TYPE;
                            else
                                devices[i].type = VOLUME_DEVICE_TYPE;
                            devices[i].isdash = 1;
                            MySqliteOpenHelper.getInstance().addDevice(devices[i]);
                        }
                    }
                    ok = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (!ok) {
//            progressDialog.dismiss();
//            tryAgainDialog.show();
            } else {
                SHomeApplication.isInitialization = true;
                SHomeApplication.save();


                MySqliteOpenHelper.getInstance().loadData(false);
                init();
//            progressDialog.dismiss();
            }
            mSwipeRefreshLayout.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {


//        if (v instanceof ImageButton) {
//            Object o = v.getTag();
//            if (o != null && o instanceof Device) {
//                Device device = (Device) o;
//                int i = data.indexOf(device);
//                device.progressBar.setVisibility(View.VISIBLE);
////                if(i!=-1)
//                adapter.notifyDataSetChanged();
//
//
////                device.titleTextView.setText(device.generationId);
////                device.titleTextView.setText(device.generationId);
//
//
//                if (device.status.toLowerCase().equals("low")) {
//                    device.status = "HIGH";
//
//                } else {
//                    device.status = "LOW";
//                }
//
//
//            } else {
//                data = MySqliteOpenHelper.getInstance().dashboarDevice;
//
//                Collections.sort(data, dashboardComprator);
//            }
//        } else {
        Object o = v.getTag();
        if (o instanceof Zone) {

            Zone z = (Zone) o;
            data = z.devices;
            Collections.sort(data, zoneComprator);
        }
//            else if (o instanceof Device) {
//                Device device = (Device) o;
//                new Services.ChangeDeviceState().execute(device);
//
//            }

//        }
//        adapter.notifyDataSetChanged();
//

    }

    public interface OnItemClickListener {
        void onItemClick(DeviecHolder item);
    }


    class MyAdapter extends RecyclerView.Adapter {
        OnItemClickListener itemClickListener;

        MyAdapter(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 3;
            } else

                return (data.get(position).span);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 3) {
                return new ClockHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_analog_clock, parent, false));
            } else


                return new DeviecHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_light_device, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position != 0)
                ((DeviecHolder) holder).setDevice(data.get(position), itemClickListener);


        }


        @Override
        public int getItemCount() {
            return data.size();
        }


    }

    public class ClockHolder extends RecyclerView.ViewHolder {

        public ClockHolder(View itemView) {
            super(itemView);
        }
    }

    public class DeviecHolder extends RecyclerView.ViewHolder implements ServiceDelegate {
        Device device;
        ProgressBar progressBar;
        ImageView devIcon;
        TextView zoneTextView,titleTextView;
        public View mview;
        public View image;

        public void setDevice(final Device device, final OnItemClickListener itemClickListener) {

            this.device = device;
            device.progressBar = progressBar;
            device.devIcon = devIcon;
//            mview.setTag(device);
            image.setTag(device);
            device.zoneTextView = (TextView) mview.findViewById(R.id.zoneName);
            device.titleTextView = (TextView) mview.findViewById(R.id.devName);
            device.devIcon = (ImageView) mview.findViewById(R.id.devIcon);
            image.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(DeviecHolder.this);
                }
            });
//            image.setTag(image.getId(),progressBar);
//            mview.setOnClickListener(MainActivity.this);
        }


        public DeviecHolder(View view) {
            super(view);
//            view.setOnClickListener(this);
            mview = view;
            image = view.findViewById(R.id.toggleButton2);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            devIcon = (ImageView) view.findViewById(R.id.devIcon);
            zoneTextView = (TextView) view.findViewById(R.id.zoneName);
            titleTextView = (TextView) view.findViewById(R.id.devName);
            progressBar.setVisibility(View.GONE);



//            int childCount = ((FrameLayout) mview).getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View childAt = ((FrameLayout) mview).getChildAt(i);
//                childAtldA.setOnClickListener(MainActivity.this);
//                if(chit instanceof ImageButton)
//                    ((ImageButton)childAt).setOnTouchListener(null);//chable(false);
//            }


        }


        @Override
        public void onPostResult(int requestCode, String date) {
            device.status = (device.status.toUpperCase().equals("HIGH") ? "LOW" : "HIGH");
            progressBar.setVisibility(View.INVISIBLE);

            if (image.isSelected()) {
                image.setSelected(false);
                image.setBackgroundResource(R.drawable.up);
                devIcon.setColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
                zoneTextView.setTextColor(Color.WHITE);
                titleTextView.setTextColor(Color.WHITE);
            } else {
                image.setSelected(true);
                image.setBackgroundResource(R.drawable.down);
                devIcon.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                zoneTextView.setTextColor(Color.GREEN);
                titleTextView.setTextColor(Color.GREEN);

            }


        }
    }


}
