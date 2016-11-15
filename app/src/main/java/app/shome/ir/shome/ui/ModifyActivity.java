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

public class ModifyActivity extends SHomeActivity {
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        listView= (ListView) findViewById(R.id.listView);
        ModifyAdapter adapter=new ModifyAdapter();
        listView.setAdapter(adapter);

    }



    class ModifyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return 10;
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
            LayoutInflater inflater = (LayoutInflater) ModifyActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return inflater.inflate(R.layout.modify_gadget,null);

        }
    }
}
