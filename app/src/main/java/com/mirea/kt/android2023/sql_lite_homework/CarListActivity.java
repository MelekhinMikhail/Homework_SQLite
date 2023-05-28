package com.mirea.kt.android2023.sql_lite_homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class CarListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView imageViewBack;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        recyclerView = findViewById(R.id.recyclerView);
        imageViewBack = findViewById(R.id.imageViewBack);

        dbManager = new DBManager(new MyAppSQLiteHelper(this, "my_database.db", null, 1));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new CarAdapter(dbManager.getAllCars()));

        imageViewBack.setOnClickListener(x -> {
            finish();
        });
    }
}