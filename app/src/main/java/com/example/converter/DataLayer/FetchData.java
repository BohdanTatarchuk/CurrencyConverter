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

    private FetchDataCallback callback;
    private String currencyA;
    private String currencyB;
    private double amount;

    public FetchData(FetchDataCallback callback, String currencyA, String currencyB, double amount) {
        this.callback = callback;
        this.currencyA = currencyA;
        this.currencyB = currencyB;
        this.amount = amount;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String data = "";
        String result = "";

        try {
            URL url = new URL("https://5b58-2001-871-237-ae68-3438-9e72-879c-11b.ngrok-free.app/api/v1/exchanges/1");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject convertsInto = new JSONObject(data);

            result = "Id: " + convertsInto.get("id") + "\n" +
                            "currencyA: " + convertsInto.get("currencyA") + "\n" +
                            "currencyB: " + convertsInto.get("currencyB") + "\n" +
                            "exchangeRate: " + convertsInto.get("exchangeRate") + "\n";

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callback.onFetchDataComplete(result);
    }
}
