package com.waleedportfolio.kaybees.Admin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.waleedportfolio.kaybees.Admin.EditDish;
import com.waleedportfolio.kaybees.Admin.Model.DishModel;
import com.waleedportfolio.kaybees.R;

import java.util.ArrayList;

public class ViewRecyclerAdapter extends RecyclerView.Adapter<ViewRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<DishModel> arrayListDish;
    ArrayList<String> keyList;
    private DatabaseReference mDatabase;


    public ViewRecyclerAdapter(Context context, ArrayList<DishModel> arrayListDish,ArrayList<String> keyList, DatabaseReference mDatabase) {
        this.context = context;
        this.arrayListDish = arrayListDish;
        this.keyList = keyList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dish_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String dName = arrayListDish.get(position).getDname();
        String dAcPrice = arrayListDish.get(position).getDprice();
        String dDes = arrayListDish.get(position).getDdescription();
        String dQuantity = arrayListDish.get(position).getDquantity();
        String dDisPrice = arrayListDish.get(position).getDdisprice();
        String key = keyList.get(position);

        holder.dishName.setText(dName);
        holder.dishAcPrice.setText("Rs " + dAcPrice);
        holder.dishDes.setText(dDes);
        holder.dishQuanitity.setText("Quantity "+ dQuantity);
        holder.dishDisPrice.setText("Rs " + dDisPrice);


        // Firebase RealTime Database Connect
        mDatabase = FirebaseDatabase.getInstance("https://kaybees-b6fd8-default-rtdb.firebaseio.com").getReference();

            // Delete Data
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("Dish").child(key).removeValue();




            }
        });


                // Edit Data
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditDish.class);

                i.putExtra("name",dName);
                i.putExtra("description",dDes);
                i.putExtra("price",dAcPrice);
                i.putExtra("discountp",dDisPrice);
                i.putExtra("quantity",dQuantity);
                i.putExtra("key",key);

                context.startActivity(i);

            }
        });






    }

    @Override
    public int getItemCount() {
        return arrayListDish.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishName,dishAcPrice,dishDes,dishQuanitity,dishDisPrice;
        ImageView deleteBtn,editBtn;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            dishName = itemView.findViewById(R.id.viewname);
            dishAcPrice = itemView.findViewById(R.id.viewAcualprice);
            dishDes  = itemView.findViewById(R.id.viewdishDescription);
            dishQuanitity = itemView.findViewById(R.id.viewdishquantity);
            dishDisPrice = itemView.findViewById(R.id.viewdishdiscountedprice);
            deleteBtn = itemView.findViewById(R.id.deletebtn);
            editBtn = itemView.findViewById(R.id.editbtn);


        }
    }
}
