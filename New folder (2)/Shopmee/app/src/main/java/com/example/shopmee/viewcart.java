package com.example.shopmee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class viewcart extends AppCompatActivity implements View.OnClickListener {
    ListView viewcart;
    String[] cart_id,product_name,quantity,image,price,prdtid;
    Button buy,bbb;
    String url="",url1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcart);
        viewcart=(ListView)findViewById(R.id.listviewcart);
        buy=(Button)findViewById(R.id.buy) ;
        bbb=(Button)findViewById(R.id.amount) ;



        final SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        url = "http://" + hu + ":5005/viewcart";

        buy.setOnClickListener(this);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response+
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                String b= jsonObj.getString("tot");
                                bbb.setText("Total amount :"+b);

                                SharedPreferences.Editor ed=sh.edit();
                                ed.putString("tot",jsonObj.getString("tot"));
                                ed.commit();

                                JSONArray js= jsonObj.getJSONArray("data");

                                cart_id=new String[js.length()];
                                product_name=new String[js.length()];
                                quantity=new String[js.length()];
                                image=new String[js.length()];
                                price=new String[js.length()];
                                prdtid=new String[js.length()];



                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    cart_id[i]=u.getString("cart_id");
                                    product_name[i]=u.getString("product_name");
                                    quantity[i]=u.getString("qqty");
                                    image[i]=u.getString("image");
                                    price[i]=u.getString("price");
                                    prdtid[i]=u.getString("product_id");




                                }

                                viewcart.setAdapter(new Custome_viewcart(getApplicationContext(),cart_id,product_name,quantity,image,price));
                                // l1.setAdapter(new Custom(getApplicationContext(),gamecode,name,type,discription,image,status));
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

                params.put("sellerid",sh.getString("sel_sellerid",""));
                params.put("lid",sh.getString("lid",""));

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

    @Override
    public void onClick(View view) {


//
        startActivity(new Intent(com.example.shopmee.viewcart.this,payment.class));
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url1,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                        // response+
//                        try {
//                            JSONObject jsonObj = new JSONObject(response);
//                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                Toast.makeText(viewcart.this, "Success.....", Toast.LENGTH_SHORT).show();
//
//
//                            }
//
//                            else {
//                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                            }
//
//                        }    catch (Exception e) {
//                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                Map<String, String> params = new HashMap<String, String>();
//
////                params.put("username",username);
//                params.put("lid",sh.getString("lid",""));
//
//                return params;
//            }
//        };
//
//        int MY_SOCKET_TIMEOUT_MS=100000;
//
//        postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(postRequest);

    }
}
