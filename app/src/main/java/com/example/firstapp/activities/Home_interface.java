package com.example.firstapp.activities;

import static com.example.firstapp.MainActivity.tokenfirebse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firstapp.R;
import com.example.firstapp.SocketIO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import io.socket.client.Socket;

public class Home_interface extends AppCompatActivity {
    private Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_interface);generatetoken();
        SocketIO app =  new SocketIO();
        mSocket = app.getSocket();


        mSocket.emit("tokenfirebase", tokenfirebse);

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
    public void generatetoken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println( "Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        tokenfirebse = task.getResult();

                        // Log and toast
                        System.out.println(tokenfirebse);
                        Toast.makeText(Home_interface.this, tokenfirebse, Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
