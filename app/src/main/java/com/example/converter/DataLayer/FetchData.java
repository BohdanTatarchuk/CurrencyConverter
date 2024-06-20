package com.example.converter.DataLayer;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class FetchData extends AsyncTask<Void, Void, String> {

    private final FetchDataCallback callback;
    private final String currencyA;
    private final String currencyB;
    private final double amount;

    public FetchData(FetchDataCallback callback, String currencyA, String currencyB, double amount) {
        this.callback = callback;
        this.currencyA = currencyA;
        this.currencyB = currencyB;
        this.amount = amount;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String data = "";

        try {
            URL url = new URL("https://currencyconverterserver.azurewebsites.net/api/v1/exchanges/?from=" + currencyA + "&to=" + currencyB + "&amount=" + amount);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            line = bufferedReader.readLine();
            data = data + line;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callback.onFetchDataComplete(result);
    }
}
