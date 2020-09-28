package com.example.fcm_admin_example;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequest {
    @POST("sendNotification")
    Call<JsonObject> postNotification(@Body NotificationMessage notificationMessage);
}
