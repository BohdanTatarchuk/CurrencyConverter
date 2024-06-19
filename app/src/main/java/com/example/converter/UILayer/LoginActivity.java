package com.example.converter.UILayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.converter.R;

public class LoginActivity extends AppCompatActivity {

    String gotPassword = "";

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

        Context context = LoginActivity.this;

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

                boolean success;

                UserLogin userLogin = new UserLogin(this::setPassword, username, password);
                userLogin.execute();

                success = checkPassword(password, gotPassword);

                if (success) {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                } else {
                    //TODO make better error message
                    /*passwordFiled.setHintTextColor(R.color.red);
                    passwordFiled.setHint(R.string.password_error);
                    try {
                        wait(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    passwordFiled.setHint(R.string.password);
                    passwordFiled.setHintTextColor(R.color.purple);*/

                    Toast.makeText(context, R.string.password_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkPassword(String password, String gotPassword) {
        if (gotPassword.equals(null)) return false;
        return password.equals(gotPassword);
    }

    public void setPassword(String password) {
        gotPassword = password;
    }
}