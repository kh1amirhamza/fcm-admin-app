package com.example.fcm_admin_example;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText edt_title, edt_body;
    Button btn_send_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_title = findViewById(R.id.edt_title);
        edt_body = findViewById(R.id.edt_body);
        btn_send_notification = findViewById(R.id.btn_send_notification);

        btn_send_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = edt_title.getText().toString();
                if (title.isEmpty()){
                    title = "Notification title";
                }
                String body = edt_body.getText().toString();
                if (body.isEmpty()){
                    body = "Notification body";
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://amirhamza-node-js-1.herokuapp.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiRequest apiRequest = retrofit.create(ApiRequest.class);
                Call<JsonObject> call = apiRequest.postNotification(new NotificationMessage(title,body));
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Notification Send",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });

    }
}