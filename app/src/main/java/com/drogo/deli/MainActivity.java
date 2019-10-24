package com.drogo.deli;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String myUrl = "Enter main url here";
    private String HI = myUrl + "fetchCommodities.php";
    private List<List_Data> list_data;
    private HomeAdapter adapter;
    private RecyclerView rv;
    private GridLayoutManager gm;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout mrl = findViewById(R.id.homerl);
        rv=(RecyclerView)findViewById(R.id.homeRecycler);
        gm=new GridLayoutManager(this,2);
        rv.setLayoutManager(gm);
        list_data=new ArrayList<>();

        if (isConnected()) {
            getImageData();
        }else {
            Snackbar snackbar = Snackbar
                    .make(mrl, "Check your Internet Connection", Snackbar.LENGTH_INDEFINITE).setActionTextColor(Color.YELLOW).setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(getIntent());
                            finish();
                            overridePendingTransition(0, 0);
                        }
                    });
            snackbar.show();
        }


    }
    private void getImageData() {
        request=new JsonArrayRequest(HI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i=0; i<response.length(); i++){
                    try {
                        jsonObject=response.getJSONObject(i);
                        String prdName = jsonObject.getString("item_name");
                        String mskname = jsonObject.getString("masking_name");
                        String imgUrl = myUrl+jsonObject.getString("imgUrl");
                        String prdDesc = jsonObject.getString("description");
                        String prdPrice = jsonObject.getString("price");
                        List_Data listData=new List_Data(imgUrl,prdName,mskname,prdDesc,prdPrice);
                        list_data.add(listData);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setupData(list_data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void setupData(List<List_Data> list_data) {
        adapter=new HomeAdapter(list_data,this);
        rv.setAdapter(adapter);

    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
}
