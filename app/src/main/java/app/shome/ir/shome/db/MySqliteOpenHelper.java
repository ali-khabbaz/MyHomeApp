package app.shome.ir.shome.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeApplication;
import app.shome.ir.shome.SHomeConstant;
import app.shome.ir.shome.db.model.Category;
import app.shome.ir.shome.db.model.Device;

import app.shome.ir.shome.db.model.Module;
import app.shome.ir.shome.db.model.Scenario;
import app.shome.ir.shome.db.model.Zone;

/**
 * Created by Iman on 10/19/2016.
 */
public class MySqliteOpenHelper extends SQLiteOpenHelper implements SHomeConstant {

    public HashMap<Long, Category> allCategories;
    public HashMap<String, Device> allDevice;
    public HashMap<Long, Module> allModules;
    public HashMap<Long, Scenario> allsScenarios;
    public HashMap<Long, Zone> allZones;
    public Vector<Device> dashboarDevice = new Vector<>();
//    public Module[] allModules;
//    public Scenario[] allsScenarios;
//    public Zone[] allZones;
    //    public Device[] allDevice;

//    public Category[] allCategories;

//    SQLiteDatabase readableDatabase;

    private static MySqliteOpenHelper instance;

    public static MySqliteOpenHelper getInstance() {
        if (instance == null) {
            instance = new MySqliteOpenHelper();
            instance.loadData(true);
        }
        return instance;

    }
    //    private String zoneSystemTableName = "default_region";
    //    private String categorySystemTableName = "default_category";


    private String zoneTableName = "zone";
    private String deviceTableName = "device";
    private String categoryTableName = "category";
    private String moduleTableName = "module";


//    private String zoneSystemTableQuery = "create table " + zoneSystemTableName + "(id integer primary key  ," +
//            "name text," +
//            "name_fa text ,deleted number)";
//    private String categorySystemTableQuery = "create table " + categorySystemTableName + "(id integer primary key ," +
//            "name text," +
//            "name_fa text,deleted number)";


    private String deviceTableQuery = "create table " + deviceTableName + "(id integer primary key AUTOINCREMENT," +
            "name text," +
            "type number," +
            "generationid text," +
            "zoneid number," +
            "defaultzoneid number," +
            "defaultcategoryid number," +
            "categoryid number," +
            "name_fa text," +
            "isdash number default 1," +
            "status text," +
            "indx number default -1," +
            "dashindx number default -1)";
    private String moduleTableQuery = "create table " + moduleTableName + "(id integer primary key AUTOINCREMENT," +
            "name text," +
            "name_fa text )";
    private String categoryTableQuery = "create table " + categoryTableName + "(id integer primary key AUTOINCREMENT default 50," +
            "name text," +
            "name_fa text ,deleted number)";

    private String zoneTableQuery = "create table " + zoneTableName + "(id integer primary key AUTOINCREMENT default 50," +
            "name text," +
            "name_fa text,deleted number)";


    private MySqliteOpenHelper() {
        super(SHomeApplication.context, "localdb", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


//        String query;
//        for (int i = 0; i < zones.length; i++) {
//            query = "insert into " + zoneSystemTableName + " values(" + (zones.length * -1 + i) + ",'" + zones[i] + "','" + zones_fa[i] + "',0)";
//            db.execSQL(query);
//        }
//        for (int i = 0; i < category.length; i++) {
//            query = "insert into " + categorySystemTableName + " values(" + (category.length * -1 + i) + ",'" + category[i] + "','" + category_fa[i] + "',0)";
//            db.execSQL(query);
//        }


//        db.execSQL(zoneSystemTableQuery);

//        db.execSQL(categorySystemTableQuery);

        db.execSQL(zoneTableQuery);
        db.execSQL(deviceTableQuery);
        db.execSQL(moduleTableQuery);
        db.execSQL(categoryTableQuery);


    }


    public void loadData(boolean reload) {
        if (allDevice != null && !reload) {
            return;
        }
        dashboarDevice.removeAllElements();

        allCategories = new HashMap<>();
        allDevice = new HashMap<>();
        allModules = new HashMap<>();
        allsScenarios = new HashMap<>();
        allZones = new HashMap<>();
        String[] zones = SHomeApplication.context.getResources().getStringArray(R.array.zones);
        String[] zones_fa = SHomeApplication.context.getResources().getStringArray(R.array.zones_fa);
        String[] category = SHomeApplication.context.getResources().getStringArray(R.array.catrgories);
        String[] category_fa = SHomeApplication.context.getResources().getStringArray(R.array.catrgories_fa);

        for (int i = 0; i < zones.length; i++) {
            Zone a = new Zone();
            a.name_fa = zones_fa[i];
            a.name = zones[i];
            a.id = i + 1;
            allZones.put((long) i + 1, a);
        }
        for (int i = 0; i < category_fa.length; i++) {
            Category a = new Category();
            a.name_fa = category_fa[i];
            a.name = category[i];
            a.id = i + 1;
            allCategories.put((long) i + 1, a);
        }

//        if (readableDatabase == null)
//            readableDatabase = getReadableDatabase();


        String query = "select * from " + zoneTableName;// + " union all select * from " + zoneSystemTableName;
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
//        allZones = new Zone[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Zone a = new Zone();
            a.name_fa = cursor.getString(cursor.getColumnIndex("name_fa"));
            a.id = cursor.getLong(cursor.getColumnIndex("id"));
            allZones.put(a.id, a);
            cursor.moveToNext();
        }
//        cursor.close();

//        query = "select * from " + zoneSystemTableName;
//         cursor = readableDatabase.rawQuery(query, null);
//        allZones = new Zone[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int i = 0; i < allZones.length; i++) {
//            allZones[i] = new Zone();
//            allZones[i].name_fa = cursor.getString(cursor.getColumnIndex("name_fa"));
//            allZones[i].name = cursor.getString(cursor.getColumnIndex("name"));
//            allZones[i].id = cursor.getLong(cursor.getColumnIndex("id"));
//            cursor.moveToNext();
//        }
//        cursor.close();


        query = "select * from " + categoryTableName;//+ " union all select * from " + categorySystemTableName;


        Cursor cursor1 = getReadableDatabase().rawQuery(query, null);
//        allCategories = new Category[cursor.getCount()];
        cursor1.moveToFirst();
        for (int i = 0; i < cursor1.getCount(); i++) {
            Category a = new Category();
            a.name_fa = cursor1.getString(cursor.getColumnIndex("name_fa"));
            a.id = cursor1.getLong(cursor.getColumnIndex("id"));
            allCategories.put(a.id, a);
            cursor1.moveToNext();
        }
//        cursor1.close();

//        query = "select * from " +categorySystemTableName ;
//
//
//        cursor = readableDatabase.rawQuery(query, null);
//        allCategories = new Category[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int i = 0; i < allZones.length; i++) {
//            allCategories[i] = new Category();
//            allCategories[i].name_fa = cursor.getString(cursor.getColumnIndex("name_fa"));
//            allCategories[i].name = cursor.getString(cursor.getColumnIndex("name"));
//            allCategories[i].id = cursor.getLong(cursor.getColumnIndex("id"));
//            cursor.moveToNext();
//        }
//        cursor.close();

//        query = "select * from " + moduleTableName;
//        cursor = readableDatabase.rawQuery(query, null);
//        allModules = new Module[cursor.getCount()];
//        cursor.moveToFirst();
//        for (int i = 0; i < allModules.length; i++) {
//            allModules[i] = new Module();
//            allModules[i].name_fa = cursor.getString(cursor.getColumnIndex("name_fa"));
//            allModules[i].id = cursor.getLong(cursor.getColumnIndex("id"));
//            cursor.moveToNext();
//        }
//        cursor.close();


        query = "select * from " + deviceTableName;

        cursor = getReadableDatabase().rawQuery(query, null);
        ;

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Device a = new Device();
            a.name_fa = cursor.getString(cursor.getColumnIndex("name_fa"));
            a.id = cursor.getLong(cursor.getColumnIndex("id"));
            a.index = cursor.getInt(cursor.getColumnIndex("indx"));
            a.isdash = cursor.getInt(cursor.getColumnIndex("isdash"));
            a.dashindex = cursor.getInt(cursor.getColumnIndex("dashindx"));

            if (a.dashindex == -1)
                a.dashindex = i;
            if (a.index == -1)
                a.index = i;

            a.type = cursor.getInt(cursor.getColumnIndex("type"));
            a.span = (a.type == VOLUME_DEVICE_TYPE) ? 2 : 1;
//            if (i % 3 == 0) a.span =2;

            a.generationId = cursor.getString(cursor.getColumnIndex("generationid"));
//            allDevice.put(a.id, a);
            long zoneid = cursor.getLong(cursor.getColumnIndex("zoneid"));
            Zone zone = allZones.get(zoneid);
            if (zone != null) {
                a.zone = zone;
                zone.devices.add(a);
            }
            long defaultzoneid = cursor.getLong(cursor.getColumnIndex("defaultzoneid"));
            Zone zone1 = allZones.get(defaultzoneid);
            if (zone1 != null) {
                a.defaulZone = zone1;
                zone1.devices.add(a);
            }
            long catid = cursor.getLong(cursor.getColumnIndex("categoryid"));
            Category category1 = allCategories.get(catid);
            if (category1 != null) {
                a.category = category1;
                category1.devices.add(a);
            }
            long defaultcatid = cursor.getLong(cursor.getColumnIndex("defaultcategoryid"));
            Category category2 = allCategories.get(defaultcatid);
            if (category2 != null) {
                a.defaulCategory = category2;
                category2.devices.add(a);
            }
            a.status=cursor.getString(cursor.getColumnIndex("status"));
//            contentValues.put("status", a.status);
            if (a.isdash == 1)
                dashboarDevice.add(a);
            allDevice.put(a.generationId, a);

            cursor.moveToNext();
        }

        cursor.close();
        dashboarDevice.add(0,null);




    }

    public Zone addZone(String name) {

        Zone z = new Zone();
        z.id = addNew(name, zoneTableName);
        z.name_fa = name;
        return z;
    }

    public Module addModule(String name) {

        Module z = new Module();
        z.id = addNew(name, moduleTableName);
        z.name_fa = name;
        return z;
    }

    public Device addDevice(Device a) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("name_fa", a.name_fa);
        contentValues.put("name", a.name);
        contentValues.put("generationid", a.generationId);
        if (a.zone != null)
            contentValues.put("zoneid", a.zone.id);
        if (a.defaulZone != null)
            contentValues.put("defaultzoneid", a.defaulZone.id);
        if (a.category != null)
            contentValues.put("categoryid", a.category.id);
        if (a.defaulCategory != null)
            contentValues.put("defaultcategoryid", a.defaulCategory.id);
        contentValues.put("indx", a.generationId);
        contentValues.put("isdash", a.isdash);
        contentValues.put("type", a.type);
        contentValues.put("status", a.status);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        a.id = writableDatabase.insert(deviceTableName, null, contentValues);

        return a;
    }

    public Scenario addScenario(String name) {

        Scenario z = new Scenario();
        z.id = addNew(name, deviceTableName);
        z.name_fa = name;
        return z;
    }

    public Category addCategory(String name) {
        Category z = new Category();
        z.id = addNew(name, categoryTableName);
        z.name_fa = name;
        return z;
    }

    private long addNew(String name, String tableName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name_fa", name);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        long id = writableDatabase.insert(tableName, null, contentValues);
        writableDatabase.close();
        return id;

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateIndex(Vector<Device> data) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String query;
        if(data==dashboarDevice)
        {
            for (int i = 0; i < data.size(); i++) {
                if(data.get(i)!=null) {
                    query = "update " + deviceTableName + "  set dashindx=" + i + " where id=" + data.get(i).id;
                    writableDatabase.execSQL(query);
                }
            }

        }else
        {
            for (int i = 0; i < data.size(); i++) {
                query="update "+deviceTableName+"  set indx="+i+" where id="+data.get(i).id;
                writableDatabase.execSQL(query);
            }

        }writableDatabase.close();

    }

    public void updateDevice(Device device) {
        String query="update "+deviceTableName+"  set status='"+device.status+"' where id="+device.id;
        getWritableDatabase().execSQL(query);

    }
}
