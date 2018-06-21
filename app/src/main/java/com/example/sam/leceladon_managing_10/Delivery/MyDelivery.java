package com.example.sam.leceladon_managing_10.Delivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.SQLliteUser;

public class MyDelivery extends AppCompatActivity {

    private SQLliteUser db;
    private RequestQueue queue;
    ListView myListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_delivery);
        myListView= (ListView) findViewById(R.id.waitinglist);
        GetRequest();
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                DeliveryMain.id_ = myListView.getItemAtPosition(position).toString().split(":")[0];
                DeliveryMain.btn=false;
                Intent intent1 = new Intent(MyDelivery.this, DeliveryDetails.class);
                startActivity(intent1);

            }});
    }


    private void GetRequest(){



        queue = Volley.newRequestQueue(MyDelivery.this);
        db = new SQLliteUser(getApplicationContext());
        ArrayList<HashMap<String, String>> result=db.getUserDetails("");
        String tocken=result.get(0).get("Tocken");
        String url ="https://www.work.le-celadon.ma/Managing_Celadon/Livreur/Get_Mypickup/"+tocken;
        Log.d("ill",url);

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Request a string response from the provided URL.
                        Log.d("ill",response);


                        //une fois l user conncter les info sont stocker dans sqlite

                        try {


                            Log.d("ill3", response);
                            JSONArray jsonArray = null;

                            jsonArray = new JSONArray(response);

                            int length = jsonArray.length();
                            List<String> listContents = new ArrayList<String>(length);
                            for (int i = 0; i < length; i++) {

                                listContents.add(jsonArray.getJSONObject(i).getString("libelle"));

                            }


                            myListView.setAdapter(new ArrayAdapter<String>(MyDelivery.this, android.R.layout.simple_list_item_1, listContents));
                        }catch (Exception e){

                        }
                    }

                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                Log.d("ill2","That didn't work!");
            }

        });
        queue.add(stringRequest);




    }


}
