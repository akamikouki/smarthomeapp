package com.example.firstapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.firstapp.activities.Home_interface;
import com.example.firstapp.MainActivity;
import com.example.firstapp.R;
import com.example.firstapp.UserAPI;
import com.example.firstapp.UserRegister;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register extends Fragment {
    public static String TAG = "TAG";

    EditText password, fullname, email, scannedarduino;
    Button sign;
    UserRegister userregister;

    public Register() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        password = view.findViewById(R.id.pass);
        sign = view.findViewById(R.id.regbtn);
        fullname = view.findViewById(R.id.fullname);
        email = view.findViewById(R.id.email);
        scannedarduino = view.findViewById(R.id.scannedarduino);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification();

                userregister = new UserRegister();
                userregister.setPassword(password.getText().toString());
                userregister.setEmail(email.getText().toString());
                userregister.setScannedarduino(scannedarduino.getText().toString());
                userregister.setFullname(fullname.getText().toString());

                Log.d(TAG, "onClick:  username" + password + " email" + email + " scannedarduino" + scannedarduino + " fullname" + fullname);
                SendPostReq(userregister);
            }
        });
        return view;
    }

    private void SendPostReq(final UserRegister m) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<Object> call = userAPI.signUp(m);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.code() == 200) {
                    MainActivity.token = new Gson().toJson(response.body());
                    Toast.makeText(getContext(), "You have signed up successully & saved your data!!!!" + MainActivity.token, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), Home_interface.class);
                    startActivity(i);
                } else if (response.code() == 409) {
                    Toast.makeText(getContext(), "email duplicated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to sign up!!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get Response!!!", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void verification() {
        String password1 = password.getText().toString();
        String fullname1 = fullname.getText().toString();
        String email1 = email.getText().toString();
        String scannedarduino1 = scannedarduino.getText().toString();

        if(TextUtils.isEmpty(fullname1)) {
            fullname.setError("Enter your fullname");
            return;
        }
        if(TextUtils.isEmpty(email1)) {
            email.setError("Enter your E-mail");
            return;
        }
        if(TextUtils.isEmpty(password1)) {
            password.setError("Enter your password");
            return;
        }
        if(TextUtils.isEmpty(scannedarduino1)) {
            scannedarduino.setError("Enter your Arduino code");
            return;
        }

    }
    }







