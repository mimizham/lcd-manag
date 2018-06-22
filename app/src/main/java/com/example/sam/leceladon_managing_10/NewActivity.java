package com.example.sam.leceladon_managing_10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class NewActivity extends AppCompatActivity {
    Button btnnew;
    EditText edtNom;
    EditText edtPerenom;
    EditText edtPhone;
    EditText emailc;
    EditText psw;
    EditText adr;
    String urlc = "https://www.work.le-celadon.ma/Managing_Celadon/Users/create";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        btnnew = findViewById(R.id.inscrire);
        edtNom = findViewById(R.id.editTextnom);
        edtPerenom = findViewById(R.id.editTextprenom);
        edtPhone = findViewById(R.id.telphone);
        emailc = findViewById(R.id.email);
        psw = findViewById(R.id.psw);
        adr=findViewById(R.id.etadresse);
        final String etNom = edtNom.getText().toString();
        final String etPre = edtPerenom.getText().toString();
        final String ettel = edtPhone.getText().toString();
        final String etemail = emailc.getText().toString();
        final String etpsw = psw.getText().toString();
        final String adress = adr.getText().toString();

        btnnew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final RequestQueue queue = Volley.newRequestQueue(NewActivity.this);
                final StringRequest insertReq = new StringRequest(Request.Method.POST, urlc,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String newscmp)
                            {
                                /*  Toast toast = Toast.makeText(getApplicationContext()
                             , "log" + etNom + "psw" + etPre+ ettel+ etemail+ etpsw+adress, LENGTH_SHORT);
                               toast.show();

                                Intent intent2 = new Intent(NewActivity.this, Leceladon.class);
                                intent2.putExtra("newCompte", newscmp);

                                startActivity(intent2);*/
                                System.out.print(newscmp);
                               /// txtc.setText(""+newscmp);
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        /*Toast toast = Toast.makeText(getApplicationContext()
                                , ""+error,Toast.LENGTH_LONG);
                        toast.show();*/
                        System.out.print(error);
                    }

                                                          })
                {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> insert = new HashMap<String, String>();
                        insert.put("nom", etNom);
                        insert.put("prenom", etPre);
                        insert.put("telphone", ettel);
                        insert.put("emailcld", etemail);
                        insert.put("adress", adress);
                        insert.put("psw", etpsw);

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
}
