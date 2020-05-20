package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laba6.other.DBHelper;
import com.example.laba6.other.Model;
import com.example.laba6.R;
import com.example.laba6.adapters.AdminPanelAdapter;

import java.util.List;

public class AdminPanel extends AppCompatActivity {
    private RecyclerView recyclerView;
    DBHelper db = new DBHelper(this);
    List<Model> listView;
    Button addnewProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        listView = db.getAllProducts();
        recyclerView=(RecyclerView)findViewById(R.id.MainAdapter);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        AdminPanelAdapter adminPanelAdapter=new AdminPanelAdapter(AdminPanel.this, listView);
        recyclerView.setAdapter(adminPanelAdapter);
        addnewProduct=(Button)findViewById(R.id.addNewProduct);
        addnewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanel.this, AddProductActivity.class);
                startActivity(intent);
            }
        });


    }
}
