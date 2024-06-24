package com.example.converter.UILayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.converter.DataLayer.PasswordChange;
import com.example.converter.R;
import com.example.converter.Utils.Utils;

public class ChangePasswordActivity extends AppCompatActivity {

    private String firstPassword;
    private String secondPassword;
    private String username;

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String defaultUnameValue = "";

    private final Context context = ChangePasswordActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText firstField = findViewById(R.id.changeFirstPasswordField);
        EditText secondField = findViewById(R.id.changeSecondPasswordField);
        ImageButton changePasswordButton = findViewById(R.id.changeChangePasswordButton);
        ImageButton backButton = findViewById(R.id.changeBackButton);

        changePasswordButton.setOnClickListener(view -> {
            firstPassword = firstField.getText().toString();
            secondPassword = secondField.getText().toString();

            if (!firstPassword.isEmpty() && !secondPassword.isEmpty()) {
                if (!Utils.checkPassword(firstPassword, secondPassword)) {
                    Toast.makeText(context, R.string.mismatch, Toast.LENGTH_SHORT).show();
                } else {
                    if (Utils.checkPassword(firstPassword, context)
                            && Utils.checkPassword(secondPassword, context)) {

                        loadPreferences();
                        new PasswordChange(firstPassword, username).execute();
                        savePreferences();

                        Toast.makeText(context, R.string.change_success, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        Log.e("Saved password", firstPassword);
        editor.putString(PREF_PASSWORD, firstPassword);
        editor.apply();
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        username = settings.getString(PREF_UNAME, defaultUnameValue);
        Log.e("Load name", username);
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