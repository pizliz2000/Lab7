package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.laba6.adapters.MainAdapter;
import com.example.laba6.other.DBHelper;
import com.example.laba6.other.Model;
import com.example.laba6.R;

public class AddProductActivity extends AppCompatActivity {
    private Button addNewProduct;
    private EditText name, price, description, count;
    private DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        addNewProduct = (Button) findViewById(R.id.newProductbtn);
        count = (EditText) findViewById(R.id.newProductCount);
        description = (EditText) findViewById(R.id.newProductDesc);
        price = (EditText) findViewById(R.id.newProductPrice);
        name = (EditText) findViewById(R.id.newProductName);
        addNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameofProduct = name.getText().toString();
                String discofProduct = description.getText().toString();
                int countofProduct = Integer.parseInt(count.getText().toString());
                int priceofProduct = Integer.parseInt(price.getText().toString());
                db.addProduct(new Model(nameofProduct, discofProduct, countofProduct, priceofProduct));
                Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
