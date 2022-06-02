package com.example.firstapp;

import static com.example.firstapp.MainActivity.BASE_URL;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.net.URI;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainService extends Service {
  //  private MediaPlayer player;
  private Socket mSocket;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        IO.Options options1 = IO.Options.builder()
                .setExtraHeaders(singletonMap("authorization", singletonList(MainActivity.token)))
                .build();
        {
            try {
                mSocket = IO.socket(URI.create(BASE_URL), options1);
                mSocket.connect();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
