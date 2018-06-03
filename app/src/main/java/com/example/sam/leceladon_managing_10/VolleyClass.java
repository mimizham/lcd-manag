package com.example.sam.leceladon_managing_10;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by SAM on 12/04/2018.
 */
public class VolleyClass {
    public Activity activity;
    public URL url;
    public Class classv;
    public String key;
    public String value;

   public void Open(){

      /* RequestQueue queue = Volley.newRequestQueue(form);
       String url ="http://www.google.com";

// Request a string response from the provided URL.
       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       // Display the first 500 characters of the response string.
                       System.out.print(("Response is: "+ response.substring(0,500)));
                   }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               System.out.print("That didn't work!");
           }
       });

// Add the request to the RequestQueue.
       queue.add(stringRequest);
   }
       // Instantiate the RequestQueue.
       final RequestQueue queue = Volley.newRequestQueue(activity);
       // Request a string response from the provided URL.
       final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
               new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       // Display the first 500 characters of the response string.
                       //      txt.setText(response);

                       Intent intent = new Intent(activity, classv);
                      //intent.putExtra("response", response);
                       intent.putExtra(key,value);
                       //startActivity(intent);
         */
 /*                  }
               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
              // txt.setText("That didn't work!");
               Toast toast = Toast.makeText(activity
                       , "log" + key, LENGTH_SHORT);
               toast.show();
           }


       }) {
           @Override
           protected Map<String, String> getParams()
           {
               Map<String, String> params = new HashMap<String, String>();
              *//* params.put;
               params.put("psw", pswc);*//*
               return params;
           }


       };

// Add the request to the RequestQueue.
       queue.add(stringRequest);*/
   }
}
