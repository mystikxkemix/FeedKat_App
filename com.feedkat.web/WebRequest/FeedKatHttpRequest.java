package WebRequest;

import org.jdeferred.Deferred;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.json.JSONException;
import org.json.JSONObject;

import Model.CatBasics;
import WebRequest.HttpRequest.Method;
import WebRequest.onResponse.ErrorListener;
import WebRequest.onResponse.SuccessListener;

public class FeedKatHttpRequest
{
	private static String url_prod = "http://feedkat.ddns.net/api/index.php";
	private static String url_local = "http://127.0.0.1/api/index.php";
	private static boolean isLocal = false;
	
	// Generate Route with correct url
	private static String getRoute(String route)
	{
		String link = (isLocal ? url_local : url_prod) + route;
		return link;
	}
	
	// send a request and return Promise as callback
	public static Promise<JSONObject, String, Double> sendRequest(Method mode, String route, JSONObject params)
	{
		final Deferred<JSONObject, String, Double> deferred = new DeferredObject<JSONObject, String, Double>();
		
		sendRequest(mode, route, params,
				new SuccessListener<JSONObject>() {
					@Override
					public void onSuccess(JSONObject result) {
						deferred.resolve(result);
					}
				},
				new ErrorListener<String>() {
					@Override
					public void onError(String result) {
						deferred.reject(result);
					}
				}
		);
		
		return deferred.promise();
	}
	
	// send a request and use listeners as callback
	public static void sendRequest(Method mode, String route, JSONObject params, SuccessListener<JSONObject> onSuccess, ErrorListener<String> onError)
	{
		String link = getRoute(route);
		new HttpRequest(mode, link, params, onSuccess, onError).execute();
	}
	
	
	// Custom request to have the basics info of a cat
	public static Promise<CatBasics, String, Double> getCatBasics(String serial_collar)
	{
		final Deferred<CatBasics, String, Double> deferred = new DeferredObject<CatBasics, String, Double>();
		
		sendRequest(Method.GET, "/catbasics/"+serial_collar, null)
		.then(new DoneCallback<JSONObject>() {
			@Override
			public void onDone(JSONObject json) {
				CatBasics cat;
				try {
					cat = new CatBasics(json);
					deferred.resolve(cat);
				} catch (JSONException e) {
					deferred.reject(e.toString());
				}
				
			}
		})
		.fail(new FailCallback<String>() {
			@Override
			public void onFail(String res) {
				deferred.reject(res);
			}
		});
		
		return deferred.promise();
	}
}
