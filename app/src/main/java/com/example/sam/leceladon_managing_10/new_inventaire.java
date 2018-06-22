package com.example.sam.leceladon_managing_10;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.sam.leceladon_managing_10.Inventaire.index2Activity;

public class new_inventaire extends AppCompatActivity {
    String url="http://work.le-celadon.ma/Managing_Celadon/Inventaires/create";
    EditText lib_inv;
    EditText type_pr;
    EditText inv_date_exp;
    EditText inv_qu;
    EditText eta_pro;
    EditText date_ren;
    String prod_rnv;
    RadioButton oui;
    RadioButton non;

    Button modif;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inventaire);

      final String recive_tock= getIntent().getStringExtra("tock_tel");
        lib_inv=findViewById(R.id.etlib_nv);
        inv_qu=findViewById(R.id.etquan_nv);
        inv_date_exp=findViewById(R.id.date_expir_nv);
        eta_pro=findViewById(R.id.etaprod_nv);
        type_pr=findViewById(R.id.ettypepro_nv);
        oui=findViewById(R.id.radioButton1);
        non=findViewById(R.id.radioButton2);


        modif=findViewById(R.id.newInv);
        final String ty = type_pr.getText().toString();
        final String dat_ex = inv_date_exp.getText().toString();
        final String qau = inv_qu.getText().toString();
        final String lib = lib_inv.getText().toString();
        final String etat = eta_pro.getText().toString();

        if (non.isChecked()==true)
        {

          prod_rnv="oui";

        }
        else
        {
            prod_rnv="non";
        }

        Intent newinv = new Intent(new_inventaire.this, index2Activity.class);
                    newinv.putExtra("lblInv",lib );
                    newinv.putExtra("quantit",qau);
                    newinv.putExtra("date_expiration",dat_ex);
                    newinv.putExtra("type_produit",ty);
                    newinv.putExtra("etat",etat);
                    newinv.putExtra("tock_tel",etat);
                    newinv.putExtra("prod_renv",prod_rnv);


         }
                }