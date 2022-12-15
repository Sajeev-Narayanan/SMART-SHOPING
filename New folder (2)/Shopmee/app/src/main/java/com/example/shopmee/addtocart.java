package com.example.shopmee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addtocart extends AppCompatActivity implements View.OnClickListener {
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


        final SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String ip=sh.getString("ip","");
        String myflag=sh.getString("myflag","");
        Toast.makeText(getApplicationContext(), "-------"+myflag, Toast.LENGTH_LONG).show();

        if(myflag.equalsIgnoreCase("1")){

            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5005/and_product_details_f";

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                    String image = jsonObj.getString("image");
                                    String url = "http://" + ip + ":5005/" + image;
                                    Picasso.with(getApplicationContext()).load(url).into(img);

                                    String price = jsonObj.getString("price");
                                    String product = jsonObj.getString("name");

                                    lvprice.setTextColor(Color.BLACK);
                                    lvproduct.setTextColor(Color.BLACK);

                                    lvprice.setText(price);
                                    lvproduct.setText(product);



                                }



                                // }
                                else {
                                    Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                }

                            }    catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("product_id",sh.getString("product_id",""));



                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS=100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);








        }

        else if (myflag.equalsIgnoreCase("0")){

            String image = sh.getString("image", "");
            String url = "http://" + ip + ":5005/" + image;
            Picasso.with(getApplicationContext()).load(url).into(img);

            String price = sh.getString("price", "");
            String product = sh.getString("product_name", "");

            lvprice.setTextColor(Color.BLACK);
            lvproduct.setTextColor(Color.BLACK);

            lvprice.setText(price);
            lvproduct.setText(product);
        }

    }

    @Override
    public void onClick(View view) {
        final String quantity=edquantity.getText().toString();

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5005/addtocart";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();



                                Intent ij=new Intent(getApplicationContext(),viewnearbysellers.class);
                                    startActivity(ij);

                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Failed to add", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("customer_l_id",sh.getString("lid",""));
                params.put("product_id",sh.getString("product_id",""));
                params.put("quantity",quantity);
                params.put("sellerlid",sh.getString("sel_sellerid",""));


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }
}
