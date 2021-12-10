package com.example.incredible_logistics;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.*;

import java.util.Calendar;

public class DashBoard extends AppCompatActivity {

    Button callSignUp, loginUserBtn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dash_board);

        //Hooks
        callSignUp = findViewById(R.id.loginsignup_btn);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logotext_tran);
        sloganText = findViewById(R.id.slogan_text);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginUserBtn = findViewById(R.id.login_btn);


        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(DashBoard.this,SignUp.class);
               startActivity(intent);

//                Pair[] pairs = new Pair[7];
//
//                pairs[0] = new Pair<View,String>(image,"logo_tran");
//                pairs[1] = new Pair<View,String>(logoText,"logotext_tran");
//                pairs[2] = new Pair<View,String>(sloganText,"slogan_text");
//                pairs[3] = new Pair<View,String>(username,"username_tran");
//                pairs[4] = new Pair<View,String>(password,"password_tran");
//                pairs[5] = new Pair<View,String>(login_btn,"login_tran");
//                pairs[6] = new Pair<View,String>(callSignUp,"signup_tran");
//
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoard.this,pairs);
//                    startActivity(intent,options.toBundle());
//                }
            }
        });

    }
    private Boolean ValidateUserName(){
        String usernamedb = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(usernamedb.isEmpty())
        {
            username.setError("Field cannot be empty");
            return false;
        }
        else if(usernamedb.length() >= 15)
        {
            username.setError("Username to long");
            return false;
        }
        else if (!usernamedb.matches(noWhiteSpace))
        {
            username.setError("No white space are allowed");
            return false;
        }
        else
        {
            username.setError(null);
            return true;
        }

    }

    private Boolean ValidatePassword(){
        String passworddb = password.getEditText().getText().toString();
        //String passwordPattern = "(?=.*[0-9])";
        if(passworddb.isEmpty())
        {
            password.setError("Field can not be empty");
            return false;
        }
        else if(passworddb.length() <= 5)
        {
            password.setError("Username to long");
            return false;
        }
//        else if(!passworddb.matches(passwordPattern))
//        {
//            password.setError("Password weak? at least 1 digit");
//            return false;
//        }
        else
        {
            password.setError(null);
            return true;
        }

    }

    public void loginUserBtn(View view) {
        if(!ValidatePassword())
        {
            return;
        }else
        {
            IsUser user = new IsUser();
            user.start();
        }
    }

    class  IsUser extends Thread{

        FirebaseDatabase rootNode;
        DatabaseReference reference;

        @Override
        public void run() {

            String userEnteredUsername = username.getEditText().getText().toString().trim();
            String userEnteredPassword = password.getEditText().getText().toString().trim();

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

            checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
                        if (passwordFromDB.equals(userEnteredPassword)) {
                            username.setError(null);
                            //email.setErrorEnable(false);

                            username.setError(null);
                            username.setErrorEnabled(false);
                            String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
                            String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                            String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phone").getValue(String.class);
                            String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);

                            Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("username", usernameFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("phoneNo", phoneNoFromDB);
                            intent.putExtra("password", passwordFromDB);
                            startActivity(intent);
                        } else {
                            //progressBar.setVisibility(View.GONE);
                            password.setError("Wrong Password");
                            password.requestFocus();
                        }
                    } else {
                        //progressBar.setVisibility(View.GONE);
                        username.setError("No such User exist");
                        username.requestFocus();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}