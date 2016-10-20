package com.tm.arifin.timbangan;

import android.app.Application;
import android.util.Log;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by arifin on 20/10/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("soket","jalan iko");

    }

    public Socket getSocket()
    {
        xSocket.connect();
        return xSocket;
    }

    private Socket xSocket;
    {
        try {
            xSocket = IO.socket("http://"+ AppConfig.IP_MONITOR +":5000");

        } catch (URISyntaxException e) {
            Log.e("Error Soket Konek", "" + e);
        }
    }

}
