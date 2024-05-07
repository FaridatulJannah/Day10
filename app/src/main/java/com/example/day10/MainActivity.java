 package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RadioGroup radioGroupType = findViewById(R.id.radioGroupType);
        RadioGroup radioGroupCity = findViewById(R.id.radioGroupCity);
        EditText TotalItem = findViewById(R.id.tieTotalDay);
        Button btnRent = findViewById(R.id.btnRent);

        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedType = radioGroupType.getCheckedRadioButtonId();
                int selectedCity = radioGroupCity.getCheckedRadioButtonId();
                RadioButton radioButtonType = findViewById(selectedType);
                RadioButton radioButtonCity = findViewById(selectedCity);
                String carType = radioButtonType.getText().toString();
                String city = radioButtonCity.getText().toString();
                int carPrice = 0;

                double basePrice;
                switch (carType) {
                    case "Pajero":
                        carPrice = 2400000;
                        break;
                    case "Alphard":
                        carPrice = 1550000;
                        break;
                    case "Inova":
                        carPrice = 850000;
                        break;
                    case "Brio":
                        carPrice = 550000;
                        break;
                }
                int cityPrice = 0;
                switch (city) {
                    case "Inside City":
                        cityPrice = 135000;
                        break;
                    case "Outside City":
                        cityPrice = 250000;
                        break;
                }
                String totalDaysStr = TotalItem.getText().toString();
                int totalDays = Integer.parseInt(totalDaysStr);
                int totalPrice = (carPrice + cityPrice) * totalDays;

                double discountPercentage = 0;
                if (totalPrice > 10000000) {
                    discountPercentage = 0.07; // Diskon 7%
                } else if (totalPrice > 5000000) {
                    discountPercentage = 0.05; // Diskon 5%
                }

                int discount = (int) (totalPrice * discountPercentage);
                int discountedTotalPrice = totalPrice - discount;

                Intent intent = new Intent(MainActivity.this, BonActivity.class);
                intent.putExtra("carType", carType);
                intent.putExtra("city", city);
                intent.putExtra("totalDays", totalDays);
                intent.putExtra("discount", discount);
                intent.putExtra("totalPrice", discountedTotalPrice);
                startActivity(intent);
            }
        });
    }
}