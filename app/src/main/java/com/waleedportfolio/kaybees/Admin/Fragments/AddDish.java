package com.waleedportfolio.kaybees.Admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.waleedportfolio.kaybees.Admin.Model.DishModel;
import com.waleedportfolio.kaybees.R;


public class AddDish extends Fragment {
    TextInputEditText dishname, dishdes,dishprice,dishdisprice,dishquantity;
    MaterialButton btnupdate;
    View view;
    private DatabaseReference mDatabase;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_add_dish, container, false);
        init();


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add Custom Validation
                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
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
               DishModel dishModel = new DishModel(dishname.getText().toString(),
                       dishdes.getText().toString(),
                       dishprice.getText().toString(),
                       dishdisprice.getText().toString(),
                       dishquantity.getText().toString());

                dishname.setText("");
                dishdes.setText("");
                dishprice.setText("");
                dishdisprice.setText("");
                dishquantity.setText("");


                //mDatabase.child("Dish").push().setValue(dishModel);

                mDatabase.child("Dish").push().setValue(dishModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                             ;
                            Toast toast = Toast.makeText(getContext(),
                                    "Sucessfully",
                                    Toast.LENGTH_SHORT

                            );


                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("waleed error",e.getMessage() );
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

//                mDatabase.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataSnapshot> task) {
//                        if (!task.isSuccessful()) {
//                            Log.e("firebase", "Error getting data", task.getException());
//                        }
//                        else {
//                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                        }
//                    }
//                });


            }






        }); // btnUpdate End



        return view;


    }


    public void init(){
        dishname = view.findViewById(R.id.dish_name);
        dishdes = view.findViewById(R.id.dish_des);
        dishprice = view.findViewById(R.id.dish_price);
        dishdisprice = view.findViewById(R.id.dish_dis_price);
        dishquantity = view.findViewById(R.id.dish_quantity);
        btnupdate = view.findViewById(R.id.btn_update_dish);

    }
}