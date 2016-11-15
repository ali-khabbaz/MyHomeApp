package app.shome.ir.shome.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;
import app.shome.ir.shome.SHomeApplication;
import app.shome.ir.shome.SHomeConstant;
import app.shome.ir.shome.db.MySqliteOpenHelper;
import app.shome.ir.shome.db.model.Category;
import app.shome.ir.shome.db.model.Device;
import app.shome.ir.shome.db.model.Module;
import app.shome.ir.shome.db.model.Scenario;
import app.shome.ir.shome.db.model.Zone;

/**
 * Created by Iman on 10/19/2016.
 */
public class AddNewDialog
{}
//extends Dialog implements View.OnClickListener, SHomeConstant {
//    DialogDelegate listener;
//
//    public void setListener(DialogDelegate listener) {
//        this.listener = listener;
//    }
//
//    SHomeActivity activity;
//    View back;
//    TextView title;
//    EditText name;
//    int type;
//    Button ok, cancle;
//
//
//    public void setType(int type) {
//        name.setText("");
//        this.type = type;
//        switch (type) {
//            case DEVICE_TYPE:
//                title.setText(R.string.new_device);
//                break;
//            case CATEGORY_TYPE:
//                title.setText(R.string.new_category);
//                break;
//            case MODULE_TYPE:
//                title.setText(R.string.new_module);
//                break;
//            case SCENARIO_TYPE:
//                title.setText(R.string.new_scenario);
//                break;
//            case ZONE_TYPE:
//                title.setText(R.string.new_zone);
//                break;
//        }
//    }
//
//    public AddNewDialog(SHomeActivity cotext) {
//        super(cotext);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //before
//        activity=cotext;
//        setContentView(R.layout.dialog_addnew);
//
//        ok = (Button) findViewById(R.id.ok);
//        cancle = (Button) findViewById(R.id.cancle);
//        ok.setOnClickListener(this);
//        cancle.setOnClickListener(this);
//        back = findViewById(R.id.back);
//        back.setOnClickListener(this);
//        title = (TextView) findViewById(R.id.title);
//        name = (EditText) findViewById(R.id.name);
//        Window window = this.getWindow();
//        DisplayMetrics displaymetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
//        int width = (int) (displaymetrics.widthPixels * 0.9);
////        int height = (int) (displaymetrics.heightPixels * 0.7);
//        window.setLayout(width, -2);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            findViewById(R.id.main_content).setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//        }
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v == ok) {
//            String nameInput = name.getText().toString();
//            if (nameInput == null || nameInput.length() == 0) {
//                Toast.makeText(SHomeApplication.context, "name is empty", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            Object a=null;
//            switch (type) {
//                case DEVICE_TYPE:
//                    a = MySqliteOpenHelper.getInstance().addDevice(nameInput);
//                    break;
//                case CATEGORY_TYPE:
//                   a = MySqliteOpenHelper.getInstance().addCategory(nameInput);
//                    break;
//                case MODULE_TYPE:
//                    a= MySqliteOpenHelper.getInstance().addModule(nameInput);
//                    break;
//                case SCENARIO_TYPE:
//                    a = MySqliteOpenHelper.getInstance().addScenario(nameInput);
//                    break;
//                case ZONE_TYPE:
//                    a = MySqliteOpenHelper.getInstance().addZone(nameInput);
//                    break;
//            }
//            if(a!=null && listener!=null)
//            {
//                listener.insertDone(a);
//
//            }
//
//            dismiss();
//
//        } else if (v == cancle) {
//            dismiss();
//        } else if (v == back) {
//            dismiss();
//        }
//    }
//}
