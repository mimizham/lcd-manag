package com.example.sam.leceladon_managing_10.Inventaire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class index2Activity extends AppCompatActivity {
    String url ="https://www.work.le-celadon.ma/Managing_Celadon/Inventaires";
    TextView txt;
    TextView txtd;
    TextView txtqu;
    TextView txtl;
    ListView lsv;
    Button moreinfo;
    private List<HashMap<String,Object>> listvi;
    private listInventaire listAd;
    private HashMap<String, Object> review;   /* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index2);
        txt=findViewById(R.id.txtinv);
        txtd=findViewById(R.id.txtdat);
        txtqu=findViewById(R.id.txtquan);
        txtl=findViewById(R.id.txtlib);
        lsv=findViewById(R.id.lvInventaire);
/**/
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(index2Activity.this);
        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         try {
                              JSONArray jarr = new JSONArray(response);
                                   System.out.println(jarr.length());
                                 for (int i = 0; i < jarr.length(); i++) {
                                     JSONObject jObj = new JSONObject(String.valueOf(jarr.getJSONObject(i)));
                                 System.out.println("jobj" + jObj);
                           /*   Toast toast = Toast.makeText(getApplicationContext()
                                     , "" + response, Toast.LENGTH_LONG);
                             toast.show();*/
                                 listvi = new ArrayList<HashMap<String, Object>>();
                                 review = new HashMap<String, Object>();
                                 //  review.put("id_inventaire",jObj.get("id_inventaire"));
                                 review.put("lblInv", jObj.get("libelle_produit"));
                                 review.put("quantit", jObj.get("quantite"));
                                 review.put("date_exp", jObj.get("date_exp"));
                                 listvi.add(review);
                                 listAd = new listInventaire(getApplicationContext(), listvi);
                                 lsv.setAdapter(listAd);
                                     lsv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                         @Override
                                         public void onItemClick(AdapterView<?> parent , View view , int position , long id){
                                             Intent in = new Intent(index2Activity.this,New2Activity.class);
                                             startActivity(in);

                                         }
                                     });

                             }
                         }
                    catch (Exception e)
                    {    System.out.println("read please this exception");
                        e.printStackTrace();

                    }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.get("id_inventaire");
                params.get("libelle_produit");
                params.get("quantite");
                params.get("date_exp");
                return params;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent newinv = new Intent(getApplicationContext(),
                       New2Activity.class);
              /* ficheAjouterRecom.putExtra("" ,);
                newinv.putExtra("libelle_produit" ,lsv.get);
                newinv.putExtra("quantite" ,);
                newinv.putExtra("date_exp" ,);
                newinv.putExtra("" ,);
*/
                startActivity(newinv);
            }
        });
    }
      /*  button2.setOnClickListener(new OnClickListener() {
@Override
public void onClick(View view) {
                 toast = Toast.makeText(getApplicationContext()
                        , "log" , LENGTH_SHORT);
                toast.show();
              Intent intent1 = new Intent(MainActivity.this, NewActivity.class);
        // intent.putExtra("response", response);
        startActivity(intent1);
        }
        }); */


}
/**/
class listInventaire extends BaseAdapter {
    public List<HashMap<String, Object>> lis;
    private LayoutInflater layoutInflater;
    private Context context;

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
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        if (view == null) {

           holder = new ViewHolder();
          layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.layout_inflate_inv, viewGroup, false);
           // holder.idinv = view.findViewById(R.id.txtinv);
            holder.lblInv= view.findViewById(R.id.txtlib);
            holder.quantit = view.findViewById(R.id.txtquan);
            holder.date_exp = view.findViewById(R.id.txtdat);
            holder.show_inv=view.findViewById(R.id.btn);
            view.setTag(holder);
            //
        } else {
            holder = (ViewHolder) view.getTag();
          // holder.show_inv.setText(lis.get(i).get
        }
        //holder.idinv.setText(lis.get(i).get("idinv").toString());
        holder.lblInv.setText(lis.get(i).get("lblInv").toString());
        holder.quantit.setText(lis.get(i).get("quantit").toString());
        holder.date_exp.setText(lis.get(i).get("date_exp").toString());

        holder.show_inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newinv = new Intent(context, New2Activity.class);
               newinv.putExtra("lblInv", String.valueOf(holder.lblInv));
                newinv.putExtra("quantit", String.valueOf(holder.quantit));
                newinv.putExtra("date_exp", String.valueOf(holder.date_exp));
              //  newinv.putExtra("lblInv", String.valueOf(holder.date_exp));
                context.startActivity(newinv);
            }
        });
        return view;
    }

    public static class ViewHolder {
        //ViewHolder est une classe interne appelé aussi cache qui contient toutesles éléments de l’Item changeables ou cliquablesImageView imgv;
        // TextView  lblEdit;
        TextView lblInv;
     //   TextView idinv;
        TextView quantit;
       TextView date_exp;
        Button show_inv;
       /*  TextView date_entre;

        TextView idfourni;
        TextView id_fact;
        TextView statutStock;
        TextView prod;
        TextView dateRenv;*/


    }


}


