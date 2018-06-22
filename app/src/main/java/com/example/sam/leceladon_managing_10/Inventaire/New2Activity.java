package com.example.sam.leceladon_managing_10.Inventaire;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.Leceladon;
import com.example.sam.leceladon_managing_10.NewActivity;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class New2Activity extends AppCompatActivity implements  View.OnClickListener{
  // DatePicker dpk; implements AdapterView.OnItemSelectedListener
    EditText lblinv;
    EditText lblquan;
    EditText type;
    EditText etatpro,daterenv;
    TextView txt8,txt9;
    Button btn,bt;
    final String lbinv="";
    ArrayList<String> prod;
   String var;
    final String url = "http://work.le-celadon.ma/Managing_Celadon/Inventaires";
    String etatprod;
    String ty;
    String quan;

    private int mYear, mMonth, mDay, mHour, mMinute;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new2);
        btn = findViewById(R.id.newInv);
        final TextView txtc = findViewById(R.id.textView5);
        lblinv = findViewById(R.id.txtinv);
        lblquan = findViewById(R.id.txtquan);
        btn=findViewById(R.id.newInv);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final RequestQueue queue = Volley.newRequestQueue(New2Activity.this);
                final StringRequest insertReq = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String newsinv) {

                                System.out.print(newsinv);

                                   try {
                                   /*
                                       lib_inv.setText(getIntent().getStringExtra("lblInv"));

                                       inv_qu.setText(getIntent().getStringExtra("quantit"));;
                                       inv_date_exp.setText(getIntent().getStringExtra("date_expiration"));
                                       inv_date.setText(getIntent().getStringExtra("etat"));
                                       bon_cmd.setText(getIntent().getStringExtra("bon_cmd"));
                                       date_ren.setText(getIntent().getStringExtra("tock_tel"));
                                       date_ren.setText(getIntent().getStringExtra("prod_renv"));
 */


                                //Parsing the fetched Json String to JSON Object
                                        JSONArray j = new JSONArray(newsinv);
                                        System.out.println("new activit"+j);
                                        JSONObject jsonobject = j.getJSONObject(0);
                                        System.out.println("jsonobject" + jsonobject);
                                        String result = "";
                                        JSONObject Data = new JSONObject();

                                        //Storing the Array of JSON String to our JSON Array
                                        for (int i = 0; i < jsonobject.length(); i++)
                                        {
                                            try {
                                                //Getting json object
                                                result = jsonobject.getString("libelle_produit");
                                              //  spinnerData.put("libelle_produit", result);
                                                System.out.println(Data);

                                                }
                                            catch (Exception e)
                                            {
                                                e.printStackTrace();
                                            }
                                        }

                                        //Calling method getStudents to get the students from the JSON Array
                                       // getproduitName(spinnerData);
                                    }
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }/* */
                                }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext()
                                , "" + error, Toast.LENGTH_LONG);
                        toast.show();
                    }


                }) /* {

                   protected Response<String> parseNetworkResponse(NetworkResponse response) {
                   // Log.d("Response Header = " + response.headers);

                    if (response.headers == null)
                    {
                        // cant just set a new empty map because the member is final.
                        response = new NetworkResponse(
                                response.statusCode,
                                response.data,
                                Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
                                response.notModified,
                                response.networkTimeMs);


                    }
                    return super.parseNetworkResponse(response);
                }
                };*/{
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> insert = new HashMap<String, String>();
                        insert.put("libelle_produit", lbinv);
                        insert.put("quantite", quan);
                        insert.put("date_renvou", String.valueOf(daterenv));
                        insert.put("statut_stock", ty);
                        return insert;
                    }

                };
                //10000 is the time in milliseconds adn is equal to 10 sec
                insertReq.setRetryPolicy(new DefaultRetryPolicy(
                        3000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(insertReq);
            }

        });
    }

    @Override
    public void onClick(View view) {

    }
}
  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
     //prendre la valeur choisiez par user ou bien saisie
    lblinv.setText(getText(i));
  //  System.out.println(""+lbinv.setText(getText(i)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getproduitName(JSONObject j) {

        //Traversing through all the items in the json array
      for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object

                prod.add(String.valueOf(j.getString("libelle_produit")));

            } catch (Exception e) {
                e.printStackTrace();
            }} /*   JSONObject json = j.getJSONObject("libelle_produit");
                System.out.println(json);
       mySpinner.setAdapter(new ArrayAdapter<String>(New2Activity.this,android.R.layout.simple_dropdown_item_1line,prod));
        } ;

    //remplir le spinner pour afficher les donnes

}*/
  /*
   final RequestQueue queue = Volley.newRequestQueue(new_inventaire.this);
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null && response.length() > 0)
                        {
                            if (!TextUtils.isEmpty(lib_inv.getText().toString().trim()) && !TextUtils.isEmpty(inv_qu.getText().toString().trim()) && !TextUtils.isEmpty(inv_date_exp.getText().toString().trim())
                                    && !TextUtils.isEmpty(date_ren.getText().toString().trim()) && !TextUtils.isEmpty(eta_pro.getText().toString().trim()) && !TextUtils.isEmpty(type_pr.getText().toString().trim())) {

                            try {

                                    JSONObject new_inv = new JSONObject(response);
                                } catch (JSONException em)
                                {
                                  System.out.println("Json error" + em);

/*
          lib_inv.setText(getIntent().getStringExtra("lblInv"));
            inv_qu.setText(getIntent().getStringExtra("quantit"));
            bon_cmd.setText(getIntent().getStringExtra("id_bonC"));
            inv_date_exp.setText(getIntent().getStringExtra("date_expiration"));
            inv_date.setText(getIntent().getStringExtra("date_c"));
            bon_cmd.setText(getIntent().getStringExtra("bon_cmd"));
            date_ren.setText(getIntent().getStringExtra("date_renvo"));
             Intent newinv = new Intent();
                    newinv.setClass(context, Leceladon.class);
                    newinv.setAction(Leceladon.class.getName());
                    newinv.setFlags(
                            Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    newinv.putExtra("lblInv", lis.get(i).get("lib_inv").toString());
                    newinv.putExtra("quantit", lis.get(i).get("quan").toString());
                    newinv.putExtra("date_expiration", lis.get(i).get("date_expi").toString());
                    newinv.putExtra("date_c",lis.get(i).get("date_crt").toString());
                    newinv.putExtra("bon_cmd",lis.get(i).get("bon_cmd").toString());
                    newinv.putExtra("renouvelle",lis.get(i).get("renouvelle").toString());
                    newinv.putExtra("date_renvo",lis.get(i).get("date_renou").toString());
                  //  newinv.putExtra("bonc",bonc);

                    context.startActivity(newinv);

                                }
                                        }
                                        else
                                        {

                                        }
                                        }
                                        else
                                        {System.out.println("no server response");
                                        Toast.makeText(getApplicationContext(), "erreur conexion", Toast.LENGTH_SHORT).show();

                                        Toast.makeText(getApplicationContext(), "Merci de remplire les champs", Toast.LENGTH_SHORT).show();
                                        }
                                        }

                                        }
                                        ,


                                        new Response.ErrorListener()
                                        {
@Override
public void onErrorResponse(VolleyError error)
        {

        System.out.println("error con" + error);
        Toast.makeText(getApplicationContext(), "veuillez verifier votre conexion", Toast.LENGTH_SHORT).show();

        error.printStackTrace();
        }


        }) {
@Override
protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("libelle_produit", lib);
        params.put("prod_renvou", etat);
        params.put("type_produit",ty);
        params.put("quantite", qau);
        params.put("date_exp",dat_ex);

        return params;
        }


        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
        3000,
        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
        }
        }

        */

