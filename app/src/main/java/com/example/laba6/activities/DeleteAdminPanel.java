package com.example.laba6.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laba6.other.DBHelper;
import com.example.laba6.other.Model;
import com.example.laba6.R;

import java.util.Random;

public class DeleteAdminPanel extends AppCompatActivity {
    private DBHelper db = new DBHelper(this);
    private EditText name, des, count, price;
    private Model toDelete;
    private Button deleteBtn, updateBtn;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_admin_panel);
        name = (EditText) findViewById(R.id.DeleteName);
        des = (EditText) findViewById(R.id.DeleteDes);
        count = (EditText) findViewById(R.id.DeleteCount);
        price = (EditText) findViewById(R.id.DeletePrice);
        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", 0);
        toDelete = db.getProduct(id);
        name.setText(toDelete.getName());
        des.setText(toDelete.getDescription());
        count.setText(String.valueOf(toDelete.getCount()));
        price.setText(String.valueOf(toDelete.getPrice()));
        updateBtn = (Button) findViewById(R.id.updateAdmin);

        deleteBtn = (Button) findViewById(R.id.deleteAdmin);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteAdminPanel.this, AdminPanel.class);
                db.deleteProduct(toDelete);
                startActivity(intent);
                updateBtn.setEnabled(false);
                ;
                deleteBtn.setEnabled(false);
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setEnabled(false);
                des.setEnabled(false);
                count.setEnabled(false);
                price.setEnabled(false);
                updateBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(DeleteAdminPanel.this, AdminPanel.class);
                        db.updateProduct(new Model(id, name.getText().toString(),
                                des.getText().toString(), Integer.parseInt(count.getText().toString()),
                                Integer.parseInt(price.getText().toString())));
                        startActivity(i);
                    }
                }, random.nextInt() % 2000 + 3000);

            }
        });
    }
}
