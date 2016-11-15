package app.shome.ir.shome;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by Mahdi on 10/30/2016.
 */
public class Utils {
    public  static int getScreenWidth(Activity activity)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        return width;
    }
}
