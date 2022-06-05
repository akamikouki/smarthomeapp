package com.example.firstapp.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;
import com.example.firstapp.SocketIO;

import io.socket.client.Socket;

public class clima_interface extends AppCompatActivity {
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_interface);
        SocketIO app =  new SocketIO();
        mSocket = app.getSocket();
      //  startService(new Intent(clima_interface.this, socketservice.class));

    }

    public void onventilateur(View view) {
        mSocket.emit("eventvonton", "VR_ON");
    }

    public void offventilateur(View view) {
        mSocket.emit("eventvontof", "VR_OF");


    }
}
