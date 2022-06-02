package com.example.firstapp.activities;

import static com.example.firstapp.MainActivity.BASE_URL;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapp.MainActivity;
import com.example.firstapp.R;
import com.example.firstapp.SocketIO;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class temp_interface extends AppCompatActivity {
    private TextView txttemp;
    private Socket mSocket;
    private Button refresh;
    static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_interface);
        txttemp = findViewById(R.id.txttemp);
        refresh = findViewById(R.id.refresh);

        SocketIO app =  new SocketIO();
        mSocket = app.getSocket();


        mSocket.on("temperature", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];
                        txttemp.setText(data + "°");
                    }

                });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mSocket.emit("refresh", "refTe");

        active = true;

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSocket.emit("refresh", "refTe");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }
    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }

    public void onventilateur(View view) {
        mSocket.emit("eventvontof", "VR_ON");
    }

    public void offventilateur(View view) {
        mSocket.emit("eventvontof", "VR_OF");
    }

    public void refreshtemp(View view) {
        mSocket.emit("refresh", "refTe");
    }

    }


