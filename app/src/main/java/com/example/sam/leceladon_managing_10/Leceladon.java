package com.example.sam.leceladon_managing_10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Leceladon extends AppCompatActivity
{
    TextView lib_inv;
    TextView inv_date;
    TextView inv_date_exp;
    TextView inv_qu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leceladon);
            lib_inv=findViewById(R.id.txtlib);
            inv_date=findViewById(R.id.txtdat);
            inv_qu=findViewById(R.id.txtquan);
            inv_date_exp=findViewById(R.id.inv_dat_exp);
            lib_inv.setText(getIntent().getStringExtra("lblInv"));
            inv_qu.setText(getIntent().getStringExtra("quantit"));
//            inv_date.setText(getIntent().getStringExtra("date_crt"));
            inv_date_exp.setText(getIntent().getStringExtra("date_expiration"));
            System.out.print(getIntent().getStringExtra("quantit"));
   }


}
