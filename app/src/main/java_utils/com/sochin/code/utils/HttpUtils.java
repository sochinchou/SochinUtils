package com.sochin.code.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;



import android.util.Log;

public class HttpUtils {

	private static final String TAG = "HttpUtils";
	private static final int DEFAULT_TIME_OUT = 30 * 1000;
	private static final String RET_NETWORK_ERROR = "network_error";



	
	/*
	public static String httpPost(String strUrl, HashMap<String, String> mapParams) {
		URL url = null;
		BufferedReader br = null;
		StringBuilder sbResult = null;
		HttpURLConnection httpConn = null;
		String strResult = RET_NETWORK_ERROR;

		try {
			url = new URL(strUrl);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(DEFAULT_TIME_OUT);
			httpConn.setReadTimeout(DEFAULT_TIME_OUT);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.connect();

			StringBuilder sbParam = new StringBuilder();
			if (mapParams != null) {
				for (String key : mapParams.keySet())
					sbParam.append("&").append(key).append("=").append(mapParams.get(key));
				sbParam.deleteCharAt(0);
			}

			PrintWriter out = new PrintWriter(httpConn.getOutputStream());
			out.print(sbParam.toString());
			out.flush();
			out.close();

			Log.d(TAG, "httpPost() >>>>> get the response ");
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				Log.d(TAG, "httpPost() >>>>> httpConn.getResponseCode() success.  code = " + httpConn.getResponseCode());
				sbResult = new StringBuilder();
				br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				String line = null;
				while ((line = br.readLine()) != null)
					sbResult.append(line).append("\n");

				strResult = sbResult.toString();
			} else {
				Log.d(TAG, "httpPost() >>>>> httpConn.getResponseCode() failed.  code = " + httpConn.getResponseCode());
				strResult = RET_NETWORK_ERROR;
			}

		} catch (MalformedURLException e) {
			Log.d(TAG, "httpPost() >>>>> MalformedURLException");
			return RET_NETWORK_ERROR;
		} catch (IOException e) {
			Log.d(TAG, "httpPost() >>>>> IOException");
			e.printStackTrace();
			return RET_NETWORK_ERROR;
		} finally {
			Log.d(TAG, "httpPost() >>>>> finally");
			try {
				if (br != null)
					br.close();
				if (httpConn != null) {
					httpConn.disconnect();
					Log.d(TAG, "httpPost() >>>>> httpConn disconnect");
				}
			} catch (IOException e) {
				Log.d(TAG, "httpPost() >>>>> close failed.");
			}
		}

		return strResult;
	}*/
	
	


	

	
	
	/*
	public static String httpGet(String strUrl, HashMap<String, String> mapParams) {

		URL url = null;
		BufferedReader br = null;
		StringBuilder sbResult = null;
		HttpURLConnection httpConn = null;
		String strResult = RET_NETWORK_ERROR;

		try {

			if (mapParams != null) {
				StringBuilder sbParam = new StringBuilder();
				for (String key : mapParams.keySet())
					sbParam.append("&").append(key).append("=").append(mapParams.get(key));
				sbParam.deleteCharAt(0);
				url = new URL(strUrl + "?" + sbParam.toString());//
			} else {
				url = new URL(strUrl);
			}

			Log.d(TAG, "httpGet() >>>>> URL = " + strUrl);

			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setConnectTimeout(DEFAULT_TIME_OUT);
			httpConn.setReadTimeout(DEFAULT_TIME_OUT);
			httpConn.connect();

			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				sbResult = new StringBuilder();
				br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				String line = "";
				while ((line = br.readLine()) != null)
					sbResult.append(line).append("\n");
				strResult = sbResult.toString();
			} else {
				strResult = RET_NETWORK_ERROR;
			}

		} catch (MalformedURLException e) {
			Log.d(TAG, "httpGet() >>>>> MalformedURLException");
			return RET_NETWORK_ERROR;
		} catch (IOException e) {
			Log.d(TAG, "httpGet() >>>>> IOException");
			return RET_NETWORK_ERROR;
		} finally {

			try {
				if (br != null)
					br.close();
				if (httpConn != null)
					httpConn.disconnect();
			} catch (IOException e) {
				Log.d(TAG, "httpGet() >>>>> close failed.");
			}
		}

		return strResult;
	}*/

	
	
	public static String fileUpload(String strUrl, Map<String, String> mapParams, File file) {

		URL url = null;
		BufferedReader br = null;
		DataOutputStream outStream = null;
		HttpURLConnection httpConn = null;
		StringBuilder sbResult = null;
		String strResult = RET_NETWORK_ERROR;

		String BOUNDARY = UUID.randomUUID().toString();
		String PREFIX = "--";
		String LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		try {
			url = new URL(strUrl);
			Log.d(TAG, "fileUpload() >>>>> url = " + url);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setReadTimeout(10 * 1000);
			httpConn.setConnectTimeout(10 * 1000);
			httpConn.setDoInput(true);// 
			httpConn.setDoOutput(true);// 
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST"); //

			httpConn.setRequestProperty("Connection", "close");
			httpConn.setRequestProperty("Accept-Charsert", "UTF-8");
			httpConn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

			outStream = new DataOutputStream(httpConn.getOutputStream());

			
			if (mapParams != null) {
				StringBuilder sb1 = new StringBuilder();
				for (Map.Entry<String, String> entry : mapParams.entrySet()) {
					sb1.append(PREFIX + BOUNDARY + LINEND);
					sb1.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
					sb1.append(LINEND);
					sb1.append(entry.getValue() + LINEND);
				}
				outStream.writeBytes(sb1.toString());
			}

			
			if (file != null) {
				StringBuilder sb2 = new StringBuilder();
				sb2.append(PREFIX + BOUNDARY + LINEND);
				sb2.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + LINEND);
				sb2.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
				sb2.append(LINEND); // start output
				outStream.writeBytes(sb2.toString());

				FileInputStream is = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}
				is.close();
				outStream.writeBytes(LINEND); // end output
			}

			outStream.writeBytes(PREFIX + BOUNDARY + PREFIX + LINEND);
			outStream.flush();


			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				sbResult = new StringBuilder();
				br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				String line = "";
				while ((line = br.readLine()) != null)
					sbResult.append(line).append("\n");
				strResult = sbResult.toString();
			} else {
				strResult = RET_NETWORK_ERROR;
			}

			return strResult;

		} catch (MalformedURLException e) {
			Log.d(TAG, "fileUpload() >>>>> MalformedURLException");
			return RET_NETWORK_ERROR;
		} catch (IOException e) {
			Log.d(TAG, "fileUpload() >>>>> IOException");
			return RET_NETWORK_ERROR;
		} finally {
			try {
				if (outStream != null)
					outStream.close();
				if (br != null)
					br.close();
				if (httpConn != null)
					httpConn.disconnect();
			} catch (IOException e) {
				Log.d(TAG, "fileUpload() >>>>> close failed.");
			}
		}
	}

	public static String fileMultiUpload(String strUrl, Map<String, String> mapParams, Map<String, File> filesMap) {

		URL url = null;
		BufferedReader br = null;
		DataOutputStream outStream = null;
		HttpURLConnection httpConn = null;
		StringBuilder sbResult = null;
		String strResult = RET_NETWORK_ERROR;

		String BOUNDARY = UUID.randomUUID().toString();
		String PREFIX = "--";
		String LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";
		String CHARSET = "UTF-8";

		try {
			url = new URL(strUrl);
			Log.d(TAG, "fileMultiUpload() >>>>> url = " + url);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setReadTimeout(10 * 1000);
			httpConn.setConnectTimeout(10 * 1000);
			httpConn.setDoInput(true);//
			httpConn.setDoOutput(true);// 
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST"); // 

			httpConn.setRequestProperty("Connection", "close");
			httpConn.setRequestProperty("Accept-Charsert", "UTF-8");
			httpConn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

			outStream = new DataOutputStream(httpConn.getOutputStream());


			if (mapParams != null) {
				StringBuilder sb1 = new StringBuilder();
				for (Map.Entry<String, String> entry : mapParams.entrySet()) {
					sb1.append(PREFIX + BOUNDARY + LINEND);
					sb1.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
					sb1.append(LINEND);
					sb1.append(entry.getValue() + LINEND);
				}
				outStream.writeBytes(sb1.toString());
			}


			if (filesMap != null) {
				for (Map.Entry<String, File> file : filesMap.entrySet()) {
					StringBuilder sb2 = new StringBuilder();
					sb2.append(PREFIX + BOUNDARY + LINEND);
					sb2.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getKey() + "\"" + LINEND);
					sb2.append("Content-Type: application/octet-stream; charset=" + CHARSET + LINEND);
					sb2.append(LINEND); // start output
					outStream.writeBytes(sb2.toString());

					InputStream is = new FileInputStream(file.getValue());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) != -1) {
						outStream.write(buffer, 0, len);
					}
					is.close();
					outStream.writeBytes(LINEND); // end output
				}
			}

			outStream.writeBytes(PREFIX + BOUNDARY + PREFIX + LINEND);
			outStream.flush();


			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				sbResult = new StringBuilder();
				br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				String line = "";
				while ((line = br.readLine()) != null)
					sbResult.append(line).append("\n");
				strResult = sbResult.toString();
			} else {
				strResult = RET_NETWORK_ERROR;
			}

			return strResult;

		} catch (MalformedURLException e) {
			Log.d(TAG, "fileUpload() >>>>> MalformedURLException");
			return RET_NETWORK_ERROR;
		} catch (IOException e) {
			Log.d(TAG, "fileUpload() >>>>> IOException");
			return RET_NETWORK_ERROR;
		} finally {
			try {
				if (outStream != null)
					outStream.close();
				if (br != null)
					br.close();
				if (httpConn != null)
					httpConn.disconnect();
			} catch (IOException e) {
				Log.d(TAG, "fileUpload() >>>>> close failed.");
			}
		}
	}


	public static void printResponseHeader(HttpURLConnection conn) {
		Log.e(TAG, "printResponseHeader() >>>");

		Map<String, String> header = new LinkedHashMap<String, String>();

		for (int i = 0;; i++) {
			Log.e(TAG, i + " --->" + conn.getHeaderFieldKey(i) + " : " + conn.getHeaderField(i));
			String headerField = conn.getHeaderField(i);
			if (headerField == null)
				break;
			header.put(conn.getHeaderFieldKey(i), headerField);
		}

		for (Map.Entry<String, String> entry : header.entrySet()) {
			String key = entry.getKey() != null ? entry.getKey() + ":" : "";
			logger(key + entry.getValue());
		}
	}


	public static InputStream getInputStreamFromUrl(String path){
		logger("getInputStreamFromUrl() >>>>>");
		InputStream is = null;
		
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				logger("http ok !");
				is = conn.getInputStream();
				return is;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return is;
	}
	
	public static byte[] getByteArrayFromUrl(String path){
		logger("getByteArrayFromUrl() >>>>>");
		byte[] byteArray = null;
		
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				logger("http ok !");
				InputStream is = conn.getInputStream();
				ByteArrayOutputStream os = new ByteArrayOutputStream(); 
				byte[] buffer = new byte[1024];
				int len = 0;
				
				while( (len = is.read(buffer)) != -1){
					os.write(buffer, 0, len);
				}
				
				is.close();
				byteArray = os.toByteArray();
				return byteArray;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return byteArray;
	}
	
	
	public static void logger(String msg) {
		Log.i(TAG, msg);
	}
}