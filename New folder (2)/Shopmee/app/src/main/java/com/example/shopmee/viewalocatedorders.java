package com.example.shopmee;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class viewalocatedorders extends AppCompatActivity {
    ListView order;
SharedPreferences sh;
String url;
String []order_id,ph_no,customer_name,address,latitude,longitude,image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewalocatedorders);
        order=(ListView)findViewById(R.id.listalocated);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        url = "http://" + hu + ":5005/dbvao";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response+
                        try {
                            JSONObject jsonObj = new JSONObject(response);
//                            Toast.makeText(viewalocatedorders.this, "__"+response, Toast.LENGTH_SHORT).show();
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                JSONArray js= jsonObj.getJSONArray("data");


                                order_id=new String[js.length()];
                                customer_name=new String[js.length()];
                                ph_no=new String[js.length()];
                                address=new String[js.length()];
                                latitude=new String[js.length()];
                                longitude=new String[js.length()];
                                image=new String[js.length()];



                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    order_id[i]=u.getString("order_id");
                                    customer_name[i]=u.getString("customer_name");
                                    ph_no[i]=u.getString("ph_no");
                                    latitude[i]=u.getString("latitude");
                                    longitude[i]=u.getString("longitude");
                                    address[i]=u.getString("house_name")+"\n"+u.getString("city")+"\n"+u.getString("district")+"\n"+u.getString("state")+"\n";
                                    image[i]=u.getString("image");





                                }

                                order.setAdapter(new Custome_viewalocatedorders(getApplicationContext(),order_id,customer_name,ph_no,latitude,longitude,address,image));
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
}
