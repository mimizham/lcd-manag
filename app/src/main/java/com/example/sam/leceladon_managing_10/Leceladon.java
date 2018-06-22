package com.example.sam.leceladon_managing_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Leceladon extends AppCompatActivity
{
    EditText lib_inv;
    EditText inv_date;
    EditText inv_date_exp;
    EditText inv_qu;
    EditText bon_cmd;
    EditText date_ren;
    RadioButton oui;
    RadioButton non;
    Button modif;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
            String renvou;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leceladon);
            lib_inv=findViewById(R.id.txtlib);
            inv_date=findViewById(R.id.date_c);
            inv_qu=findViewById(R.id.txtquan);
            inv_date_exp=findViewById(R.id.inv_dat_exp);
            bon_cmd=findViewById(R.id.bon_cmd);
            date_ren = findViewById(R.id.date_renvou);
            oui=findViewById(R.id.radioButton);
            non=findViewById(R.id.radioButton2);
            modif=findViewById(R.id.btn_valide_nv_inv);
            lib_inv.setText(getIntent().getStringExtra("lblInv"));
            inv_qu.setText(getIntent().getStringExtra("quantit"));
            bon_cmd.setText(getIntent().getStringExtra("id_bonC"));
            inv_date_exp.setText(getIntent().getStringExtra("date_expiration"));
            inv_date.setText(getIntent().getStringExtra("date_c"));
            bon_cmd.setText(getIntent().getStringExtra("bon_cmd"));
            date_ren.setText(getIntent().getStringExtra("date_renvo"));
  /*          renvou=getIntent().getStringExtra("renouvelle");

             System.out.print("renvo"+renvou);
*/
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
            } /**/
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Leceladon.this, new_inventaire.class);

                startActivity(intent);
            }
        });
    }


}
