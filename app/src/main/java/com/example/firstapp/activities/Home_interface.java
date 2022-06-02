package com.example.firstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapp.MainActivity;
import com.example.firstapp.MainService;
import com.example.firstapp.R;

import io.socket.client.Socket;

public class Home_interface extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_interface);

    }

        public void lumiere(View view) {
            Intent intent = new Intent(Home_interface.this, Leds_interface.class);
            startActivity(intent);
        }

        public void clima(View view) {
            Intent intent = new Intent(Home_interface.this, clima_interface.class);
            startActivity(intent);
        }

    public void temperature(View view) {
        Intent intent = new Intent(Home_interface.this, temp_interface.class);
        startActivity(intent);
    }

        public void humidity(View view) {
            Intent intent = new Intent(Home_interface.this, humidity_interface.class);
            startActivity(intent);
        }
}
