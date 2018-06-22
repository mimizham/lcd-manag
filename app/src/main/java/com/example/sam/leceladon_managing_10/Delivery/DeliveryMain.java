package com.example.sam.leceladon_managing_10.Delivery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.Inventaire.New2Activity;
import com.example.sam.leceladon_managing_10.MainActivity;
import com.example.sam.leceladon_managing_10.Menu.Menu;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.SQLliteUser;

import static android.widget.Toast.LENGTH_SHORT;

public class DeliveryMain extends AppCompatActivity {

    private SQLliteUser db;
    private RequestQueue queue;
    public static String id_;
    public static boolean btn=true;
    TextView txt;
    ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ill","001");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_main);

        myListView= (ListView) findViewById(R.id.waitinglist);
        GetRequest();
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                id_ = myListView.getItemAtPosition(position).toString().split(":")[0];
                Intent intent1 = new Intent(DeliveryMain.this, DeliveryDetails.class);
                // intent.putExtra("response", response);
                startActivity(intent1);

            }});

    }

    private void GetRequest(){



        queue = Volley.newRequestQueue(DeliveryMain.this);
        Log.d("ill","0001");
        db = new SQLliteUser(getApplicationContext());
        ArrayList<HashMap<String, String>> result=db.getUserDetails("");
        String tocken=result.get(0).get("Tocken");
        String url ="https://www.work.le-celadon.ma/Managing_Celadon/Livreur/Wait_PickUp/"+tocken+"/true";
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


                            myListView.setAdapter(new ArrayAdapter<String>(DeliveryMain.this, android.R.layout.simple_list_item_1, listContents));
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
