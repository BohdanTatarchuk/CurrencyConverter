package com.example.converter.UILayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.converter.DataLayer.UserLogin;
import com.example.converter.DataLayer.UserRepository;
import com.example.converter.R;

public class LoginActivity extends AppCompatActivity {

    String pass;

    String name;

    Context context = LoginActivity.this;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView buttonSingUp = findViewById(R.id.loginSingUpText);
        ImageButton buttonSingIn = findViewById(R.id.loginSingInButton);
        EditText passwordFiled = findViewById(R.id.loginPasswordField);
        EditText usernameFiled = findViewById(R.id.loginUsernameField);

        buttonSingUp.setOnClickListener(view -> {
            Intent intent = new Intent(context, SingUpActivity.class);
            startActivity(intent);
        });

        buttonSingIn.setOnClickListener(view -> {
            if (passwordFiled.getText().toString().isEmpty() ||
                    usernameFiled.getText().toString().isEmpty()) {
                Toast.makeText(context, R.string.error_empty, Toast.LENGTH_SHORT).show();
            } else {
                String username = usernameFiled.getText().toString();
                String password = passwordFiled.getText().toString();
                pass = password;
                name = username;

                new UserRepository(this::setExists, username).execute();
            }
        });
    }

    public void setExists(String userExists) {
        boolean exists = userExists.equals("true");

        if (!exists) {
            Toast.makeText(context, R.string.user_not_exists, Toast.LENGTH_SHORT).show();
        } else {
            new UserLogin(this::setAccepted, name, pass).execute();
        }

        Log.e("exists", String.valueOf(exists));
    }

    public void setAccepted(String check) {
        boolean checkPass = check.equals("true");

        Log.e("checkPass: ", String.valueOf(checkPass));

        if (checkPass) {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(context, R.string.password_error, Toast.LENGTH_SHORT).show();
        }
    }
}
