package com.waleedportfolio.kaybees.Consumer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.waleedportfolio.kaybees.Consumer.Fragments.Home;
import com.waleedportfolio.kaybees.Consumer.Fragments.MyCard;
import com.waleedportfolio.kaybees.Consumer.Fragments.MyProfile;
import com.waleedportfolio.kaybees.R;

public class UserDashboard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        getSupportActionBar().hide();
        init();

        // Bottom Navigation Bar
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()== R.id.user_home){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.usercontainer,new Home()).commit();
                    return true;
                }
                else if(item.getItemId() == R.id.user_mycard){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.usercontainer,new MyCard()).commit();
                    return true;
                }
                else if(item.getItemId() == R.id.user_myprofile){
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.usercontainer,new MyProfile()).commit();
                    return true;
                }


                return false;
            }
        });
    }

    public void init(){
        bottomNavigationView = findViewById(R.id.bottomNavview);
    }
}