package com.example.sam.leceladon_managing_10;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.Delivery.DeliveryDetails;
import com.example.sam.leceladon_managing_10.Delivery.DeliveryMain;
import com.example.sam.leceladon_managing_10.Inventaire.New2Activity;
import com.example.sam.leceladon_managing_10.Inventaire.index2Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import database.SQLliteUser;

import static android.widget.Toast.LENGTH_SHORT;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    String cookie = null;
    Toast toast;
    Button button;
    Button button2;
    String url = "https://www.work.le-celadon.ma/Managing_Celadon/Users/login";

    private SQLliteUser db;
    String tock_tel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonconn);

        final TextView txt = findViewById(R.id.textView);
        final EditText log = findViewById(R.id.editTextlog);

        final EditText psw = findViewById(R.id.editTextpsw);
        db = new SQLliteUser(getApplicationContext());

        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final String logc = log.getText().toString();
                final String pswc = psw.getText().toString();

                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                // Request a string response from the provided URL.
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               if (response != null && response.length() > 0)
                                {
                                    if (!TextUtils.isEmpty(log.getText().toString().trim()) && !TextUtils.isEmpty(psw.getText().toString().trim()))
                                    {
                                        try {
                                            JSONObject jsonuser = new JSONObject(response);
                                            if (jsonuser.optString("token").length()==70)
                                            {
                                                //Toast.makeText(getApplicationContext(),String.valueOf(jsonuser.get("token")) , Toast.LENGTH_SHORT).show();
                                                db.addUser(valueOf(jsonuser.get("token")));
                                                tock_tel = db.getUserDetails();
                                                System.out.println("tock tel" + tock_tel);
                                                Intent intent = new Intent(MainActivity.this, index2Activity.class);
                                                intent.putExtra("tock_tel", tock_tel);
                                                startActivity(intent);

                                            }
                                           else
                                            {
                                                Log.i("err", valueOf(jsonuser.optString("erreur")));
                                              //  txt.setText("le mot de passe ou login est incorrecte");if(jsonuser.optString("erreur")=="le mot de passe ou login est incorrecte")
                                                Toast.makeText(getApplicationContext(), String.valueOf(jsonuser.getString("erreur")), Toast.LENGTH_SHORT).show();
                                            }


                                        }
                                        catch (JSONException em)
                                        {
                                            // JSON error  System.out.println(""+em.printStackTrace());
                                          //  Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                            System.out.println("Json error" + em);


                                        }
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Merci de remplire les champs", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    System.out.println("no server response");
                                    Toast.makeText(getApplicationContext(), "erreur conexion", Toast.LENGTH_SHORT).show();

                                }
                            }
                        },


                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                //txt.setText("That didn't work!");
                                System.out.println("error con" + error);
                                Toast.makeText(getApplicationContext(), "veuillez verifier votre conexion", Toast.LENGTH_SHORT).show();

                                error.printStackTrace();
                            }


                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("emailu", logc);
                        params.put("psw", pswc);
                        return params;
                    }


                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                        3000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(stringRequest);
            }
        });

    }

    }





