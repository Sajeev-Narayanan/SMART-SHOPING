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

import com.squareup.picasso.Picasso;

public class custom_view_allocatedproducts extends BaseAdapter {

    String[] name, image, quantity,price;

    private Context context;

    public custom_view_allocatedproducts(Context appcontext, String[]name, String[]image,String[] quantity,String[] price)
    {
        this.context=appcontext;
        this.name=name;
        this.image=image;
        this.quantity=quantity;
        this.price=price;




    }

    @Override
    public int getCount() {
        return name.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_allocatedproducts,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView83);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView10);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView86);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView88);

//        TextView tv3=(TextView)gridView.findViewById(R.id.txt12);
//        Button b1=(Button)gridView.findViewById(R.id.button9);
//        b1.setTag(i);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos=(int)view.getTag();
//
//                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor ed=sh.edit();
//                ed.putString("sel_sellerid",login_id[pos]);
//                ed.commit();
//
//                Intent ij=new Intent(context,viewproduct.class);
//                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(ij);
//
//            }
//        });

//        Button b_locate=(Button)gridView.findViewById(R.id.button11);
//        b_locate.setTag(i);
//
//        b_locate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos=(int)view.getTag();
//                Uri gmmIntentUri = Uri.parse("geo:"+latitude[pos]+","+longitude[pos]);
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(mapIntent);
//
//            }
//        });
//
//        Button cart=(Button)gridView.findViewById(R.id.cart);
//        cart.setTag(i);
//        cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int pos=(int)view.getTag();
//
//                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor ed=sh.edit();
//                ed.putString("sel_sellerid",login_id[pos]);
//                ed.commit();
//
//                Intent ij=new Intent(context,viewcart.class);
//                ij.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(ij);
//
//            }
//        });

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);






        tv1.setText(name[i]);
        tv2.setText(quantity[i]);
        tv3.setText(price[i]);






        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5005/"+image[i];


        Picasso.with(context).load(url). into(im);

        return gridView;
    }
}
