package com.example.converter.DataLayer;

import android.os.AsyncTask;
import android.util.Log;

import com.example.converter.DataLayer.CallbackInterfaces.UserLoginCallback;
import com.example.converter.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserLogin extends AsyncTask<Void, Void, String> {

    private final UserLoginCallback callback;
    private final String username;
    private final String password;

    public UserLogin(UserLoginCallback callback, String username, String password) {
        this.callback = callback;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String data = "";
        String result;
        String receivedPassword;
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

            Log.e("CodeLogin", String.valueOf(responseCode));

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                data += line;
            }

            JSONObject obj = new JSONObject(data);
            receivedPassword = obj.getString("password");

            Log.e("Received password: ", receivedPassword);

            if (Utils.checkPassword(password, receivedPassword)) {
                result = "true";
            } else {
                result = "false";
            }

            inputStream.close();
            bufferedReader.close();
            connection.disconnect();
        } catch (IOException | JSONException e) {
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
