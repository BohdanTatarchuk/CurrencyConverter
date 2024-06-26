package com.example.converter.UILayer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.converter.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";
    private static final String PREF_LANGUAGE = "Language";

    private String name;
    private String pass;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = SettingsActivity.this;
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backButton = findViewById(R.id.settingsBackButton);
        ImageButton changePasswordButton = findViewById(R.id.settingsChangePasswordButton);
        ImageButton logOutButton = findViewById(R.id.settingsLogOutButton);
        ImageView showButton = findViewById(R.id.settingsShowButton);
        TextView passwordText = findViewById(R.id.settingsPassword);

        loadPreferences();
        initSpinner();

        showButton.setOnClickListener(view -> passwordText.setText(pass));

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });

        changePasswordButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, ChangePasswordActivity.class);
            startActivity(intent);
        });

        logOutButton.setOnClickListener(view -> {
            clearPreferences();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        TextView usernameText = findViewById(R.id.settingsUsername);

        name = settings.getString(PREF_UNAME, "");
        pass = settings.getString(PREF_PASSWORD, "");
        String language = settings.getString(PREF_LANGUAGE, "en");

        usernameText.setText(name);
        setLocale(language);
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.apply();
    }

    private void saveLanguagePreference(String language) {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_LANGUAGE, language);
        editor.apply();
    }

    private void clearPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(PREF_UNAME);
        editor.remove(PREF_PASSWORD);
        editor.remove(PREF_LANGUAGE);
        editor.apply();
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public void initSpinner() {
        MaterialAutoCompleteTextView languagesList = findViewById(R.id.settingsLanguagesList);
        String[] languages = new String[]{"English", "Ukrainian", "German", "Polish"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, languages);
        languagesList.setAdapter(adapter);

        languagesList.setOnItemClickListener((adapterView, view, position, l) -> {
            String lang = adapterView.getItemAtPosition(position).toString();
            String languageToLoad = null;

            switch (lang) {
                case "Ukrainian":
                    languageToLoad = "uk";
                    break;
                case "English":
                    languageToLoad = "en";
                    break;
                case "German":
                    languageToLoad = "de";
                    break;
                case "Polish":
                    languageToLoad = "pl";
                    break;
            }

            if (languageToLoad != null) {
                Locale locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                saveLanguagePreference(languageToLoad);
                Log.e("Selected language", languageToLoad);
                recreate();
            }
        });

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String savedLanguage = settings.getString(PREF_LANGUAGE, "en");
        String lang = getLanguageName(savedLanguage);
        if (lang != null) {
            languagesList.setText(lang, false);
        }
    }

    private String getLanguageName(String languageCode) {
        switch (languageCode) {
            case "uk":
                return "Ukrainian";
            case "en":
                return "English";
            case "de":
                return "German";
            case "pl":
                return "Polish";
            default:
                return null;
        }
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
        initSpinner();
    }
}
