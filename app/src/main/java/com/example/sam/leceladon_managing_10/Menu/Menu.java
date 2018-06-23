package com.example.sam.leceladon_managing_10.Menu;
//import com.example.sam.leceladon_managing_10.Delivery.DeliveryMain;
//import com.example.sam.leceladon_managing_10.Delivery.MyDelivery;
import com.example.sam.leceladon_managing_10.Delivery.DeliveryMain;
import com.example.sam.leceladon_managing_10.Delivery.MyDelivery;
import com.example.sam.leceladon_managing_10.Inventaire.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sam.leceladon_managing_10.Inventaire.New2Activity;
import com.example.sam.leceladon_managing_10.R;

public class Menu extends AppCompatActivity {

    RelativeLayout r,d,m,ra ;
    String resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
         resp= getIntent().getStringExtra("tock_tel");
        System.out.print("sqq"+resp);

        r= findViewById(R.id.relativeLInventory);
        d=findViewById(R.id.relativeLDelivry);
        m=findViewById(R.id.relativeDeliveryM);
        ra=findViewById(R.id.relativeaddLInventory);
        r.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(Menu.this, index2Activity.class);
                intent1.putExtra("tock_tel", resp);
                startActivity(intent1);



            }
        });
        ra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(Menu.this, New2Activity.class);
                intent1.putExtra("tock_tel", resp);
                startActivity(intent1);



            }
        });
        d.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(Menu.this, DeliveryMain.class);
                intent1.putExtra("tock_tel", resp);
                startActivity(intent1);


            }
        });
        m.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               DeliveryMain.btn=false;
                Intent intent1 = new Intent(Menu.this, MyDelivery.class);
                intent1.putExtra("tock_tel", resp);
              startActivity(intent1);


            }
        });

    }



}
