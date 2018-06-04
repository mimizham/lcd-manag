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
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.Leceladon;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class index2Activity extends AppCompatActivity implements View.OnClickListener
{
    String url = "https://www.work.le-celadon.ma/Managing_Celadon/Inventaires/index";
    TextView txt_inv;
    TextView txt_date;
    TextView txt_date_exp;
    TextView txt_qu;
    String bonc;
    String prod_ren;
    ListView lsv;
    Button moreinfo;
    private List<HashMap<String, Object>> listvi;
    private listInventaire listAd;

    private HashMap<String, Object> review;  /*  */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index2);
        txt_inv = findViewById(R.id.txtinv);
        txt_date_exp = findViewById(R.id.txtdat_exp);
        txt_date = findViewById(R.id.txtdat);
        txt_qu = findViewById(R.id.txtquan);
        lsv = findViewById(R.id.lvInventaire);
        moreinfo = findViewById(R.id.btn_inv);
        final String resp = getIntent().getStringExtra("tock_tel");

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(index2Activity.this);
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
                            listvi = new ArrayList<HashMap<String, Object>>();
                            for (int i = 0; i < jarr.length(); i++)
                            {

                                JSONObject jObj = new JSONObject(String.valueOf(jarr.getJSONObject(i)));
                                review = new HashMap<String, Object>();
                             /*  System.out.println("i" + i + String.valueOf(jarr.getJSONObject(i)));*/
                                review.put("lib_inv", jObj.get("libelle_produit"));
                                review.put("quan",jObj.get("quantite"));
                                review.put("date_expi", jObj.get("date_exp"));
                                review.put("date_crt",jObj.get("date_entre"));
                                bonc= String.valueOf(jObj.get("id_bonC"));
                                prod_ren=String.valueOf(jObj.get("prod_renvou"));
                                listvi.add(review);
                                listAd = new listInventaire(getApplicationContext(), listvi);
                               //Log.i("kk", String.valueOf(listvi));
                                lsv.setAdapter(listAd);
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
                        params.put("tock", resp);
                        return params;
                    }
                };

                       queue.add(stringRequest);


       /* lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent newinv = new Intent(getApplicationContext(),
                        New2Activity.class);
                newinv.putExtra("libelle_produit", lsv.get);
                newinv.putExtra("quantite", );
                newinv.putExtra("date_exp", );
                newinv.putExtra("", );

                startActivity(newinv);

                String main = lsv.getSelectedItem().toString();
                System.out.print(main);
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.print("kskss");
                Intent in = new Intent(index2Activity.this, New2Activity.class);
                startActivity(in);

                finish();

            }
        });
    }

    /* */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {

    }
}
class listInventaire extends BaseAdapter
{
    public List<HashMap<String, Object>> lis;
    private LayoutInflater layoutInflater;
    private Context context;
    // Button show_inv;

    public listInventaire(Context context, List<HashMap<String, Object>> lis) {
        this.lis = lis;
        this.layoutInflater = layoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.lis.size();
    }

    @Override
    public Object getItem(int i) {
        return this.lis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {

         ViewHolder holder = null;
        if (view == null || holder == null)
        {   holder = new ViewHolder();
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_inflate_inv, viewGroup, false);

            holder.lblInv = view.findViewById(R.id.txtinv);
          /*    Log.i("oo", String.valueOf(lis));*/
            holder.quantit = view.findViewById(R.id.txtquan);
            holder.date_exp = view.findViewById(R.id.txtdat_exp);
            holder.date_c = view.findViewById(R.id.txtdat);
            holder.show_inv= view.findViewById(R.id.btn_inv);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        /* System.out.println("size" +lis.size());
            Log.i("ool", lis.get(i).toString());
       Log.i("loo", lis.get(i).toString());*/
            holder.lblInv.setText(lis.get(i).get("lib_inv").toString());
            holder.quantit.setText(lis.get(i).get("quan").toString());
            holder.date_exp.setText(lis.get(i).get("date_expi").toString());
            holder.date_c.setText(lis.get(i).get("date_crt").toString());
            holder.show_inv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

                    context.startActivity(newinv);

                }
            });


        return view;
    }

    public static class ViewHolder
    {
        //ViewHolder est une classe interne appelé aussi cache qui contient toutesles éléments de l’Item changeables ou cliquablesImageView imgv;
        // TextView  lblEdit;        //
        TextView lblInv;
        TextView quantit;
        TextView date_c;
        TextView date_exp;
        Button show_inv;

    }
}





/*
 for (i = 0; i < lis.size(); i++)
        { System.out.println("size" +lis.size());
            Log.i("ool", lis.get(i).toString());
         /*     holder.lblInv.setText(lis.get(i).get("lib_inv"+i).toString());
        holder.quantit.setText(lis.get(i).get("quantite").toString());
       Log.i("loo", lis.get(i).toString());
            holder.date_exp.setText(lis.get(i).get("date_expi"+i).toString());
                    holder.date_c.setText(lis.get(i).get("date_crt"+i).toString());

                    }
//show_inv = view.findViewById(R.id.btn); holder.date_exp = view.findViewById(R.id.txtdat);
            //   holder.show_inv=view.findViewById(R.id.btn);
            //holder.idinv.setText(lis.get(i).get("idinv").toString());
     holder.date_exp.setText(lis.get(i).get("date_expiration").toString());
  holder.show_inv.setOnClickListener(new View.OnClickListener()
          {
                @Override
                public void onClick(View view) {
                    Intent newinv = new Intent(context, New2Activity.class);
                    newinv.putExtra("lblInv", String.valueOf(holder.lblInv));
                    newinv.putExtra("quantit", String.valueOf(holder.quantit));
                  //  newinv.putExtra("date_expiration", String.valueOf(holder.date_exp));
                    //  newinv.putExtra("lblInv", String.valueOf(holder.date_exp));
                    context.startActivity(newinv);
                }
            });

JSONArray jarr = new JSONArray(response);
                             System.out.println("lenght"+jarr);
                             System.out.println("lenght"+jarr.length());$
                             JSONObject jObj = new JSONObject(String.valueOf(jarr.getJSONObject(0)));
                             review.put("lblInv", jObj.get("libelle_produit"));
                             review.put("quantit", jObj.get("quantite"));
                             review.put("date_exp", jObj.get("date_exp"));
                             listvi.add(review);
                             listAd = new listInventaire(getApplicationContext(), listvi);
                             lsv.setAdapter(listAd);
                             Toast toast = Toast.makeText(getApplicationContext()
                                     , "" + response, Toast.LENGTH_LONG);
                             toast.show();*/