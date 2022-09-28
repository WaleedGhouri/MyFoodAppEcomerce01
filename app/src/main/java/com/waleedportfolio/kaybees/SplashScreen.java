package com.waleedportfolio.kaybees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.waleedportfolio.kaybees.Admin.AdminDashboard;
import com.waleedportfolio.kaybees.Consumer.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        /****** Create Thread that will sleep for 5 seconds****/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    if(getSharePreferencesData() == true ){
                        Intent i = new Intent(getBaseContext(), AdminDashboard.class);
                        startActivity(i);

                    }
                    else
                    {

                        // Check if user is signed in (non-null) and update UI accordingly.
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        if(currentUser != null){
                            Intent i = new Intent(getBaseContext(), UserDashboard.class);
                            startActivity(i);
                            finish();

                        }
                        else{
                            // After 5 seconds redirect to another intent
                            Intent i=new Intent(getBaseContext(),SignIn.class);
                            startActivity(i);


                        }
                    }


                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

    }

    @Override
    public void onStart() {
        super.onStart();




    }

    public boolean getSharePreferencesData(){
        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref",
                Context.MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        //String s1 = sh.getString("name", "");
        //int a = sh.getInt("age", 0);

        boolean isLogin = sh.getBoolean("isLogin",false);
        return isLogin;


    }
}