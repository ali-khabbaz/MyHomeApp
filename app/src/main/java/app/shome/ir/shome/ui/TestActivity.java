package app.shome.ir.shome.ui;
import android.net.wifi.WifiManager;
import android.os.Handler;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.shome.ir.shome.R;
import app.shome.ir.shome.SHomeActivity;

public class TestActivity extends SHomeActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_analog);
    }
}
//extends AppCompatActivity {
//
//    private Socket socket;
//
//    private static final int SERVERPORT = 500;
//    private static final String SERVER_IP = "172.16.1.189";
//    Handler mainHandler;//
//
//    TextView inputTxt;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
////        String LOCAL_IP = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
//        setContentView(R.layout.activity_test);
//        inputTxt = (TextView) findViewById(R.id.inputText);
//        mainHandler = new Handler(getMainLooper());
//        new Thread(new ClientThread()).start();
//
//
//
//    }
//
//    public void onClick(View view) {
//        try {
//            EditText et = (EditText) findViewById(R.id.EditText01);
//            String str = et.getText().toString();
//            PrintWriter out = new PrintWriter(new BufferedWriter(
//                    new OutputStreamWriter(socket.getOutputStream())), true);
//            out.println(str);
//
//
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    void setInputText(final String messageString)
//    {
//        mainHandler.post(new Runnable() {
//            @Override
//            public void run() {
//
//                inputTxt.setText(messageString);
//            }
//        });
//    }
//
//    class ClientThread implements Runnable {
//
//        @Override
//        public void run() {
//
//            try {
//                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
//                socket = new Socket(serverAddr, SERVERPORT);
//
//                new  Thread(new CommunicationThread()).start();
//
//
//            } catch (UnknownHostException e1) {
//                e1.printStackTrace();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }catch (Exception e)
//            {
//
//            }
//
//        }
//
//    }
//
//    class CommunicationThread implements Runnable {
//
//        public void run() {
//
//            while (!Thread.currentThread().isInterrupted()) {
//
//                String messageString = "";
//
//                byte[] messageByte =new byte[100];
//
//                try
//                {
//                    DataInputStream in = new DataInputStream(socket.getInputStream());
//
//                    int bytesRead = in.read(messageByte);
//                    messageString += new String(messageByte, 0, bytesRead);
//                    if(in.available()>0)
//                    {
//                        messageByte =new byte[in.available()];
//                        bytesRead = in.read(messageByte);
//                        messageString += new String(messageByte, 0, bytesRead);
//                    }
//                    setInputText(messageString);
//
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//}