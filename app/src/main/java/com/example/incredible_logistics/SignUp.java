package com.example.incredible_logistics;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity{

    Button registerUserBtn;
    TextInputLayout name, email, password, phone, username;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        registerUserBtn = findViewById(R.id.sign_btn);
    }

    private Boolean ValidateName(){
        String namedb = name.getEditText().getText().toString();

        if(namedb.isEmpty())
        {
            name.setError("Field can not be empty");
            return false;
        }else
        {
            name.setError(null);
            return true;
        }

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

    private Boolean ValidateEmail(){
        String emaildb = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(emaildb.isEmpty()){
            email.setError("Field can not be empty");
            return false;
        }
        else if(!emaildb.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }

    }

    private Boolean ValidatePhone(){
        String phonedb = phone.getEditText().getText().toString();

        if(phonedb.isEmpty())
        {
            phone.setError("Field can not be empty");
            return false;
        }
        else
        {
            phone.setError(null);
            return true;
        }

    }

    private Boolean ValidatePassword(){
        String passworddb = password.getEditText().getText().toString();
//        String passwordPattern = "^"+"(?=.*[0-9])";
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

    public void registerUserBtn(View view){
        if(!ValidateName() | !ValidateUserName() | !ValidateEmail() | !ValidatePassword() | !ValidatePhone() ){
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        String namedb = name.getEditText().getText().toString();
        String  usernamedb = username.getEditText().getText().toString();
        String emaildb = email.getEditText().getText().toString();
        String phonedb = phone.getEditText().getText().toString();
        String passworddb = password.getEditText().getText().toString();

        UserHelperClass userdata = new UserHelperClass(namedb, usernamedb,emaildb,phonedb,passworddb);
        reference.child(usernamedb).setValue(userdata);
        Toast.makeText(this, "You Successfully Sign up", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SignUp.this,DashBoard.class);
        startActivity(intent);
    }
}