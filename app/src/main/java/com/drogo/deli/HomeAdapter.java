package com.drogo.deli;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
        List<List_Data> list_data;
        Context ct;

    public HomeAdapter(List<List_Data> list_data, Context ct) {
            this.list_data = list_data;
            this.ct = ct;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final List_Data listData=list_data.get(position);
            Picasso.with(ct)
                    .load(listData.getImageurl())
                    .into(holder.img);
            holder.meCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(ct, listData.getProductName(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ct,ItemPreview.class);
                    intent.putExtra("prdMask",listData.getMaskName());
                    intent.putExtra("prdName",listData.getProductName());
                    intent.putExtra("prdPrice",listData.getProductPrice());
                    intent.putExtra("prdDesc",listData.getDescription());
                    intent.putExtra("imageurl",listData.getImageurl());
                    ct.startActivity(intent);
                }
            });
            holder.name.setText(listData.getProductName());
            holder.maskName.setText(listData.getMaskName());
            holder.desc.setText(listData.getDescription());
            holder.price.setText(listData.getProductPrice());

        }

        @Override
        public int getItemCount() {
            return list_data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView img;
            private TextView name,maskName,desc,price;
            private CardView meCard;
            public ViewHolder(View itemView) {
                super(itemView);
                img= itemView.findViewById(R.id.hItemImage);
                name = itemView.findViewById(R.id.hItemName);
                maskName = itemView.findViewById(R.id.hItemMask);
                desc = itemView.findViewById(R.id.hItemDesc);
                price = itemView.findViewById(R.id.hItemPrice);
                meCard = itemView.findViewById(R.id.myHomeCard);
            }
        }
}
