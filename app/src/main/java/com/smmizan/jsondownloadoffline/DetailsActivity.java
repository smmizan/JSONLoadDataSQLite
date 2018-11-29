package com.smmizan.jsondownloadoffline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {


    TextView t1,t2,t3,t4,t5,t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        String string1 = getIntent().getStringExtra("Products_Name");
        String string2 = getIntent().getStringExtra("Products_Code");
        String string3 = getIntent().getStringExtra("products_type");

        String string4 = getIntent().getStringExtra("products_pack_size");
        String string5 = getIntent().getStringExtra("quantity");
        String string6 = getIntent().getStringExtra("products_price");

        t1 = (TextView) findViewById(R.id.text1);
        t2 = (TextView) findViewById(R.id.text2);
        t3 = (TextView) findViewById(R.id.text3);

        t4 = (TextView) findViewById(R.id.text4);
        t5 = (TextView) findViewById(R.id.text5);
        t6 = (TextView) findViewById(R.id.text6);




        t1.setText(string1);
        t2.setText(string2);
        t3.setText(string3);

        t4.setText(string4);
        t5.setText(string5);
        t6.setText(string6);




    }
}
