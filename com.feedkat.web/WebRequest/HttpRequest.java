package WebRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import sun.net.www.http.HttpClient;

public class HttpRequest {
	public enum Method {
		POST("POST"),
		GET("GET"),
		DELETE("DELETE"),
		PUT("PUT");
		
		private String mKey;
		Method(String key) {
			this.mKey = key;
		}
		
		public final String getKey() {
			return this.mKey;
		}
	}
	private String mUrl;
	private Method mType;
	private onResponse.SuccessListener<JSONObject> mOnSuccess;
	private onResponse.ErrorListener<String> mOnError;
	private HttpURLConnection mConnection;
	private JSONObject mParams;
	private Runnable requestRunnable;
	
	public HttpRequest(@NotNull Method type, @NotNull String url) {
		this(type, url, null, null, null);
	}
	
	public HttpRequest(@NotNull Method type, @NotNull String url, @NotNull JSONObject obj) {
		this(type, url, obj, null, null);
	}

	public HttpRequest(@NotNull Method type,@NotNull String url, @Nullable JSONObject params, @Nullable onResponse.SuccessListener<JSONObject> onSuccess, @Nullable onResponse.ErrorListener<String> onError) {
		this.mUrl = url;
		this.mType = type;
		this.mOnSuccess = onSuccess;
		this.mOnError = onError;
		this.mParams = params;
		initRunnable();
	}
	
	private void initRunnable() {
		requestRunnable = new Runnable() {
			@Override
			public void run() {
				try
				{
					URL url = new URL(mUrl);
					mConnection = (HttpURLConnection) url.openConnection();
					mConnection.setRequestMethod(mType.getKey());
					mConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

					if(mParams != null) mConnection.setRequestProperty("Content-Length", Integer.toString(mParams.toString().getBytes().length));
					else				mConnection.setRequestProperty("Content-Length", "0");
					mConnection.setRequestProperty("Content-Language", "en-US");  

					mConnection.setUseCaches(false);
					mConnection.setDoOutput(true);

				    //Send request
					if(mParams != null) 
					{
					    DataOutputStream wr = new DataOutputStream (mConnection.getOutputStream());
					    wr.writeBytes(mParams.toString());
					    wr.close();
					}

				    //Get Response  
				    InputStream is = mConnection.getInputStream();
				    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
				    String line;
				    while ((line = rd.readLine()) != null) {
				      response.append(line);
				      response.append('\r');
				    }
				    rd.close();
				    
				    if(mOnSuccess!=null) mOnSuccess.onSuccess(new JSONObject(response.toString()));
					
				} catch (Exception e) {
					if(mOnError != null) mOnError.onError("URL : " + mUrl.toString() + " TYPE : " + mType.getKey() + " error : " + e.toString());
				}
				
			}
		};
	}
	
	public void execute() {
		execute(true);
	}
	
	public void execute(boolean asynchrone) {	
		if(requestRunnable == null) return;
		
		if(asynchrone) 	new Thread(requestRunnable, "HttpRequestThread").start();
		else			requestRunnable.run();
	}
}
