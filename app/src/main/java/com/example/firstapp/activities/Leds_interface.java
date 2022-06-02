package com.example.firstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.firstapp.MainService;
import com.example.firstapp.R;
import com.example.firstapp.SocketIO;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Leds_interface extends AppCompatActivity {
    private Socket mSocket;
    private TextView statulamp1;
    private Intent serviceIntent;
    //private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leds_interface);

        serviceIntent = new Intent(this, MainService.class);
        startService(serviceIntent);
        statulamp1 = findViewById(R.id.statulamp1);

       // SocketIO app =  new SocketIO();
       // mSocket = app.getSocket();
       mSocket.emit("statuled", "statu");

        mSocket.on("statuandroid", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];
                        statulamp1.setText(data);                    }
                });
            }
        });
    }
    public void onbedroom(View view) {
        mSocket.emit("evntledon", "ledon");
        mSocket.emit("statuled", "statu");

    }
    public void offbedroom(View view) {
        mSocket.emit("evntledof", "ledof");
        mSocket.emit("statuled", "statu");

    }
}


