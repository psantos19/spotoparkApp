package com.example.spotoparkapp.downloaders;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetPersons extends AsyncTask<String, Void, JSONArray> {

    @Override
    protected JSONArray doInBackground(String... urls) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try{

            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();
            while(data != -1)
            {
                char current = (char)data;
                result += current;
                data = reader.read();
            }

            JSONArray arr = new JSONArray(result);

            return arr;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}