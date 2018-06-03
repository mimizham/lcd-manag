package com.example.sam.leceladon_managing_10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Leceladon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url ="https://www.work.le-celadon.ma/Managing_Celadon/Inventaires/index";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leceladon);
        final TextView textView = findViewById(R.id.textView3);
        //TextView textView1 = findViewById(R.id.textView5);
        final String resp = getIntent().getStringExtra("tock_tel");

        System.out.println("lenght"+resp);
       final RequestQueue queue = Volley.newRequestQueue(Leceladon.this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                  /* System.out.println("i" +String.valueOf(jarr.getJSONObject(i)));
                                JSONObject jObj = new JSONObject(String.valueOf(jarr.getJSONObject(i)));

                                System.out.println("jobj" + jObj.get("libelle_produit"));
                           JSONArray jarr = new JSONArray(response);
                            System.out.println("read"+jarr); JSONObject jsonobject = jarr.getJSONObject(0);
                            System.out.println("read please this exception"+jsonobject);
                        JSONObject jObj = new JSONObject(response); for(int p=0;p<jObj.length();p++)
                                {
                                    System.out.println(jObj.getString(String.valueOf(p)));
  textView.setText("respjxn"+response);
*/
                            JSONArray jarr = new JSONArray(response);
                            JSONObject jObj = new JSONObject(String.valueOf(jarr));

                            Log.i("res", String.valueOf(jarr));


                          }
                        catch (Exception e)
                        {
                            System.out.println("read please this exception"+ e);


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error con"+error);
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tock",resp);
                return params;
            }
        };
        //10000 is the time in milliseconds adn is equal to 10 sec
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                3000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


}
