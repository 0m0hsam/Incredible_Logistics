package com.example.incredible_logistics;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UserDashboard extends AppCompatActivity {

    TextView fullNameLabel, usernameLabel, phoneLabel, emailLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        emailLabel = findViewById(R.id.email_field);
        phoneLabel = findViewById(R.id.phone_field);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);

        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNo = intent.getStringExtra("phoneNo");
        String user_password = intent.getStringExtra("password");

        //emailLabel.getEditText().setText(user_name);
        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        emailLabel.setText(user_email);
        phoneLabel.setText(user_phoneNo);
       // pictureLabel.setText(user_picture);

    }
}