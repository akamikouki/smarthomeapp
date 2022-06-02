package com.example.firstapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {
    @Headers("Content-Type: application/json")
    @POST("/login")
    Call<Object> login(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("/register")
    Call<Object> signUp(@Body UserRegister userregister);

    @Headers("Content-Type: application/json")
    @GET("/user")
    Call<User> getUser(@Body User user);
}
