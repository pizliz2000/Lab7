package com.example.laba6.adapters;



import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.example.laba6.other.Model;
import com.example.laba6.R;
import com.example.laba6.other.ProductModel;

import java.io.Serializable;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter .ExampleViewHolder> implements Serializable {
    private Context mContext;
    List<ProductModel>models;

    public CartAdapter (Context context, List<ProductModel>newModels) {
        mContext=context;
        models=newModels;
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cart_item, null);
        return new ExampleViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ProductModel currentItem =models.get(position);
        String name = currentItem.getName();
        holder.nameTextView.setText(name);
        holder.countTextView.setText(String.valueOf(currentItem.getCount()));

    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView countTextView;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.cartName);
            countTextView=itemView.findViewById(R.id.cartCount);
        }
    }

}


