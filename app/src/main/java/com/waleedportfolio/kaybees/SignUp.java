package com.waleedportfolio.kaybees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    TextInputEditText name,email1,password1,confirmpassword,contact;
    MaterialButton btnSignup;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();


// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()){
                    name.setError("Enter your Name");
                    return;
                }
                if(email1.getText().toString().isEmpty()){
                    email1.setError("Enter your Email");
                    return;
                }
                if(!email1.getText().toString().contains("@")){
                    email1.setError("Email is inorrect");
                    return;
                }
                if(password1.getText().toString().isEmpty()){
                    password1.setError("Enter your Password");
                    return;
                }
                if(password1.getText().length()<6){
                    password1.setError("Password Length has more then 6 characters");
                    return;
                }
                if(confirmpassword.getText().toString().isEmpty()){
                    confirmpassword.setError("Enter your Confirm Password");
                    return;
                }
                if(confirmpassword.getText().length()<6){
                    confirmpassword.setError("Password Length has more then 6 characters");
                    return;
                }
                if(contact.getText().length()<12){
                    contact.setError("11 Digits Required ");
                    return;
                }

                // CustomerName: Waleed Ghouri
                // Customer Email: waleedkhan@gmail.com
                // Customer Password: waleedghouri11
                // Customer contact: 03xxxxxxxxx

                mAuth.createUserWithEmailAndPassword(email1.getText().toString(),password1.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            SignUpModel signUpModel = new SignUpModel(
                                    name.getText().toString(),
                                    email1.getText().toString(),
                                    password1.getText().toString(),
                                    confirmpassword.getText().toString(),
                                    contact.getText().toString()

                            );

                            Toast.makeText(SignUp.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this,SignIn.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(SignUp.this, "Something Wrong ", Toast.LENGTH_SHORT).show();


                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("tag",e.getMessage() );


                    }
                });



            }


        });
        Toast.makeText(this, "Button is Ok", Toast.LENGTH_SHORT).show();
    }

    public void init(){
        name = findViewById(R.id.customername);
        email1 = findViewById(R.id.customeremail);
        password1 = findViewById(R.id.customerpassword);
        confirmpassword = findViewById(R.id.customerconfirmpassword);
        contact = findViewById(R.id.customercontact);
        btnSignup = findViewById(R.id.btnsignup);

    }
}