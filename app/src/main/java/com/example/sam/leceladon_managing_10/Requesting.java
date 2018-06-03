package com.example.sam.leceladon_managing_10;

import android.content.Context;
import android.content.SharedPreferences;
import android.app.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Requesting  {
    ProgressDialog progressDialog;

    /* public static String postHttpPage(String url, Map<String,String> urlParameters, String cookie) throws Exception
    {
      //  System.out.println("\nSending 'POST' request to URL : " + url);
       URL obj = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
        return postPage(conn, getQuery(urlParameters), cookie);
    }

    private static String postPage(HttpURLConnection conn,String urlParameters, String cookie) throws Exception {
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.57 Safari/537.36");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(urlParameters.getBytes().length));
        conn.setRequestProperty("Content-Language", "en-US");
        conn.setRequestProperty("Cookie", cookie);
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        StringBuffer response = new StringBuffer();
        int responseCode = conn.getResponseCode();
        System.out.println(responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static String retrieveCookies(String url) throws IOException {
        URL obj = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
        String cookies=conn.getHeaderField("Set-Cookie");
        conn.disconnect();
        return cookies;
    }
    public static String getQuery(Map<String,String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> pair : params.entrySet())
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }*/
   public static final int CONNECTION_TIMEOUT=1000*15;
        public static final String SERVER_ADDRESS="";

    //SharedPreferences userlocaldb;
   public Requesting(Context context)
   {

       progressDialog=new ProgressDialog(context);
       progressDialog.setCancelable(false);
       progressDialog.setTitle("Processing");
       progressDialog.setMessage("Merci de patienter ....");
       //userlocaldb=context.getSharedPreferences(SP_NAME,0);
   }
/*
   public void storedUserDataInBG(User user)
   {
    new StoreUserDataAscyncTask().execute();
  }
*/

}

