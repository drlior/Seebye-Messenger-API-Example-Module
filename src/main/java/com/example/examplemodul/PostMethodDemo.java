package com.example.examplemodul;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PostMethodDemo extends AsyncTask<String , Void ,String> {
    private String server_response;

    public static String execute_non_async() throws IOException {
        URL url;
        HttpURLConnection urlConnection;
        String server_response="";

        url = new URL("http://tehilim.meteor-comm.com:86/api/contacts/");
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setRequestMethod("POST");
        //urlConnection.setRequestProperty("Authorization", "basic " +
        //        "user:pass");

        DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream ());

        try {
            JSONObject obj = new JSONObject();
            obj.put("type" , "GROUP");
            obj.put("strDisplayname" , "lior");
            obj.put("strIDMessenger" , "lior@");


            wr.writeBytes(obj.toString());
            Log.e("JSON Input", obj.toString());
            wr.flush();
            wr.close();
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        urlConnection.connect();

        int responseCode = urlConnection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            server_response = readStream(urlConnection.getInputStream());
        }
        return server_response;

    }

    @Override
    protected String doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection;

        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");

            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream ());

            try {
                JSONObject obj = new JSONObject();
                obj.put("type" , "GROUP");
                obj.put("strDisplayname" , "lior");
                obj.put("strIDMessenger" , "lior@");

                wr.writeBytes(obj.toString());
                Log.e("JSON Input", obj.toString());
                wr.flush();
                wr.close();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            urlConnection.connect();

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                server_response = readStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("Response", "" + server_response);
    }


    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}