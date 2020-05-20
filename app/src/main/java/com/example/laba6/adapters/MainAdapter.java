package com.example.laba6.adapters;


import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.example.laba6.activities.DeleteAdminPanel;
import com.example.laba6.activities.MainActivity;
import com.example.laba6.other.DBHelper;
import com.example.laba6.other.Helper;
import com.example.laba6.other.Model;
import com.example.laba6.R;
import com.example.laba6.other.ProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ExampleViewHolder> implements Serializable {
    private Context mContext;
    List<Model> models;

    public MainAdapter(Context context, List<Model> newModels) {
        mContext = context;
        models = newModels;
    }


    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.main_activity_item, null);
        return new ExampleViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        final DBHelper db = new DBHelper(mContext);
        final Helper helper = new Helper(mContext);
        final Model currentItem = models.get(position);

        holder.nameTextView.setText(currentItem.getName());
        holder.decTextView.setText(currentItem.getDescription());
        holder.priceTextView.setText(String.valueOf(currentItem.getPrice()));
        holder.countTextView.setText(String.valueOf(currentItem.getCount()));

        holder.buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper.isExist(currentItem.getId())) {
                    ProductModel pM = helper.getProduct(currentItem.getId());
                    helper.updateProduct(new ProductModel(pM.getId(), pM.getName(),
                            pM.getDescription(), pM.getPrice(), pM.getCount() + 1));
                } else {
                    helper.addProduct(new ProductModel(currentItem.getId(), currentItem.getName(),
                            currentItem.getDescription(), currentItem.getPrice(), 1));
                }

                if (currentItem.getCount() > 1) {
                    db.updateProduct(new Model(currentItem.getId(), currentItem.getName(),
                            currentItem.getDescription(), currentItem.getCount() - 1,
                            currentItem.getPrice()));
                } else {
                    db.deleteProduct(new Model(currentItem.getId(), currentItem.getName(),
                            currentItem.getDescription(), currentItem.getCount(),
                            currentItem.getPrice()));
                }
                Intent intent = new Intent(mContext, MainActivity.class);
                ((MainActivity) mContext).finish();
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, priceTextView, decTextView, countTextView;

        public Button buyBtn;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            countTextView = itemView.findViewById(R.id.mainAdapterCount);
            decTextView = itemView.findViewById(R.id.mainAdapterDescription);
            nameTextView = itemView.findViewById(R.id.mainAdapterName);
            priceTextView = itemView.findViewById(R.id.mainAdapterPrice);

            buyBtn = itemView.findViewById(R.id.mainAdapterBuyBtn);
        }
    }

}

