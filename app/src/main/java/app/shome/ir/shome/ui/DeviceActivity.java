package app.shome.ir.shome.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;

/**
 * Created by Mahdi on 11/01/2016.
 */
public class DeviceActivity extends SHomeActivity{

    ListView lv;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        lv= (ListView) findViewById(R.id.device_list);


        MyAddapter maddpter=new MyAddapter();
        lv.setAdapter(maddpter);

    }
    class MyAddapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View inflate = inflater.inflate(R.layout.activity_device_detail, null);
            return inflate;
        }
    }
}
