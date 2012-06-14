package services;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import DataProviders.Category;
import android.util.Log;

public class APIConnection {

	private static final String TAG = "APIConnection";
	private static Map<String, String> RequestsMade = new HashMap<String, String>();
	public String status;
	// public Authentication authentication;
	// public List<Language> languages;
	public List<DataProviders.Category> categories;
	// public List<Country> countries;
	public List<DataProviders.subCategory> subCategories;
	// public List<State> states;
	public List<classes.Order> orders;
	public List<classes.Item> products;
	public classes.Address address;
	public List<classes.Address> addresses;
	// public Error error;
	public classes.User account;
	public classes.Order order;

	/*
	 * get
	 * 
	 * @brief Gets an API response from the given URL
	 * 
	 * @param url Url to get the Response object from.
	 * 
	 * @return Response object containing the data
	 */

	public static APIConnection get(String url, Map<String, String> headers,
			String Command) throws APIBadResponseException, XMLParseException, URISyntaxException {
		
		String getCall = null;
		HttpGet getRequest = null;
		String finalurl = url + "?" + URLEncode(headers);
		int coda = 1 ;
		if (coda == 1)
		{
			
			InputStream a = getResource2(URI.create(finalurl));
			return null;
		}

		if (RequestsMade.containsKey(finalurl)) {
			getCall = RequestsMade.get(finalurl);
		} else {
			getRequest = new HttpGet(finalurl);
		//	getRequest.setURI(new URI("finalurl"));
			HttpClient client = new DefaultHttpClient();
			try {
				HttpResponse getResponse = client.execute(getRequest);
				final int statusCode = getResponse.getStatusLine()
						.getStatusCode();
				if (statusCode != HttpStatus.SC_OK) {
					getCall = null;
					return null;
				}
				HttpEntity getResponseEntity = getResponse.getEntity();
				if (getResponseEntity != null) {

					String resp = EntityUtils.toString(getResponseEntity);
					getCall = resp;
					RequestsMade.put(finalurl, getCall);

				}
			} catch (IOException e) {
				Log.e(TAG, "Error for URL " + url, e);
				getRequest.abort();

			}
			return null;

		}

		return parseString(getCall, Command);
	}

	
	public final static InputStream getResource2(final URI uri)
             {
        
        try {
            final URLConnection uc = uri.toURL().openConnection();
            HttpURLConnection huc = null;
            
            if(uc instanceof HttpURLConnection) {
                huc = (HttpURLConnection) uc;
            }
            uc.setAllowUserInteraction(false);
            uc.setDefaultUseCaches(false);
            uc.connect();
            
            if(huc != null) {
              //  validateStatusLine(uri, huc.getResponseCode(), huc.getResponseMessage());
            }
            InputStream is = uc.getInputStream(); 
            @SuppressWarnings("unused")
			final String encoding = uc.getContentEncoding();
            return is;
        } catch (final MalformedURLException e) {
            throw new RuntimeException();
        } catch (final IOException e) {
            throw new RuntimeException();
        }
    }
	
	private static APIConnection parseString(String getCall, String command) {

		APIConnection Resp = new APIConnection();
		if (command.equals("GetCategories")) {
			List<Category> ans = new ArrayList<Category>();
			Document doc = null;
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(getCall));

				doc = db.parse(is);
				NodeList nodes = doc.getElementsByTagName("category");

				// iterate the categories
				for (int i = 0; i < nodes.getLength(); i++) {
					Element element = (Element) nodes.item(i);

					int id = Integer.parseInt(element.getAttribute("id"));

					NodeList codeNode = element.getElementsByTagName("code");
					Element line = (Element) codeNode.item(0);
					String code = getCharacterDataFromElement(line);

					NodeList nameNode = element.getElementsByTagName("name");
					line = (Element) nameNode.item(0);
					String name = getCharacterDataFromElement(line);

					ans.add(new Category(id, code, name));

				}
			} catch (Exception e) {

				NodeList nodes = doc.getElementsByTagName("error");
				Element element = (Element) nodes.item(0);

				Integer code = Integer.parseInt(element.getAttribute("code"));
				Log.w(TAG, code.toString());
				//TODO MANEJO DE ERRORES
//				RequestError err = new catalogError(code, langid);
//				err.handle(e);
			}
			Resp.categories = ans;

		}

		return Resp;
	}

	
	
	private static String getCharacterDataFromElement(Element e) {

		Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return null;
	  }




	public static String URLEncode(Map<String, String> headers) {
		List<NameValuePair> params = new LinkedList<NameValuePair>();

		for (String key : headers.keySet()) {
			params.add(new BasicNameValuePair(key, headers.get(key)));
		}

		return URLEncodedUtils.format(params, "utf-8");
	}

	
}
