package com.example.converter.UILayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.converter.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class SettingsActivity extends AppCompatActivity {
    
    private String selectedLanguage = "en";

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
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButton = findViewById(R.id.settingsBackButton);
        MaterialAutoCompleteTextView languagesList = findViewById(R.id.settingsLanguagesList);

        TextView passwordText = findViewById(R.id.settingsPassword);
        TextView usernameText = findViewById(R.id.settingsUsername);

        loadPreferences();

        Context context = SettingsActivity.this;

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });

        languagesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        selectedLanguage = "uk";
                        break;
                    case 1:
                        selectedLanguage = "en";
                        break;
                    case 2:
                        selectedLanguage = "de";
                        break;
                    case 3:
                        selectedLanguage = "pl";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedLanguage = "en";
            }
        });
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        TextView passwordText = findViewById(R.id.settingsPassword);
        TextView usernameText = findViewById(R.id.settingsUsername);

        name = settings.getString(PREF_UNAME, defaultUnameValue);
        pass = settings.getString(PREF_PASSWORD, defaultPasswordValue);

        passwordText.setText(pass);
        usernameText.setText(name);

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