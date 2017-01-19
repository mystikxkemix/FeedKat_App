package WebRequest;


public class onResponse<T>
{
	public onResponse(){}
	
	public interface SuccessListener<T>
	{
		public void onSuccess(T result);
	}
	
	public interface ErrorListener<T>
	{
		public void onError(T result);
	}
}
