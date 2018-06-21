package com.example.sam.leceladon_managing_10.Delivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.sam.leceladon_managing_10.R;

import java.util.ArrayList;
import java.util.HashMap;

import database.SQLliteUser;

public class Product_Delivery_Details extends AppCompatActivity {
    private SQLliteUser db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__delivery__details);
        WebView webView = findViewById(R.id.webviewII);
        db = new SQLliteUser(getApplicationContext());
        ArrayList<HashMap<String, String>> result=db.getUserDetails("");
        String tocken=result.get(0).get("Tocken");
        webView.loadUrl("https://work.le-celadon.ma/Managing_Celadon/Livreur/ProductonResult/"+tocken+"/"+DeliveryMain.id_);


    }
}
