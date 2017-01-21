package com.example.examplemodul;

import android.os.StrictMode;

import com.google.gson.GsonBuilder;
import com.seebye.messengerapi.api.Contact;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.CloseableHttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;

class ContactAPI {

    static void sync_with_server(Contact input_contact) {
        Map<String, String> contact = new HashMap<String, String>();

        contact.put("type", input_contact.getType().toString());
        contact.put("strDisplayname", input_contact.getDisplayname());
        contact.put("strIDMessenger", input_contact.getIDMessenger());
        String json = new GsonBuilder().create().toJson(contact, Map.class);
        makeRequest("http://tehilim.meteor-comm.com:86/api/contacts/", json);
    }

    private static HttpResponse makeRequest(String uri, String json) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            HttpPost httpPost = new HttpPost(uri);
            httpPost.setEntity(new StringEntity(json, HTTP.UTF_8));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");














/*

            HttpClient httpClient = new HttpClient() {
                @Override
                public HttpParams getParams() {
                    return null;
                }

                @Override
                public ClientConnectionManager getConnectionManager() {
                    return null;
                }

                @Override
                public HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
                    return null;
                }

                @Override
                public HttpResponse execute(HttpUriRequest request, HttpContext context) throws IOException, ClientProtocolException {
                    return null;
                }

                @Override
                public HttpResponse execute(HttpHost target, HttpRequest request) throws IOException, ClientProtocolException {
                    return null;
                }

                @Override
                public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
                    return null;
                }

                @Override
                public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
                    return null;
                }

                @Override
                public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
                    return null;
                }

                @Override
                public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
                    return null;
                }

                @Override
                public <T> T execute(HttpHost target, HttpRequest request, ResponseHandler<? extends T> responseHandler, HttpContext context) throws IOException, ClientProtocolException {
                    return null;
                }
            };

            //UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity();

            //httpPost.setEntity(urlEncodedFormEntity);

            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);

                InputStream inputStream = httpResponse.getEntity().getContent();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();

                String bufferedStrChunk = null;

                while((bufferedStrChunk = bufferedReader.readLine()) != null){
                    stringBuilder.append(bufferedStrChunk);
                }

                String result = stringBuilder.toString();
                return  null;

            } catch (ClientProtocolException cpe) {

                cpe.printStackTrace();
            } catch (IOException ioe) {
                System.out.println("Second Exception caz of HttpResponse :" + ioe);
                ioe.printStackTrace();
            }
            */


            DefaultHttpClient a = new DefaultHttpClient();

            CloseableHttpResponse b = a.execute(httpPost);
            //return b;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
