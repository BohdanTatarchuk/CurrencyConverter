package com.example.converter.UILayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.converter.DataLayer.FetchData;
import com.example.converter.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

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

        MaterialAutoCompleteTextView autoCompleteA = findViewById(R.id.inputA);
        MaterialAutoCompleteTextView autoCompleteB = findViewById(R.id.inputB);

        EditText inputField = findViewById(R.id.mainAmount);
        TextView resultText = findViewById(R.id.mainResult);
        ImageButton convertButton = findViewById(R.id.mainConvertButton);
        ImageButton backButton = findViewById(R.id.mainBackButton);
        ImageButton settingsButton = findViewById(R.id.mainSettingsButton);

        Context context = MainActivity.this;

        convertButton.setOnClickListener(view -> {
            if ((autoCompleteA.getText().toString().isEmpty()) ||
                    (autoCompleteB.getText().toString().isEmpty()) ||
                    inputField.getText().toString().isEmpty()) {
                Toast.makeText(context, R.string.error_select, Toast.LENGTH_SHORT).show();
            } else {
                String currencyA = autoCompleteA.getText().toString();
                String currencyB = autoCompleteB.getText().toString();
                double amount = Double.parseDouble(inputField.getText().toString());

                FetchData fetchData = new FetchData(resultText::setText, currencyA, currencyB, amount);
                fetchData.execute();
            }
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        });

        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, SettingsActivity.class);
            startActivity(intent);
        });
    }
}