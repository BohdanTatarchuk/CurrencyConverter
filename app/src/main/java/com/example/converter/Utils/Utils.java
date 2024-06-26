package com.example.converter.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.converter.R;

import java.text.DecimalFormat;

public class Utils {

    public static String round(String number){
        double res = Double.parseDouble(number);
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(res);
    }

    public static boolean checkPassword(String password, Context context) {
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

    public static boolean checkPassword(String passwordA, String passwordB) {
        return passwordA.equals(passwordB);
    }

    public static boolean isLatinLetter(String word, Context context) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))) {
                Toast.makeText(context, R.string.name_error, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
