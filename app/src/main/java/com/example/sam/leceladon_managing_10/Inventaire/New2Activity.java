package com.example.sam.leceladon_managing_10.Inventaire;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class New2Activity extends AppCompatActivity{
    String new_inv_lib;
    String new_inv_qun;
    String new_inv_eta;
    String new_inv_exp;
  //  String new_inv_bonc; String new_inv_ty;
    String new_inv_tock;
    String new_inv_renvo;
    String get_fac,get_bonc,get_dtexp,get_prod,get_renv;
    EditText lblinv;
    EditText lblquan,nv_idfou,nv_idcmd,nv_dtexp,nv_dtrenv,nv_idbonc,nv_prod;
    EditText daterenv;
    Button btn;
    final String lbinv="";
  //  ArrayList<String> prod;
    final String url = "http://work.le-celadon.ma/Managing_Celadon/Inventaires";
    String ty;
    String quan;
    private int mYear, mMonth, mDay, mHour, mMinute;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new2);
        new_inv_lib= getIntent().getStringExtra("nv_lblInv");
        new_inv_qun= getIntent().getStringExtra("nv_quantit");
       // new_inv_exp = getIntent().getStringExtra("nv_date_expiration");
     //   new_inv_ty= getIntent().getStringExtra("nv_type_produit");
        new_inv_eta= getIntent().getStringExtra("nv_etat");
        new_inv_tock= getIntent().getStringExtra("nv_tock_tel");
        new_inv_renvo= getIntent().getStringExtra("nv_prod_renv");

        Log.i("new", new_inv_eta+new_inv_exp+new_inv_lib);
        btn = findViewById(R.id.newInv);
        lblinv = findViewById(R.id.txtinv);
        lblquan = findViewById(R.id.txtquan);
        btn=findViewById(R.id.newInv);
        nv_idbonc=findViewById(R.id.etbonc_nv);
        nv_idfou=findViewById(R.id.etref_fact);
        nv_dtexp=findViewById(R.id.etdatexp);
        nv_dtrenv=findViewById(R.id.etrenv_nv);
        nv_idcmd=findViewById(R.id.etbonc_nv);
        nv_prod=findViewById(R.id.etref_prod);
        get_fac=nv_idfou.getText().toString();
        get_bonc=nv_idbonc.getText().toString();
        get_dtexp=nv_dtexp.getText().toString();
        get_renv=nv_dtrenv.getText().toString();
        get_prod=nv_prod.getText().toString();
        btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                final RequestQueue queue = Volley.newRequestQueue(New2Activity.this);
                final StringRequest insertReq = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String newsinv)
                            {
                                System.out.print(newsinv);

                                   try {

                                       Toast toast = Toast.makeText(getApplicationContext()
                                               , "Bien ajouter" , Toast.LENGTH_LONG);
                                       toast.show();


                                                }
                                            catch (Exception e)
                                            {
                                                e.printStackTrace();
                                            }
                                        }



                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        System.out.print(error);
                    }


                })

          {
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> insert = new HashMap<String, String>();

                        insert.put("id_facture", quan);
                        insert.put("id_fournisseur", quan);
                        insert.put("id_bonC", quan);
                        insert.put("date_renvou", String.valueOf(daterenv));
                        insert.put("statut_stock", ty);
                        insert.put("libelle_produit", new_inv_lib);
                        insert.put("quantite", new_inv_qun);
                        insert.put("date_exp",  new_inv_exp);
                       // insert.put("type_produit", new_inv_ty);
                        insert.put("statut_stock", new_inv_eta);
                        insert.put("tock", new_inv_tock);
                        insert.put("prod_renvou",new_inv_renvo);
                        return insert;
                    }

                };
                //10000 is the time in milliseconds adn is equal to 10 sec
                insertReq.setRetryPolicy(new DefaultRetryPolicy(
                        1000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(insertReq);
            }

        });
    }


}


