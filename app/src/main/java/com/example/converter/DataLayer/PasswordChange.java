package com.example.converter.DataLayer;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PasswordChange extends AsyncTask<Void, Void, String> {

    private final String username;
    private final String password;

    public PasswordChange(String password, String username) {
        this.password = password;
        this.username = username;
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpURLConnection connection = null;
        BufferedOutputStream out = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("https://currencyconverterserver.azurewebsites.net/api/v1/users/updateByLogin=" + username);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            JSONObject object = new JSONObject();
            object.put("login", username);
            object.put("password", password);

            String data = object.toString();

            out = new BufferedOutputStream(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            connection.connect();

            int code = connection.getResponseCode();
            Log.e("Connection code: ", String.valueOf(code));

            if (code == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    errorResponse.append(line);
                }
                Log.e("Error Response: ", errorResponse.toString());
                return errorResponse.toString();
            }

        } catch (IOException | JSONException e) {
            Log.e("UserRegistration", "Error: " + e.getMessage(), e);
            return "Error: " + e.getMessage();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    Log.e("UserRegistration", "Error closing output stream: " + e.getMessage(), e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("UserRegistration", "Error closing reader: " + e.getMessage(), e);
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
