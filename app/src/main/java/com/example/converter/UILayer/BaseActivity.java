package com.example.converter.UILayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    String pass;
    String name;
    String language;

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";
    private static final String PREF_PASSWORD = "Password";
    private static final String PREF_LANGUAGE = "Language";

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    protected void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        String defaultUnameValue = "";
        name = settings.getString(PREF_UNAME, defaultUnameValue);

        String defaultPasswordValue = "";
        pass = settings.getString(PREF_PASSWORD, defaultPasswordValue);

        String defaultLanguage = "";
        language = settings.getString(PREF_LANGUAGE, defaultLanguage);
        setLocale(language);

    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(PREF_UNAME, name);
        editor.putString(PREF_PASSWORD, pass);
        editor.putString(PREF_LANGUAGE, language);
        editor.apply();
    }

}
