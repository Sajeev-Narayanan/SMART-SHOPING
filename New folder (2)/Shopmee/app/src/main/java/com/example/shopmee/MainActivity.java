package com.example.shopmee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ip;
    Button btsubmit;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip=(EditText)findViewById(R.id.editText21);
        btsubmit=(Button)findViewById(R.id.button8);
        btsubmit.setOnClickListener(this);
         sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         ip.setText(sh.getString("ip",""));

    }

    @Override
    public void onClick(View view) {
        String ipadress=ip.getText().toString();

        SharedPreferences.Editor ed=sh.edit();
        ed.putString("ip",ipadress);
        ed.commit();

        Intent ij=new Intent(getApplicationContext(),login.class);
        startActivity(ij);
    }
}
