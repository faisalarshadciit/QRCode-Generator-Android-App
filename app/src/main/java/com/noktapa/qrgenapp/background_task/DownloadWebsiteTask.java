package com.noktapa.qrgenapp.background_task;

import android.os.AsyncTask;

import com.noktapa.qrgenapp.listeners.CountryNameListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWebsiteTask extends AsyncTask<Void, Void, String> {

    private final CountryNameListener listener;

    public DownloadWebsiteTask(CountryNameListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String urlString = "http://www.ip-api.com/json";
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String websiteContent = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a string
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();

            if (inputStream == null) {
                // Nothing to do.
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            websiteContent = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return websiteContent;
    }

    @Override
    protected void onPostExecute(String result) {
        // Here, you can parse the JSON response and extract the country name.
        // For example, if the JSON response contains a "country" field:
        // JSONObject jsonObject = new JSONObject(result);
        // String countryName = jsonObject.getString("country");
        // Then, you can use the countryName as needed.

        try {
            JSONObject jsonObject = new JSONObject(result);
            String countryName = jsonObject.getString("country");

            // Notify the listener with the country name
            if (listener != null) {
                listener.onCountryNameReceived(countryName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
