package com.example.shopmee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addtobuy extends AppCompatActivity implements View.OnClickListener {
    EditText edquantity;
    ImageView img;
    TextView lvproduct,lvprice;
    Button btaddtocart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocart);
        edquantity=(EditText)findViewById(R.id.editText4);
        img=(ImageView)findViewById(R.id.imageView2);
        lvproduct=(TextView) findViewById(R.id.textView);
        lvprice=(TextView) findViewById(R.id.textView2);
        btaddtocart=(Button)findViewById(R.id.button2);
        btaddtocart.setOnClickListener(this);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip","");
        String image=sh.getString("image","");
        String url="http://" + ip + ":5005/"+image;
        Picasso.with(getApplicationContext()).load(url). into(img);

        String price=sh.getString("price","");
        String product=sh.getString("product_name","");

        lvprice.setTextColor(Color.BLACK);
        lvproduct.setTextColor(Color.BLACK);

        lvprice.setText(price);
        lvproduct.setText(product);


    }

    @Override
    public void onClick(View view) {
        final String quantity=edquantity.getText().toString();


        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed=sh.edit();
        ed.putString("qty",quantity);
        ed.commit();


        Intent ins= new Intent(getApplicationContext(),paymentbuy.class);
        startActivity(ins);









//        params.put("customer_l_id",sh.getString("lid",""));
//                params.put("product_id",sh.getString("product_id",""));
//                params.put("quantity",quantity);
//                params.put("sellerlid",sh.getString("sel_sellerid",""));

    }
}
