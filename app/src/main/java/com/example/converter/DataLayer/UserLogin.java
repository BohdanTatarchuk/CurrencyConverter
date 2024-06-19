package com.example.converter.DataLayer;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserLogin extends AsyncTask<Void, Void, String> {

    private UserLoginCallback callback;
    private String username;
    private String password;

    public UserLogin(UserLoginCallback callback, String username, String password) {
        this.callback = callback;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String data = "";
        String recievedPassword = "";

        try {
            URL url = new URL("https://currencyconverterserver.azurewebsites.net/api/v1/users/" + username);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            line = bufferedReader.readLine();
            data = data + line;

            if (data.equals("null")) {
                recievedPassword = null;
            } else {
                JSONObject object = new JSONObject(data);
                recievedPassword = String.valueOf(object.get("password"));
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

        return recievedPassword;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callback.onUserLoginComplete(result);
    }
}
