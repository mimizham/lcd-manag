package com.example.sam.leceladon_managing_10;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.Inventaire.New2Activity;
import com.example.sam.leceladon_managing_10.Inventaire.index2Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import database.SQLliteUser;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    String cookie = null;
    Toast toast;
    Button button;
    Button button2;
    String url ="https://www.work.le-celadon.ma/Managing_Celadon/Users/login";
    Button button1;
    private SQLliteUser db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.buttonconn);
        button2=findViewById(R.id.buttonlink);
        button1=findViewById(R.id.inv);
        final TextView txt =findViewById(R.id.textView);
        final EditText log = findViewById(R.id.editTextlog);;
        final EditText psw = findViewById(R.id.editTextpsw);;

        db = new SQLliteUser(getApplicationContext());
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final String logc = log.getText().toString();
                final String pswc = psw.getText().toString();
               /* */
                // Instantiate the RequestQueue.
                final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                // Request a string response from the provided URL.
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    toast = Toast.makeText(getApplicationContext()
                                            , "log" + response, LENGTH_SHORT);
                                    toast.show();
                                    System.out.println(""+response);
                     //une fois l user conncter les info sont stocker dans sqlite
                                    JSONArray jObj = new JSONArray(response);
                                    // Now store the user in SQLite
                                    JSONObject jsonobject = jObj.getJSONObject(0);
                                    System.out.println("jsonobject"+jsonobject);

                                    //String name =jsonobject.getString("nom");
                                        String token = jsonobject.getString("tocken");
                                        String created_at =jsonobject.getString("id_user_encry");

                                        System.out.println("n"+token+created_at);
                                        // Inserting row in users table

                                        db.addUser(token,created_at);
                                        System.out.println("getuserdetaill"+db.getUserDetails());
                                        // Launch main activity
    /**/
                               Intent intent = new Intent(MainActivity.this,Leceladon.class);
                                intent.putExtra("response", db.getUserDetails());
                                startActivity(intent);


                                       // finish();

                                } catch (JSONException e) {
                                    // JSON error System.out.println(
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txt.setText("That didn't work!");
                    }


                }) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("emailu", logc);
                        params.put("psw", pswc);
                        return params;
                    }


                };

// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /* toast = Toast.makeText(getApplicationContext()
                        , "log" , LENGTH_SHORT);
                toast.show();
               */Intent intent1 = new Intent(MainActivity.this, New2Activity.class);
               // intent.putExtra("response", response);
                startActivity(intent1);
            }
        });
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                /* toast = Toast.makeText(getApplicationContext()
                        , "log" , LENGTH_SHORT);
                toast.show();
               */Intent intent1 = new Intent(MainActivity.this, index2Activity.class);
               // intent1.putExtra("response", res);
                startActivity(intent1);
            }
        });
    }
}


