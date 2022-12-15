package com.example.shopmee;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class customer_viewprofile extends AppCompatActivity {
    TextView edname,edmail,edphone,eddistrict,edstate,edpost,edpin;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_viewprofile);
        edname=(TextView)findViewById(R.id.textView20);
        edmail=(TextView)findViewById(R.id.textView21);

        edphone=(TextView)findViewById(R.id.textView30);

        eddistrict=(TextView)findViewById(R.id.tvdist);
        edstate=(TextView)findViewById(R.id.textView26);
        edpost=(TextView)findViewById(R.id.textView28);
        edpin=(TextView)findViewById(R.id.textView29);
        image=(ImageView)findViewById(R.id.imageView9);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5005/customer_viewprofile";

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

                                edname.setText(jsonObj.getString("name"));
                                edmail.setText(jsonObj.getString("email_id"));

                                edphone.setText(jsonObj.getString("ph_no"));

                                Log.d("hellooooooo",jsonObj.getString("district"));
                                eddistrict.setText(jsonObj.getString("district"));
                                edstate.setText(jsonObj.getString("state"));
                                edpost.setText(jsonObj.getString("post"));
                                edpin.setText(jsonObj.getString("pincode"));




                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String hu = sh.getString("ip", "");
                                String url = "http://" + hu + ":5005"+jsonObj.getString("image");
                                Picasso.with(getApplicationContext()).load(url).into(image);



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
