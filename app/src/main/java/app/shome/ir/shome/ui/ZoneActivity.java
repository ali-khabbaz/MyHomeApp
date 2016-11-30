package app.shome.ir.shome.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.HashMap;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;
import app.shome.ir.shome.db.MySqliteOpenHelper;
import app.shome.ir.shome.db.model.Zone;

/**
 * Created by Mahdi on 11/01/2016.
 */
public class ZoneActivity extends SHomeActivity {
    Zone[] zones;
    ListView lv;
    Button save_btn;
    Zone current;
    EditText name;
    MyAddapter maddpter ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);
        lv = (ListView) findViewById(R.id.zone_list);
        save_btn = (Button)findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = name.getText().toString().trim();
                if(current!=null && trim.length()>0) {
                    current.name_fa=trim;
                    MySqliteOpenHelper.getInstance().updateZone(current);
                    maddpter.notifyDataSetChanged();
                }else  if(trim.length()==0)
                {
                    Toast toast = Toast.makeText(ZoneActivity.this, "Name Empty", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();

                }

            }
        });
        name = (EditText) findViewById(R.id.name);
        HashMap<Long, Zone> allZones = MySqliteOpenHelper.getInstance().allZones;
        Collection<Zone> values = allZones.values();
        zones =new Zone[values.size()];
        if(zones.length>0 && zones[0]!=null)
            current=zones[0];
        values.toArray(zones);


        maddpter = new MyAddapter();
        lv.setAdapter(maddpter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                current=zones[position];
                name.setText(current.name_fa);
            }
        });

    }

    class MyAddapter extends BaseAdapter {

        @Override
        public int getCount() {
            return zones==null?0:zones.length;
        }

        @Override
        public Object getItem(int position) {
            return zones[position];
        }

        @Override
        public long getItemId(int position) {
            return zones[position].id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View inflate = inflater.inflate(R.layout.activity_zone_tab, null);
            TextView title= (TextView) inflate.findViewById(R.id.zonetitle);
            title.setText(zones[position].name_fa);
            return inflate;
        }
    }
}
