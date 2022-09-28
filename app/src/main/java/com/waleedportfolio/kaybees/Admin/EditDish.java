package com.waleedportfolio.kaybees.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.waleedportfolio.kaybees.Admin.Model.DishModel;
import com.waleedportfolio.kaybees.R;

public class EditDish extends AppCompatActivity {
    TextInputEditText dishname, dishdes, dishprice, dishdisprice, dishquantity;
    MaterialButton btnedit;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dish);
        init();

        String name = getIntent().getExtras().getString("name");
        String description = getIntent().getExtras().getString("description");
        String price = getIntent().getExtras().getString("price");
        String discountp = getIntent().getExtras().getString("discountp");
        String quantity = getIntent().getExtras().getString("quantity");
        String key = getIntent().getExtras().getString("key");

        dishname.setText(name);
        dishdes.setText(description);
        dishprice.setText(price);
        dishdisprice.setText(discountp);
        dishquantity.setText(quantity);

//        dishname.setText("");
//        dishquantity.setText("");
//        dishprice.setText("");
//        dishdisprice.setText("");
//        dishquantity.setText("");

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Edit Now", Toast.LENGTH_SHORT).show();
                if(dishname.getText().toString().isEmpty()){
                    dishname.setError("Enter Dish Name");
                    return;
                }
                if(dishdes.getText().toString().isEmpty()){
                    dishdes.setError("Enter Dish Description");
                    return;
                }
                if(dishprice.getText().toString().isEmpty()){
                    dishprice.setError("Enter Dish Price");
                }
                if(dishdisprice.getText().toString().isEmpty()){
                    dishdisprice.setError("Enter Dish Discounted Price");
                    return;
                }
                if(dishquantity.getText().toString().isEmpty()){
                    dishquantity.setError("Enter Quantity of Dish");
                    return;
                }


                // Firebase RealTime Database Connect
                mDatabase = FirebaseDatabase.getInstance("https://kaybees-b6fd8-default-rtdb.firebaseio.com").getReference();
                // get the objects from Model Class
                DishModel dishModel = new DishModel(dishname.getText().toString(),dishdes.getText().toString(),dishprice.getText().toString(),dishdisprice.getText().toString(),dishquantity.getText().toString());
                mDatabase.child("Dish").child(key).setValue(dishModel);
                finish();

            }
        });


    }

    private void init() {
        dishname = findViewById(R.id.dish_name);
        dishdes = findViewById(R.id.dish_des);
        dishprice = findViewById(R.id.dish_price);
        dishdisprice = findViewById(R.id.dish_dis_price);
        dishquantity = findViewById(R.id.dish_quantity);
        btnedit = findViewById(R.id.btn_edit_dish);

    }
}