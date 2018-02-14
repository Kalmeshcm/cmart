package com.example.abc.cmart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;

/**
 * Created by ABC on 06-02-2018.
 */


public class ShowProduct extends AppCompatActivity {
    private Activity context;
    private TextView tvname, tvdesc, tvprice;
    private ImageView imageView;
    private String name, desc, price, imageurl;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_show);


        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        desc = intent.getStringExtra("desc");
        price = intent.getStringExtra("price");
        imageurl = intent.getStringExtra("image");

        tvprice = (TextView) findViewById(R.id.tvprice);
        tvdesc = (TextView) findViewById(R.id.tvdesc);
        tvname = (TextView) findViewById(R.id.tvname);
        imageView = (ImageView) findViewById(R.id.pimage);


        tvname.setText(name);
        tvprice.setText(price);
        tvdesc.setText(desc);

        Glide.with(this).load(imageurl).into(imageView);


    }

}