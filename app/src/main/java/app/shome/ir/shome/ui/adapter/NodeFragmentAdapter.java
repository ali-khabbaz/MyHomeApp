package app.shome.ir.shome.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeApplication;
import app.shome.ir.shome.SHomeConstant;
import app.shome.ir.shome.db.model.Category;
import app.shome.ir.shome.db.model.Device;
import app.shome.ir.shome.db.model.Module;
import app.shome.ir.shome.db.model.Scenario;
import app.shome.ir.shome.db.model.Zone;
import app.shome.ir.shome.ui.fragment.NodeManagmentFragment;

/**
 * Created by Iman on 10/19/2016.
 */
//
//public class NodeFragmentAdapter<T> extends BaseAdapter implements SHomeConstant {
//    Vector<T> data = new Vector<>();
//    NodeManagmentFragment fragment;
//    public NodeFragmentAdapter(NodeManagmentFragment fragment)
//    {
//        this.fragment=fragment;
//    }
//
//
//    //    public void setData(Vector<T> data) {
////        this.data = data;
////
////    }
//    public void addItem(T object) {
//        data.add(object);
//    }
//
//    @Override
//    public int getCount() {
//        return data.size();
//    }
//
//    @Override
//    public T getItem(int position) {
//        return data.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        TextView textView=new TextView(SHomeApplication.context);
//        Object object=data.get(position);
//        switch (fragment.getType()) {
//            case DEVICE_TYPE:
//                textView.setText(((Device)object).name_fa);
//                break;
//            case CATEGORY_TYPE:
//                textView.setText(((Category)object).name_fa);
//                break;
//            case MODULE_TYPE:
//                textView.setText(((Module)object).name_fa);
//                break;
//            case SCENARIO_TYPE:
//                textView.setText(((Scenario)object).name_fa);
//                break;
//            case ZONE_TYPE:
//                textView.setText(((Zone)object).name_fa);
//                break;
//        }
//        return textView;
//    }
//
//    public void addAll(List<T> items) {
//        data.addAll(items);
//
//    }
//}
