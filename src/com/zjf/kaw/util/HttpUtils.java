package com.zjf.kaw.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.util.Log;

/**
 * ��װ��������
 * 
 * @author tarena
 * 
 */
public class HttpUtils {
	/**
	 * ����get���󣬻�ȡ����˷��ص�������
	 * @param urlAll
	 *            :����ӿ�
	 * @param httpArg
	 *            :����
	 * @return ���ؽ��
	 */
	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;
		Log.i("edu", httpUrl);

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			// ����apikey��HTTP header
			connection.setRequestProperty("apikey",
					"fc307254d5950dad74524206200a89df");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	}
