import com.sun.net.httpserver.HttpContext;


public interface Googler {
	
	//UP TO ARCh + DEVS: do we use DataSample<String> or do we just do nothing but String ops here?
	public DataSample<String> searchFor(String term);
	
	public String runGoogleQuery(String term);//if you want a string
	
	public String translateThis(String termIn);
	
	public String toString(Googler g);
	
	//Some HTTP functions to send query to Google? START BELOW:
	// https://cloud.google.com/appengine/docs/java/javadoc/com/google/appengine/api/urlfetch/HTTPRequest
	// IF ALL OTHER API SEARCHES FAIL: https://developers.google.com/products/
	//	the official Translate API is paid: https://cloud.google.com/translate/v2/pricing
	
	//WARNING: I DONT KNOW WHICH HTTP CLASSES ARE BEST FOR NETCODE
	public void setConnection(HttpContext connection);
	public HttpContext getConnection();//this should be HTTP or something
	
}
