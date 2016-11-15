package app.shome.ir.shome.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Vector;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;
import app.shome.ir.shome.SHomeConstant;
import app.shome.ir.shome.db.MySqliteOpenHelper;
import app.shome.ir.shome.db.model.Category;
import app.shome.ir.shome.db.model.Device;
import app.shome.ir.shome.db.model.Module;
import app.shome.ir.shome.db.model.Scenario;
import app.shome.ir.shome.db.model.Zone;
//import app.shome.ir.shome.ui.adapter.NodeFragmentAdapter;
import app.shome.ir.shome.ui.dialog.AddNewDialog;
import app.shome.ir.shome.ui.dialog.DialogDelegate;

public class NodeManagmentFragment{}
//extends Fragment implements DialogDelegate, SHomeConstant {
//    int type;
//    ListView listView;
//    NodeFragmentAdapter adapter;
//    MySqliteOpenHelper sqliteOpenHelper = MySqliteOpenHelper.getInstance();
//
//
//    public void setType(int type) {
//        this.type = type;
//        switch (type) {
//            case DEVICE_TYPE:
//                adapter = new NodeFragmentAdapter<Device>(this);
//                if (sqliteOpenHelper.allDevice != null)
//                    adapter.addAll(Arrays.asList(sqliteOpenHelper.allDevice));
//                break;
//            case CATEGORY_TYPE:
//                adapter = new NodeFragmentAdapter<Category>(this);
//
//                if (sqliteOpenHelper.allCategories != null)
//                    adapter.addAll(Arrays.asList(sqliteOpenHelper.allCategories));
//                break;
//            case MODULE_TYPE:
//                adapter = new NodeFragmentAdapter<Module>(this);
//                if (sqliteOpenHelper.allModules != null)
//                    adapter.addAll(Arrays.asList(sqliteOpenHelper.allModules));
//
//                break;
//
//            case SCENARIO_TYPE:
//                adapter = new NodeFragmentAdapter<Scenario>(this);
//
//                if (sqliteOpenHelper.allsScenarios != null)
//                    adapter.addAll(Arrays.asList(sqliteOpenHelper.allsScenarios));
//
//                break;
//            case ZONE_TYPE:
//                adapter = new NodeFragmentAdapter<Zone>(this);
//                if (sqliteOpenHelper.allZones != null)
//                    adapter.addAll(Arrays.asList(sqliteOpenHelper.allZones));
//
//                break;
//        }
//
//
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_node_managmen, container, false);
//        listView = (ListView) rootView.findViewById(R.id.listView);
//        if (adapter != null && listView != null)
//            listView.setAdapter(adapter);
//        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddNewDialog addNewDialog = SHomeActivity.addNewDialog;
////                addNewDialog.setType(type);
////                addNewDialog.setListener(NodeManagmentFragment.this);
////                addNewDialog.show();
//
//            }
//        });
//
//
//        return rootView;
//    }
//
//    @Override
//    public void insertDone(Object a) {
//        adapter.addItem(a);
//        adapter.notifyDataSetChanged();
//
//    }
//
//
//    public int getType() {
//        return type;
//    }
//}