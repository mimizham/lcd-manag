package com.example.sam.leceladon_managing_10.Inventaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.sam.leceladon_managing_10.NewActivity;
import com.example.sam.leceladon_managing_10.R;

public class new_inventaire extends AppCompatActivity
{
    String url = "http://work.le-celadon.ma/Managing_Celadon/Inventaires/create";
    EditText lib_inv;
    EditText type_pr;
   EditText  inv_date_cr;
    EditText inv_qu;
    EditText eta_pro;
    String prod_rnv;
    RadioButton oui;
    RadioButton non;

    Button modif,annuler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_inventaire);

        final String recive_tock = getIntent().getStringExtra("tock_tel");
        lib_inv = findViewById(R.id.etlib_nv);
        inv_qu = findViewById(R.id.etquan_nv);
          inv_date_cr = findViewById(R.id.date_cr_nv);
     //   eta_pro = findViewById(R.id.etaprod_nv);
     //   type_pr = findViewById(R.id.ettypepro_nv);
        oui = findViewById(R.id.radioButton1);
        non = findViewById(R.id.radioButton2);
        annuler=findViewById(R.id.retour_nv);


        modif = findViewById(R.id.newInv_nv);
        //final String ty = type_pr.getText().toString();
       final String dat_cr= inv_date_cr.getText().toString();
        final String qau = inv_qu.getText().toString();
        final String lib = lib_inv.getText().toString();
        final String etat = eta_pro.getText().toString();
        if (non.isChecked() == true)
        {
            prod_rnv = "oui";
        }
        else
        {
            prod_rnv = "non";
        }
        modif.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
              Log.i("prod", prod_rnv);
            Intent newinv = new Intent(new_inventaire.this, New2Activity.class);
            newinv.putExtra("nv_lblInv",lib);
            newinv.putExtra("nv_quantit",qau);
           newinv.putExtra("nv_date_creation", dat_cr);
          //  newinv.putExtra("nv_type_produit",ty);
            newinv.putExtra("nv_etat", etat);
            newinv.putExtra("nv_tock_tel", recive_tock);
            newinv.putExtra("nv_prod_renv", prod_rnv);
            startActivity(newinv);

        }});
        annuler.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


              /*  ty = null;
                qau ="";
                lib = "";
                etat ="";*/
            }});
}
}


