package com.example.sam.leceladon_managing_10.Inventaire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
import com.example.sam.leceladon_managing_10.MainActivity;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNoAccess extends AppCompatActivity implements View.OnClickListener{
   String url="http://work.le-celadon.ma/Managing_Celadon/Users/getn";
   TextView nom,email,tel,dtc;
         Button  btnact;
         String tocken;
         ListView lsvus;
    private List<HashMap<String, Object>> list_user;
    private listUserNOACCES listAd_us;

    private HashMap<String, Object> review_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_noacces);
        nom=findViewById(R.id.Textnom);
        email=findViewById(R.id.Textemail);
       tel=findViewById(R.id.texttelphone);
        dtc=findViewById(R.id.dtc);
        lsvus=findViewById(R.id.list_user_noacc);
       tocken= getIntent().getStringExtra("tock_tel");
      // System.out.print(tocken);

        final RequestQueue queue = Volley.newRequestQueue(UserNoAccess.this);
        // Request a string tockenonse from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        Log.i("res", response);
                       try
                        {
                             JSONArray jarr = new JSONArray(response);
                            JSONObject jsonobj = jarr.getJSONObject(0);
                        Log.i("res1",String.valueOf(jsonobj));
                            switch (jsonobj.keys().next())
                            {

                                case "device":
                                {
                                    list_user = new ArrayList<HashMap<String, Object>>();
                                    for (int i = 0; i < jarr.length(); i++)
                                    {
                                        JSONObject jObj = new JSONObject(String.valueOf(jarr.getJSONObject(i)));
                                        Log.i("case", String.valueOf(jObj));
                                        review_us = new HashMap<String, Object>();
                                        review_us.put("email", jObj.getString("id_usr"));
                                        review_us.put("nom", jObj.getString("name"));
                                        review_us.put("lastname", jObj.getString("lastname"));
                                        review_us.put("date_creat", jObj.getString("date-creat"));
                                        review_us.put("tel", jObj.get("device"));
                                        System.out.println("i" + String.valueOf(jObj.getString("date-creat")));
                                        list_user.add(review_us);
                                        listAd_us = new listUserNOACCES(getApplicationContext(), list_user);
                                        Log.i("kk", String.valueOf(list_user));
                                        lsvus.setAdapter(listAd_us);/**/
                                    }

                                }
                                break;
                                case "erreur":
                                {
                                    Intent intent = new Intent(UserNoAccess.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                break;
                            }

                        }
                        catch (Exception e)
                        {
                            System.out.println("read please this exception" + e);
                        } /**/
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        System.out.println("error con" + error);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tock", tocken);

                return params;
            }
        };

        queue.add(stringRequest);


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {
    }

    @Override
    public void onClick(View view) {

    }
}
class listUserNOACCES extends BaseAdapter
{
    public List<HashMap<String, Object>> lis;
    private LayoutInflater layoutInflater;
    private Context context;

    public listUserNOACCES(Context context, List<HashMap<String, Object>> lis_us) {
        this.lis = lis_us;
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
            view = layoutInflater.inflate(R.layout.list_no_acces, viewGroup, false);


           Log.i("oo", String.valueOf(lis));
            holder.id_user = view.findViewById(R.id.Textemail);
            holder.nom = view.findViewById(R.id.Textnom);
            holder.tel= view.findViewById(R.id.texttelphone);
            holder.date_c = view.findViewById(R.id.dtc);
            holder.act_us= view.findViewById(R.id.btn_active);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        holder.id_user.setText(lis.get(i).get("email").toString());
        holder.nom.setText(lis.get(i).get("nom").toString()+""+ lis.get(i).get("lastname").toString());
        holder.tel.setText(lis.get(i).get("tel").toString());
        holder.date_c.setText(lis.get(i).get("date_creat").toString());
        holder.act_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(context, "User Activer avec succé", Toast.LENGTH_SHORT).show();
               Intent newinv = new Intent();
                newinv.setClass(context, Leceladon.class);
                newinv.setAction(Leceladon.class.getName());
                newinv.setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                newinv.putExtra("id_inv", lis.get(i).get("id_inv").toString());
                newinv.putExtra("tock_tel", lis.get(i).get("resp").toString());/* */
            }
        });


        return view;
    }

    public static class ViewHolder
    {
        //ViewHolder est une classe interne appelé aussi cache qui contient toutesles éléments de l’Item changeables ou cliquablesImageView imgv;
        TextView id_user;
        TextView nom;
        TextView tel;
        TextView date_c;
        TextView email;
        Button act_us;

    }
}