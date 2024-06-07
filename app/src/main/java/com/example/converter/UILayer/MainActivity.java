package com.example.converter.UILayer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        TextInputLayout listA = findViewById(R.id.dropdown_menuA);
        TextInputLayout listB = findViewById(R.id.dropdown_menuB);
        MaterialAutoCompleteTextView autoCompleteA = findViewById(R.id.inputA);
        MaterialAutoCompleteTextView autoCompleteB = findViewById(R.id.inputB);

        EditText inputField = findViewById(R.id.amount);
        TextView resultText = findViewById(R.id.result);
        Button convertButton = findViewById(R.id.convertButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoCompleteA.getText().toString().isEmpty()) {
                    listA.setError("Please select an option");
                } else if (autoCompleteB.getText().toString().isEmpty()) {
                    listB.setError("Please select an option");
                } else {
                    Toast.makeText(MainActivity.this,
                                          autoCompleteA.getText().toString() + " " + autoCompleteB.getText().toString(),
                                          Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}