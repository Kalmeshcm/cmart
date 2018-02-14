package com.example.abc.cmart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ABC on 13-02-2018.
 */

public class RecyclerAdafter extends RecyclerView.Adapter<RecyclerAdafter.myViewHolder> {

    private List<Products> products;
    private Context context;

    public RecyclerAdafter(List<Products> products, Context context){
        this.products = products;
        this.context = context;

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_single_item,parent,false);


        return new myViewHolder(view,context,products);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        holder.title.setText(products.get(position).getPname());
        holder.description.setText(products.get(position).getPdesc());
        holder.price.setText(products.get(position).getPprice());

        Glide.with(context).load(products.get(position).getPimageurl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title,description,price;
        ImageView image;
        Context context;
        List<Products> products;

        public myViewHolder(View itemView,Context context,List<Products> products) {
            super(itemView);

            this.context = context;
            this.products= products;

            itemView.setOnClickListener(this);
            title = (TextView)itemView.findViewById(R.id.tvMain);
            description = (TextView)itemView.findViewById(R.id.tvDescription);
            price =(TextView)itemView.findViewById(R.id.tvPrice);
            image = (ImageView)itemView.findViewById(R.id.ivMain);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Products products = this.products.get(position);
            Intent intent = new Intent(this.context,ShowProduct.class);
            intent.putExtra("name",products.getPname());
            intent.putExtra("desc",products.getPdesc());
            intent.putExtra("price","Rs "+products.getPprice()+"/-");
            intent.putExtra("image",products.getPimageurl());
            this.context.startActivity(intent);

        }
    }
}
