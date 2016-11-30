package app.shome.ir.shome.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;
import app.shome.ir.shome.db.MySqliteOpenHelper;
import app.shome.ir.shome.db.model.Device;

/**
 * Created by Mahdi on 11/01/2016.
 */
public class DeviceActivity extends SHomeActivity {
    Device[] devices;
    ListView listView;
    LinearLayout detail;
    EditText name;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        listView = (ListView) findViewById(R.id.device_list);
        detail = (LinearLayout) findViewById(R.id.detail);
        name = (EditText) findViewById(R.id.name);
        Collection<Device> values = MySqliteOpenHelper.getInstance().allDevice.values();
        devices = new Device[values.size()];
        values.toArray(devices);


        MyAddapter maddpter = new MyAddapter();
        listView.setAdapter(maddpter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail(devices[position]);

            }
        });

    }

    private void showDetail(Device e) {
        name.setText(e.name_fa);


    }

    class MyAddapter extends BaseAdapter {

        @Override
        public int getCount() {
            return devices == null ? 0 : devices.length;
        }

        @Override
        public Object getItem(int position) {
            return devices[position];
        }

        @Override
        public long getItemId(int position) {
            return devices[position].id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View inflate = inflater.inflate(R.layout.activity_device_detail, null);
            TextView name = (TextView) inflate.findViewById(R.id.name);
            name.setText(devices[position].name_fa);
            return inflate;
        }
    }
}
