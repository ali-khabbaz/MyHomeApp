package app.shome.ir.shome.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeConstant;
import app.shome.ir.shome.db.MySqliteOpenHelper;
import app.shome.ir.shome.db.model.Device;
import app.shome.ir.shome.ui.LongPressListener;

/**
 * Created by Iman on 10/25/2016.
 */
public class DashboardFragment extends Fragment implements SHomeConstant {

    Vector<Device> dashboarDevice;
    Comparator<Device> comparator = new Comparator<Device>() {
        @Override
        public int compare(Device lhs, Device rhs) {
            return lhs.dashindex.compareTo(rhs.dashindex);
        }
    };
    GridLayout mGrid;

    public DashboardFragment() {
        dashboarDevice = MySqliteOpenHelper.getInstance().dashboarDevice;

        Collections.sort(dashboarDevice, comparator);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View v=  inflater.inflate(R.layout.fragment_layout, container, false);
        mGrid= (GridLayout) v.findViewById(R.id.grid_layout);
        int row = 0, column = 0;
        for (int i = 0; i < dashboarDevice.size(); i++) {
            Device device = dashboarDevice.get(i);

            final View itemView = inflater.inflate(R.layout.fragment_light_device,null);
//            final TextView lightZoon = (TextView) itemView.findViewById(R.id.lightZoon);
            final TextView lightZoon1=new TextView(getContext());
            lightZoon1.setText(""+i);

            final TextView lightName = (TextView) itemView.findViewById(R.id.lightName);
            lightName.setText(String.valueOf(i + 1));
//            lightZoon.setText(String.valueOf(i + 1));
            if (column + device.span > mGrid.getColumnCount()) {
                row++;
                column = 0;
            }
            GridLayout.Spec rowSpec = GridLayout.spec(row);
            GridLayout.Spec columnSpec = GridLayout.spec(column, device.span, 1f);
            GridLayout.LayoutParams doubleLayoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            mGrid.addView(itemView,doubleLayoutParams);
//            mGrid.addView(lightZoon1,doubleLayoutParams);
            column += device.span;
//            if (device.dashindex == -1)
//                device.dashindex = i;
            device.dashView = itemView;
            itemView.setOnLongClickListener(new LongPressListener());
            itemView.setTag(device);
        }
        mGrid.setOnDragListener(new DragListener());
        return mGrid;
    }

    private void reArrange(View v, float x, float y) {
        final float cellWidth = mGrid.getWidth() / mGrid.getColumnCount();
        final int column1 = (int) (x / cellWidth);
        final float cellHeight = mGrid.getHeight() / mGrid.getRowCount();
        final int row1 = (int) Math.floor(y / cellHeight);
        Collections.sort(dashboarDevice, comparator);
        int row = 0, column = 0;
        Device a = (Device) v.getTag();
        for (int i = 0; i < dashboarDevice.size(); i++) {
            Device device = dashboarDevice.get(i);

            if (device.dashView == v)
                continue;
            if (row == row1) {
                if (column1 <= column || column1 < column + device.span || column + a.span + device.span > mGrid.getColumnCount()) {
                    for (int j = a.dashindex; j < i; j++) {

                        device.dashindex = j;
                    }
                    for (int j = i; j < dashboarDevice.size(); j++) {

                        device.dashindex = j + 1;
                    }
                    a.dashindex = i;
                    break;
                }
            } else if (row > row1) {
                for (int j = a.dashindex; j < i; j++) {

                    device.dashindex = j;
                }
                for (int j = i; j < dashboarDevice.size(); j++) {

                    device.dashindex = j + 1;
                }
                a.dashindex = i;
                break;
            }

            if (column + device.span >  mGrid.getColumnCount()) {
                row++;
                column = 0;
            }
            column += device.span;
        }
        Collections.sort(dashboarDevice, comparator);

        mGrid.removeAllViews();
        row = 0;
        column = 0;
        for (int i = 0; i < dashboarDevice.size(); i++) {
            Device device = dashboarDevice.get(i);

            if (column + device.span >  mGrid.getColumnCount()) {
                row++;
                column = 0;
            }

            GridLayout.Spec rowSpec = GridLayout.spec(row);
            GridLayout.Spec columnSpec = GridLayout.spec(column, device.span, 1f);
            GridLayout.LayoutParams doubleLayoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            mGrid.addView(device.dashView, doubleLayoutParams);
            column += device.span;
        }

//        }

        return;
    }

    class DragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            final View view = (View) event.getLocalState();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_LOCATION:
                    if (view == v) return true;
                    mGrid.removeView(view);
                    reArrange(view, event.getX(), event.getY());
                    break;
                case DragEvent.ACTION_DROP:
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (!event.getResult()) {
                        view.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return true;
        }
    }

}
