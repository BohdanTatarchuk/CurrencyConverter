package com.example.converter.DataLayer;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UserRegistration extends AsyncTask<Void, Void, String> {

    private final String username;
    private final String password;

    public UserRegistration(String password, String username) {
        this.password = password;
        this.username = username;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("https://currencyconverterserver.azurewebsites.net/api/v1/users/save");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());

            JSONObject object = new JSONObject();
            object.put("login", username);
            object.put("password", password);

            String data = object.toString();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            int code = connection.getResponseCode();

            Log.e("Connection code: ", String.valueOf(code));

            connection.connect();
        } catch (IOException | JSONException e) {
            Log.e("UserRegistration", "Error: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return "";
    }
}
