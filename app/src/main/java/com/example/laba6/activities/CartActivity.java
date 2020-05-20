package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.laba6.other.Helper;
import com.example.laba6.other.Model;
import com.example.laba6.R;
import com.example.laba6.other.MyNewDelayHelper;
import com.example.laba6.other.ProductModel;

import com.example.laba6.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Random random = new Random();
    Helper db = new Helper(this);
    List<ProductModel> productModels;
    Button buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        productModels = db.getAllProducts();
        recyclerView = (RecyclerView) findViewById(R.id.cartRecycleView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this, productModels);
        recyclerView.setAdapter(cartAdapter);
        buy = (Button) findViewById(R.id.butProduct);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buy.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(CartActivity.this, MainActivity.class);
                        startActivity(i);
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Покупка совершена", Toast.LENGTH_SHORT);
                        toast.show();
                        db.deleteAll();

                    }
                }, random.nextInt() % 2000 + 3000);


            }
        });
    }
}
