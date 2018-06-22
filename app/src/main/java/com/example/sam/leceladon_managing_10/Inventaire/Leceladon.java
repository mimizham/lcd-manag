package com.example.sam.leceladon_managing_10.Inventaire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.Inventaire.index2Activity;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leceladon extends AppCompatActivity
{    private List<HashMap<String, Object>> listvi;

    String url = "https://www.work.le-celadon.ma/Managing_Celadon/Inventaires/show";
    TextView txt_inv_dt;
    TextView txt_date_dt;
    TextView txt_date_exp_dt;
    TextView txt_qu_dt;
    TextView bonc_dt;
    TextView prod_ren_dt;
    TextView fact_dt;
    TextView forni_dt;
    ListView lsv_dt_dt;
    Button ret_dt;
    private List<HashMap<String, Object>> listvi_dt;
    private Detaile_Inv listAd_dt;

    private HashMap<String, Object> review;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leceladon);
        lsv_dt_dt =findViewById(R.id.Detaille_Inventaire);
        txt_inv_dt = findViewById(R.id.etlibinv_dt);
        txt_date_exp_dt = findViewById(R.id.etdatexp);
        txt_date_dt = findViewById(R.id.txtdat);
        txt_qu_dt = findViewById(R.id.etquan_dt);
        bonc_dt=findViewById(R.id.etbonc_dt);
        forni_dt=findViewById(R.id.etforni_dt);
        fact_dt=findViewById(R.id.etfac_dt);
        ret_dt= findViewById(R.id.ret_dt);
            final String resp_dt = getIntent().getStringExtra("tock_tel");

            // Instantiate the RequestQueue.
            final RequestQueue queue = Volley.newRequestQueue(Leceladon.this);
            // Request a string response from the provided URL.
            final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            try
                            {
                                Log.i("res", response);
                                JSONArray jarr = new JSONArray(response);
                                listvi_dt = new ArrayList<HashMap<String, Object>>();
                                for (int i = 0; i < jarr.length(); i++)
                                {

                                    JSONObject jObj = new JSONObject(String.valueOf(jarr.getJSONObject(i)));
                                    review = new HashMap<String, Object>();

                                    review.put("lib_inv_dt", jObj.get("libelle_produit"));
                                    review.put("quan",jObj.get("quantite"));
                                    review.put("date_expi", jObj.get("date_exp"));
                                    review.put("date_crt",jObj.get("date_entre"));
                                    review.put("bon_cmd",jObj.get("id_bonC"));
                                    review.put("renouvelle",jObj.get("prod_renvou"));
                                    review.put("date_renou",jObj.get("date_renvou"));
                             /*     System.out.println("i" +String.valueOf(jObj.get("prod_renvou")));
                               bonc= String.valueOf(jObj.get("id_bonC"));
                                prod_ren=String.valueOf(jObj.get("prod_renvou"));*/
                                    listvi_dt.add(review);
                                listAd_dt = new Detaile_Inv(getApplicationContext(), listvi);
                                    //Log.i("kk", String.valueOf(listvi));
                                    lsv_dt_dt.setAdapter(listAd_dt);
                                }

                            }
                            catch (Exception e)
                            {
                                System.out.println("read please this exception" + e);
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            System.out.println("error con" + error);
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("tock", resp_dt);
                            params.put("tock", resp_dt);
                            return params;
                        }
                     };

            queue.add(stringRequest);


        }


    }

    /* */


    class Detaile_Inv extends BaseAdapter
    {
        public List<HashMap<String, Object>> lis_dt;
        private LayoutInflater layoutInflater_dt;
        private Context context_dt;
        // Button show_inv;

        public Detaile_Inv(Context context, List<HashMap<String, Object>> lis_dt)
        {
            this.lis_dt = lis_dt;
            this.layoutInflater_dt = layoutInflater_dt.from(context);
            this.context_dt = context;
        }

        @Override
        public int getCount() {
            return this.lis_dt.size();
        }

        @Override
        public Object getItem(int i) {
            return this.lis_dt.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup)
        {

            ViewHolder holder1 = null;
            if (view == null || holder1 == null)
            {   holder1 = new ViewHolder();
                layoutInflater_dt = (LayoutInflater) context_dt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflater_dt.inflate(R.layout.layout_detaille_inv, viewGroup, false);

              holder1.lblInv_dt = view.findViewById(R.id.etlibinv_dt);
                holder1.quantit_dt = view.findViewById(R.id.etquan_dt);
                holder1.date_exp_dt = view.findViewById(R.id.etdatexp);
                holder1.date_c_dt = view.findViewById(R.id.txtdat_dt);
                holder1.bon_c_dt= view.findViewById(R.id.etbonc_dt);
                holder1.forni_dt= view.findViewById(R.id.etforni_dt);
                holder1.fact_dt= view.findViewById(R.id.etfac_dt);

                holder1.show_inv_dt= view.findViewById(R.id.btn_inv); /* ;    Log.i("oo", String.valueOf(lis));*/
                view.setTag(holder1);
            }
            else{
                holder1 = (Detaile_Inv.ViewHolder) view.getTag();
            }
        /* System.out.println("size" +lis.size());
            Log.i("ool", lis.get(i).toString());
       Log.i("loo", lis.get(i).toString());
            holder.lblInv_dt.setText(lis.get(i).get("lib_inv").toString());
            holder.quantit.setText(lis.get(i).get("quan").toString());
            holder.date_exp.setText(lis.get(i).get("date_expi").toString());
            holder.date_c.setText(lis.get(i).get("date_crt").toString());
*/


            return view;
        }

        public static class ViewHolder
        {
            //ViewHolder est une classe interne appelé aussi cache qui contient toutesles éléments de l’Item changeables ou cliquablesImageView imgv;//
            TextView lblInv_dt;
            TextView quantit_dt;
            TextView date_c_dt;
            TextView bon_c_dt;
            TextView fact_dt;
            TextView forni_dt;
            TextView date_exp_dt;
            Button show_inv_dt;

        }
    }

 /**/
/*
    EditText lib_inv;

    EditText inv_date;
    EditText inv_date_exp;
    EditText inv_qu;
 //   EditText bon_cmd;
    EditText date_ren;
    RadioButton oui;
    RadioButton non;
    Button modif; *//*lib_inv=findViewById(R.id.txtlib);
           // inv_date=findViewById(R.id.date_c);
            inv_qu=findViewById(R.id.txtquan);
            inv_date_exp=findViewById(R.id.inv_dat_exp);
       //     bon_cmd=findViewById(R.id.bon_cmd);
            date_ren = findViewById(R.id.date_renvou);
            oui=findViewById(R.id.radioButton);
            non=findViewById(R.id.radioButton2);
            modif=findViewById(R.id.btn_valide_nv_inv);
             lib_inv.setText(getIntent().getStringExtra("lblInv"));
            inv_qu.setText(getIntent().getStringExtra("quantit"));
//            bon_cmd.setText(getIntent().getStringExtra("id_bonC"));
            inv_date_exp.setText(getIntent().getStringExtra("date_expiration"));
            inv_date.setText(getIntent().getStringExtra("date_c"));
            date_ren.setText(getIntent().getStringExtra("date_renvo"));
          renvou=getIntent().getStringExtra("renouvelle");

             System.out.print("renvo"+renvou);

            if (getIntent().getStringExtra("renouvelle").equals("N"))
            {
                non.setChecked(true);
                oui.setChecked(false);
                Log.i(",nhkhnk",getIntent().getStringExtra("renouvelle"));
            }
            else
            {
                System.out.print("N");
                oui.setChecked(true);
                non.setChecked(false);
            }
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Leceladon.this, new_inventaire.class);

                startActivity(intent);
            }
        }) */