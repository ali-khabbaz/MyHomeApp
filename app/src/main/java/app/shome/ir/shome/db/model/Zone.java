package app.shome.ir.shome.db.model;

import java.util.Vector;

/**
 * Created by Iman on 10/19/2016.
 */
public class Zone {
    public String name;
    public String name_fa;
    public long id;
    boolean system;
    boolean deleted;
    public Vector<Device> devices=new Vector<>();

}
