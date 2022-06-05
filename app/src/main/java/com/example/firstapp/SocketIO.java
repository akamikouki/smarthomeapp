package com.example.firstapp;

import static com.example.firstapp.MainActivity.BASE_URL;
import static com.example.firstapp.MainActivity.tokenfirebse;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.firstapp.activities.Home_interface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.net.URI;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketIO extends Application {

    private Socket mSocket;
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


    public Socket getSocket() {
        return mSocket;
    }
}