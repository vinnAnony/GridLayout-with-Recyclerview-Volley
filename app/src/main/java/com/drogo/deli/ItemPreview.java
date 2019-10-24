package com.drogo.deli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemPreview extends AppCompatActivity {
    private ImageView img;
    TextView pname,mask,price,descr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_preview);

        img = findViewById(R.id.selectedImg);
        pname = findViewById(R.id.selectedName);
        mask = findViewById(R.id.selectedMaskName);
        price = findViewById(R.id.selectedPrice);
        descr = findViewById(R.id.selectedDesc);

        Intent i=getIntent();
        String prdMask=i.getStringExtra("prdMask");
        String prdName=i.getStringExtra("prdName");
        String prdPrice="Ksh."+i.getStringExtra("prdPrice");
        String prdDesc=i.getStringExtra("prdDesc");
        String imageurl=i.getStringExtra("imageurl");
        Picasso.with(this)
                .load(imageurl)
                .into(img);
        pname.setText(prdName);
        mask.setText(prdMask);
        price.setText(prdPrice);
        descr.setText(prdDesc);

    }
}
