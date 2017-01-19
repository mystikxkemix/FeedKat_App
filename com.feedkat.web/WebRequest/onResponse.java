package WebRequest;

import org.json.JSONObject;

public class onResponse<T> {
	public onResponse(){}
	
	public interface SuccessListener<T> {
		public void onSuccess(T result);
	}
	
	public interface ErrorListener<T> {
		public void onError(T result);
	}
}
