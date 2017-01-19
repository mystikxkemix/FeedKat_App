package WebRequest;

import org.json.JSONException;
import org.json.JSONObject;

import Model.CatBasics;
import WebRequest.HttpRequest.Method;
import WebRequest.onResponse.ErrorListener;
import WebRequest.onResponse.SuccessListener;

public class FeedKatHttpRequest {
	private static String url_prod = "http://feedkat.ddns.net/api/index.php";
	private static String url_local = "http://127.0.0.1/api/index.php";
	private static boolean isLocal = false;
	
	// Generate Route with correct url
	private static String getRoute(String route){
		String link = (isLocal ? url_local : url_prod) + route;
		return link;
	}
	
	// send a request and use listeners as callback
	public static void sendRequest(Method mode, String route, JSONObject params, SuccessListener<JSONObject> onSuccess, ErrorListener<String> onError){
		String link = getRoute(route);
		new HttpRequest(mode, link, params, onSuccess, onError).execute();
	}
	
	
	public static void getCatBasics(String serial_collar, SuccessListener<CatBasics> onSuccess) {
		getCatBasics(serial_collar, onSuccess, null);
	}
	// Custom request to have the basics info of a cat
	public static void getCatBasics(String serial_collar, SuccessListener<CatBasics> onSuccess, ErrorListener<String> onError) {
		
		sendRequest(Method.GET, "/catbasics/"+serial_collar, null,
				new SuccessListener<JSONObject>() {
					@Override
					public void onSuccess(JSONObject json) {
						CatBasics cat;
						try {
							cat = new CatBasics(json);
							onSuccess.onSuccess(cat);
						} catch (JSONException e) {
							if(onError != null) onError.onError("Erreur de JSON : "+e.toString());
						}
					}
				},
				new ErrorListener<String>() {
					@Override
					public void onError(String result) {
						if(onError != null) onError.onError(result);
					}
				});
	}
}
