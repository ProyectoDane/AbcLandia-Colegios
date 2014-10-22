package com.frba.abclandia.webserver;

import android.util.Log;

import com.frba.abclandia.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ABCLandiaRestClient {
	private static final String BASE_URL = "http://yaars.com.ar/abclandia/public/";
	 
	private static AsyncHttpClient client = new AsyncHttpClient();

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.get(getAbsoluteUrl(url), params, responseHandler);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	  }

	  private static String getAbsoluteUrl(String relativeUrl) {
		  Log.d("URLS", BASE_URL + relativeUrl);
	      return BASE_URL + relativeUrl;
	  }

}
