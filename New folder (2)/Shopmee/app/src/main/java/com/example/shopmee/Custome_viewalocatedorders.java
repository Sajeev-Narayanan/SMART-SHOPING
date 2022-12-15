package com.example.shopmee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Custome_viewalocatedorders extends BaseAdapter {

    String []order_id,customer_name,ph_no,address,latitude,longitude,image;
    private Context context;

    public Custome_viewalocatedorders(Context appcontext,String[]order_id,String[]customer_name,String[]ph_no,String[]latitude,String[]longitude, String[] address,String[] image)
    {
        this.context=appcontext;
        this.order_id=order_id;
        this.ph_no=ph_no;
        this.customer_name=customer_name;
        this.latitude=latitude;
        this.longitude=longitude;
        this.address=address;
        this.image=image;




    }

    @Override
    public int getCount() {
        return customer_name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custome_viewalocatedorders, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.txt11);
        ImageView im = (ImageView) gridView.findViewById(R.id.imageView3);
        TextView tv2 = (TextView) gridView.findViewById(R.id.txt12);
        TextView tv3 = (TextView) gridView.findViewById(R.id.txt13);
        Button b1 = (Button) gridView.findViewById(R.id.button9);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("orderid", order_id[pos]);
                ed.commit();

                Intent ij = new Intent(context, update_status.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ij);

            }
        });

        Button b_locate = (Button) gridView.findViewById(R.id.button11);
        b_locate.setTag(i);

        b_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                Uri gmmIntentUri = Uri.parse("geo:" + latitude[pos] + "," + longitude[pos]);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mapIntent);

            }
        });

        Button b2 = (Button) gridView.findViewById(R.id.cart);
        b2.setTag(i);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("order_id", order_id[pos]);
                ed.commit();

                Intent ij = new Intent(context, view_allocated_products.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ij);

            }
        });



        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText("NAME       : "+customer_name[i]);
        tv2.setText("PHONE      : "+ph_no[i]);
        tv3.setText("ADDRESS   : "+address[i]);




        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");

        String url = "http://" + ip + ":5005/" + image[i];
//        Toast.makeText(context, "pp="+url, Toast.LENGTH_SHORT).show();


        Picasso.with(context).load(url).into(im);

        return gridView;
    }
}
