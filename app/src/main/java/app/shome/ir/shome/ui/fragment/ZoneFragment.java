package app.shome.ir.shome.ui.fragment;

import android.support.v4.app.Fragment;

import app.shome.ir.shome.db.model.Zone;

/**
 * Created by Iman on 10/25/2016.
 */
public class ZoneFragment extends Fragment {
    public Zone zone;

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
