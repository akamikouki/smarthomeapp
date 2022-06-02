package com.example.firstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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


    }

    public void onventilateur(View view) {
        mSocket.emit("eventvonton", "VR_ON");
    }

    public void offventilateur(View view) {
        mSocket.emit("eventvontof", "VR_OF");


    }
}
