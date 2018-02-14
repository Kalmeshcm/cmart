package com.example.abc.cmart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ABC on 05-02-2018.
 */

public class ProductList extends ArrayAdapter<Products>{

    private Activity context;
    private List<Products> productlist;
    private LayoutInflater layoutInflater;


    public ProductList(Activity context, List<Products> productlist) {
        super(context, R.layout.main_activity_single_item,productlist);
        this.context=context;
        this.productlist=productlist;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.main_activity_single_item, null);
        }

        TextView title = (TextView)convertView.findViewById(R.id.tvMain);
        TextView description = (TextView)convertView.findViewById(R.id.tvDescription);
        TextView price =(TextView)convertView.findViewById(R.id.tvPrice);
        ImageView image = (ImageView)convertView.findViewById(R.id.ivMain);

        Products products = productlist.get(position);

        title.setText(products.getPname());
        description.setText(products.getPdesc());
        price.setText(products.getPprice());

        Glide.with(context).load(products.getPimageurl()).into(image);

        return convertView;

    }

}

