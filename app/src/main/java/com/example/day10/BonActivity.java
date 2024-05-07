package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String carType = extras.getString("carType");
            String city = extras.getString("city");
            int totalDays = extras.getInt("totalDays");
            int discount = extras.getInt("discount");
            int totalPrice = extras.getInt("totalPrice");

            TextView carTypeTextView = findViewById(R.id.tvBonCarType);
            carTypeTextView.setText("Car Type : " + carType);

            TextView cityTextView = findViewById(R.id.tvBonCity);
            cityTextView.setText("City : " + city);

            TextView totalDaysTextView = findViewById(R.id.tvBonDayRent);
            totalDaysTextView.setText("Total Days : " + totalDays);

            TextView discountTextView = findViewById(R.id.tvDiskon);
            discountTextView.setText("Discount : " + discount);

            TextView totalPriceTextView = findViewById(R.id.tvTotal);
            totalPriceTextView.setText("Total Price : " + totalPrice);
        }
    }
}

