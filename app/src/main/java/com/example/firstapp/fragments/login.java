package com.example.firstapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.firstapp.MainActivity;
import com.example.firstapp.R;
import com.example.firstapp.User;
import com.example.firstapp.UserAPI;
import com.example.firstapp.activities.Home_interface;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class login extends Fragment {
    private EditText email, password;
    private Button log;
    String email1,password1;

    public login() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.pass_login);
        log = view.findViewById(R.id.login_btn);




        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification();

                final User user = new User();
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                SendPostReq(user);
            }
        });


        return view;
    }

    private void verification() {
        email1 = email.getText().toString();
        password1 = password.getText().toString();
        if(TextUtils.isEmpty(email1)) {
            email.setError("Enter your E-mail");
            return;

        }
        if(TextUtils.isEmpty(password1)) {
            password.setError("Enter your password");
return;
        }

    }

    private void SendPostReq(final User m) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<Object> call = userAPI.login(m);
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.code() == 200) {
                    MainActivity.token = new Gson().toJson(response.body());

                    Toast.makeText(getContext(), "response.body!!!!" + MainActivity.token, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(), Home_interface.class);
                    startActivity(i);
                } else if (response.code() == 404) {
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
}
        /*
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
               if(response.code()==200){

                   Toast.makeText(getContext(), "You have login successully !"+new Gson().toJson(response.body()), Toast.LENGTH_SHORT).show();

                   Toast.makeText(getContext(), "You have logged in successully!!", Toast.LENGTH_SHORT).show();
               }else if(response.code()==404) {
                   Toast.makeText(getContext(), "error to search!!", Toast.LENGTH_SHORT).show();

               }else{
                   Toast.makeText(getContext(), "Failed to login!!!", Toast.LENGTH_SHORT).show();
               }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get Response!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
  /*  if (response.code() == 200) {
                  //  String token = new Gson().toJson(response.body());
                  //  Toast.makeText(getContext(), "You have login successully !" , Toast.LENGTH_SHORT).show();

                    Toast.makeText(getContext(), "You have logged in successully!!", Toast.LENGTH_SHORT).show();
                //} else if (response.code() == 404) {
                    //Toast.makeText(getContext(), "error to search!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Failed to login!!!", Toast.LENGTH_SHORT).show();
                }*/
