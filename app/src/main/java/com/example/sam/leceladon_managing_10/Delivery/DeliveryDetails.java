package com.example.sam.leceladon_managing_10.Delivery;
import  com.example.sam.leceladon_managing_10.Menu.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import database.SQLliteUser;

import static android.widget.Toast.LENGTH_SHORT;

public class DeliveryDetails extends AppCompatActivity {
    private SQLliteUser db;
    private RequestQueue queue;
    Toast toast;
    Button btn,btn2,btnlivre;
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        GetRequest();
        btn = findViewById(R.id.button_details);
        btnlivre=findViewById(R.id.btn_delivered);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                queue = Volley.newRequestQueue(DeliveryDetails.this);
                db = new SQLliteUser(getApplicationContext());
                ArrayList<HashMap<String, String>> result=db.getUserDetails("");
                String tocken=result.get(0).get("Tocken");
                String url ="https://www.work.le-celadon.ma/Managing_Celadon/Livreur/TakeLivraison/"+tocken+"/"+DeliveryMain.id_;
                System.out.print(url);

                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }

                        }, new Response.ErrorListener() {
                    @Override

                    public void onErrorResponse(VolleyError error) {
                        Log.d("ill2","That didn't work!");
                    }

                });
                queue.add(stringRequest);
            }

        });
        btn2=findViewById(R.id.button_products);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DeliveryDetails.this, Product_Delivery_Details.class);
                // intent.putExtra("response", response);
                startActivity(intent1);
            }
        });

        btnlivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ////////////////////////

                queue = Volley.newRequestQueue(DeliveryDetails.this);
                db = new SQLliteUser(getApplicationContext());
                ArrayList<HashMap<String, String>> result=db.getUserDetails("");
                String tocken=result.get(0).get("Tocken");
                String url ="https://www.work.le-celadon.ma/Managing_Celadon/Livreur/Done/"+tocken+"/"+DeliveryMain.id_;


                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent1 = new Intent(DeliveryDetails.this, DeliveryMain.class);
                                String msg="Erreur";

                                if(response.trim()!=""){
                                    msg="Bien reçu, Merci! ";
                                }
                                startActivity(intent1);
                                finish();
                                toast = Toast.makeText(getApplicationContext()
                                        , msg , LENGTH_SHORT);
                                toast.show();
                            }

                        }, new Response.ErrorListener() {
                    @Override

                    public void onErrorResponse(VolleyError error) {
                        Log.d("ill2","That didn't work!");
                    }

                });
                queue.add(stringRequest);

                //////////////////////////

            }

        });
    }
    private void GetRequest(){



        queue = Volley.newRequestQueue(DeliveryDetails.this);
        Log.d("ill","0001");
        db = new SQLliteUser(getApplicationContext());
        ArrayList<HashMap<String, String>> result=db.getUserDetails("");
        String tocken=result.get(0).get("Tocken");
        String url ="https://www.work.le-celadon.ma/Managing_Celadon/Livreur/Wait_PickUp_Detaille/"+tocken+"/"+DeliveryMain.id_;
        toast = Toast.makeText(getApplicationContext()
                , "Loadind ..." , LENGTH_SHORT);
        toast.show();
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = null;

                            jsonArray = new JSONArray(response);

                            int length = jsonArray.length();
                            txt1=(TextView)findViewById(R.id.textView14);
                            TextView txt2=(TextView)findViewById(R.id.textView18);
                            TextView txt3=(TextView)findViewById(R.id.textView17);
                            TextView txt=(TextView)findViewById(R.id.textView19);
                            TextView txtSts=(TextView)findViewById(R.id.txt_sts);


                            txt1.setText(txt1.getText()+ jsonArray.getJSONObject(0).getString("id_commande"));
                            txt2.setText(txt2.getText()+ jsonArray.getJSONObject(0).getString("count"));
                            txt3.setText(txt3.getText()+jsonArray.getJSONObject(0).getString("Date_De_Livrasion"));
                            txt.setText(txt.getText()+jsonArray.getJSONObject(0).getString("adressefournisseur"));
                            String status="";
                            switch (jsonArray.getJSONObject(0).getString("statut")){
                                case "O":status="Ouvert";
                                    btn.setVisibility(View.VISIBLE);
                                    break;
                                case "C":status="En cours";
                                    btnlivre.setVisibility(View.VISIBLE);
                                    btn.setVisibility(View.INVISIBLE);
                                    break;
                                case "Z":status="Livré";
                                    btn.setVisibility(View.INVISIBLE);
                                    break;
                            }

                            txtSts.setText(txtSts.getText()+" "+status);

                        }catch (Exception e){

                        }
                    }

                }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {
                toast = Toast.makeText(getApplicationContext()
                        , "Une erreur est survenu" , LENGTH_SHORT);
                toast.show();
            }

        });
        queue.add(stringRequest);




    }

}
