package com.example.converter.UILayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.converter.DataLayer.UserRepository;
import com.example.converter.R;

public class SingUpActivity extends AppCompatActivity {

    private final Context context = SingUpActivity.this;

    String name;
    String pass;

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String defaultUnameValue = "";
    private final String defaultPasswordValue = "";

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

        loadPreferences();

        button.setOnClickListener(view -> {
            if (passwordField.getText().toString().isEmpty() ||
                usernameField.getText().toString().isEmpty()) {
                Toast.makeText(context, R.string.error_empty, Toast.LENGTH_SHORT).show();
            } else {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                name = username;
                pass = password;

                new UserRepository(this::setExists, username).execute();
            }
        });
    }

    public void setExists(String userExists) {
        boolean exists = userExists.equals("true");

        if (exists) {
            Toast.makeText(context, R.string.error_register, Toast.LENGTH_SHORT).show();
        } else {
            if (checkPassword(pass)) {
                new UserRegistration(pass, name).execute();
                Toast.makeText(context, R.string.success_register, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
                savePreferences();
                startActivity(intent);
           }
        }

        Log.e("exists", String.valueOf(exists));
    }

    public boolean checkPassword(String password) {
        if (password.length() < 8) {
            Toast.makeText(context, R.string.password_is_too_short, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() > 16) {
            Toast.makeText(context, R.string.password_is_too_long, Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean lowerChars = false;
        boolean upperChars = false;
        boolean specialChars = false;
        boolean numbers = false;

        for (int i = 0; i < password.length(); i++) {
            char test = password.charAt(i);
            if (Character.isLowerCase(test)) lowerChars = true;
            if (Character.isUpperCase(test)) upperChars = true;
            if (Character.isDigit(test)) numbers = true;
            if (test >= 33 && test <= 47 || test >= 58 && test <= 64 || test >= 91 && test <= 96 || test >= 123 && test <= 126)
                specialChars = true;
        }
        if (lowerChars && upperChars && numbers && specialChars) {
            return true;
        }
        if (!lowerChars) {
            Toast.makeText(context, R.string.no_lower_case_characters, Toast.LENGTH_SHORT).show();
        }
        if (!upperChars) {
            Toast.makeText(context, R.string.no_upper_case_characters, Toast.LENGTH_SHORT).show();
        }
        if (!numbers) {
            Toast.makeText(context, R.string.no_numbers, Toast.LENGTH_SHORT).show();
        }
        if (!specialChars) {
            Toast.makeText(context, R.string.no_special_characters, Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        EditText passwordFiled = findViewById(R.id.registerPasswordField);
        EditText usernameFiled = findViewById(R.id.registerUsernameField);

        name = settings.getString(PREF_UNAME, defaultUnameValue);
        pass = settings.getString(PREF_PASSWORD, defaultPasswordValue);

        passwordFiled.setText(pass);
        usernameFiled.setText(name);

        Log.e("Load name", name);
        Log.e("Load password", pass);
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Log.e("Saved name", name);
        Log.e("Saved password", pass);
        editor.putString(PREF_UNAME, name);
        editor.putString(PREF_PASSWORD, pass);
        editor.apply();
    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }
}