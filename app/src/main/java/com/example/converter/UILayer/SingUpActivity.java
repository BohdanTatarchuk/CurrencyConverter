package com.example.converter.UILayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.converter.DataLayer.UserLogin;
import com.example.converter.DataLayer.UserRegistration;
import com.example.converter.R;

public class SingUpActivity extends AppCompatActivity {

    String gotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView button = findViewById(R.id.registerSingUpButton);
        EditText passwordField = findViewById(R.id.registerPasswordField);
        EditText usernameField = findViewById(R.id.registerUsernameField);

        button.setOnClickListener(view -> {
            Context context = SingUpActivity.this;
            if (passwordField.getText().toString().isEmpty() ||
                usernameField.getText().toString().isEmpty()) {
                Toast.makeText(context, R.string.error_empty, Toast.LENGTH_SHORT).show();
            } else {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                //TODO fix registration problem (user already exists)
                /*boolean exists;
                UserLogin userLogin = new UserLogin(this::setPassword, username, password);
                userLogin.execute();
                exists = !checkPassword(gotPassword);

                if (exists) {
                    Toast.makeText(context, R.string.error_register, Toast.LENGTH_SHORT).show();
                } else {
                    UserRegistration register = new UserRegistration(password, username);
                    register.execute();
                    Toast.makeText(context, R.string.success_register, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }*/
            }
        });
    }

    public boolean checkPassword(String gotPassword) {
        return gotPassword == null;
    }

    public void setPassword(String password) {
        gotPassword = password;
    }
}