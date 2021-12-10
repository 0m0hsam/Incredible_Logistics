package com.example.incredible_logistics;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    //TextInputLayout itemName, itemDestination, itemDescription, receiverName, receiverTel;
    TextView fullNameLabel, usernameLabel, phoneLabel, emailLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hooks
        usernameLabel = findViewById(R.id.username);
        emailLabel = findViewById(R.id.email_field);
        phoneLabel = findViewById(R.id.phone_field);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
//        itemName = findViewById(R.id.item_name);
//        itemDestination = findViewById(R.id.item_destination);
//        receiverName = findViewById(R.id.receiver_name);
//        receiverTel = findViewById(R.id.receiver_tel);
//        itemDescription = findViewById(R.id.item_name);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UserProfile.this, "User Successfully Log In", Toast.LENGTH_SHORT).show();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

//    private void showAllUserData() {
//        Intent intent = getIntent();
//        String user_username = intent.getStringExtra("username");
//        String user_name = intent.getStringExtra("name");
//        String user_email = intent.getStringExtra("email");
//        String user_phoneNo = intent.getStringExtra("phoneNo");
//        String user_password = intent.getStringExtra("password");
//
//
//
//        fullNameLabel.setText(user_name);
//        usernameLabel.setText(user_username);
//        //emailLabel.getEditText().setText(user_name);
//        emailLabel.setText(user_email);
//        phoneLabel.setText(user_phoneNo);
//        //password.setText(user_password);
//
//    }
}