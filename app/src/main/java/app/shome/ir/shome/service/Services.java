package app.shome.ir.shome.service;

import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import app.shome.ir.shome.SHomeApplication;
import app.shome.ir.shome.db.model.Device;

/**
 * Created by Iman on 10/20/2016.
 */
public class Services {
//    public static final int SERVERPORT = 8080;

    //    public static final String SERVER_IP = "172.16.1.193";
//
//    public static final String SERVER_IP = "192.168.1.3";

    //    public static final String SERVER_IP = "192.168.1c.2";
//    public static final String SERVER_IP = "172.16.1.157";
    public static class DetectLocalServer extends AsyncTask<String, String, String> {
        ServiceDelegate serviceDelegate;
        int requestCode;
        int port;

        public DetectLocalServer(int requestCode, ServiceDelegate serviceDelegate, int defaultPort) {
            this.requestCode = requestCode;
            this.serviceDelegate = serviceDelegate;
            this.port = defaultPort;

        }


        //        Json Status : { “ CmdT “ : “ 3 ”, “ CmdNO “ : “ integer “ , “ DD “ : “ 1  |  3  |  6  | …  |  ALL”, “ Zone “ : “ Room1 | Hall | Bathroom …  |  ALL “ }
        @Override
        protected String doInBackground(String... params) {

            WifiManager wm = (WifiManager) SHomeApplication.context.getSystemService(SHomeApplication.context.WIFI_SERVICE);
            int ipAddress = wm.getConnectionInfo().getIpAddress();
            String ip1 = String.format("%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff));
            String ip = "";
            for (int i = 192; i < 194; i++) {
                ip = ip1 + "." + i;
                Socket socket = new Socket();
//                socket.setSoTimeout(500);
                try {
                    socket.connect(new InetSocketAddress(ip, 8080), 100);
                    serviceDelegate.onPostResult(0,ip);

                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }


            return ip;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (serviceDelegate != null) {
                serviceDelegate.onPostResult(requestCode, s);

            }
        }
    }

    public static class GetStatusService extends AsyncTask<String, String, String> {
        ServiceDelegate serviceDelegate;
        int requestCode;
        String ip;
        int port;

        public GetStatusService(int requestCode, ServiceDelegate serviceDelegate, String ip, int port) {
            this.requestCode = requestCode;
            this.serviceDelegate = serviceDelegate;
            this.ip = ip;
            this.port = port;

        }

        String messageString = "";

        //        Json Status : { “ CmdT “ : “ 3 ”, “ CmdNO “ : “ integer “ , “ DD “ : “ 1  |  3  |  6  | …  |  ALL”, “ Zone “ : “ Room1 | Hall | Bathroom …  |  ALL “ }
        @Override
        protected String doInBackground(String... params) {
            try {
//                if(true)
//                    return null;
//                Thread.sleep(1000);
                InetAddress serverAddr = InetAddress.getByName(ip);

                Socket socket = new Socket(serverAddr, port);
                Thread.sleep(1000);
                JSONObject object = new JSONObject();
                object.put("CmdT", "3");
                object.put("CmdNO", "1");
                object.put("DD", "ALL");
                object.put("Zone", "ALL");
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                out.println(object.toString());

                byte[] messageByte = new byte[100];
                DataInputStream in = new DataInputStream(socket.getInputStream());
                int bytesRead = in.read(messageByte);
                messageString += new String(messageByte, 0, bytesRead);
                if (in.available() > 0) {
                    messageByte = new byte[in.available()];
                    bytesRead = in.read(messageByte);
                    messageString += new String(messageByte, 0, bytesRead);
                }
                socket.close();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return messageString;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (serviceDelegate != null) {
                serviceDelegate.onPostResult(requestCode, s);

            }
        }
    }

    public static class ChangeDeviceState extends AsyncTask<String, String, String> {

        String messageString = "";
        ServiceDelegate serviceDelegate;

        Device device;
        String ip;
        int port;
        int requestCode;

        public ChangeDeviceState(int requestCode,ServiceDelegate d,Device device,  String ip, int port) {
            serviceDelegate=d;
            this.ip = ip;
            this.port = port;
            this.device=device;
            this.requestCode=requestCode;

        }


        @Override
        protected String doInBackground(String... params) {
            try {
//                if(params==null || params.length==0)
//                    return null;
//                this.device=params[0];


//                Device param = params[0];
                InetAddress serverAddr = InetAddress.getByName(ip);
                Socket socket = new Socket(serverAddr, port);
                Thread.sleep(1000);
                JSONObject object = new JSONObject();
                object.put("CmdT", "1");
                object.put("CmdNO", "2");
                JSONArray array = new JSONArray();
                JSONObject object2 = new JSONObject();
                object2.put("DTYPE", "" + device.type);
                object2.put("DID", "" + device.id);
                object2.put("DGID", "" + device.generationId);

                object2.put("ST", device.status.toUpperCase().equals("HIGH") ? "LOW" : "HIGH");

                array.put(object2);
                object.put("DD", array);
//                object.put("DD","ALL");
                object.put("Zone", "ALL");
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                out.println(object.toString());

                byte[] messageByte = new byte[1];
                DataInputStream in = new DataInputStream(socket.getInputStream());
                int bytesRead = in.read(messageByte);
                messageString += new String(messageByte, 0, bytesRead);
                if (in.available() > 0) {
                    messageByte = new byte[in.available()];
                    bytesRead = in.read(messageByte);
                    messageString += new String(messageByte, 0, bytesRead);
                }
                socket.close();

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return messageString;
        }

        @Override
        protected void onPostExecute(String s) {
            serviceDelegate.onPostResult(requestCode,s);

//            recyclerView.getAdapter().notifyItemChanged(position);
        }
    }

}
