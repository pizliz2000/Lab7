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
import com.example.laba6.adapters.MainAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DBHelper db = new DBHelper(this);
    private List<Model> listView;
    private Button adminPannel;
    private Button cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = db.getAllProducts();
        recyclerView = (RecyclerView) findViewById(R.id.MainAdapter);
        MainAdapter mainAdapter = new MainAdapter(MainActivity.this, listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mainAdapter);


        adminPannel = (Button) findViewById(R.id.gotoAdminPanel);
        adminPannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminPanel.class);
                startActivity(intent);
            }
        });
        cart=(Button)findViewById(R.id.gotoCart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

}
