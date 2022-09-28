package com.waleedportfolio.kaybees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.waleedportfolio.kaybees.Admin.AdminDashboard;
import com.waleedportfolio.kaybees.Consumer.UserDashboard;

public class SignIn extends AppCompatActivity {
    TextInputEditText email, pass;
    MaterialButton signinBtn;
    TextView signupText;


    String adminEmail;
    String adminPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        init();



        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()){
                    email.setError("please enter your email first");
                    return;
                }
                if(pass.getText().toString().isEmpty()){
                    email.setError("please enter your password first");
                    return;
                }
                if(pass.length() <6){
                    pass.setError("password length is greater than 6 characters");
                    return;
                }
                if(!email.getText().toString().contains("@")){
                    pass.setError("please check your email");
                    return;

                }

                if(email.getText().toString().equals(adminEmail) && pass.getText().toString().equals(adminPassword)){
                    saveData();
                    Snackbar.make(v, "SignIn Sucessfully",Snackbar.LENGTH_LONG).show();
                    Intent i = new Intent(SignIn.this,AdminDashboard.class);
                    startActivity(i);
                    finish();
                }

                else {

                    siginin(email.getText().toString(), pass.getText().toString());


                }




            }

        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "User Registered Sucessfully",Snackbar.LENGTH_LONG).show();
                Intent i = new Intent(SignIn.this,SignUp.class);
                startActivity(i);
                finish();

            }
        });


    }

    public void siginin(String email,String pass){

        // Initialize Firebase Auth
        FirebaseAuth mAuth; mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignIn.this, "Sign In Sucessfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignIn.this, UserDashboard.class);
                    startActivity(intent);
                    finish();
                }
                
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                
            }
        });
    }
    public void init(){
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        signinBtn = findViewById(R.id.btnsign);
        signupText = findViewById(R.id.textsignup);

        adminEmail = "admin@gmail.com";
        adminPassword = "wal123456";

    }


    // sharePrefrences are used to savedata
    public void saveData(){
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
        myEdit.putBoolean("isLogin",true);




// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();

    }
}
