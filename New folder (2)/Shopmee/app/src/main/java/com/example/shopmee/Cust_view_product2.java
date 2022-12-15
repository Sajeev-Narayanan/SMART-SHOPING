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

import com.squareup.picasso.Picasso;

public class Cust_view_product2  extends BaseAdapter {

    private final Context context;
    String[] product_name, price, quantity,description,image,product_id,category_name,seller_id;

    public Cust_view_product2(Context applicationContext, String[] product_name, String[] price, String[] quantity, String[] image, String[] description, String[] product_id, String[] category_name,String [] seller_id) {

        this.context=applicationContext;
        this.product_name=product_name;
        this.price=price;
        this.quantity=quantity;
        this.image=image;
        this.description=description;
        this.product_id=product_id;
        this.category_name=category_name;
        this.seller_id=seller_id;




    }

    @Override
    public int getCount() {
        return product_name.length;
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


        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.cust_product2,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView3);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView4);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView4);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView5);
        TextView tv4=(TextView) gridView.findViewById(R.id.textView6);
        TextView tv5=(TextView) gridView.findViewById(R.id.textView78);



        Button b1=(Button)gridView.findViewById(R.id.button15);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = (int) view.getTag();

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("product_id",product_id[i]);
                ed.putString("product_name",product_name[i]);
                ed.putString("price",price[i]);
                ed.putString("image",image[i]);
                ed.putString("selerid",seller_id[i]);
                ed.commit();

                Intent ij=new Intent(context,addtocart.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ij);

            }
        });


        Button b17=(Button)gridView.findViewById(R.id.button18);
        b17.setTag(i);
        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int i = (int) view.getTag();


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("product_id",product_id[i]);
                ed.putString("product_name",product_name[i]);
                ed.putString("price",price[i]);
                ed.putString("image",image[i]);

                ed.putString("selerid",seller_id[i]);
                ed.commit();

                Intent ij=new Intent(context,addtobuy.class);
                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ij);

            }
        });






        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);





        tv1.setText(product_name[i]);
        tv2.setText(price[i]);
        tv3.setText(quantity[i]);
        tv4.setText(description[i]);
        tv5.setText(category_name[i]);





        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5005/"+image[i];


        Picasso.with(context).load(url). into(im);

        return gridView;



    }
}
