package com.example.shopmee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

public class Custome_viewpreviousorders extends BaseAdapter {

    String[] seller_name, product_name, quantiy, date, image,prdd;

    private Context context;

    public Custome_viewpreviousorders (Context appcontext,String[]seller_name,String[]product_name,String[]quantiy,String[]date,String[]image,String[]prdd)
    {
        this.context=appcontext;
        this.seller_name=seller_name;
        this.product_name=product_name;
        this.quantiy=quantiy;
        this.date=date;
        this.image=image;
        this.prdd=prdd;




    }

    @Override
    public int getCount() {
        return seller_name.length;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.costume_viewpreviousorders, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView10);
        ImageView im = (ImageView) gridView.findViewById(R.id.imageView5);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView11);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView12);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView14);
        Button b1 = (Button) gridView.findViewById(R.id.button);
        b1.setTag(prdd);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("prdd",prdd[i]);
                ed.commit();


                Intent ii=new Intent(context.getApplicationContext(),product_review_gives.class);
                ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ii);

            }
        });
//





        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);






        tv1.setText(seller_name[i]);
        tv2.setText(product_name[i]);
        tv3.setText(date[i]);
        tv4.setText(quantiy[i]);







        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5005/"+image[i];
//        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();


        Picasso.with(context).load(url). into(im);

        return gridView;
    }
}
