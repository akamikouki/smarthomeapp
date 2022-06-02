package com.example.firstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.firstapp.R;
import com.example.firstapp.SocketIO;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class humidity_interface extends AppCompatActivity {
    private TextView ctxthumidity;
    private Socket mSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humidity_interface);
        ctxthumidity = findViewById(R.id.txthumidity);


        SocketIO app =  new SocketIO();
        mSocket = app.getSocket();
        mSocket.emit("refresh", "refTe");


        mSocket.on("humidity", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];

                        ctxthumidity.setText(data+"%");
                    }

                });
            }
        });
    }


    protected void onStart() {
        super.onStart();
        mSocket.emit("refresh", "refTe");

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
    public void refreshtemp(View view) {
        mSocket.emit("refresh", "refTe");
    }

    public void humidity(View view) {
        mSocket.emit("refresh", "refTe");

    }
}
