package com.waleedportfolio.kaybees.Consumer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waleedportfolio.kaybees.Consumer.Adapter.FoodRecyclerView;
import com.waleedportfolio.kaybees.R;


public class Home extends Fragment {
    RecyclerView recyclerView;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        FoodRecyclerView foodRecyclerView = new FoodRecyclerView();
        recyclerView.setAdapter(foodRecyclerView);
        return view;
    }
    private void init(){
        recyclerView = view.findViewById(R.id.foodrec);
    }


}