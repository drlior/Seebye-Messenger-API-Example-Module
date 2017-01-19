package com.example.examplemodul;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.seebye.messengerapi.api.App;

import cz.msebera.android.httpclient.Header;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends App
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		CrashHandler.setCrashHandler();


	}

    public void test_ws() {
        try {
            RequestParams request_params = new RequestParams();
            request_params.put("type", "NORMAL");
            request_params.put("strDisplayname", "lior");
            request_params.put("strIDMessenger", "12345");
            ContactRestClient.get("contacts/", request_params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    RequestParams request_params = new RequestParams();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    RequestParams request_params = new RequestParams();
                }
            });

        }
        catch (Exception e){

            RequestParams request_params = new RequestParams();
        }
    }
}