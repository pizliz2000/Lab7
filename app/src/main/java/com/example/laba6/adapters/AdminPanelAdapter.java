package com.example.laba6.adapters;


import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.laba6.other.Model;
import com.example.laba6.R;
import com.example.laba6.activities.DeleteAdminPanel;

import java.io.Serializable;
import java.util.List;


public class AdminPanelAdapter extends RecyclerView.Adapter<AdminPanelAdapter.ExampleViewHolder> implements Serializable {
    private Context mContext;
    List<Model>models;
    public AdminPanelAdapter(Context context, List<Model>newModels) {
        mContext=context;
        models=newModels;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.admin_panel_fragment, null);
        return new ExampleViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, final int position) {
        final Model currentItem =models.get(position);
        String name = currentItem.getName();
        holder.nameTextView.setText(name);



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, DeleteAdminPanel.class);
                int id=currentItem.getId();
                intent.putExtra("id", id);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public LinearLayout cardView;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.adminPanelname);
            cardView=itemView.findViewById(R.id.cardAdminPanel);
        }
    }

}

