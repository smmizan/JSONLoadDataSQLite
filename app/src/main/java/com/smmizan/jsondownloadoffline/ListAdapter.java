package com.smmizan.jsondownloadoffline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mizan on 13/11/2018.
 */

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Products_Name;
    ArrayList<String> ProductsCode;
    ArrayList<String> Products_type;

    ArrayList<String> Products_Pack;
    ArrayList<String> Products_Quantity;
    ArrayList<String> Products_Price;


    public ListAdapter(Context context2, ArrayList<String> id, ArrayList<String> Products_Name, ArrayList<String> ProductsCode,ArrayList<String> Products_type,
                       ArrayList<String> Products_Pack,ArrayList<String> Products_Quantity,ArrayList<String> Products_Price)
    {

        this.context = context2;
        this.ID = id;
        this.Products_Name = Products_Name;
        this.ProductsCode = ProductsCode;
        this.Products_type = Products_type;
        this.Products_Pack = Products_Pack;
        this.Products_Quantity = Products_Quantity;
        this.Products_Price = Products_Price;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View myView, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (myView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            myView = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.tvProductsName = (TextView) myView.findViewById(R.id.tProductsName);
            holder.tvProductsCode = (TextView) myView.findViewById(R.id.tProductsCode);
         //   holder.TextViewProductsType = (TextView) myView.findViewById(R.id.tProductsType);

            holder.TextViewProductsPack = (TextView) myView.findViewById(R.id.tProductsPack);
            holder.TextViewProductsQuantity = (TextView) myView.findViewById(R.id.tProductsQuantity);
           // holder.TextViewProductsPrice = (TextView) myView.findViewById(R.id.tProductsPrice);

            myView.setTag(holder);

        } else {

            holder = (Holder) myView.getTag();
        }
        holder.tvProductsName.setText(Products_Name.get(position));
        holder.tvProductsCode.setText(ProductsCode.get(position));
      //  holder.TextViewProductsType.setText(Products_type.get(position));

        holder.TextViewProductsPack.setText(Products_Pack.get(position));
        holder.TextViewProductsQuantity.setText(Products_Quantity.get(position));
      //  holder.TextViewProductsPrice.setText(Products_Price.get(position));

        return myView;
    }

    public class Holder {

        TextView tvProductsName;
        TextView tvProductsCode;
        TextView TextViewProductsType;
        TextView TextViewProductsPack;
        TextView TextViewProductsQuantity;
        TextView TextViewProductsPrice;
    }
}
