package com.example.converter.DataLayer;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserRepository extends AsyncTask<Void, Void, String> {

    private final UserLoginCallback callback;
    private final String username;


    public UserRepository(UserLoginCallback callback, String username) {
        this.callback = callback;
        this.username = username;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String data = "";
        String result;
        HttpURLConnection connection;
        InputStream inputStream;
        BufferedReader bufferedReader;

        try {
            URL url = new URL("https://currencyconverterserver.azurewebsites.net/api/v1/users/" + username);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }

            Log.e("CodeCheck", String.valueOf(responseCode));

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                data += line;
            }

            if(data.isEmpty() || data.equals("null")) {
                result = "false";
            } else {
                result = "true";
            }

            Log.e("User exists", result);

            inputStream.close();
            bufferedReader.close();
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callback.onUserLoginComplete(result);
    }
}
