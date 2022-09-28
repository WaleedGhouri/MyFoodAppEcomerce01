package com.waleedportfolio.kaybees.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.waleedportfolio.kaybees.Admin.Fragments.AddDish;
import com.waleedportfolio.kaybees.Admin.Fragments.CompleteOrders;
import com.waleedportfolio.kaybees.Admin.Fragments.PendingOrders;
import com.waleedportfolio.kaybees.Admin.Fragments.ViewDish;
import com.waleedportfolio.kaybees.R;
import com.waleedportfolio.kaybees.SignIn;

public class AdminDashboard extends AppCompatActivity {
    NavigationView myNav;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        init();
        openFragment(new AddDish());

        myNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.add_dish){
                    openFragment(new AddDish());

                }
                else  if(item.getItemId() == R.id.view_dish){
                    openFragment(new ViewDish());

                }
                else  if(item.getItemId() == R.id.pending_order){
                    openFragment(new PendingOrders());

                }
                else  if(item.getItemId() == R.id.complete_orders){
                    openFragment(new CompleteOrders());

                }
                else  if(item.getItemId() == R.id.sign_out){
                    saveData();
                    Intent i = new Intent(AdminDashboard.this, SignIn.class);
                    startActivity(i);
                    finish();

                }
                drawerLayout.close();
                return false;
            }
        });

    }

    public void openFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.admin_Container,fragment);
        ft.commit();

    }
    public void init(){
        myNav = findViewById(R.id.my_nav);
        drawerLayout = findViewById(R.id.my_drawer);

    }
    // sharePrefrences are used to savedata
    public void saveData(){
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
        myEdit.putBoolean("isLogin",false);




// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();

    }
}