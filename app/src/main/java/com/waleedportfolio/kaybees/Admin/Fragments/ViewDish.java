package com.waleedportfolio.kaybees.Admin.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.waleedportfolio.kaybees.Admin.Adapters.ViewRecyclerAdapter;
import com.waleedportfolio.kaybees.Admin.Model.DishModel;
import com.waleedportfolio.kaybees.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ViewDish extends Fragment {

    View view;
    private DatabaseReference mDatabase;
    RecyclerView viewRecyclerView;
    ArrayList<DishModel> arrayListDish = new ArrayList();
    ArrayList<String> keyList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_view_dish, container, false);
        init();

        // Set LInearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        viewRecyclerView.setLayoutManager(linearLayoutManager);


        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");

        // Firebase RealTime Database Connect
        mDatabase = FirebaseDatabase.getInstance("https://kaybees-b6fd8-default-rtdb.firebaseio.com").getReference();


        mDatabase.child("Dish").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 arrayListDish.clear();
                 keyList.clear();
                 //Log.d("addlistener", String.valueOf(snapshot.getValue()));
                 Log.d("addlistener", String.valueOf(snapshot.getKey()));


                 for(DataSnapshot dataSnapshot: snapshot.getChildren())
                 {
                     keyList.add(dataSnapshot.getKey());
                     DishModel dishModel = dataSnapshot.getValue(DishModel.class);
                     arrayListDish.add(dishModel);

                 }
                 Collections.reverse(arrayListDish);
                 Collections.reverse(keyList );


                 // RecyclerView Adapter on ViewDish
                 ViewRecyclerAdapter viewRecyclerAdapter = new ViewRecyclerAdapter(getContext(),arrayListDish, keyList, mDatabase );
                 viewRecyclerView.setAdapter(viewRecyclerAdapter);

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });

        return view;


    }

    private void init() {
        viewRecyclerView = view.findViewById(R.id.viewrecyclerview);

    }
}