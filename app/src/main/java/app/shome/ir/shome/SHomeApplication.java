package app.shome.ir.shome;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * Created by Iman on 10/19/2016.
 */
public class SHomeApplication extends Application implements SHomeConstant {
    public static Context context;
    public static boolean isInitialization = false;
    public static String LOCAL_IP;
    public static int LOCAL_PORT;
    public static String DDNS_IP;
    public static int DDNS_PORT;
    public static String REMOTE_IP;
    public static int REMOTE_PORT;


    public static Typeface BYEKAN_NORMAL;//


    @Override
    public void onCreate() {
        super.onCreate();
        if (BYEKAN_NORMAL == null) {
            BYEKAN_NORMAL = Typeface.createFromAsset(getAssets(), "font/Samim.ttf");

        }
        context = this;
        SharedPreferences app = getSharedPreferences("app", MODE_PRIVATE);
        isInitialization = app.getBoolean("initial", false);
        LOCAL_IP = app.getString("local_ip", SHomeConstant.LOCAL_IP);
        LOCAL_PORT = app.getInt("local_port", SHomeConstant.LOCAL_PORT);
        DDNS_IP = app.getString("ddns_ip", SHomeConstant.DDNS_IP);
        DDNS_PORT = app.getInt("ddns_port", SHomeConstant.DDNS_PORT);
        REMOTE_IP = app.getString("remote_ip", "-1");
        REMOTE_PORT = app.getInt("remote_port", -1);

    }


    public static void save() {
        SharedPreferences app = context.getSharedPreferences("app", MODE_PRIVATE);
        SharedPreferences.Editor edit = app.edit();
        edit.putBoolean("initial", isInitialization);
        edit.putString("local_ip", LOCAL_IP);
        edit.putInt("local_port", LOCAL_PORT);
        edit.putString("ddns_ip", DDNS_IP);
        edit.putInt("ddns_port", DDNS_PORT);
        edit.putString("remote_ip", REMOTE_IP);
        edit.putInt("remote_port", REMOTE_PORT);
        edit.commit();

    }
}
