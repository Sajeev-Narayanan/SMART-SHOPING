package com.example.shopmee;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText eduname,edmail,edphone,edcity,eddistrict,edstate,edpost,edpin,edpswd;
    Button btsubmit;
    ImageView image;
    Bitmap bitmap=null;
    ProgressDialog pd=null;
    String name,mail,phone,district,place,post,pin,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
      eduname=(EditText)findViewById(R.id.editText3);

      edmail=(EditText)findViewById(R.id.editText11);
      edphone=(EditText)findViewById(R.id.editText10);

      edcity=(EditText)findViewById(R.id.editText7);
      eddistrict=(EditText)findViewById(R.id.editText8);
      edstate=(EditText)findViewById(R.id.editText12);
      edpost=(EditText)findViewById(R.id.editText13);
      edpin=(EditText)findViewById(R.id.editText9);
      edpswd=(EditText)findViewById(R.id.editText25);

      btsubmit=(Button)findViewById(R.id.button6);
      btsubmit.setOnClickListener(this);
      image=(ImageView)findViewById(R.id.imageView);
      image.setOnClickListener(this);
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String hu = sh.getString("ip", "");
//        String url = "http://" + hu + ":5005/amd_registration";

    }

    @Override
    public void onClick(View view) {
        if (view == image) {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, 100);
        }
        if(view==btsubmit){
            int f=0;
            name = eduname.getText().toString();
            mail = edmail.getText().toString();
            phone = edphone.getText().toString();

            district = eddistrict.getText().toString();
            place = edstate.getText().toString();
            post = edpost.getText().toString();
            pin = edpin.getText().toString();

            password = edpswd.getText().toString();



//            final String statee = state.getText().toString();
//            final String statee = state.getText().toString();
//            final String statee = state.getText().toString();

            if (name.length() == 0) {
                f=1;
                eduname.setError("Missing");

            }  if (mail.length() == 0) {
                f=1;
                edmail.setError("Missing");
            }  if (phone.length() == 0) {
                f=1;
                edphone.setError("Missing");

            }  if (district.length() == 0) {
                f=1;
                eddistrict.setError("Missing");
            }
            if (place.length() == 0) {
                f=1;
                edstate.setError("Missing");
            }
            if (post.length() == 0) {
                f=1;
                edpost.setError("Missing");
            }
            if (pin.length() == 0) {
                f=1;
                edpin.setError("Missing");
            }  if (password.length() == 0) {
                f=1;
                edpswd.setError("Missing");
            }
            if (f==0)

                uploadBitmap(bitmap);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {

            Uri imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                image.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    private void uploadBitmap(final Bitmap bitmap) {

        pd=new ProgressDialog(Registration.this);
        pd.setMessage("Uploading....");
        pd.show();
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5005/and_registration";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            pd.dismiss();
                            JSONObject obj = new JSONObject(new String(response.data));
                            String result=obj.getString("status");
                            if (result.equals("ok")){
                                Toast.makeText(Registration.this, "successfully registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration.this,login.class));
                            }
                            else{
                                Toast.makeText(Registration.this, "something went wrong", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", name);

                params.put("email", mail);
                params.put("phone", phone);

                params.put("place", place);
                params.put("district", district);
                params.put("post", post);
                params.put("pin", pin);
                params.put("password", password);




                return params;
            }


            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }
}
