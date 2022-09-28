package com.waleedportfolio.kaybees.Consumer.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waleedportfolio.kaybees.Consumer.Adapter.CardRecyclerView;
import com.waleedportfolio.kaybees.R;

public class MyCard extends Fragment {
    View view;
    RecyclerView cardRecycler;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_card, container, false);
        init();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        cardRecycler.setLayoutManager(linearLayoutManager);

        CardRecyclerView cardRecyclerView = new CardRecyclerView();
        cardRecycler.setAdapter(cardRecyclerView);




        return view;
    }

    public void init(){
        cardRecycler = view.findViewById(R.id.mycarrec);

    }
}